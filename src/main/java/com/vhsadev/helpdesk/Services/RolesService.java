package com.vhsadev.helpdesk.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Repositories.RolesRepository;
import com.vhsadev.helpdesk.Services.Interfaces.IRoleService;

@Service
public class RolesService implements IRoleService{

	@Autowired
	private RolesRepository roleRepository;
	
	@Override
	public List<Role> findAll() {
		return null;
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		Role roleCreated = roleRepository.save(role);
		return roleCreated;
	}
}
