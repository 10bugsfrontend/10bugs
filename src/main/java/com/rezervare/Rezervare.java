package com.rezervare;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.client.Client;
import com.cursa.Cursa;
import com.utils.BaseEntity;

//clasa rezervare

@Entity
public class Rezervare extends BaseEntity {
	// relatia 1 la 1 cu client
	@OneToOne
	protected Client client;
	// relatia 1 la 1 cu cursa
	@OneToOne
	protected Cursa cursa;
	// statusul poate fi de mai multe tipuri mentionate in enumul StatusType
	@Column
	protected StatusType status;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date data;

	// statia de unde s-a facut rezervarea
	@Column
	protected String plecare;

	// statia unde trebuie sa ajunga
	@Column
	protected String sosire;

	// stegulet pentru a arata daca o rezervare e platita sau nu
	@Column
	protected Boolean isPaid = false;

	@Column
	protected Boolean isRated = false;

	public Rezervare(Client client, Cursa cursa, StatusType status, Date data, String plecare, String sosire,
			Boolean isPaid, Boolean isRated) {
		super();
		this.client = client;
		this.cursa = cursa;
		this.status = status;
		this.data = data;
		this.plecare = plecare;
		this.sosire = sosire;
		this.isPaid = isPaid;
		this.isRated = isRated;
	}

	public Rezervare(Long id, Client client, Cursa cursa, StatusType status, Date data, String plecare, String sosire,
			Boolean isPaid, Boolean isRated) {
		super(id);
		this.client = client;
		this.cursa = cursa;
		this.status = status;
		this.data = data;
		this.plecare = plecare;
		this.sosire = sosire;
		this.isPaid = isPaid;
		this.isRated = isRated;
	}

	public Rezervare(Long id, Client client, Cursa cursa, Date data, String plecare, String sosire, Boolean isPaid,
			Boolean isRated) {
		super(id);
		this.client = client;
		this.cursa = cursa;
		this.data = data;
		this.plecare = plecare;
		this.sosire = sosire;
		this.isPaid = isPaid;
		this.isRated = isRated;
	}

	public Rezervare(Client client, Cursa cursa, Date data, String plecare, String sosire, Boolean isPaid,
			Boolean isRated) {
		super();
		this.client = client;
		this.cursa = cursa;
		this.data = data;
		this.plecare = plecare;
		this.sosire = sosire;
		this.isPaid = isPaid;
		this.isRated = isRated;
	}

	public Rezervare(Long id, Client client, Cursa cursa) {
		super(id);
		this.client = client;
		this.cursa = cursa;
	}

	public Rezervare() {
		super();
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Cursa getCursa() {
		return cursa;
	}

	public void setCursa(Cursa cursa) {
		this.cursa = cursa;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getPlecare() {
		return plecare;
	}

	public void setPlecare(String plecare) {
		this.plecare = plecare;
	}

	public String getSosire() {
		return sosire;
	}

	public void setSosire(String sosire) {
		this.sosire = sosire;
	}

	public Boolean isPaid() {
		return isPaid;
	}

	public void setPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Boolean getIsRated() {
		return isRated;
	}

	public void setIsRated(Boolean isRated) {
		this.isRated = isRated;
	}

}
