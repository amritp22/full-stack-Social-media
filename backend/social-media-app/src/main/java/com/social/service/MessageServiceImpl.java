package com.social.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.social.models.Chat;
import com.social.models.Message;
import com.social.models.User;
import com.social.repository.ChatRepository;
import com.social.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageRepository  messageRepository;
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepository;

	@Override
	public Message createMessage(User user, Integer chatId, Message msg) throws Exception {
		Message req=new Message();
		Chat chat=chatService.findChatById(chatId);
		
		req.setChat(chat);
		req.setUser(user);
		req.setContent(msg.getContent());
		req.setImage(msg.getImage());
		req.setTimeStamp(LocalDateTime.now());
		Message savedMessage= messageRepository.save(req);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
		
	}

	@Override
	public List<Message> findChatMessages(Integer chatId) {
		// TODO Auto-generated method stub
		return messageRepository.findByChatId(chatId);
	}

}
