package com.proiect;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import com.firma.*;
import com.repos.*;

@RunWith(value = SpringRunner.class)
@DataJpaTest
@Import(TestConfigurationMail.class)
public class FirmeRepositoryIntegration {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RepoFirme repoFirme;

	@Test
	public void whenFindByNameReturnFirma() {
		String parola = BCrypt.hashpw("aaa", BCrypt.gensalt(12));
		Firma f = new Firma("DanyTur", parola, "Dany", "0789656432", "da@yahoo.com", "23453211");
		entityManager.persist(f);
		entityManager.flush();

		Firma found = repoFirme.findByName(f.getName());
		assertThat(found.getCui() == f.getCui());
	}

	public void whenFindByUserNameReturnFirma() {
		String parola = BCrypt.hashpw("aaa", BCrypt.gensalt(12));
		Firma f = new Firma("DanyTur", parola, "Dany", "0789656432", "da@yahoo.com", "23453211");
		entityManager.persist(f);
		entityManager.flush();
		assertThat(f.getPhoneNumber().charAt(0) == '1');
		Firma found = repoFirme.findByUsername(f.getUsername()).isPresent()
				? repoFirme.findByUsername(f.getUsername()).get()
				: null;
		assertThat(found.getEmail() != f.getEmail());

	}

}
