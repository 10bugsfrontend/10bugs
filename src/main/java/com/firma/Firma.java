package com.firma;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cursa.Cursa;
import com.rating.Rating;
import com.user.User;

//clasa firma extinde clasa abstracta user
@Entity
public class Firma extends User {

	@Column
	protected String email;

	@Column
	protected String cui;

	@OneToMany
	protected List<Cursa> curse;

	@OneToOne
	protected Rating rating;

	public Firma(String name) {
		super(name);
	}

	public Firma() {
		super();
	}

	public Firma(String username, String password, String name, String phoneNumber, String email, String cui) {
		super(username, password, name, phoneNumber);
		this.email = email;
		this.cui = cui;
	}

	public Firma(String username, String password, String name, String phoneNumber, String email, String cui,
			List<Cursa> curse, Rating rating) {
		super(username, password, name, phoneNumber);
		this.email = email;
		this.cui = cui;
		this.curse = curse;
		this.rating = rating;
	}

	public Firma(Long id, String username, String password, String name, String phoneNumber, String email, String cui,
			List<Cursa> curse, Rating rating) {
		super(id, username, password, name, phoneNumber);
		this.email = email;
		this.cui = cui;
		this.curse = curse;
		this.rating = rating;
	}

	public Firma(String username, String password) {
		super(username, password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}

	public List<Cursa> getCurse() {
		return curse;
	}

	public void setCurse(List<Cursa> curse) {
		this.curse = curse;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

}
