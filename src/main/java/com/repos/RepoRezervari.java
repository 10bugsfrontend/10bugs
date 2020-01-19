package com.repos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.client.Client;
import com.cursa.Cursa;
import com.firma.Firma;
import com.rezervare.Rezervare;
import com.rezervare.StatusType;

public interface RepoRezervari extends JpaRepository<Rezervare, Long> {

	List<Rezervare> findAllByClient(Client c);

	// cauta rezervarile dupa firma
	List<Rezervare> findAllByCursaFirma(Firma firma);

	List<Rezervare> findAllByCursa(Cursa cursa);

	// cauta rezervarile dupa client si care nu au statusul in lista de statusul
	List<Rezervare> findAllByClientAndStatusNotIn(Client client, StatusType[] s);

	// cauta rezervarile dupa firma si care nu au statusul in lista de statusul
	List<Rezervare> findAllByCursaFirmaAndStatusNotIn(Firma firma, StatusType[] s);

	// gasete o rezervare dupa cursa si client
	Optional<Rezervare> findByCursaAndClient(Cursa cursa, Client client);

	List<Rezervare> findAllByClientAndStatus(Client client, StatusType overdue);

	// gaseste toate rezervarile inainte de o anumita data
	List<Rezervare> findAllByDataBefore(Date d);

	// gaseste toate rezervarile dupa firma inainte de o anumita data
	List<Rezervare> findAllByCursaFirmaAndDataBefore(Firma firma, Date d);

	List<Rezervare> findAllByCursaFirmaAndStatus(Firma firma, StatusType pending);

	// gaseste toate rezervarile inainte de o data si cu un anumit status
	List<Rezervare> findAllByDataBeforeAndStatus(Date d, StatusType active);

	List<Rezervare> findAllByCursaAndData(Cursa c, Date data);

	List<Rezervare> findAllByCursaAndDataAndStatus(Cursa c, Date data, StatusType active);

	List<Rezervare> findAllByCursaFirmaAndDataBetween(Firma f, Date dataGenerarii, Date date);

	List<Rezervare> findAllByDataBetweenAndStatus(Date dataGenerarii, Date date, StatusType completed);

	Rezervare findTopByCursaFirmaAndStatusOrderByDataAsc(Firma f, StatusType completed);

}
