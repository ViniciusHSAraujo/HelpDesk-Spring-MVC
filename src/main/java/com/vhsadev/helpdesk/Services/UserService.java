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
		User user = findById(id);
		userRepository.delete(user);
	}

	private User findById(Long id) throws NotFoundException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new NotFoundException("Not found a user with this id.");
		}
		return user.get();
	}

	@Override
	public User update(Long id, User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
