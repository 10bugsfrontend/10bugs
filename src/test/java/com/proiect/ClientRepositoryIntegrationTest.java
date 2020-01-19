package com.proiect;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.client.Client;
import com.repos.RepoClients;

// Asa testezi un repository si metodele din el
@RunWith(value = SpringRunner.class)
@DataJpaTest
@Import(TestConfigurationMail.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ClientRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private RepoClients repoClients;

	@Test
	public void whenFindByUserameThenReturnClient() {
		// given
		String parola = BCrypt.hashpw("aaa", BCrypt.gensalt(12));
		Client a = new Client("ionel", parola, "Ionel Pop", "0770000000", "xy@yahoo.com");
		entityManager.persist(a);
		entityManager.flush();

		// when
		Client found = !repoClients.findByUsername(a.getUsername()).isEmpty()
				? repoClients.findByUsername(a.getUsername()).get()
				: null;

		// then
		assertThat(found.getName()).isEqualTo(a.getName());
		assertThat(found.getPhoneNumber()).isEqualTo(a.getPhoneNumber());
	}

	public void whenFindByPhoneNumberThenReturnClient() {
		String parola = BCrypt.hashpw("aaa", BCrypt.gensalt(12));
		Client a = new Client("ionel", parola, "Ionel Pop", "0770000000", "xy@yahoo.com");
		entityManager.persist(a);
		entityManager.flush();
		Client found2 = repoClients.findByPhoneNumber(a.getPhoneNumber()).isPresent()
				? repoClients.findByPhoneNumber(a.getPhoneNumber()).get()
				: null;
		assertThat(found2.getName()).isEqualTo(a.getName());
		assertThat(found2.getEmail()).isEqualTo(a.getEmail());
	}

}
