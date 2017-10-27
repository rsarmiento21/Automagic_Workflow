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
	private String dateTaskCompleted;
	
	
	
	
	public Task() {
		super();
	}
	


	public Task(int id, Story story, String name, String dateTaskCompleted) {
		super();
		this.id = id;
		this.story = story;
		this.name = name;
		this.dateTaskCompleted = dateTaskCompleted;
	}

	public Task(int id, Story story, String name, Timestamp dateTaskCompleted) {
		super();
		this.id = id;
		this.story = story;
		this.name = name;
		this.dateTaskCompleted = dateTaskCompleted.toString();
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

	public String getDateTaskCompleted() {
		return dateTaskCompleted;
	}

	public void setDateTaskCompleted(String dateTaskCompleted) {
		this.dateTaskCompleted = dateTaskCompleted;
	}
	
	public void setDateTaskCompleted(Timestamp dateTaskCompleted) {
		this.dateTaskCompleted = this.dateTaskCompleted.toString();
	}


	@Override
	public String toString() {
		return "Task [id=" + id + ", story=" + story.getId() + ", name=" + name + ", dateTaskCompleted=" + dateTaskCompleted
				+ "]";
	}
}
