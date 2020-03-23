package com.vhsadev.helpdesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Repositories.RolesRepository;
import com.vhsadev.helpdesk.Services.Interfaces.IRoleService;

import javassist.NotFoundException;

@Service
public class RolesService implements IRoleService {

	@Autowired
	private RolesRepository roleRepository;

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		Role roleCreated = roleRepository.save(role);
		return roleCreated;
	}

	@Override
	public void delete(Long id) throws Exception {
		Role role = findById(id);
		roleRepository.delete(role);
	}

	private Role findById(Long id) throws NotFoundException {
		Optional<Role> role = roleRepository.findById(id);

		if (!role.isPresent()) {
			throw new NotFoundException("Not found a role with this id.");
		}
		return role.get();
	}
}
