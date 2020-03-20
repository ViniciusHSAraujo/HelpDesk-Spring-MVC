package com.vhsadev.helpdesk.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vhsadev.helpdesk.Models.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@GetMapping("/")
	public String index(Model model) {
		return "users/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("user", new User());
		return "users/create";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("user", new User());
		return "users/edit";
	}

}
