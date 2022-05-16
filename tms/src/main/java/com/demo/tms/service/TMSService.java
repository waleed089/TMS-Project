package com.demo.tms.service;

import java.util.List;

import com.demo.tms.entity.TaskEntity;

public interface TMSService {

	public List<TaskEntity> getAllTasks();
	
	public TaskEntity addTask(TaskEntity taskEntity);
	
	public TaskEntity getById(Long taskId);
	
}
