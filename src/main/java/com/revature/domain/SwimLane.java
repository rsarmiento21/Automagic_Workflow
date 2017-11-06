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
	
	@Column(name="SL_POSITION")
	private int order;
	
	@JsonManagedReference
	@OneToMany(mappedBy="swimLane", cascade={CascadeType.MERGE, CascadeType.REMOVE}, fetch=FetchType.EAGER)
	private Set<Story> stories;
	
	
	
	public SwimLane() {}

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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "SwimLane [id=" + id + ", board=" + board.getId() + ", name=" + name + ", position=" + order + ", stories="
				+ stories + "]";
	}

}