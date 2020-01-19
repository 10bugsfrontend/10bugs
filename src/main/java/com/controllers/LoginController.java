package com.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.client.Client;
import com.firma.Firma;
import com.services.LoginService;
import com.services.Service;
import com.user.User;

//clasa folosita pentru a face legatura dintre metodele din service si datele din frontend
//metodele din clase de tip controller sunt apelate automat cand valoarea de la requestMapping este introdusa in url
@Controller
public class LoginController {

	@Autowired
	private LoginService service;
	@Autowired
	protected Service mainService;

	private Map<String, User> loggedInUsers = new HashMap<>();

	// metoda de login, este apelata implicit
	// parametrul device ajuta sa vedem daca apelul a fost facut de pe un mobil sau
	// desktop
	// deschide jsp-ul corespunzator
	@RequestMapping(value = { "/", "/login" })
	public String login(Model model, Device device) {
		model.addAttribute("login", new Firma());
		if (device.isNormal()) {
			return "login";
		} else {
			return "loginMobile";
		}
	}

	// autentificarea
	// este apelata la apasarea butonului de login din formularul de login
	// verifica mai inati daca datele de autentificare corespund unui utilizator
	// deja logat
	// in caz afirmativ, un mesaj de eroare este generat
	// daca nu, pune userul in sesiune
	// daca userul este de tip client, se face o verificare suplimentara si anume
	// daca clientul are 3 sau mai multe
	// rezervari OVERDUE, contul e blocat
	// daca nu exista nicio eroare, atunci daca userul e client, este redirectat la
	// pagina de acasa pentru clienti
	// daca e firma, pentru firme
	@PostMapping(value = "/autentifyLogin")
	public ModelAndView autentifyLogin(@ModelAttribute("login") Firma fi, HttpSession session) {
		User u = new Firma(fi.getUsername(), fi.getPassword());
		ModelAndView modelerr = new ModelAndView("login");
		if (service.login(u) != null) {
			if (service.login(u).getClass() == Firma.class) {
				Firma t = (Firma) service.login(u);
				if (loggedInUsers.containsKey("user")) {
					User f = (User) session.getAttribute("user");
					if (f.getUsername().equals(u.getUsername())) {
						ModelAndView model = new ModelAndView("login");
						model.addObject("User already logged in!");
						return model;
					}
				} else {
					session.setAttribute("user", t);
					ModelAndView model = new ModelAndView("redirect:/secured/welcome-firma");
					return model;
				}
			} else if (service.login(u).getClass() == Client.class) {
				Client t = (Client) service.login(u);
				if (loggedInUsers.containsKey("user")) {
					User f = (User) session.getAttribute("user");
					if (f.getUsername().equals(u.getUsername())) {
						ModelAndView model = new ModelAndView("login");
						model.addObject("User already logged in!");
						return model;
					}
				} else {
					if (mainService.checkIfAccountIsBlocked(u).equals("Client ok")) {
						session.setAttribute("user", t);
						ModelAndView model = new ModelAndView("redirect:/secured/welcome-client");
						return model;
					} else {
						ModelAndView model = new ModelAndView("login");
						model.addObject("errorMessage", "Clientul este blocat");
						return model;
					}
				}
			}
		}

		else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("errorMessage", "Wrong credentials!");
			return model;
		}
		return modelerr;

	}

	// metoda este apelata la inregistrarea unei firme
	// preia datele introduse in formular si verifica daca exista deja o firma cu
	// aceleasi date
	// daca da, eroare
	// daca nu, salveaza noua firma in baza de date
	@RequestMapping(value = "/saveFirma")
	public ModelAndView inregistrareFirma(@ModelAttribute("firma") Firma f, HttpSession session) {
		boolean ok = mainService.inregistrareFirma(f);
		if (ok == false) {
			User u = (User) session.getAttribute("user");
			if (u == null) {
				String error = "Username deja inregistrat";
				ModelAndView model = new ModelAndView("registerFirma");
				model.addObject("firma", new Firma());
				model.addObject("errorMessage", error);
				return model;
			} else {
				mainService.updateFirma(f);
				return new ModelAndView("home");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

	// metoda este apelata la inregistrarea unui client
	// preia datele introduse in formular si verifica daca exista deja un client cu
	// aceleasi date
	// daca da, eroare
	// daca nu, salveaza noul client in baza de date
	@RequestMapping(value = "/saveClient")
	public ModelAndView inregistrareClient(@ModelAttribute("client") Client f, HttpSession session) {
		boolean ok = mainService.inregistrareClient(f);
		if (ok == false) {
			User u = (User) session.getAttribute("user");
			if (u == null) {
				String error = "Username deja inregistrat";
				ModelAndView model = new ModelAndView("registerClient");
				model.addObject("client", new Client());
				model.addObject("errorMessage", error);
				return model;
			} else {
				mainService.updateClient(f);
				return new ModelAndView("home");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	// metoda afiseaza formularul de inregistrare al unei firme
	@RequestMapping(value = "/registerFirma")
	public ModelAndView formularInregsitrareFirma() {
		ModelAndView model = new ModelAndView("registerFirma");
		model.addObject("firma", new Firma());
		return model;
	}

	// metoda afiseaza formularul de inregistrare al unui client
	// verifica daca clientul a intrat de pe mobil sau desktop si afiseaza pagina
	// corespunzatoare
	@RequestMapping(value = "/registerClient")
	public ModelAndView formularInregsitrareClient(Device device) {
		ModelAndView model;
		if (device.isNormal()) {
			model = new ModelAndView("registerClient");
		} else {
			model = new ModelAndView("registerClientMobile");
		}
		model.addObject("client", new Client());
		return model;
	}

	// delogarea
	// userul delogat este scos din sesiune si redirectat la pagina de login
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpSession session, Device device) {
		session.removeAttribute("user");
		session.invalidate();
		return login(model, device);

	}
}
