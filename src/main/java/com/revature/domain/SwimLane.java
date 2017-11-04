package com.revature.domain;

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

/*
 * SL_ID
BD_ID
SL_NAME
 */

@Entity
@Table(name="SWIM_LANE")
public class SwimLane {

	@Id
	@SequenceGenerator(name="slSeq", sequenceName="swim_lane_seq", allocationSize=1)
	@GeneratedValue(generator="slSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="SL_ID")
	private int id;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="BD_ID")
	private Board board;
	
	@Column(name="SL_NAME")
	private String name;
	
	@OneToMany(mappedBy="swimLane", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Story> stories;
	
	

	public SwimLane() {
		super();
	}




	public SwimLane(int id, Board board, String name) {
		super();
		this.id = id;
		this.board = board;
		this.name = name;
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
		return "SwimLane [id=" + id + ", board=" + board.getId() + ", name=" + name +  "]";
	}
	
	
	
	
	
}
