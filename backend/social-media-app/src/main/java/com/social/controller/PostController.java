package com.social.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.User;
import com.social.models.UserPost;
import com.social.service.PostService;
import com.social.service.UserService;

@RestController
public class PostController {
	
	@Autowired
	PostService postService;
	@Autowired
	UserService userService;

	@PostMapping("/api/posts")
	public ResponseEntity<UserPost> createPost(@RequestBody UserPost post,@RequestHeader("Authorization")String jwt) throws Exception{
		
		User loginUser=userService.findUserByJwt(jwt);
		UserPost createdPost=postService.createNewPost(post, loginUser.getId());
		return new ResponseEntity<>(createdPost,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/api/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
		User loginUser=userService.findUserByJwt(jwt);
		String msg=postService.deletePost(postId, loginUser.getId());
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<UserPost> findPostById(@PathVariable Integer postId) throws Exception{

		UserPost postFound=postService.findPostById(postId);
		return new ResponseEntity<UserPost>(postFound,HttpStatus.ACCEPTED);
	}
	@GetMapping("/posts/user/{userId}")
	public ResponseEntity<List<UserPost>> findUsersPosts(@PathVariable Integer userId){
		List<UserPost> posts=postService.findPostByUserId(userId);
		return new ResponseEntity<List<UserPost>>(posts,HttpStatus.ACCEPTED);
	}
	@GetMapping("/api/posts")
	public ResponseEntity<List<UserPost>> findAllPosts(){
		List<UserPost> posts=postService.findAllPost();
		return new ResponseEntity<List<UserPost>>(posts,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/posts/save/{postId}")
	public ResponseEntity<UserPost> savePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{
		User loginUser=userService.findUserByJwt(jwt);
		UserPost savePost= postService.savedPost(postId, loginUser.getId());
		return new ResponseEntity<UserPost>(savePost,HttpStatus.ACCEPTED);
	}
	@PutMapping("/api/posts/like/{postId}")
	public ResponseEntity<UserPost> likePostHandler(@PathVariable Integer postId,@RequestHeader("Authorization")String jwt) throws Exception{

		User loginUser=userService.findUserByJwt(jwt);
		UserPost likePost= postService.likePost(postId, loginUser.getId());
		return new ResponseEntity<UserPost>(likePost,HttpStatus.ACCEPTED);
	}
}