package com.revature.domain;

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

/*
 * SL_ID
BD_ID
SL_NAME
 */

@Entity
@Table(name="SWIM_LANE")
public class SwimLane {

	@Id
	@Column(name="SL_ID")
	private int id;
	

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="BD_ID")
	private Board board;
	
	@Column(name="SL_NAME")
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="SL_ID")
	private Set<Story> stories;
	
	

	public SwimLane() {
		super();
	}




	public SwimLane(int id, Board board, String name, Set<Story> stories) {
		super();
		this.id = id;
		this.board = board;
		this.name = name;
		this.stories = stories;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public Board getBoard() {
		return board;
	}




	public void setBoard(Board board) {
		this.board = board;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public Set<Story> getStories() {
		return stories;
	}




	public void setStories(Set<Story> stories) {
		this.stories = stories;
	}




	@Override
	public String toString() {
		return "SwimLane [id=" + id + ", board=" + board + ", name=" + name + ", stories=" + stories + "]";
	}
	
	
	
	
	
}
