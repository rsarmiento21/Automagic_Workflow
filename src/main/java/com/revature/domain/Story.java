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

//swimline

//story
//task

/*
 * ST_ID
SL_ID
ST_TITLE
ST_DESC
ST_POINTS
ST_COMPLETED
 */


@Entity
@Table(name="STORY")
public class Story {
	
	@Id
	@Column(name="ST_ID")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="SL_ID")
	private int swimLaneId;
	
	@Column(name="ST_TITLE")
	private String title;
	
	@Column(name="ST_DESC")
	private String description;
	
	@Column(name="ST_POINTS")
	private int points;
	
	@Column(name="ST_COMPLETED")
	private String dateStoryCompleted;
	
	public Story() {
		super();
	}
	public Story(int id, int swimLaneId, String title, String description, int points, String dateStoryCompleted) {
		super();
		this.id = id;
		this.swimLaneId = swimLaneId;
		this.title = title;
		this.description = description;
		this.points = points;
		this.dateStoryCompleted = dateStoryCompleted;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSwimLaneId() {
		return swimLaneId;
	}
	public void setSwimLaneId(int swimLaneId) {
		this.swimLaneId = swimLaneId;
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
		return this.dateStoryCompleted;
	}
	public void setDateStoryCompleted(String dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted;
	} 
	
	public void setDateStoryCompleted(Timestamp dateStoryCompleted) {
		this.dateStoryCompleted = dateStoryCompleted.toString();
	}
	@Override
	public String toString() {
		return "Story [id=" + id + ", swimLaneId=" + swimLaneId + ", title=" + title + ", description=" + description
				+ ", points=" + points + ", dateStoryCompleted=" + dateStoryCompleted + "]";
	} 

	
	

}
