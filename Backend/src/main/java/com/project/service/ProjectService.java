package com.project.service;

import java.util.List;

import com.project.exception.ChatException;
import com.project.exception.ProjectException;
import com.project.exception.UserException;
import com.project.model.Chat;
import com.project.model.Project;
import com.project.model.User;

public interface ProjectService {
	Project createProject(Project project, Long userId) throws Exception;

//	List<Project> getProjectsByOwner(User owner) throws ProjectException;

	List<Project> getProjectsByTeam(User user,String category,String tag) throws ProjectException;


	Project getProjectById(Long projectId) throws ProjectException;

	String deleteProject(Long projectId,Long userId) throws UserException;

	Project updateProject(Project updatedProject, Long id) throws ProjectException;
	
	List<Project> searchProjects(String keyword, User user) throws ProjectException;

	void addUserToProject(Long projectId, Long userId) throws UserException, ProjectException;

	void removeUserFromProject(Long projectId, Long userId) throws UserException, ProjectException;

	Chat getChatByProjectId(Long projectId) throws ProjectException, ChatException;


	
}
