package com.vhsadev.helpdesk.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vhsadev.helpdesk.Models.User;
import com.vhsadev.helpdesk.Services.Interfaces.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("")
	public String index(Model model) {
		model.addAttribute("users", userService.findAll());
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
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "users/create";
		}
		
		User userCreated = this.userService.create(user);
		
		return "redirect:/users";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		
		try {
			userService.delete(id);
		} catch (Exception e) {
			//TODO - Informar se não deletar..
		}
		
		return "redirect:/users";
	}

}
