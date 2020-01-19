package com.rating;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import com.utils.BaseEntity;

//clasa de rating, corespunzatoare unei firme, o firma are un rating
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Rating extends BaseEntity {

	// media notelor
	@Column
	public double scor;

	// contorul nr de note
	@Column
	public int nr_de_ratings;

	public Rating() {
		super();
	}

	public Rating(double scor, int nr_de_ratings) {
		super();
		this.scor = scor;
		this.nr_de_ratings = nr_de_ratings;
	}

	public Rating(Long id, double scor, int nr_de_ratings) {
		super(id);
		this.scor = scor;
		this.nr_de_ratings = nr_de_ratings;
	}

	public double getScor() {
		return scor;
	}

	public void setScor(double scor) {
		this.scor = scor;
	}

	public int getNr_de_ratings() {
		return nr_de_ratings;
	}

	public void setNr_de_ratings(int nr_de_ratings) {
		this.nr_de_ratings = nr_de_ratings;
	}

}
