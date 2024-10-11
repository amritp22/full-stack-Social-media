package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Chat;
import com.social.models.User;
import com.social.request.CreateChatRequest;
import com.social.service.ChatService;
import com.social.service.UserService;

@RestController
public class ChatController {

	@Autowired
	private UserService userService;
	@Autowired
	private ChatService chatService;
	
	@PostMapping("/api/chat")
	public Chat createChat(@RequestHeader("Authorization") String jwt,@RequestBody CreateChatRequest user2) throws Exception {
		User foundUser =userService.findUserByJwt(jwt);
		User userF=userService.findUserById(user2.getUserId());
		Chat created=chatService.createChat(foundUser, userF);
		return created;
	}
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		User foundUser =userService.findUserByJwt(jwt);
		List<Chat> chatsList=chatService.findUserChat(foundUser.getId());
		return chatsList;
	}
}
