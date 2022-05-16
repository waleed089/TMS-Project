package com.demo.tms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.tms.dto.ResponseDTO;
import com.demo.tms.dto.TaskDTO;
import com.demo.tms.queue.RabbitMqSender;
import com.demo.tms.response.TMSCustomResponse;

@RestController
@RequestMapping("/api")
public class TMSController {
	
	@Autowired
	private TMSCustomResponse tmsCustomResponse;

	@Autowired
	private RabbitMqSender rabbitMqSender;
	
	@Value("${app.message}")
	private String message;
	
	@GetMapping(path = "/about")
	public ResponseEntity<ResponseDTO<String>> about() {
		ResponseDTO<String> response = tmsCustomResponse.aboutTMS();
		return new ResponseEntity<ResponseDTO<String>>(response, response.getStatusCode());
	}
	
	@GetMapping(path = "/getAll")
	public ResponseEntity<ResponseDTO<List<TaskDTO>>> getAllTaskData(){
		ResponseDTO<List<TaskDTO>> response = tmsCustomResponse.getAllTaskData();
		
		return new ResponseEntity<>(response,(response.getCode()!=200 ? HttpStatus.OK : response.getStatusCode()));
	}
	
	@PostMapping(value ="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addTask(@RequestBody TaskDTO taskDTO){
		UUID uuid = UUID.randomUUID();
		taskDTO.setId(uuid.toString());

		taskDTO = tmsCustomResponse.add(taskDTO);
		rabbitMqSender.sendTask(taskDTO);
		
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@PutMapping(value ="/remind", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> remindMeLater(@RequestBody TaskDTO taskDTO){
		System.out.println(taskDTO.getUpdatedAt());
		System.out.println(taskDTO.getPriority());
		tmsCustomResponse.add(taskDTO);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getTask/{taskId}")
	public ResponseEntity<ResponseDTO<TaskDTO>> getTaskById(@PathVariable("taskId") Long taskId){
		ResponseDTO<TaskDTO> response = tmsCustomResponse.getTaskById(taskId);
		return new ResponseEntity<>(response,(response.getCode()!=200 ? HttpStatus.OK : response.getStatusCode()));
	}
	
}
