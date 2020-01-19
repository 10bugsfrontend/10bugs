package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rating.Rating;

@Repository
public interface RepoRatings extends JpaRepository<Rating, Long> {

}
