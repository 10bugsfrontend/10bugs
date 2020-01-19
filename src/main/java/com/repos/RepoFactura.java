package com.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.factura.Factura;
import com.firma.Firma;

public interface RepoFactura extends JpaRepository<Factura, Long> {

	// gaseste cea mai recenta factura generata pentru o firma
	Factura findTopByFirmaOrderByDataGenerariiDesc(Firma f);

}
