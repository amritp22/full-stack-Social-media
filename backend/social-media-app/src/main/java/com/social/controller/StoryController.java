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
import com.social.models.Story;
import com.social.models.User;
import com.social.service.StoryService;
import com.social.service.UserService;

@RestController
public class StoryController {

	@Autowired
	private StoryService storyService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestHeader("Authorization") String jwt,@RequestBody Story story) {
		
		User userF=userService.findUserByJwt(jwt);
		
		Story createdStory=storyService.createStory(story, userF);
				return createdStory;
	}
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@RequestHeader("Authorization") String jwt,@PathVariable Integer userId) throws Exception {
		
		User userF=userService.findUserByJwt(jwt);
		
		List<Story> Stories=storyService.findStoryByUserId(userId);
				return Stories;
	}
}
