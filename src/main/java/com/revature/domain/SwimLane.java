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
	
	@OneToMany(mappedBy="swimLane", fetch=FetchType.EAGER)
	private Set<Story> stories;
	
	
}
