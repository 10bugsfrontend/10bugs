package com.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import com.factura.Factura;
import com.firma.Firma;
import com.repos.RepoFactura;
import com.repos.RepoFirme;
import com.repos.RepoRezervari;
import com.rezervare.Rezervare;
import com.rezervare.StatusType;
import com.user.User;

@Component
public class ReportService {

	@Autowired
	private RepoFactura repoFacturi;

	@Autowired
	private RepoRezervari repoRezervari;

	@Autowired
	private RepoFirme repoFirme;

	@Autowired
	private Service mainService;

	public String generateReport(User u) {
		try {
			Optional<Firma> f = repoFirme.findByUsername(u.getUsername());
			if (f.isPresent()) {
				Factura fact = repoFacturi.findTopByFirmaOrderByDataGenerariiDesc(f.get());
				if (fact != null) {
					List<Rezervare> rezerrvari = repoRezervari.findAllByDataBetweenAndStatus(fact.getDataGenerarii(),new Date(), StatusType.COMPLETED);
					

					rezerrvari = mainService.modificaOraListaRezervari(rezerrvari);
					String reportPath = "C:\\Users\\marso\\Documents\\workspace-\\autobugs\\src\\main\\java\\com\\utils\\";

					// Compile the Jasper report from .jrxml to .japser
					JasperReport jasperReport = JasperCompileManager
							.compileReport(reportPath + "\\raportFacturi.jrxml");

					// Get your data source
					JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(rezerrvari);

					// Add parameters
					Map<String, Object> parameters = new HashMap<>();

					parameters.put("createdBy", "Autobugs");
					parameters.put("dataGenerarii", new Date());
					parameters.put("numeFirma", "Danny");
					parameters.put("sumaFactura", fact.getSuma());

					// Fill the report
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
							jrBeanCollectionDataSource);

					// Export the report to a PDF file
					JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\raportFacturi" + ".pdf");

					System.out.println("Done");

					return "Report successfully generated @path= " + reportPath;
				}
			}
			return "No factura";
		} catch (Exception e) {
			e.printStackTrace();
			return "Error--> check the console log";
		}

	}

}
