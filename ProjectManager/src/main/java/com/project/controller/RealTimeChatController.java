package com.project.controller;

import com.project.exception.ChatException;
import com.project.exception.ProjectException;
import com.project.exception.UserException;
import com.project.model.Message;
import com.project.model.User;
import com.project.request.CreateMessageRequest;
import com.project.service.MessageService;
import com.project.service.ProjectService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RealTimeChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @MessageMapping("/chat/{groupId}")
    public Message dispatchMessage(@Payload CreateMessageRequest request,
                                   @DestinationVariable String groupId)
            throws UserException, ChatException, ProjectException {

        User user = userService.findUserById(request.getSenderId());
        if (user == null) {
            throw new UserException("User not found with ID " + request.getSenderId());
        }

        var chat = projectService.getProjectById(request.getProjectId()).getChat();
        if (chat == null) {
            throw new ChatException("Chats not found for the specified project.");
        }

        Message sentMessage = messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        simpMessagingTemplate.convertAndSend("/topic/chat/" + groupId, sentMessage);

        return sentMessage;
    }

    @MessageMapping("/messages/{projectId}")
    public void fetchMessages(@DestinationVariable Long projectId) throws ProjectException, ChatException {
        var messages = messageService.getMessagesByProjectId(projectId);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + projectId, messages);
    }
}
