package com.factura;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.firma.Firma;
import com.utils.BaseEntity;

//folosita pentru a monetiza aplicatia
@Entity
public class Factura extends BaseEntity {

	@OneToOne
	private Firma firma;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dataGenerarii;

	@Column
	private double suma;

	@Column
	private int nrZile;

	public Factura() {

	}

	public Factura(Firma firma, Date dataGenerarii, double suma, int nrZile) {
		super();
		this.firma = firma;
		this.dataGenerarii = dataGenerarii;
		this.suma = suma;
		this.nrZile = nrZile;
	}

	public Factura(Long id, Firma firma, Date dataGenerarii, double suma, int nrZile) {
		super(id);
		this.firma = firma;
		this.dataGenerarii = dataGenerarii;
		this.suma = suma;
		this.nrZile = nrZile;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public Date getDataGenerarii() {
		return dataGenerarii;
	}

	public void setDataGenerarii(Date dataGenerarii) {
		this.dataGenerarii = dataGenerarii;
	}

	public double getSuma() {
		return suma;
	}

	public void setSuma(double suma) {
		this.suma = suma;
	}

	public int getNrZile() {
		return nrZile;
	}

	public void setNrZile(int nrZile) {
		this.nrZile = nrZile;
	}

}
