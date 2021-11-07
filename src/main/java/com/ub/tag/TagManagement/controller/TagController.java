package com.ub.tag.TagManagement.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ub.tag.TagManagement.Model.Tag;
import com.ub.tag.TagManagement.dao.TagRepo;
import com.ub.tag.TagManagement.service.UserServiceImpl;

@Controller
public class TagController {

	public static String uploadDirectory = "src/main/resources/static/uploads";

	@Autowired
	private TagRepo tagRepo;

	@Autowired
	private UserServiceImpl userService;

	
	private Logger logger = Logger.getLogger(getClass().getName());

	@GetMapping("/tagform")
	public String showTagForm(Model tagModel) {
		Tag theTag=new Tag();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		String name=userService.findByUserName(userName).getFirstName()+" "+userService.findByUserName(userName).getLastName();
		theTag.setCreateDate(date);
		theTag.setUserName(userName);
		theTag.setName(name);
		theTag.setDepartment(userService.findByUserName(userName).getDepartment());
		tagModel.addAttribute("tagAttr", theTag);
		return "tag-form";
	}

	/*
	 * @ResponseBody
	 * 
	 * @PostMapping("/savetag") public String
	 * processTag(@Valid @ModelAttribute("tagAttr") Tag theTag, BindingResult
	 * theBindingResult, Model model, final @RequestParam("image") MultipartFile
	 * file) { try { DateTimeFormatter dtf =
	 * DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); LocalDateTime now =
	 * LocalDateTime.now(); String date = dtf.format(now); Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication();
	 * String userName = authentication.getName(); byte[] image = file.getBytes();
	 * Tag tag=new Tag(); tag.setDepartment(theTag.getDepartment());
	 * tag.setDescription(theTag.getDescription()); tag.setCreateDate(date);
	 * tag.setImage(image); tag.setUserName(userName);
	 * System.out.println(userName+"-------------------"); tagService.saveTag(tag);
	 * return "SuccessFull";
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return "Failed"; }
	 */

	@PostMapping("/savetag")
	public String processTag(@Valid @ModelAttribute("tagAttr") Tag theTag, BindingResult theBindingResult, Model model,
			final @RequestParam("image") MultipartFile file) {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			String date = dtf.format(now);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			StringBuilder fileNames = new StringBuilder();
			String filename = userName + "_"+file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDirectory, filename);
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				System.out.println("writing file "+fileNameAndPath.toAbsolutePath()+e);
			}
			

			Tag tag = new Tag();
			String name=userService.findByUserName(userName).getFirstName()+" "+userService.findByUserName(userName).getLastName();
			tag.setName(name);
			tag.setDepartment(userService.findByUserName(userName).getDepartment());
			tag.setDescription(theTag.getDescription());
			tag.setCreateDate(date);
			tag.setImage(uploadDirectory.split("/")[4]+ "/"+ filename);
			tag.setUserName(userName);
			tag.setArea(theTag.getArea());
			tag.setStatus("OPEN");
			tag.setSubArea(theTag.getSubArea());
			System.out.println(fileNameAndPath.toString() + "-------------------");
			tagRepo.save(tag);
			return "redirect:/showtags";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "Failed";
	}

	@GetMapping("/showtags")
	public String getTags(Model model) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			List<Tag> tag = tagRepo.findByUserNameOrderByIdDesc(userName);//tagService.getAllActiveTag();
			model.addAttribute("theTag",tag);
			
			return "tag-list";
		} catch (Exception e) {

			model.addAttribute("message", "Error in getting image");
			return "redirect:/";
		}
	}
	
	@GetMapping("/showdetails")
	public String getDbDetils(@RequestParam Long id,Model model) {
		try {

			Tag tag = tagRepo.getOne(id);
			model.addAttribute("theTag",tag);
			
			return "showdetails";
		} catch (Exception e) {

			model.addAttribute("message", "Error in getting image");
			return "redirect:/";
		}
	}

}
