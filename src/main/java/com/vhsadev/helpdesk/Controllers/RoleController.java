package com.vhsadev.helpdesk.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Services.Interfaces.IRoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	
	@Autowired
	private IRoleService roleService;

	@GetMapping("")
	public String index(Model model) {
		return "roles/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";
	}

	@PostMapping
	public String save(@Valid @ModelAttribute("role") Role role, Model model, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "redirect:/roles/new";
		}
		
		Role roleCreated = roleService.create(role);
		
		return "redirect:/roles";
	}

	@DeleteMapping
	public String delete(Model model) {
		return null;
	}
}
