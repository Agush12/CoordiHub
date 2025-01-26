package com.project.service;

import java.util.List;

import com.project.exception.ChatException;
import com.project.exception.ProjectException;
import com.project.model.Chat;

public interface ChatService {

	Chat createChat(Chat chat);

	//Chat getChatsByProjectId(Long projectId) throws ChatException, ProjectException;

//	Chat addUsersToChat(Long chatId, List<Long> userIds) throws ChatException;

	
//	 List<Chat> searchChatsByName(String name) throws ChatException;

	// Other methods as needed
}
