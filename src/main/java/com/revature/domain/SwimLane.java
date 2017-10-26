package com.revature.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public SwimLane() {
		
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

	@Override
	public String toString() {
		return "SwimLane [id=" + id + ", board=" + board + ", name=" + name + "]";
	}
	
	
}
