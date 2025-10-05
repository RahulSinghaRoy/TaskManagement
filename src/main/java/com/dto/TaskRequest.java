package com.dto;

import java.time.LocalDateTime;

import com.enums.TaskStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

	private String id;
	private String title;
	private String description;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	private LocalDateTime dueDate;
	private String assignedUser;
	private String createdBy;
	private String updatedBy;
}
