package com.revature.domain;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//swimline

//story
//task


@Entity
@Table(name="STORY")
public class Story {
	
	@Id
	@Column(name="ST_ID")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SL_ID")
	private SwimLane swimLane;
	
	@Column(name="ST_TITLE")
	private String title;
	
	@Column(name="ST_DESC")
	private String description;
	
	@Column(name="ST_POINTS")
	private int points;
	
	@Column(name="ST_COMPLETED")
	private String dateStoryCompleted;
	
	@OneToMany(mappedBy="story", fetch=FetchType.EAGER)
	private Set<Task> tasks;
	
	
	
	public Story() {
		super();
	}
	
	

	public Story(int id, SwimLane swimLane, String title, String description, int points, String dateStoryCompleted) {
		super();
		this.id = id;
		this.swimLane = swimLane;
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted;
	}

	public Story(int id, SwimLane swimLane, String title, String description, int points, Timestamp dateStoryCompleted) {
		super();
		this.id = id;
		this.swimLane = swimLane;
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted.toString();
	}


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

	public String getDateStoryCompleted() {
		return dateStoryCompleted;
	}

	public void setDateStoryCompleted(String dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted;
	}

	public void setDateStoryCompleted(Timestamp dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted.toString();
	}

	
	@Override
	public String toString() {
		return "Story [id=" + id + ", swimLane=" + swimLane + ", title=" + title + ", description=" + description
				+ ", points=" + points + ", dateStoryCompleted=" + dateStoryCompleted + "]";
	}
		
	

}
