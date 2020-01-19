package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.statie.Statie;

@Repository
public interface RepoStatie extends JpaRepository<Statie, Long> {

}
