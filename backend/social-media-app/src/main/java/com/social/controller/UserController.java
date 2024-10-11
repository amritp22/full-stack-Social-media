package com.social.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.social.exceptions.UserException;
import com.social.models.User;
import com.social.repository.UserRepository;
import com.social.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;
//moved to authControler	
//	@PostMapping("/user")
//	public User createUser(@RequestBody User newUser){
//		User savedUser=userService.registerUser(newUser);
//		return savedUser;
//	}
	@GetMapping("/api/users")
	public List<User> getAllUsers(){
		List<User> userList=userRepository.findAll();
		return userList;
	}
	
	@GetMapping("/api/user/{userId}")
	public User getUserById(@PathVariable("userId") Integer id) throws UserException{
		User foundUser=userService.findUserById(id);
		return foundUser;
	}
	
	
	@PutMapping("/api/user")
	public User updateUser(@RequestBody User newUser,@RequestHeader("Authorization")String jwt) throws UserException{
		//user will be able to update its own data using token
		User loginUser=userService.findUserByJwt(jwt);
		User updatedUser=userService.updateUser(newUser, loginUser.getId());
		return updatedUser;
	}
	@PutMapping("/api/user/follow/{userId2}")
	public User followUserHandler(@PathVariable Integer userId2,@RequestHeader("Authorization")String jwt) throws UserException {
		User loginUser=userService.findUserByJwt(jwt);
		User fuser=userService.followUser(loginUser.getId(), userId2);
		return fuser;
	}
	
	@GetMapping("/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users=userService.searchUser(query);
		return users;
	}
	@GetMapping("/api/users/profile")
	public User findUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user=userService.findUserByJwt(jwt);
		//pswd should not be visible in frontend
		user.setPassword(null);
		return user;
	}
}
