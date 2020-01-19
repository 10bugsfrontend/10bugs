package com.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

//clasa care extinde clasa User
//tipul mostenirii ii un tabel pentru fiecare clasa

import com.user.User;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Client extends User {

	@Column
	public String email;

	public Client() {
		super();
	}

	public Client(String username, String password, String name, String phoneNumber, String email) {
		super(username, password, name, phoneNumber);
		this.email = email;
	}

	public Client(Long id, String username, String password, String name, String phoneNumber, String email) {
		super(id, username, password, name, phoneNumber);
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
