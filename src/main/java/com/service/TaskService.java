package com.service;

import java.time.LocalDateTime;
import java.util.List;

import com.dto.TaskRequest;
import com.entity.Task;
import com.enums.TaskStatus;

public interface TaskService {

	TaskRequest createTask(TaskRequest task);
	TaskRequest updateTaskStatus(String taskId, String userID, TaskStatus status);
	List<TaskRequest> findCreatedTaskOfUserByStatusAndDate(String userID, TaskStatus status, LocalDateTime date);
	List<TaskRequest> findAssignedTaskOfUserByStatusAndDate(String userID, TaskStatus status, LocalDateTime date);
	void deleteTask(String taskId, String userId);
}
