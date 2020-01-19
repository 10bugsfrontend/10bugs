package com.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.client.Client;
import com.firma.Firma;
import com.repos.RepoClients;
import com.repos.RepoFirme;
import com.user.User;

//clasa e folosita pentru functionalitatile de conectare si deconectare
@Service
public class LoginService {
	// adnotarea @Autowired initializeaza automat la rulare atributele adnotate
	// astfel
	@Autowired
	private RepoFirme repoFirme;
	@Autowired
	private RepoClients repoClienti;

	// functia de login
	// primeste un parametru de tip user
	// verifica mai intai daca acel user e firma, cautandu-l dupa username in
	// repoFirme
	// daca este, verifica daca parolele se potrivesc, iar in caz afirmativ, logarea
	// are loc
	// daca nu, verifica daca userul e client si in caz afirmativ verifica daca
	// parolele se potrivesc
	// daca da, logarea are loc
	// daca nu, returneaza null
	public User login(User u) {
		Optional<Firma> y = repoFirme.findByUsername(u.getUsername());
		Firma fir = new Firma();
		Client cr = new Client();
		if (y.isPresent()) {
			fir = y.get();
			boolean i = BCrypt.checkpw(u.getPassword(), fir.getPassword());
			if (i) {
				return fir;
			}

		} else {
			Optional<Client> c = repoClienti.findByUsername(u.getUsername());
			if (c.isPresent()) {
				cr = c.get();
				boolean i = BCrypt.checkpw(u.getPassword(), cr.getPassword());
				if (i) {
					return cr;
				}
			}
		}

		return null;

	}
}
