package com.demo.tms.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity {

	private BigInteger taskId;
	private String id;
	private String title;
	private String description;
	private String priority;
	private Boolean status;
	private Date createdAt;
	private Date updatedAt;
	private Date dueDate;
	private Date resolvedAt;

	@Id
	@SequenceGenerator(name ="task_sequence", sequenceName = "task_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "task_sequence")
	@Column(name="task_id")
	public BigInteger getTaskId() {
		return taskId;
	}
	public void setTaskId(BigInteger taskId) {
		this.taskId = taskId;
	}
	
	@Column(name="id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "priority")
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	@Column(name = "status")
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(name = "due_date")
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "resolved_at")
	public Date getResolvedAt() {
		return resolvedAt;
	}
	public void setResolvedAt(Date resolvedAt) {
		this.resolvedAt = resolvedAt;
	}
	
}
