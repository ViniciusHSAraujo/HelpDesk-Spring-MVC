package com.vhsadev.helpdesk.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Models.User;
import com.vhsadev.helpdesk.Repositories.RolesRepository;
import com.vhsadev.helpdesk.Repositories.UserRepository;
import com.vhsadev.helpdesk.Services.Interfaces.IRoleService;
import com.vhsadev.helpdesk.Services.Interfaces.IUserService;

import javassist.NotFoundException;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		User userCreated = userRepository.save(user);
		return userCreated;
	}

	@Override
	public void delete(Long id) throws Exception {
		User user = getById(id);
		userRepository.delete(user);
	}

	public User getById(Long id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			return null;
		}
		return user.get();
	}

	@Override
	public User update(Long id, User user) {
		User userExists = getById(id);
		if (userExists != null) {
			userExists.setId(user.getId());
			userExists.setName(user.getName().equals("") ? userExists.getName() : user.getName());
			userExists.setLastName(user.getLastName().equals("") ? userExists.getLastName() : user.getLastName());
			userExists.setEmail(user.getEmail().equals("") ? userExists.getEmail() : user.getEmail());
			userExists.setPassword(user.getPassword().equals("") ? userExists.getPassword() : user.getPassword());
			userExists.setIsActive(user.getIsActive());
			
			userRepository.save(userExists);
		}
		return userExists;
	}
}
