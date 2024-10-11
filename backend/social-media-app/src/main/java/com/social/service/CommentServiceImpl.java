package com.social.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Comment;
import com.social.models.User;
import com.social.models.UserPost;
import com.social.repository.CommentRepository;
import com.social.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	PostRepository postRepository;

	@Override
	public Comment createComment(Comment comment, Integer postId, Integer userId) throws Exception {
		User userF=userService.findUserById(userId);
		UserPost postF=postService.findPostById(postId);
		
		
		comment.setContent(comment.getContent());
		comment.setUser(userF);
		comment.setCreatedAt(LocalDateTime.now());	
		Comment savedComment=commentRepository.save(comment);
		
		postF.getComment().add(savedComment);
		postRepository.save(postF);
		
		return savedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> opt=commentRepository.findById(commentId);
		if (opt.isEmpty()) {
		throw new Exception("comment not exist");
		}
		
		return opt.get();
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		User userF=userService.findUserById(userId);
		Comment foundComment=findCommentById(commentId);
		if(foundComment.getLiked().contains(userF)) {
			foundComment.getLiked().remove(userF);
		}
		else {
			foundComment.getLiked().add(userF);
		}
		
		return commentRepository.save(foundComment);
	}

}
