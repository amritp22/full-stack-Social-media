package com.social.models;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reels {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="reel_id")
	private Integer id;
	private String title;
	private String video;
	@ManyToOne
	private User user;
}
