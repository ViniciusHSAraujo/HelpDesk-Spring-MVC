package com.vhsadev.helpdesk.Services.Interfaces;

import java.util.List;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Models.User;

import javassist.NotFoundException;

public interface IUserService {
	
	public List<User> findAll();
	
	public User create(User user);
	
	public void delete(Long id) throws Exception;
	
	public User update(Long id, User user);

}
