package com.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.client.Client;
import com.cursa.Cursa;
import com.firma.Firma;
import com.rating.Rating;
import com.rezervare.StatusType;
import com.services.ReportService;
import com.services.Service;
import com.statie.Statie;
import com.traseu.Traseu;
import com.user.User;

//controllerul principal
//ajuta la legatura dintre service si frontend
@Controller
@RequestMapping(value = "/secured")
public class MainController {
	@Autowired
	protected Service mainService;

	@Autowired
	protected ReportService reportGenerator;

	// converteste automat un string in data
	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		binder.initDirectFieldAccess();

		final SimpleDateFormat dateFormat = new SimpleDateFormat("kk:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	// pagina de bun venit pentru client
	@RequestMapping(value = "/welcome-client")
	public ModelAndView welcomePage(HttpServletRequest request, Device device, HttpSession session) {
		ModelAndView model = new ModelAndView("home");
		return model;
	}

	// pagina de bun venit pentru firma, care contine si lista rezervarilor aflate
	// in pending(adica necesita atentie)
	@RequestMapping(value = "/welcome-firma")
	public ModelAndView welcomePageFirma(HttpSession session) {
		User u = (User) session.getAttribute("user");
		ModelAndView model = new ModelAndView("homeAdmin");
		model.addObject("listaRezervari", mainService.listaRezervariPending(u));
		return model;
	}

	// se afiseaza formularul de adaugare al unui traseu
	@RequestMapping(value = "/addTraseu")
	public ModelAndView addNrStatiiTraseu(HttpServletRequest request, @RequestParam("nrStatii") Integer nr) {
		ModelAndView model = new ModelAndView("addTraseu");
		Traseu traseu = new Traseu();
		List<Statie> statii = new ArrayList<>();
		for (int i = 0; i < nr; i++) {
			statii.add(new Statie());
		}
		traseu.setStatii(statii);
		model.addObject("traseu", traseu);
		return model;
	}

	// salvarea unui traseu
	// se apeleaza la apasarea butonului de salvare
	@RequestMapping(value = "/saveTraseu")
	public ModelAndView addTraseu(@ModelAttribute("traseu") Traseu traseu, HttpServletRequest request) {
		Long i = mainService.addTraseu(traseu);
		ModelAndView model = new ModelAndView("addCursa");
		model.addObject("traseuId", i);
		model.addObject("cursa", new Cursa());
		return model;
	}

	// salvarea unei curse (are loc dupa salvarea unui traseu)
	// redirectarea clientului la pagina de acasa
	@RequestMapping(value = "/saveCursa")
	public ModelAndView saveCursa(@ModelAttribute("cursa") Cursa cursa, @RequestParam("trasId") Long traseuId,
			HttpSession session) {
		User u = (User) session.getAttribute("user");
		mainService.saveCursa(u, cursa, traseuId);
		ModelAndView model = new ModelAndView("homeAdmin");
		model.addObject("listaRezervari", mainService.listaRezervariPending(u));
		return model;
	}

	// duce la pagina de cautare a unei curse
	@RequestMapping(value = "/cautaCurse")
	public ModelAndView cautaCurse(HttpServletRequest request) {
		return new ModelAndView("formularCautare");
	}

	// duce la formularul de introducere al unui numar de statii
	@RequestMapping(value = "/alegeNrStatii")
	public ModelAndView alegeNrStatii() {

		return new ModelAndView("nrStatii");
	}

	// afisarea de curse de la o statie de imbarcare la alta, in functie de data
	// daca butonul de dus-intors a fost bifat, afiseaza si cursele intors
	@GetMapping(value = "/listareCurseDupaCriterii")
	public ModelAndView listareCurseDusIntors(@RequestParam("plecare") String plecare,
			@RequestParam("destinatie") String sosire, @RequestParam("ruta") String dusintors,
			@RequestParam("date") String d, HttpSession session, Device device) {
		User u = (User) session.getAttribute("user");
		boolean validDate = mainService.checkIfDateExists(d);
		boolean capeteValide = mainService.validateCapete(plecare, sosire);
		if (validDate) {
			if (!capeteValide) {
				List<Cursa> dus = mainService.cautaCurseDupaPlecareSiVenire(plecare, sosire, d);
				List<Cursa> intors = mainService.cautaCurseDupaPlecareSiVenire(sosire, plecare, d);
				if (dus.size() == 0) {
					return new ModelAndView("errorPage");
				}
				if (dusintors.compareTo("dusintors,on") == 0 && intors != null) {
					ModelAndView model;
					if (device.isMobile()) {
						model = new ModelAndView("dusIntors");
					} else {
						model = new ModelAndView("dusIntorsDesktop");
					}
					model.addObject("tip", mainService.clientsaufirma(u));
					model.addObject("listaDus", dus);
					model.addObject("listaIntors", intors);
					model.addObject("Plecare", plecare);
					model.addObject("Sosire", sosire);
					model.addObject("dataCautarii", d);
					return model;
				} else {
					ModelAndView model;
					if (device.isNormal()) {
						model = new ModelAndView("curseDesktop");
					} else {
						model = new ModelAndView("curse");
					}
					model.addObject("tip", mainService.clientsaufirma(u));
					model.addObject("listaCurse", dus);
					model.addObject("Plecare", plecare);
					model.addObject("Sosire", sosire);
					model.addObject("dataCautarii", d);
					return model;
				}
			} else {
				ModelAndView model = new ModelAndView("home");
				model.addObject("tip", mainService.clientsaufirma(u));
				if (validDate) {
					model.addObject("dataCautarii", d);
				}
				model.addObject("errorMessage", "Introduceti va rog si statiile de plecare si sosire.");
				return model;
			}

		} else {
			ModelAndView model = new ModelAndView("home");
			model.addObject("tip", mainService.clientsaufirma(u));
			if (!capeteValide) {
				model.addObject("Plecare", plecare);

				model.addObject("Sosire", sosire);
			}
			model.addObject("errorMessageDate", "Introduceti va rog si data de plecare.");
			return model;
		}

	}

	// rezerva o cursa dupa id, plecare, sosire si data
	// redirectare la pagina de acasa
	@RequestMapping(value = "/rezervaCursa")
	public ModelAndView rezervaCursa(@RequestParam("id") Long id, @RequestParam("plecare") String plecare,
			@RequestParam("sosire") String sosire, @RequestParam("data") String d, HttpSession session) {
		User u = (User) session.getAttribute("user");
		mainService.salveazaRezervare(u, d, id, plecare, sosire);
		return new ModelAndView("home");
	}

	// deschide formularul de adaugare al ratingului pentru o firma
	@RequestMapping(value = "/rating")
	public ModelAndView rating(HttpServletRequest request, @RequestParam("id") Long id) {

		ModelAndView model = new ModelAndView("rating");
		model.addObject("rating", new Rating());
		model.addObject("rezId", id);
		return model;
	}

	// trimite ratingul, se apeleaza la apasarea butonului de trimitere
	@RequestMapping(value="/sendRating")
	public ModelAndView sendRating(@RequestParam("rezId") Long id, @ModelAttribute("rating") Rating rating) {
		mainService.addRating(id,(int)rating.getScor());
		return new ModelAndView("home");
	}

	// listeaza rezervarile pentru un client sau firma, si afiseaza pagina
	// corespunzatoare
	// dispozitivului de pe care a fost accesat
	@RequestMapping(value = "/listaRezervari")
	public ModelAndView listeazaRezervarile(HttpServletRequest request, HttpSession session, Device device) {
		User u = (User) session.getAttribute("user");
		ModelAndView model;
		if (device.isMobile()) {
			model = new ModelAndView("rezervariList");
		} else {
			model = new ModelAndView("rezervariListDesktop");
		}
		model.addObject("tip", mainService.clientsaufirma(u));
		model.addObject("listaRezervari", mainService.getListaRezervari(u));
		return model;

	}

	// returneaza toate cursele pentru o firma
	@RequestMapping(value = "/allCurse")
	public ModelAndView allCurse(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView("curseDesktop");
		User u = (User) session.getAttribute("user");
		model.addObject("listaCurse", mainService.getAllCurse(u));
		model.addObject("tip", mainService.clientsaufirma(u));
		return model;
	}

	// lista rezervarilor pentru o cursa
	@RequestMapping(value = "/listaRezervariPtCursa")
	public ModelAndView listaRezervariPtCursa(@RequestParam("id") Long id,
			@RequestParam(name = "date", required = false) String data) throws ParseException {
		ModelAndView model = new ModelAndView("rezervariListDesktop");
		model.addObject("idCursa", id);
		if (data == null || data.isBlank())
			model.addObject("listaRezervari", mainService.findRezervariByCursaId(id));
		else {
			model.addObject("listaRezervari", mainService.findRezervariByCursaIdAndData(id, data));
		}
		return model;
	}

	// anuleaza o rezervare dupa id
	@RequestMapping(value = "/cancelReservation")
	public ModelAndView cancelRezervation(@RequestParam("id") Long id, HttpSession session) {
		User u = (User) session.getAttribute("user");
		mainService.cancelReservation(u, id);
		return new ModelAndView("home");
	}

	// deschide formularul de schimbare a unei parole
	@RequestMapping(value = "/changePasswordForm")
	public ModelAndView changePasswordForm(HttpServletRequest request, HttpSession session) {
		ModelAndView model = new ModelAndView("changePassword");
		User u = (User) session.getAttribute("user");
		model.addObject("tip", mainService.clientsaufirma(u));
		return model;
	}

	// salveaza parola schimbata
	@RequestMapping(value = "/changePassword")
	public ModelAndView changePassword(HttpServletRequest request, @RequestParam("parolaNoua") String parolaNoua,
			@RequestParam("cparola") String cparola, HttpSession session) {
		User u = (User) session.getAttribute("user");
		if (parolaNoua.equals(cparola)) {
			mainService.changePassword(u, parolaNoua);
			String tip = mainService.clientsaufirma(u);
			if (tip.equals("client")) {
				return new ModelAndView("home");
			} else {

				ModelAndView model = new ModelAndView("homeAdmin");
				model.addObject("listaRezervari", mainService.listaRezervariPending(u));
				return model;
			}
		} else {
			ModelAndView model = new ModelAndView("changePassword");
			model.addObject("tip", mainService.clientsaufirma(u));
			model.addObject("errorMessage", "Parolele nu corespund");
			return model;
		}

	}

	// afiseaza detaliile contului despre un client sau firma
	@RequestMapping(value = "/showInfo")
	public ModelAndView showInfoAboutUser(HttpServletRequest request, HttpSession session) {
		User u = (User) session.getAttribute("user");
		if (mainService.showInfo(u).getClass() == Firma.class) {
			ModelAndView model = new ModelAndView("registerFirma");
			model.addObject("firma", mainService.showInfo(u));
			model.addObject("tip", mainService.clientsaufirma(u));
			return model;
		} else if (mainService.showInfo(u).getClass() == Client.class) {
			ModelAndView model = new ModelAndView("registerClient");
			model.addObject("client", mainService.showInfo(u));
			model.addObject("tip", mainService.clientsaufirma(u));
			return model;
		} else {
			return new ModelAndView("home");
		}

	}

	// afiseaza istoricul rezervarilor
	// tine de cont de dispozitivul de pe care a fost accesat
	@RequestMapping(value = "/reservationHistory")
	public ModelAndView showReservationHistory(HttpSession session, Device device) {
		User u = (User) session.getAttribute("user");
		ModelAndView model;
		if (device.isMobile()) {
			model = new ModelAndView("rezervariHistory");
		} else {
			model = new ModelAndView("rezervariHistoryDesktop");
		}
		model.addObject("listaRezervari", mainService.reservationHistory(u));
		model.addObject("rating", new Rating());
		model.addObject("tip", mainService.clientsaufirma(u));
		return model;
	}

	@RequestMapping(value = "/showFactura")
	public ModelAndView showFactura(HttpSession session) {
		User u = (User) session.getAttribute("user");
		String s = reportGenerator.generateReport(u);
		if (s.equals("No factura")) {
			mainService.genereazaFactura();
			reportGenerator.generateReport(u);
		}
		ModelAndView model = new ModelAndView("facturaPdf");
		return model;
	}

	// marcheaza o rezervare ca overdue(adica neonorata)
	@RequestMapping(value = "/overdue")
	public ModelAndView markAsOverdue(@RequestParam("id") Long id, HttpSession session, HttpServletRequest request,
			Device device) {
		mainService.markAsOverdueOrCompleted(id, StatusType.OVERDUE);
		return listeazaRezervarile(request, session, device);
	}

	// marcheaza o rezervare ca completed(adica onorata)
	@RequestMapping(value = "/completed")
	public ModelAndView markAsCompleted(@RequestParam("id") Long id, HttpSession session, HttpServletRequest request,
			Device device) {
		mainService.markAsOverdueOrCompleted(id, StatusType.COMPLETED);
		return listeazaRezervarile(request, session, device);
	}

	// plateste o rezervare
	@RequestMapping(value = "/plata")
	public ModelAndView Plata(@RequestParam("id") Long id, HttpSession session, HttpServletRequest request,
			Device device) {
		mainService.platesteRezervare(id);
		return listeazaRezervarile(request, session, device);
	}

}
