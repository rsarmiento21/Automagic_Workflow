package com.revature.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

//swimline

//story
//task


@Entity
@Table(name="STORY")
public class Story {
	
	@Id
	@SequenceGenerator(name="storySeq", sequenceName="story_seq", allocationSize=1)
	@GeneratedValue(generator="storySeq",strategy=GenerationType.SEQUENCE)
	@Column(name="ST_ID")
	private int id;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SL_ID")
	private SwimLane swimLane;
	
	@Column(name="ST_TITLE")
	private String title;
	
	@Column(name="ST_DESC")
	private String description;
	
	@Column(name="ST_POINTS")
	private int points;
	
	@Column(name="ST_COMPLETED")
	private Timestamp dateStoryCompleted;
	
	@Column(name="ST_POSITION")
	private int order;
	
	@JsonManagedReference
	@OneToMany(mappedBy="story", cascade={CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<Task> tasks;
	
	
	
	public Story() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SwimLane getSwimLane() {
		return swimLane;
	}

	public void setSwimLane(SwimLane swimLane) {
		this.swimLane = swimLane;
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Timestamp getDateStoryCompleted() {
		return dateStoryCompleted;
	}

	public void setDateStoryCompleted(Timestamp dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "Story [id=" + id + ", swimLane=" + swimLane.getId() + ", title=" + title + ", description=" + description
				+ ", points=" + points + ", dateStoryCompleted=" + dateStoryCompleted + ", tasks=" + tasks + ", position="
				+ order + "]";
	}
	
	

}
