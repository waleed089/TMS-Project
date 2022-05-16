package com.demo.tms.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TaskDTO {

	private Long taskId;
	private String id;
	private String title;
	private String description;
	private String priority;
	private Boolean status;
	private Date createdAt;
	private Date updatedAt;
	private Date dueDate;
	private Date resolvedAt;

	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Date getResolvedAt() {
		return resolvedAt;
	}
	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
	}
	
}
