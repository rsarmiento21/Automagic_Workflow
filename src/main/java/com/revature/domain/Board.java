package com.revature.domain;

import java.util.List;
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

import java.util.Set;

@Entity
@Table(name="BOARD")
public class Board {
	@Id
	@Column(name="BD_ID")
	private int id;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="OWNER_ID")
	private BoardUser owner;
	
	@Column(name="BD_NAME")
	private String name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="BD_ID")
	private List<SwimLane> swimLanes;
	
	public Board() {}
	
	

	public Board(int id, BoardUser owner, String name, List<SwimLane> swimLanes) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
		this.swimLanes = swimLanes;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BoardUser getOwner() {
		return owner;
	}

	public void setOwner(BoardUser owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public List<SwimLane> getSwimLanes() {
		return swimLanes;
	}



	public void setSwimLanes(List<SwimLane> swimLanes) {
		this.swimLanes = swimLanes;
	}



	@Override
	public String toString() {
		return "Board [id=" + id + ", owner=" + owner.getId() + ", name=" + name +"]";
	}

	

}
