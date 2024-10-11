package com.social.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.User;
import com.social.models.UserPost;
import com.social.repository.PostRepository;
import com.social.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@Override
	public UserPost createNewPost(UserPost post, Integer userId) throws Exception {
		
		User user=userService.findUserById(userId);
		
		UserPost newPost = new UserPost();
		newPost.setCaption(post.getCaption());
		newPost.setImage(post.getImage());
		newPost.setVideo(post.getVideo());
		newPost.setId(post.getId());
		newPost.setUser(user);
		newPost.setCreatedAt(LocalDateTime.now());
		
		return postRepository.save(newPost);
	}

	@Override
	public String deletePost(Integer postId, Integer userId) throws Exception {
		// TODO Auto-generated method stub
		UserPost post=findPostById(postId);
		User user=userService.findUserById(userId);
		if(post.getUser().getId()!=user.getId()) {
			throw new Exception("post is not of that user");
		}
		postRepository.deleteById(postId);
		return "post deleted successfully";
	}

	@Override
	public List<UserPost> findPostByUserId(Integer userId) {
		
		return postRepository.findPostByUserId(userId);
	}

	@Override
	public UserPost findPostById(Integer postId) throws Exception {
		
		Optional<UserPost> post=postRepository.findById(postId);
		if(post.isEmpty()) {
			throw new Exception("post not found");
		}
		return post.get();
	}

	@Override
	public List<UserPost> findAllPost() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public UserPost savedPost(Integer postId, Integer userId) throws Exception {
		UserPost post=findPostById(postId);
		User user=userService.findUserById(userId);
		if(user.getSavedPost().contains(post)) {
			user.getSavedPost().remove(post);
		}
		else {
			user.getSavedPost().add(post);
		}
		userRepository.save(user);
		return post;
	}

	@Override
	public UserPost likePost(Integer postId, Integer userId) throws Exception {

		UserPost post=findPostById(postId);
		User user=userService.findUserById(userId);
		if(post.getLiked().contains(user)) {
			post.getLiked().remove(user);
		}
		else {
			post.getLiked().add(user);
		}
		
		return postRepository.save(post);
	}

}
