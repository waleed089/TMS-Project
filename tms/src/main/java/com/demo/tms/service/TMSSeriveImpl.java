package com.demo.tms.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.tms.entity.TaskEntity;
import com.demo.tms.repo.TMSRepository;


@Service
public class TMSSeriveImpl implements TMSService {
	
	@Autowired
	private TMSRepository tmsRepository;

	@Override
	public List<TaskEntity> getAllTasks() {
		return tmsRepository.findAll();
	}

	@Override
	public TaskEntity addTask(TaskEntity taskEntity) {
		return tmsRepository.save(taskEntity);
	}

	@Override
	public TaskEntity getById(BigInteger taskId) {
		/*
		 * Optional<TaskEntity> op = tmsRepository.findById(taskId); if(op.isPresent())
		 * return tmsRepository.findById(taskId).get(); else return null;
		 */
		return tmsRepository.findById(taskId).orElse(null);
	}

	@Override
	public List<Object[]> getAllTasksByOrder() {
		return tmsRepository.getAllTaskByOrder();
	}

	
}
