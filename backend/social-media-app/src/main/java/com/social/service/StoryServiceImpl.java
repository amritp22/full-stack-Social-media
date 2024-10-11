package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Story;
import com.social.models.User;
import com.social.repository.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService {

	@Autowired
	private StoryRepository storyRepository;
	@Autowired
	private UserService userService;
	@Override
	public Story createStory(Story story, User user) {
		Story createStory=new Story();
		createStory.setImage(story.getImage());
		createStory.setCaption(story.getCaption());
		createStory.setUser(user);
		return storyRepository.save(createStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user=userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}

	
}
