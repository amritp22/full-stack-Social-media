package com.social.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="chat_id")
	private Integer id;
	private String chat_name;
	private String chat_image;
	@ManyToMany
	private List<User> users=new ArrayList<>();
	
	@OneToMany(mappedBy="chat")
	private List<Message> messages=new ArrayList<>();
	private LocalDateTime timestamp;
}
