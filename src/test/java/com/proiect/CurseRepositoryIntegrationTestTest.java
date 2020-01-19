package com.proiect;

import static org.assertj.core.api.Assertions.assertThat;

//import org.apache.commons.logging.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

import com.cursa.Cursa;
import com.statie.*;
import com.traseu.*;
import java.util.ArrayList;
import java.util.List;

import com.repos.RepoCurse;
import com.firma.*;
import java.util.Date;

@RunWith(value = SpringRunner.class)
@DataJpaTest
@Import(TestConfigurationMail.class)
public class CurseRepositoryIntegrationTestTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RepoCurse repoCurse;

	@Test
	public void whenFindAllByTraseuStatieInceputAndTraseuStatieSosire() {
		String parola = BCrypt.hashpw("aaa", BCrypt.gensalt(12));
		Firma f = new Firma("Dany", parola, "DanyTrans", "0767564879", "dan@yahoo.com", "12343231");
		entityManager.persist(f);
		Statie a1 = new Statie("Roman", "Bacau", 40, 0);
		Statie a2 = new Statie("Bacau", "Adjud", 60, 40);
		Statie a3 = new Statie("Adjud", "Focsani", 50, 100);
		Statie a4 = new Statie("Focsani", "Buzau", 55, 150);
		Statie a5 = new Statie("Buzau", "Bucuresti", 70, 205);
		Statie a6 = new Statie(" Bucuresti", "", 0, 275);
		List<Statie> lStatii = new ArrayList<>();
		lStatii.add(a1);
		lStatii.add(a2);
		lStatii.add(a3);
		lStatii.add(a4);
		lStatii.add(a5);
		lStatii.add(a6);
		for (Statie s : lStatii)
			entityManager.persist(s);
		entityManager.flush();
		Traseu b = new Traseu(lStatii);
		entityManager.persist(b);
		entityManager.flush();
		Date p1 = java.sql.Time.valueOf("8:00:00");
		Date p2 = java.sql.Time.valueOf("15:00:00");
		Cursa a = new Cursa(b, p1, p2, f, 15, 6, 7.5, 65, "Wi-Fi", "Mon,Wed,Fri,Sun");
		entityManager.persist(a);
		entityManager.flush();

		assertThat(b.getStatieInceput() == "Roman");

		List<Cursa> found = repoCurse.findAllByTraseuStatieInceputAndTraseuStatieSosire(
				a.getTraseu().getStatieInceput(), a.getTraseu().getStatieSosire());
		assertThat(found.size() == 1);
		assertThat(found.get(0).getCapacitate() == 15);
		List<Cursa> found2 = repoCurse.findAllCurseByFirma(f);
		assertThat(found2.get(0).getFirma() == a.getFirma());

	}

}
