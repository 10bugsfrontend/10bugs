package com.traseu;

import com.statie.Statie;
import com.utils.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Traseu extends BaseEntity {
	// relatia 1 la n, o lista de statii
	@OneToMany
	public List<Statie> statii;
	// de unde incepe
	@Column
	public String statieInceput;
	// unde se termina
	@Column
	public String statieSosire;

	public Traseu() {
		statii = new ArrayList<>();

	}

	public Traseu(List<Statie> statii) {
		super();
		this.statii = statii;
		statieInceput = statii.get(0).getOras();
		statieSosire = statii.get(statii.size() - 1).getUrmStatie();
	}

	public Traseu(Long id, List<Statie> statii, String statie_inceput, String statie_sosire) {
		super(id);
		this.statii = statii;
		this.statieInceput = statie_inceput;
		this.statieSosire = statie_sosire;
	}

	public Traseu(List<Statie> statii, String statie_inceput, String statie_sosire) {
		super();
		this.statii = statii;
		this.statieInceput = statie_inceput;
		this.statieSosire = statie_sosire;
	}

	public List<Statie> getStatii() {
		return statii;
	}

	public void setStatii(List<Statie> statii) {
		this.statii = statii;
	}

	public void addStatii(Statie s) {
		this.statii.add(s);
	}

	public String getStatieInceput() {
		return statieInceput;
	}

	public void setStatieInceput(String statie_inceput) {
		this.statieInceput = statie_inceput;
	}

	public String getStatieSosire() {
		return statieSosire;
	}

	public void setStatieSosire(String statie_sosire) {
		this.statieSosire = statie_sosire;
	}

}
