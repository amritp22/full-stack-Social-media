package com.social.service;

import java.util.List;

import com.social.exceptions.UserException;
import com.social.models.User;

public interface UserService {

	User registerUser(User user);
	 User updateUser(User user,Integer userId) throws UserException;
	 User findUserById(Integer userId) throws UserException;
	 User findUseryEmail(String email);
	 List<User> searchUser(String query);
	 User followUser (Integer userId1,Integer userId2) throws UserException;
	 User findUserByJwt(String jwt);
}
