package com.social.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.config.JwtProvider;
import com.social.exceptions.UserException;
import com.social.models.User;
import com.social.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User registerUser(User newUser) {
		// TODO Auto-generated method stub
		List<User> userList=new ArrayList<>();
		User u1=new User();
		u1.setId(newUser.getId());
		u1.setFirstName(newUser.getFirstName());
		u1.setLastName(newUser.getLastName());
		u1.setEmail(newUser.getEmail());
		u1.setPassword(newUser.getPassword());
		
		User savedUser= userRepository.save(u1);
		return savedUser;
	}



	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> u1=userRepository.findById(userId);
		if(u1.isPresent()) {
			return u1.get();
		}
		throw new UserException("user not present with id"+userId);
	}

	@Override
	public User findUseryEmail(String email) {
		User foundUser=userRepository.findByEmail(email);
		return foundUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User followUser(Integer loginUserId, Integer userId2) throws UserException {
		User user1=findUserById(loginUserId);
		User user2=findUserById(userId2);
		
		user2.getFollower().add(loginUserId);
		user1.getFollowing().add(userId2);
		userRepository.save(user1);
		userRepository.save(user2);
		
		return user1;
				
	}


    @Override
	public User updateUser(User newUser, Integer userId) throws UserException {
		Optional<User> u1=userRepository.findById(userId);
		if(u1.isEmpty()) {
		 throw new UserException("user not present with id"+userId);
		}
		
		User u2=u1.get();
		if(newUser.getFirstName()!=null) {
			u2.setFirstName(newUser.getFirstName());
		}
		if(newUser.getLastName()!=null) {
		u2.setLastName(newUser.getLastName());
		}
		if (newUser.getEmail()!=null) {
			u2.setEmail(newUser.getEmail());
		}
		User updatedUser=userRepository.save(u2);
		return updatedUser;
	}



	@Override
	public User findUserByJwt(String jwt) {
		String email=JwtProvider.generateEmailFromJwtToken(jwt);
		User user=findUseryEmail(email);
		return user;
	}

}
