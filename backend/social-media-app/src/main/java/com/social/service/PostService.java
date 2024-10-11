package com.social.service;

import java.util.List;

import com.social.models.UserPost;

public interface PostService {

	UserPost createNewPost(UserPost post,Integer userId) throws Exception;
	String deletePost(Integer postId,Integer userId) throws Exception;
	List<UserPost> findPostByUserId(Integer UserId);
	UserPost findPostById(Integer postId) throws Exception;
	List<UserPost> findAllPost();
	
	UserPost savedPost(Integer postId,Integer UserId) throws Exception;
	UserPost likePost(Integer postId,Integer UserId) throws Exception;
	
}
