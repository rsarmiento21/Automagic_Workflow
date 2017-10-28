package com.revature.domain;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * TSK_ID
ST_ID
TSK_NAME
TSK_COMPLETED
 */

@Entity
@Table(name="TASK")
public class Task {

	@Id
	@Column(name="TSK_ID")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ST_ID")
	private Story story;
		
	
	@Column(name="TSK_NAME")
	private String name;
	
	@Column(name="TSK_COMPLETED")
	private int taskCompleted;
	
	
	
	
	public Task() {
		super();
	}




	public Task(int id, Story story, String name, int taskCompleted) {
		super();
		this.id = id;
		this.story = story;
		this.name = name;
		this.taskCompleted = taskCompleted;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Story getStory() {
		return story;
	}




	public void setStory(Story story) {
		this.story = story;
	}



	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public int getTaskCompleted() {
		return taskCompleted;
	}




	public void setTaskCompleted(int taskCompleted) {
		this.taskCompleted = taskCompleted;
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", story=" + story.getId() + ", name=" + name + "]";

	}
}
