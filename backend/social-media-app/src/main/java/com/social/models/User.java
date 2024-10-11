package com.social.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String gender;
	private List<Integer> follower =new ArrayList<>();
	private List<Integer> following=new ArrayList<>();
	@ManyToMany
	private List<UserPost> savedPost=new ArrayList<>();
	
	
	public User() {
		//demo constructor
	}
	
	

	public User(Integer id, String firstName, String lastName, String email, String password, String gender,
			List<Integer> follower, List<Integer> following, List<UserPost> savedPost) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.follower = follower;
		this.following = following;
		this.savedPost = savedPost;
	}







	public List<UserPost> getSavedPost() {
		return savedPost;
	}


	public void setSavedPost(List<UserPost> savedPost) {
		this.savedPost = savedPost;
	}


	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public List<Integer> getFollower() {
		return follower;
	}




	public void setFollower(List<Integer> follower) {
		this.follower = follower;
	}




	public List<Integer> getFollowing() {
		return following;
	}




	public void setFollowing(List<Integer> following) {
		this.following = following;
	}




	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
