package com.demo.tms.response;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
	
	public ResponseDTO<List<TaskDTO>> getAllTaskDataByOrder(){
		ResponseDTO<List<TaskDTO>> response = new ResponseDTO<List<TaskDTO>>();
		
		List<Object[]> listObj = tmsService.getAllTasksByOrder();
		
		if(listObj.isEmpty()) {
			response.setStatusCode(HttpStatus.NO_CONTENT);
			response.setCode(HttpStatus.NO_CONTENT.value());
			response.setMessage("No Data Found");
			response.setData(new ArrayList<TaskDTO>());
			return response;
		}
		else {
			TaskDTO taskDTO = null;
			List<TaskDTO> listTask = new ArrayList<TaskDTO>();
			for (Object[] result : listObj) {
				taskDTO = new TaskDTO();
				
				taskDTO.setTaskId( (BigInteger) result[0]);
				taskDTO.setId( (String) result[1]);
				taskDTO.setCreatedAt((Date) result[2]);
				taskDTO.setDescription((String) result[3]);
				taskDTO.setDueDate((Date) result[4]);
				taskDTO.setPriority((String) result[5]);
				taskDTO.setResolvedAt((Date) result[6]);
				taskDTO.setStatus((Boolean) result[7]);
				taskDTO.setTitle((String) result[8]);
				taskDTO.setUpdatedAt((Date) result[9]);
				
				listTask.add(taskDTO);
			}
			response.setStatusCode(HttpStatus.OK);
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Data Found");
			response.setData(listTask);
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
	
	public ResponseDTO<TaskDTO> getTaskById(BigInteger taskId) {
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
