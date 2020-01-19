package com.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cursa.Cursa;
import com.firma.Firma;

@Repository
public interface RepoCurse extends JpaRepository<Cursa, Long> {
	// functie care are query personalizat
	// returneaza o lista de curse care urmeaza un traseu intr-o anumita zi
	// queryul gaseste si cursele a carui traseu nu porneste neaparat din statia de
	// inceput si nu se sfarseste
	// (gaseste si daca statiile sunt intermediare )
	@Query(value = "with CTE AS\r\n" + "(SELECT c.id,\r\n" + "		c.capacitate,\r\n" + "		c.ocupate,\r\n"
			+ "		c.frecventa, \r\n" + "		c.ora_de_plecare,\r\n" + "		c.ora_de_sosire,\r\n"
			+ "		c.pret,\r\n" + "		c.facilitati,\r\n" + "		c.durata, \r\n			" + "		f.name,\r\n"
			+ "		t.statie_inceput,\r\n" + "		t.statie_sosire,\r\n" + "		c.traseu_id\r\n"
			+ "  FROM [dbo].[cursa] c\r\n" + "  inner join dbo.traseu_statii ts on c.traseu_id=ts.traseu_id\r\n"
			+ "   inner join traseu t on t.id=c.traseu_id\r\n" + "  inner join dbo.statie s on s.id=ts.statii_id\r\n"
			+ "  inner join dbo.firma_curse fc on fc.curse_id=c.id\r\n"
			+ "  inner join dbo.firma f on f.id=fc.firma_id\r\n" + "\r\n"
			+ "where s.urm_statie= :statieSosire and c.frecventa like :zi  and c.capacitate > c.ocupate)\r\n"
			+ "SELECT *\r\n" + "  FROM CTE\r\n" + "  inner join dbo.traseu_statii ts on CTE.traseu_id=ts.traseu_id\r\n"
			+ "   inner join traseu t on t.id=CTE.traseu_id\r\n" + "  inner join dbo.statie s on s.id=ts.statii_id\r\n"
			+ "  inner join dbo.firma_curse fc on fc.curse_id=CTE.id\r\n"
			+ "  inner join dbo.firma f on f.id=fc.firma_id\r\n"
			+ "where s.oras= :statieInceput and CTE.frecventa like :zi and CTE.capacitate > CTE.ocupate ", nativeQuery = true)
	List<Cursa> findAllByTraseuStatii(@Param("statieInceput") String statie_inceput,
			@Param("statieSosire") String statie_sosire, @Param("zi") String zi);

	// gasete toate cursele dupa firma
	List<Cursa> findAllCurseByFirma(Firma firma);

	List<Cursa> findAllByTraseuStatieInceputAndTraseuStatieSosire(String plecare, String sosire);
}
