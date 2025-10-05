package com.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.TaskRequest;
import com.enums.TaskStatus;
import com.service.TaskService;

@RequestMapping("/task")
@RestController
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createtask")
	public ResponseEntity<TaskRequest> createTask(@RequestBody TaskRequest taskRequest){
		TaskRequest createdTask = taskService.createTask(taskRequest);
		
		return new ResponseEntity<TaskRequest>(createdTask, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatetask/{taskId}/{userId}/{status}")
	public ResponseEntity<TaskRequest> updateTask(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId, @PathVariable("status") TaskStatus status){
		TaskRequest updatedTask = taskService.updateTaskStatus(taskId, userId, status);
		
		return new ResponseEntity<TaskRequest>(updatedTask, HttpStatus.OK);
	}
	
	@GetMapping("/findtaskbycreatedbyandstatusanddate")
	public ResponseEntity<List<TaskRequest>> findTaskByCreatedByAndStatusAndDate(@RequestParam String userId, 
																				 @RequestParam(required = false) TaskStatus status, 
																				 @RequestParam(required = false) LocalDateTime date){
				List<TaskRequest> taskList = taskService.findCreatedTaskOfUserByStatusAndDate(userId, status, date);
				
				return new ResponseEntity<List<TaskRequest>>(taskList, HttpStatus.OK);
			}
	
	@GetMapping("/findtaskbyassigneduserandstatusanddate")
	public ResponseEntity<List<TaskRequest>> findTaskByAssignedUserAndStatusAndDate(@RequestParam String userId, 
																					@RequestParam(required = false) TaskStatus status, 
																					@RequestParam(required = false) LocalDateTime date){
				List<TaskRequest> taskList = taskService.findAssignedTaskOfUserByStatusAndDate(userId, status, date);
				
				return new ResponseEntity<List<TaskRequest>>(taskList, HttpStatus.OK);
			}
	
	@DeleteMapping("/deletetask/{taskId}/{userId}")
	public ResponseEntity<String> deleteTask(@PathVariable("taskId") String taskId, @PathVariable("userId") String userId){
		taskService.deleteTask(taskId, userId);
		
		return new ResponseEntity<String>("Task deleted successfully...", HttpStatus.OK);
	}
}
