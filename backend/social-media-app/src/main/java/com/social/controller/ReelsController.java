package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Reels;
import com.social.models.User;
import com.social.service.ReelsService;
import com.social.service.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestHeader("Authorization") String jwt,@RequestBody Reels reels) {
		
		User userF=userService.findUserByJwt(jwt);
		
		Reels createdReel=reelsService.createReel(reels, userF);
				return createdReel;
	}
	@GetMapping("/api/reels")
	public List<Reels> findAllReels(){
		List<Reels> allReels=reelsService.findAllReels();
		return allReels;
	}
	@GetMapping("/api/reels/users/{userId}")
	public List<Reels> findAllReels(@PathVariable Integer userId) throws Exception{
		User userF=userService.findUserById(userId);
		List<Reels> usersReels=reelsService.findUserReels(userId);
		return usersReels;
	}
}
