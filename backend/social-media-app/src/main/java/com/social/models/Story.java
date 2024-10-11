package com.social.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Story {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "story_id")
	private Integer id;
	
	private String image;
	private String caption;
	@ManyToOne
	private User user;
	private LocalDateTime createdAt;
}
