package recipes.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import recipes.app.model.User;
import recipes.app.repository.IUserRepository;
import recipes.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserRepository userRepository;
	
	public User retrieveUser(String name) {
		
		return userRepository.findByName(name);
	}
}
