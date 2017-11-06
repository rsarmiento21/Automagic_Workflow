package com.revature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	@SequenceGenerator(name="taskSeq", sequenceName="task_seq", allocationSize=1)
	@GeneratedValue(generator="taskSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="TSK_ID")
	private int id;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ST_ID")
	private Story story;
	
	@Column(name="TSK_NAME")
	private String name;
	
	@Type(type="numeric_boolean")
	@Column(name="TSK_COMPLETED")
	private boolean taskCompleted;
	
	@Column(name="TSK_POSITION")
	private int order;
	
	
	
	public Task() {}

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

	public boolean getTaskCompleted() {
		return taskCompleted;
	}

	public void setTaskCompleted(boolean taskCompleted) {
		this.taskCompleted = taskCompleted;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", story=" + story.getId() + ", name=" + name + ", taskCompleted=" + taskCompleted + ", position=" + order + "]";
	}
}
