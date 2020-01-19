package com.statie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.utils.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Statie extends BaseEntity {
	// locul unde se afla statia
	@Column
	private String oras;
	// urmatoarea localitate unde merge
	@Column
	private String urmStatie;
	@Column
	private int km_urmStatie;
	@Column
	private int km_inapoi;

	public Statie() {
		this.oras = null;
		this.urmStatie = null;
		this.km_urmStatie = 0;
		this.km_inapoi = 0;
	}

	public Statie(String oras, String urmStatie, int km_urmStatie, int km_inapoi) {
		super();
		this.oras = oras;
		this.urmStatie = urmStatie;
		this.km_urmStatie = km_urmStatie;
		this.km_inapoi = km_inapoi;
	}

	public Statie(String oras, String urmStatie, int km_urmStatie) {
		super();
		this.oras = oras;
		this.urmStatie = urmStatie;
		this.km_urmStatie = km_urmStatie;
		this.km_inapoi = km_urmStatie;
	}

	public Statie(Long id, String oras, String urmStatie, int km_urmStatie, int km_inapoi) {
		super(id);
		this.oras = oras;
		this.urmStatie = urmStatie;
		this.km_urmStatie = km_urmStatie;
		this.km_inapoi = km_inapoi;
	}

	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}

	public String getUrmStatie() {
		return urmStatie;
	}

	public void setUrmStatie(String urmStatie) {
		this.urmStatie = urmStatie;
	}

	public int getKm_urmStatie() {
		return km_urmStatie;
	}

	public void setKm_urmStatie(int km_urmStatie) {
		this.km_urmStatie = km_urmStatie;
	}

	public int getKm_inapoi() {
		return km_inapoi;
	}

	public void setKm_inapoi(int km_inapoi) {
		this.km_inapoi = km_inapoi;
	}

	@Override
	public String toString() {
		return "Statie [oras=" + oras + ", urmStatie=" + urmStatie + ", km_urmStatie=" + km_urmStatie + ", km_inapoi="
				+ km_inapoi + "]";
	}

}
