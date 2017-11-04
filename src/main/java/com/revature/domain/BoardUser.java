package com.revature.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BOARD_USER")
public class BoardUser {
	
	@Id
	@SequenceGenerator(name="buSeq", sequenceName="board_user_seq", allocationSize=1)
	@GeneratedValue(generator="buSeq",strategy=GenerationType.SEQUENCE)
	@Column(name="BU_ID")
	private int id;
	
	@Column(name="BU_USERNAME")
	private String username;
	
	@Column(name="BU_PASSWORD")
	private String password;
	
	@OneToMany(mappedBy="owner", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
	private Set<Board> boards;
	
	public BoardUser() {}
	
	 

	public BoardUser(int id, String username, String password, Set<Board> boards) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.boards = boards;
	}



	public BoardUser(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Board> getBoards() {
		return boards;
	}

	public void setBoards(Set<Board> boards) {
		this.boards = boards;
	}

	@Override
	public String toString() {
		return "BoardUser [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}
