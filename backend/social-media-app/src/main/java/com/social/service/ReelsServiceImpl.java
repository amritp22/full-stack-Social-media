package com.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Reels;
import com.social.models.User;
import com.social.repository.ReelsRepository;
@Service
public class ReelsServiceImpl implements ReelsService {
	
	@Autowired
	private ReelsRepository reelsRepository;
	@Autowired
	private UserService userService;

	@Override
	public Reels createReel(Reels reels, User user) {
		Reels creatReel=new Reels();
		creatReel.setTitle(reels.getTitle());
		creatReel.setVideo(reels.getVideo());
		creatReel.setUser(user);
		
		return reelsRepository.save(creatReel);
	}

	@Override
	public List<Reels> findAllReels() {
		// TODO Auto-generated method stub
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReels(Integer userId) throws Exception {
		
		userService.findUserById(userId);
		return reelsRepository.findByUserId(userId);
	}

}
