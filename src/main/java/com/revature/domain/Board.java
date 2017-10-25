package com.revature.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
//	@OneToMany(mappedBy="ownerId", fetch=FetchType.EAGER)
//	private Set<SwimLane> swimLanes;
	
	public Board() {}

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

	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name + "]";
	}

}
