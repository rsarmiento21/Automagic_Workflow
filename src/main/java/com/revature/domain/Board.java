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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="BOARD")
public class Board {
	
	@Id
	@SequenceGenerator(name="boardSeq", sequenceName="board_seq", allocationSize=1)
	@GeneratedValue(generator="boardSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="BD_ID")
	private int id;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="OWNER_ID")
	private BoardUser owner;
	
	@Column(name="BD_NAME")
	private String name;

	@JsonManagedReference
	@OneToMany(mappedBy="board", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<SwimLane> swimLanes;
	
	public Board() {}
	
	

	public Board(int id, BoardUser owner, String name) {
		super();
		this.id = id;
		this.owner = owner;
		this.name = name;
	}



	public Board(int id, BoardUser owner, String name, Set<SwimLane> swimLanes) {
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

	
	
	public Set<SwimLane> getSwimLanes() {
		return swimLanes;
	}



	public void setSwimLanes(Set<SwimLane> swimLanes) {
		this.swimLanes = swimLanes;
	}



	@Override
	public String toString() {
		return "Board [id=" + id + ", name=" + name +"]";
	}

	

}
