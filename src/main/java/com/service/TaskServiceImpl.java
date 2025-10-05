package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.TaskRequest;
import com.entity.Task;
import com.enums.TaskStatus;
import com.exception.TaskCreationException;
import com.exception.TaskDeletionException;
import com.exception.TaskNotFoundException;
import com.exception.TaskUpdateException;
import com.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.repository.TaskRepository;
import com.repository.UserRepository;
import com.util.IdGenerator;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Transactional
	@Override
	public TaskRequest createTask(TaskRequest task) {
		userRepository.findById(task.getAssignedUser()).orElseThrow(()-> new UserNotFoundException("User not found..."));
		
		long count = taskRepository.countByAssignedUserAndStatus(task.getAssignedUser(), task.getStatus());
		if(count>=10) {
			throw new TaskCreationException("A user can't have more than 10 pending task...");
		}
		Task newTask = objectMapper.convertValue(task, Task.class);
		newTask.setId(idGenerator.generateId("TASK"));
		Task createdTask = taskRepository.save(newTask);
		
		return objectMapper.convertValue(createdTask, TaskRequest.class);
	}

	@Transactional
	@Override
	public TaskRequest updateTaskStatus(String taskId, String userID, TaskStatus status) {
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task not found..."));
		if(!task.getAssignedUser().equals(userID) && !task.getCreatedBy().equals(userID)) {
			throw new TaskUpdateException("Only assigned user or the creator can change status...");
		}
		task.setStatus(status);
		task.setUpdatedBy(userID);
		Task updatedTask = taskRepository.save(task);
		return objectMapper.convertValue(updatedTask, TaskRequest.class);
	}

	@Override
	public List<TaskRequest> findCreatedTaskOfUserByStatusAndDate(String userID, TaskStatus status, LocalDateTime date) {
		List<Task> taskList=null;
		if(status!=null && date!=null) {
			taskList = taskRepository.findAllByCreatedByAndStatusAndDueDateBefore(userID, status, date);
		}else if(status==null && date!=null) {
			taskList = taskRepository.findAllByCreatedByAndDueDateBefore(userID, date);
		}else if(status!=null && date==null) {
			taskList = taskRepository.findAllByCreatedByAndStatus(userID, status);
		}else if(status==null && date==null) {
			throw new TaskNotFoundException("Status and date both can't be null...");
		}
		
		if(taskList.isEmpty()) {
			throw new TaskNotFoundException("No task found...");
		}
		List<TaskRequest> taskReqList = taskList.stream().map(task->objectMapper.convertValue(task, TaskRequest.class)).collect(Collectors.toList());
		return taskReqList;
	}

	@Override
	public List<TaskRequest> findAssignedTaskOfUserByStatusAndDate(String userID, TaskStatus status, LocalDateTime date) {
		List<Task> taskList = null;
		if(status!=null && date!=null) {
			taskList = taskRepository.findAllByAssignedUserAndStatusAndDueDateBefore(userID, status, date);
		}else if(status==null && date!=null) {
			taskList = taskRepository.findAllByAssignedUserAndDueDateBefore(userID, date);
		}else if(status!=null && date==null) {
			taskList = taskRepository.findAllByAssignedUserAndStatus(userID, status);
		}else if(status==null && date==null) {
			throw new TaskNotFoundException("Status and date both can't be null...");
		}
		if(taskList.isEmpty()) {
			throw new TaskNotFoundException("No task found...");
		}
		List<TaskRequest> taskReqList = taskList.stream().map(task->objectMapper.convertValue(task, TaskRequest.class)).collect(Collectors.toList());
		return taskReqList;
	}

	@Transactional
	@Override
	public void deleteTask(String taskId, String userId) {
		Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException("Task not found..."));
		if(!task.getCreatedBy().equals(userId)) {
			throw new TaskDeletionException("Only the task creator can delete a task...");
		}
		
		taskRepository.deleteById(taskId);
	}

}
