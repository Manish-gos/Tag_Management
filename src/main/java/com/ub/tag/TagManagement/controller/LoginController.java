package com.ub.tag.TagManagement.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/Login")
	public String showMyLoginPage() {
		
		return "login";
		
	}
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
	@PostMapping("/logout")
	public String showSignoutPage() {
		System.out.println("Logout success");
		return "login";
		
	}
}


