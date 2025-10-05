package com.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Task;
import com.enums.TaskStatus;
@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

	long countByAssignedUserAndStatus(String id, TaskStatus status);
	List<Task> findAllByCreatedByAndStatusAndDueDateBefore(String id, TaskStatus status, LocalDateTime dateTime);
	List<Task> findAllByCreatedByAndStatus(String id, TaskStatus status);
	List<Task> findAllByCreatedByAndDueDateBefore(String id, LocalDateTime dateTime);
	List<Task> findAllByAssignedUserAndStatusAndDueDateBefore(String id, TaskStatus status, LocalDateTime dateTime);
	List<Task> findAllByAssignedUserAndStatus(String id, TaskStatus status);
	List<Task> findAllByAssignedUserAndDueDateBefore(String id, LocalDateTime dateTime);
}
