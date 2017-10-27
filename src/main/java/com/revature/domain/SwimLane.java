package com.revature.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SWIM_LANE")
public class SwimLane {
	@Id
	@Column(name="SL_ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="BD_ID")
	private Board board;
	
	@Column(name="SL_NAME")
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="SL_ID")
	private Set<Story> stories;

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
		return "SwimLane [id=" + id + ", board=" + board.getId() + ", name=" + name + ", stories=" + stories + "]";
	}

	
	
	
	
}
