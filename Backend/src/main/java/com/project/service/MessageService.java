package com.project.service;

import java.util.List;

import com.project.exception.ChatException;
import com.project.exception.ProjectException;
import com.project.exception.UserException;
import com.project.model.Message;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws UserException, ChatException, ProjectException;

    List<Message> getMessagesByProjectId(Long projectId) throws ProjectException, ChatException;
}

