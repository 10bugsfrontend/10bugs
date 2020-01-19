package com.proiect;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.traseu.*;
import com.repos.*;
import com.statie.*;

@RunWith(value = SpringRunner.class)
@DataJpaTest
@Import(TestConfigurationMail.class)
public class TraseuRepositoryIntegration {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RepoTraseu repoTraseu;

	@Test
	public void testTraseu() {
		Statie a1 = new Statie("Roman", "Bacau", 40, 0);
		Statie a2 = new Statie("Bacau", "Adjud", 60, 40);
		Statie a3 = new Statie("Adjud", "Focsani", 50, 100);
		Statie a4 = new Statie("Focsani", "Buzau", 55, 150);
		Statie a5 = new Statie("Buzau", "Bucuresti", 70, 205);
		Statie a6 = new Statie(" Bucuresti", "Iasi", 0, 275);
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
		b = repoTraseu.findByStatieInceput("Roman");
		assertThat(b.getStatieInceput().equals("Roma"));
	}

}
