package com.vhsadev.helpdesk.Controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		model.addAttribute("roles", roleService.findAll());
		return "roles/index";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("role", new Role());
		return "roles/create";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "roles/create";
		}
		
		Role roleCreated = this.roleService.create(role);
		
		return "redirect:/roles";
	}


	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id, Model model) {
		
		try {
			roleService.delete(id);
		} catch (Exception e) {
			//TODO - Informar se não deletar..
		}
		
		return "redirect:/roles";
	}
}
