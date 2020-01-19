package com.cursa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.firma.Firma;
import com.traseu.Traseu;
import com.utils.BaseEntity;

@Entity
//clasa Cursa

public class Cursa extends BaseEntity {
	// traseu, relatia unu la unu
	@OneToOne
	protected Traseu traseu;
	// data, sub forma timestamp
	@Temporal(TemporalType.TIMESTAMP)
	protected Date ora_de_plecare;

	// data, sub forma timestamp
	@Temporal(TemporalType.TIMESTAMP)
	protected Date ora_de_sosire;

	// relatia n la 1
	@ManyToOne(targetEntity = Firma.class)
	protected Firma firma;

	// nr locuri
	@Column
	protected int capacitate;

	@Column
	protected int ocupate;

	// durata in ore a cursei
	@Column
	protected double durata;

	// pret, e intreg
	@Column
	protected int pret;

	// se mentioneaza prin virgula facilitatile
	@Column
	protected String facilitati;

	// frecventa se da ca un string unde zilele is separate prin virgula
	// Mon,Tue,Wed,Thu,Fri,Sat,Sun
	@Column
	protected String frecventa;

	public Cursa() {
		super();
	}

	// ora de plecare si de sosire sunt date sub forma unui string si le converteste
	// la data
	public Cursa(String ora_de_plecare, String ora_de_sosire, int capacitate, int pret, String facilitati,
			String frecventa) {
		super();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
		String s = formatter.format(ora_de_plecare);
		String sos = formatter.format(ora_de_sosire);

		try {
			this.ora_de_plecare = formatter.parse(s);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.ora_de_sosire = formatter.parse(sos);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.capacitate = capacitate;
		this.pret = pret;
		this.facilitati = facilitati;
		this.frecventa = frecventa;
	}

	public Cursa(Traseu traseu, Date ora_de_plecare, Date ora_de_sosire, Firma firma, int capacitate, int ocupate,
			double durata, int pret, String facilitati, String frecventa) {
		super();
		this.traseu = traseu;
		this.ora_de_plecare = ora_de_plecare;
		this.ora_de_sosire = ora_de_sosire;
		this.firma = firma;
		this.capacitate = capacitate;
		this.ocupate = ocupate;
		this.durata = durata;
		this.pret = pret;
		this.facilitati = facilitati;
		this.frecventa = frecventa;
	}

	public Cursa(Long id, Traseu traseu, Date ora_de_plecare, Date ora_de_sosire, Firma firma, int capacitate,
			int ocupate, double durata, int pret, String facilitati, String frecventa) {
		super(id);
		this.traseu = traseu;
		this.ora_de_plecare = ora_de_plecare;
		this.ora_de_sosire = ora_de_sosire;
		this.firma = firma;
		this.capacitate = capacitate;
		this.ocupate = ocupate;
		this.durata = durata;
		this.pret = pret;
		this.facilitati = facilitati;
		this.frecventa = frecventa;
	}

	public Traseu getTraseu() {
		return traseu;
	}

	public void setTraseu(Traseu traseu) {
		this.traseu = traseu;
	}

	public Date getOra_de_plecare() {
		return ora_de_plecare;
	}

	public void setOra_de_plecare(Date ora_de_plecare) {
		this.ora_de_plecare = ora_de_plecare;
	}

	public Firma getFirma() {
		return firma;
	}

	public void setFirma(Firma firma) {
		this.firma = firma;
	}

	public int getCapacitate() {
		return capacitate;
	}

	public void setCapacitate(int capacitate) {
		this.capacitate = capacitate;
	}

	public int getOcupate() {
		return ocupate;
	}

	public void setOcupate(int ocupate) {
		this.ocupate = ocupate;
	}

	public double getDurata() {
		return durata;
	}

	public void setDurata(double durata) {
		this.durata = durata;
	}

	public Date getOra_de_sosire() {
		return ora_de_sosire;
	}

	public void setOra_de_sosire(Date ora_de_sosire) {
		this.ora_de_sosire = ora_de_sosire;
	}

	public int getPret() {
		return pret;
	}

	public void setPret(int pret) {
		this.pret = pret;
	}

	public String getFacilitati() {
		return facilitati;
	}

	public void setFacilitati(String facilitati) {
		this.facilitati = facilitati;
	}

	public String getFrecventa() {
		return frecventa;
	}

	public void setFrecventa(String frecventa) {
		this.frecventa = frecventa;
	}

}
