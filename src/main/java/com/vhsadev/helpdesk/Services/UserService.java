package com.vhsadev.helpdesk.Services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vhsadev.helpdesk.Models.Role;
import com.vhsadev.helpdesk.Models.User;
import com.vhsadev.helpdesk.Repositories.RolesRepository;
import com.vhsadev.helpdesk.Repositories.UserRepository;
import com.vhsadev.helpdesk.Services.Interfaces.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private RolesRepository roleRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User create(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByName("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		userRepository.save(user);
		
		return user;
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
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
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
