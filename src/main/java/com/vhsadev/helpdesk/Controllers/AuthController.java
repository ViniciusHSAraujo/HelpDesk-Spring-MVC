package com.vhsadev.helpdesk.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vhsadev.helpdesk.Models.User;
import com.vhsadev.helpdesk.Services.Interfaces.IUserService;

@Controller
public class AuthController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "auth/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "auth/register";
	}
	
	@PostMapping("/register")
	public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "users/create";
		}
		
		User userCreated = userService.create(user);
		
		return "redirect:/users";
	}

}
