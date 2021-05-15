package com.church.manager.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "user_user")
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String login;
	private String password;
	private boolean isAdmin;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="church_id", referencedColumnName = "id")
	@JsonBackReference
	private Church church;

	public User(Long id, String login, String password, boolean isAdmin, Church church) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.isAdmin = isAdmin;
		this.church = church;
	}

	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public Church getChurch() {
		return church;
	}
	public void setChurch(Church church) {
		this.church = church;
	}

}
