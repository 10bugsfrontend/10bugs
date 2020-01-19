package com.user;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.utils.BaseEntity;

//User class is inherited  by Client and Firma
//clasa abstracta
@MappedSuperclass
public abstract class User extends BaseEntity {

	@Column
	protected String username;

	@Column
	protected String password;

	@Column
	protected String name;

	@Column
	protected String phoneNumber;

	public User() {
		super();
	}

	public User(String name) {
		super();
		this.name = name;
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String name, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public User(Long id, String username, String password, String name, String phoneNumber) {
		super(id);
		this.username = username;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
