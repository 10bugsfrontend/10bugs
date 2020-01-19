package com.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statie.Statie;
import com.traseu.Traseu;

@Repository
public interface RepoTraseu extends JpaRepository<Traseu, Long> {

	Traseu findByStatieInceput(String string);

}
