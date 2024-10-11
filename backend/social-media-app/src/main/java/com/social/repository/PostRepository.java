package com.social.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.social.models.UserPost;

public interface PostRepository extends JpaRepository<UserPost, Integer> {

	@Query("select p from UserPost p where p.user.id=:userId")
	List<UserPost> findPostByUserId(Integer userId);
}
