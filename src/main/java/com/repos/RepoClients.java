package com.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.Client;

//repo pentru clienti
@Repository
public interface RepoClients extends JpaRepository<Client, Long> {

	// cauta un client dupa username
	// Optional<Client>: daca nu exista client cu usernameul respectiv, ajuta ca sa
	// nu se genereze null pointer exception
	// Opttional.get() se returneaza entitatea daca exista
	Optional<Client> findByUsername(String username);

	Optional<Client> findByPhoneNumber(String phoneNumber);

	Optional<Client> findByPhoneNumberAndEmail(String phoneNumber, String email);

}
