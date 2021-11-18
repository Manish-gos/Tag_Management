package com.ub.tag.TagManagement.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ub.tag.TagManagement.Model.Assign;
import com.ub.tag.TagManagement.Model.Tag;
import com.ub.tag.TagManagement.Model.User;
import com.ub.tag.TagManagement.dao.AssignRepo;
import com.ub.tag.TagManagement.dao.TagRepo;
import com.ub.tag.TagManagement.service.UserServiceImpl;

@Controller
@RequestMapping(path = "/systems")
public class AdminController {

	@Autowired
	private TagRepo tagRepo;
	
	@Autowired
	private AssignRepo assignRepo;

	@Autowired
	private UserServiceImpl userService;

	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@GetMapping("/")
	public String getTagsList(Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			String department = userService.findByUserName(userName).getDepartment();
			List<Tag> tag = tagRepo.findByDepartmentOrderByIdDesc(department);//tagService.getAllActiveTag();
			model.addAttribute("theTag",tag);
			
			return "admintag-list";
		} catch (Exception e) {

			model.addAttribute("message", "Error in getting image");
			return "redirect:/";
		}
	}
	
	
	@PostMapping("/assign")
	public String assign(@Valid @ModelAttribute("userObj") User user, BindingResult theBindingResult, Model model) {
		System.out.println(user.getUserName()+"--------------------"+user.getId());
		Assign asg=new Assign();
		Tag tag=tagRepo.getOne(user.getId());
		asg.setUserName(user.getUserName());
		asg.setTagId(user.getId());
		asg.setDescription(tag.getDescription());
		asg.setStatus(tag.getStatus());
		assignRepo.save(asg);
	
		return "admintag-list";
	}
}
