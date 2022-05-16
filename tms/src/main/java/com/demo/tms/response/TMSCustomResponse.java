package com.demo.tms.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.tms.dto.ResponseDTO;
import com.demo.tms.dto.TaskDTO;
import com.demo.tms.entity.TaskEntity;
import com.demo.tms.service.TMSService;

@Service
public class TMSCustomResponse {
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TMSService tmsService;
	
	public ResponseDTO<String> aboutTMS(){
		ResponseDTO<String> response = new ResponseDTO<String>();
		response.setStatusCode(HttpStatus.OK);
		response.setCode(HttpStatus.OK.value());
		response.setMessage("SUCCESS");
		response.setData("TMS - Task Management System is Working!!");
		return response;
	}
	
	public ResponseDTO<List<TaskDTO>> getAllTaskData(){
		List<TaskDTO> taskList = tmsService.getAllTasks().stream().map(task -> modelMapper.map(task, TaskDTO.class)).collect(Collectors.toList());;
		ResponseDTO<List<TaskDTO>> response = new ResponseDTO<List<TaskDTO>>();
		
		if(taskList.isEmpty()) {
			response.setStatusCode(HttpStatus.NO_CONTENT);
			response.setCode(HttpStatus.NO_CONTENT.value());
			response.setMessage("No Data Found");
			response.setData(new ArrayList<TaskDTO>());
			return response;
		}
		else {
			response.setStatusCode(HttpStatus.OK);
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Data Found");
			response.setData(taskList);
			return response;
		}
	}
	
	public TaskDTO add(TaskDTO taskDTO) {
		TaskEntity taskEntity = modelMapper.map(taskDTO, TaskEntity.class);
		
		taskEntity = tmsService.addTask(taskEntity);
		
		return modelMapper.map(taskEntity, TaskDTO.class);
	}
	
	public void updateTask(TaskDTO taskDTO) {
		TaskEntity taskEntity = modelMapper.map(taskDTO, TaskEntity.class);
		tmsService.addTask(taskEntity);
	}
	
	public ResponseDTO<TaskDTO> getTaskById(Long taskId) {
		TaskEntity taskEntity = tmsService.getById(taskId);
		ResponseDTO<TaskDTO> response = new ResponseDTO<TaskDTO>();
		
		if(taskEntity == null) {
			response.setStatusCode(HttpStatus.NO_CONTENT);
			response.setCode(204);
			response.setMessage("No Data Found");
			response.setData(null);
			return response;
		}
		else {
			response.setStatusCode(HttpStatus.OK);
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Data Found");
			response.setData(modelMapper.map(taskEntity, TaskDTO.class));
			return response;
		}
	}
	
}
