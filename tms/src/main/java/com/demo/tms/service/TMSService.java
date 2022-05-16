package com.demo.tms.service;

import java.math.BigInteger;
import java.util.List;

import com.demo.tms.entity.TaskEntity;

public interface TMSService {

	public List<TaskEntity> getAllTasks();
	
	public List<Object[]> getAllTasksByOrder();
	
	public TaskEntity addTask(TaskEntity taskEntity);
	
	public TaskEntity getById(BigInteger taskId);
	
}
