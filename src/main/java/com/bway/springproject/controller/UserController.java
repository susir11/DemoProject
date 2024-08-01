package com.bway.springproject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springproject.model.User;
import com.bway.springproject.service.UserService;
import com.bway.springproject.utils.VerifyRecaptcha;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping({"/", "/login"})
	public String getLogin() {
		return "LoginForm";
	}
	
	@PostMapping("/login")
	public String postLogin(@ModelAttribute User user, Model model, HttpSession session, @RequestParam ("g-recaptcha-response") String gcode) throws IOException {
		
		if (VerifyRecaptcha.verify(gcode)) {
			
		
		User usr = userService.userLogin(user.getEmail(), user.getPassword());
				if (usr != null) {
					session.setAttribute("validuser", usr);
					session.setMaxInactiveInterval(400);
				
//					model.addAttribute("uname", usr.getFname());
					return "Home";	
				}
				
				}
				
				
		model.addAttribute("message", "you are robot !!");
		return "LoginForm";
	}
	
	
	
	@GetMapping("/signup")
	public String getSignup() {
		return "SignupForm";
	}
	
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute User user) {
		userService.userSignup(user);
		return "LoginForm";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "LoginForm";
	}
	
	
	@GetMapping("/profile")
	public String getProfile() {
		return "Profile";
	}
	
}
