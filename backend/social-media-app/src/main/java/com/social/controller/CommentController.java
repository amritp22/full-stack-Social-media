package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Comment;
import com.social.models.User;
import com.social.service.CommentService;
import com.social.service.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comment/posts/{postId}")
	public Comment createComment(@RequestBody Comment comment,
			@RequestHeader("Authorization") String jwt,@PathVariable Integer postId) throws Exception {
		
		User foundUser =userService.findUserByJwt(jwt);
		Comment createdComment=commentService.createComment(comment, postId, foundUser.getId());
		
		
		return createdComment;
	}

	@PutMapping("/api/comment/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws Exception {
		
		User foundUser =userService.findUserByJwt(jwt);
		Comment likedComment=commentService.likeComment(commentId, foundUser.getId());
		
		
		return likedComment	;
	}
}
