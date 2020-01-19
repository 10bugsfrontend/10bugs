package com.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firma.Firma;

@Repository
public interface RepoFirme extends JpaRepository<Firma, Long> {

	Optional<Firma> findByUsername(String username);

	Firma findByName(String name);

}
