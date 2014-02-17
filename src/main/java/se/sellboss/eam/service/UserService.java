package se.sellboss.eam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.sellboss.eam.domain.Asset;
import se.sellboss.eam.domain.User;
import se.sellboss.eam.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}

	public User findUser(String username) {
		return userRepository.findByUsername(username);
	}

	public void saveUser(User p) {
		userRepository.save(p);
	}

}
