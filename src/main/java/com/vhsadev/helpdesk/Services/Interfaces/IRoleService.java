package com.vhsadev.helpdesk.Services.Interfaces;

import java.util.List;

import com.vhsadev.helpdesk.Models.Role;

import javassist.NotFoundException;

public interface IRoleService {
	
	public List<Role> findAll();
	
	public Role create(Role role);
	
	public void delete(Long id) throws Exception;

}
