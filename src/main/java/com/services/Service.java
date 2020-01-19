package com.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.client.Client;
import com.cursa.Cursa;
import com.factura.Factura;
import com.firma.Firma;
import com.rating.Rating;
import com.repos.*;
import com.rezervare.Rezervare;
import com.rezervare.StatusType;
import com.statie.Statie;
import com.traseu.Traseu;
import com.user.User;
import java.time.DayOfWeek;

//clasa principala service
@Component
public class Service {
	@Autowired
	protected RepoClients repoClients;
	@Autowired
	protected RepoCurse repoCurse;
	@Autowired
	protected RepoFirme repoFirme;
	@Autowired
	protected RepoRatings repoRatings;
	@Autowired
	protected RepoStatie repoStatii;
	@Autowired
	protected RepoTraseu repoTrasee;
	@Autowired
	protected RepoRezervari repoRezervari;
	@Autowired
	protected RepoFactura repoFacturi;
	// bean folosit pentru trimiterea de email
	@Autowired
	protected JavaMailSender javaMailSender;

	// adauga un traseu in baza de date
	// returneaza id-ul traseului
	public Long addTraseu(Traseu traseu) {
		List<Statie> ss = traseu.getStatii();
		for (int i = 0; i < ss.size(); i++) {
			Statie s2 = repoStatii.save(ss.get(i));
			ss.set(i, s2);
		}
		traseu.setStatieInceput(ss.get(0).getOras());
		traseu.setStatieSosire(ss.get(ss.size() - 1).getUrmStatie());
		Traseu t = repoTrasee.save(traseu);
		return t.getId();

	}

	// primeste o data sub forma unui string si verifica ce zi a saptamanii este si
	// returneaza
	// numele scurt al acelei zile in engleza (Mon,Tue etc)
	protected String checkwhatDayOfWeekIs(String d) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		formatter = formatter.withLocale(Locale.US);
		LocalDate ldate = LocalDate.parse(d, formatter);
		DayOfWeek dow = ldate.getDayOfWeek();
		String output = dow.getDisplayName(TextStyle.SHORT, Locale.US);
		return output;
	}

	// cauta cursele care pleaca (chiar si ca statie intermediara) din
	// statie_inceput si ajung in statie_sosire
	// intr-o anumita data (d) data ca string
	// returneaza lista de curse
	public List<Cursa> cautaCurseDupaPlecareSiVenire(String statie_inceput, String statie_sosire, String d) {
		String data = d;
		d = checkwhatDayOfWeekIs(d);
		String da = "%" + d + "%";
		List<Cursa> curse = repoCurse.findAllByTraseuStatii(statie_inceput, statie_sosire, da);
		try {
			curse = setCorectHourForCurse(curse, statie_inceput, statie_sosire, data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return curse;
	}

	// returneaza lista de rezervari active sau anulate pentru un utilizator
	// daca userul e client, returneaza pentru el
	// daca e firma, returneaza toate rezervarile pt firma
	// null daca nu e nici client nici firma
	public List<Rezervare> getListaRezervari(User u) {
		Optional<Client> c = repoClients.findByUsername(u.getUsername());
		StatusType[] s = { StatusType.OVERDUE, StatusType.COMPLETED, StatusType.PENDING };
		if (c.isPresent()) {
			List<Rezervare> rez = repoRezervari.findAllByClientAndStatusNotIn(c.get(), s);
			return modificaOraListaRezervari(rez);
		} else {
			Optional<Firma> f = repoFirme.findByUsername(u.getUsername());
			if (f.isPresent()) {
				List<Rezervare> rez = repoRezervari.findAllByCursaFirmaAndStatusNotIn(f.get(), s);
				return modificaOraListaRezervari(rez);
			}
		}
		return null;
	}

	// adapteaza ora de plecare si de sosire precum si pretul pentru o rezervare
	// tine cont de ora de plecare si de sosire ale cursei din capete si calculeaza
	// in functie de numarul de
	// kilometri parcursi
	// de asemenea adaptaeza si pretul unei rezervari
	protected Rezervare calculateDataForRezervare(Rezervare r) {
		Cursa c = r.getCursa();
		String plecare = r.getPlecare();
		String sosire = r.getSosire();
		Date d1 = c.getOra_de_plecare();
		Date d2 = c.getOra_de_sosire();
		int pret = c.getPret();
		long diff = Math.abs(d1.getTime() - d2.getTime());
		long minute = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
		int km_total_final = 0;
		int km_total = 0;
		int km_de_scazut = 0;
		Traseu t = c.getTraseu();
		int p = -1;
		int s = -1;
		for (Statie st : t.getStatii()) {
			km_total_final += st.getKm_urmStatie();
		}
		for (int i = 0; i < t.getStatii().size(); i++) {

			if (p == -1 && t.getStatii().get(i).getOras().equals(plecare)) {
				p = i;
			} else if (p == -1) {
				km_de_scazut += t.getStatii().get(i).getKm_urmStatie();
			}
			if (s == -1 && t.getStatii().get(i).getUrmStatie().equals(sosire) && p <= i) {
				s = i;
			}
			if (i >= p && (s == -1 || s == i)) {
				km_total += t.getStatii().get(i).getKm_urmStatie();
			}
		}
		if (s < p) {

		} else {
			double timpPerKm = (double) minute / km_total_final;
			double pretPerKm = (double) pret / km_total_final;
			double plec = km_de_scazut * timpPerKm;
			double sos = (km_total) * timpPerKm;
			int pretFinal = (int) ((km_total - km_de_scazut) * pretPerKm);
			Calendar calen = Calendar.getInstance();
			calen.setTime(d1);
			Date d11 = new Date((long) (calen.getTimeInMillis() + (plec * 60000)));
			Date d22 = new Date((long) (calen.getTimeInMillis() + (sos * 60000)));
			c.setOra_de_plecare(d11);
			c.setOra_de_sosire(d22);
			c.setOcupate(nrLcouriOcupatePerCursa(c, r.getPlecare(), r.getSosire(), r.getData()));
			c.setPret(pretFinal);
			r.setCursa(c);
			return r;
		}
		return null;
	}

	// pentru fiecare rezervare din lista de rezervari apeleaza
	// calculateDataForRezervare
	// adauga rezervarea returnata unei liste noi
	// returneaza lista noua
	protected List<Rezervare> modificaOraListaRezervari(List<Rezervare> rez) {
		List<Rezervare> rezFinal = new ArrayList<>();
		for (Rezervare r : rez) {
			Rezervare re = calculateDataForRezervare(r);
			if (re != null) {
				rezFinal.add(re);
			}
		}
		return rezFinal;
	}

	// salveaza o cursa in baza de date
	// traseul cursei il gaseste si seteaza dupa id
	public void saveCursa(User u, Cursa cursa, Long traseuId) {
		Firma f = repoFirme.findByUsername(u.getUsername()).get();
		cursa.setFirma(f);
		cursa.setTraseu(repoTrasee.findById(traseuId).get());
		Cursa c = repoCurse.save(cursa);
		List<Cursa> curse = f.getCurse();
		curse.add(c);
		f.setCurse(curse);
		repoFirme.save(f);

	}

	// returneaza toate cursele pentru o firma anume
	public List<Cursa> getAllCurse(User u) {
		Optional<Firma> firma = repoFirme.findByUsername(u.getUsername());
		if (firma.isPresent()) {
			List<Cursa> curse = repoCurse.findAllCurseByFirma(firma.get());
			return curse;
		}
		return null;
	}

	// metoda de inregistrare a unei firme
	// returneaza true daca firma a fost salvata cu succes sau false daca firma cu
	// acelasi username exista deja
	public boolean inregistrareFirma(Firma f) {
		Optional<Firma> firma = repoFirme.findByUsername(f.getUsername());
		if (!firma.isPresent()) {
			String parola = BCrypt.hashpw(f.getPassword(), BCrypt.gensalt(12));
			f.setPassword(parola);
			repoFirme.save(f);
			return true;
		} else {
			return false;
		}

	}

	// adapteaza ora de plecare, ora de sosire, pretul si nr de locuri disponibile
	// pentru o anumita cursa
	// in functie de numarul de km
	// care trece (chiar si intermediar) printr-o anumita statie si ajunge in alta
	// intr-o anumita zi
	public Cursa calculateDataForCursa(Cursa c, String plecare, String sosire, Date dat) {
		Date d1 = c.getOra_de_plecare();
		Date d2 = c.getOra_de_sosire();
		int pret = c.getPret();
		long diff = Math.abs(d1.getTime() - d2.getTime());
		long minute = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
		int km_total_final = 0;
		int km_total = 0;
		int km_de_scazut = 0;
		Traseu t = c.getTraseu();
		int p = -1;
		int s = -1;
		// calculeaza km totali
		for (Statie st : t.getStatii()) {
			km_total_final += st.getKm_urmStatie();
		}
		// calculeaza km intre cele doua statii intermediare
		for (int i = 0; i < t.getStatii().size(); i++) {

			if (p == -1 && t.getStatii().get(i).getOras().equals(plecare)) {
				p = i;
			} else if (p == -1) {
				km_de_scazut += t.getStatii().get(i).getKm_urmStatie();
			}
			if (s == -1 && t.getStatii().get(i).getUrmStatie().equals(sosire) && p <= i) {
				s = i;
			}
			if (i >= p && (s == -1 || s == i)) {
				km_total += t.getStatii().get(i).getKm_urmStatie();
			}
		}
		if (s < p) {

		} else {
			double timpPerKm = (double) minute / km_total_final;
			double pretPerKm = (double) pret / km_total_final;
			int pretFinal = (int) ((km_total - km_de_scazut) * pretPerKm);
			double plec = km_de_scazut * timpPerKm;
			double sos = (km_total) * timpPerKm;
			Calendar calen = Calendar.getInstance();
			calen.setTime(d1);
			Date d11 = new Date((long) (calen.getTimeInMillis() + (plec * 60000)));
			Date d22 = new Date((long) (calen.getTimeInMillis() + (sos * 60000)));
			c.setOra_de_plecare(d11);
			c.setOra_de_sosire(d22);
			c.setOcupate(nrLcouriOcupatePerCursa(c, plecare, sosire, dat));
			c.setPret(pretFinal);
			return c;
		}
		return null;
	}

	// pentru fiecare cursa din lista, apeleaza metoda calculateDataForCursa si
	// adauga cursele returnate in
	// o noua lista
	// returneaza noua lista
	public List<Cursa> setCorectHourForCurse(List<Cursa> curse, String plecare, String sosire, String data)
			throws ParseException {
		List<Cursa> curse2 = new ArrayList<>();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dat = format.parse(data);
		for (Cursa c : curse) {

			Cursa c2 = calculateDataForCursa(c, plecare, sosire, dat);
			if (c2 != null) {
				curse2.add(c2);
			}
		}

		return curse2;
	}

	// metoda de inregistrare pentru client
	// daca un client cu acelasi username exista, returneaza fals
	// daca nu, salveaza clientul nou in baza de date si returneaza true
	public boolean inregistrareClient(Client f) {
		Optional<Client> client = repoClients.findByUsername(f.getUsername());
		if (!client.isPresent()) {
			String parola = BCrypt.hashpw(f.getPassword(), BCrypt.gensalt(12));
			f.setPassword(parola);
			repoClients.save(f);
			return true;
		} else {
			return false;
		}

	}

	// functie de schimbare a parolei pentru client sau firma
	public void changePassword(User u, String parola) {
		Optional<Client> client = repoClients.findByUsername(u.getUsername());
		if (client.isPresent()) {
			Client c = client.get();
			parola = BCrypt.hashpw(parola, BCrypt.gensalt(12));
			c.setPassword(parola);
			repoClients.save(c);
		} else {
			Optional<Firma> firma = repoFirme.findByUsername(u.getUsername());
			if (firma.isPresent()) {
				Firma f = firma.get();
				parola = BCrypt.hashpw(parola, BCrypt.gensalt(12));
				f.setPassword(parola);
				repoFirme.save(f);
			}
		}
	}

	// metoda care afiseaza detaliile despre contul propriu al unui client sau firme
	// in functie de tipul userului
	public User showInfo(User u) {
		Optional<Client> client = repoClients.findByUsername(u.getUsername());
		if (client.isPresent()) {
			Client c = client.get();
			c.setPassword("");
			return c;
		} else {
			Optional<Firma> firma = repoFirme.findByUsername(u.getUsername());
			if (firma.isPresent()) {
				Firma c = firma.get();
				c.setPassword("");
				return c;
			}
		}
		return null;
	}

	// cauta o cursa dupa id si returneaza lista de rezervari pentru acea cursa
	public List<Rezervare> findRezervariByCursaId(Long id) {
		Optional<Cursa> c = repoCurse.findById(id);
		return repoRezervari.findAllByCursa(c.get());
	}

	// determina daca un user e client sau firma si returneaza un string
	// corespunzator
	public String clientsaufirma(User u) {
		Optional<Client> client = repoClients.findByUsername(u.getUsername());
		if (client.isPresent()) {
			return "client";
		} else {
			return "firma";
		}
	}

	// anuleaza o rezervare dupa id
	public void cancelReservation(User u, Long id) {

		Rezervare r = repoRezervari.findById(id).get();
		r.setStatus(StatusType.CANCELLED);
		repoRezervari.save(r);
	}

	// verifica daca un cont (de client) e blocat sau nu
	// returneaza un string care reflecta acest lucru
	public String checkIfAccountIsBlocked(User u) {
		Optional<Client> c = repoClients.findByUsername(u.getUsername());
		if (c.isPresent()) {
			List<Rezervare> r = repoRezervari.findAllByClientAndStatus(c.get(), StatusType.OVERDUE);
			if (r.size() >= 3) {
				return "Clientul este blocat";
			}
		}
		return "Client ok";
	}

	// verifica daca o data e dupa data curenta
	protected boolean validateDate(Date d) {
		if (d.after(new Date()))
			return true;
		return false;
	}

	// valideaza plecarea sau destinatia e nula sau " "(goala)
	public boolean validateCapete(String plecare, String dest) {
		return (plecare.isEmpty() || dest.isEmpty());
	}

	// verifica daca stringul care va fi transformat in data e gol sau nu
	public boolean checkIfDateExists(String d) {
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		Date data = null;
		try {
			if (!d.isEmpty()) {
				data = date.parse(d);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return data != null;
	}

	// returneaza istoricul rezervarilor pt un user
	// daca userul e client, returneaza pentru el
	// daca e firma, returneaza pentru firma respectiva
	public List<Rezervare> reservationHistory(User u) {
		Optional<Client> c = repoClients.findByUsername(u.getUsername());
		if (c.isPresent()) {
			return repoRezervari.findAllByClient(c.get());
		} else {
			Optional<Firma> f = repoFirme.findByUsername(u.getUsername());
			if (f.isPresent()) {
				return repoRezervari.findAllByCursaFirma(f.get());
			}
		}
		return null;
	}

	// marcheaza o rezervare overdue(neonorata) sau completa
	public void markAsOverdueOrCompleted(Long id, StatusType status) {
		Rezervare r = repoRezervari.findById(id).get();
		r.setStatus(status);
		repoRezervari.save(r);

	}

	// cauta toate rezervarile a caror data e dupa data curenta si le trece in
	// statusul pending
	// adica marcheaza ca necesita atentie din partea firme
	public void checkForPendingReservations() {
		Date d = new Date();
		List<Rezervare> rezervari = repoRezervari.findAllByDataBeforeAndStatus(d, StatusType.ACTIVE);
		for (Rezervare r : rezervari) {
			r.setStatus(StatusType.PENDING);
			repoRezervari.save(r);
		}
	}

	// lista tuturor rezervarilor aflate in pending pentru o firma
	public List<Rezervare> listaRezervariPending(User u) {
		checkForPendingReservations();
		Optional<Firma> f = repoFirme.findByUsername(u.getUsername());
		if (f.isPresent()) {
			return repoRezervari.findAllByCursaFirmaAndStatus(f.get(), StatusType.PENDING);
		} else
			return null;
	}

	// salveaza o rezervare pentru un client
	// daca clientul exista deja, se salveaza in contul lui
	// daca nu exista, se creeaza un client nou
	public void salveazaRezervare(String name, String phone, String email, String d, Long id, String plecare,
			String sosire) {
		Client client = new Client(null, null, name, phone, email);
		Optional<Client> o = repoClients.findByPhoneNumberAndEmail(client.getPhoneNumber(), client.getEmail());
		Client c = new Client();
		if (o.isPresent()) {
			c = o.get();
		} else {
			c = repoClients.save(client);
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (validateDate(date)) {
			Cursa cs = repoCurse.findById(id).get();
			cs.setOcupate(cs.getOcupate() + 1);
			cs = repoCurse.save(cs);
			Rezervare r = new Rezervare(c, cs, date, plecare, sosire, false, false);
			r.setStatus(StatusType.ACTIVE);
			r = repoRezervari.save(r);
			sendEmail(r, c);
		}

	}

	// adauga un rating la o firma, folosindu-se de id-ul rezervarii
	public void addRating(Long id, Integer rating) {
		Rezervare r = repoRezervari.findById(id).get();
		Firma f = r.getCursa().getFirma();
		Rating ra = f.getRating();
		r.setIsRated(true);
		repoRezervari.save(r);
		if (ra != null) {
			double t = ra.getScor() * ra.getNr_de_ratings();
			ra.setNr_de_ratings(ra.getNr_de_ratings() + 1);
			ra.setScor((t + rating) / ra.getNr_de_ratings());
		} else {
			ra = new Rating(rating, 1);
			f.setRating(ra);
		}
		repoRatings.save(ra);

	}

	// salveaza o rezervare
	public void salveazaRezervare(User u, String d, Long id, String plecare, String sosire) {
		Optional<Client> client = repoClients.findByUsername(u.getUsername());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = formatter.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Cursa cs = repoCurse.findById(id).get();
		cs.setOcupate(cs.getOcupate() + 1);
		cs = repoCurse.save(cs);
		Rezervare r = new Rezervare(client.get(), cs, date, plecare, sosire, false, false);
		r.setStatus(StatusType.ACTIVE);
		r = repoRezervari.save(r);
		sendEmail(r, client.get());

	}

	// schimba atributul explicit isPaid in true pentru o rezervare data prin id
	public void platesteRezervare(Long id) {
		Optional<Rezervare> r = repoRezervari.findById(id);
		if (r.isPresent()) {
			Rezervare re = r.get();
			re.setPaid(true);
			repoRezervari.save(re);
		}
	}

	// numara locurile ocupate intr-o cursa, in functie de statia de imbarcare si de
	// coborare la o anumita data
	public int nrLcouriOcupatePerCursa(Cursa c, String plecare, String sosire, Date data) {
		List<Rezervare> rezervari = repoRezervari.findAllByCursaAndDataAndStatus(c, data, StatusType.ACTIVE);
		int locuriOcupate = 0;
		for (Rezervare r : rezervari) {
			if (plecare != null && sosire != null) {
				if (r.getPlecare().equals(plecare) && r.getSosire().equals(sosire)) {
					locuriOcupate++;
				}
			}
		}
		return locuriOcupate;
	}

	// trimite un email clientului la efectuarea unei rezervari
	// se genereaza un cod unic de rezervare pe care clientul trebuie sa il prezinte
	// soferului in ziua imbarcarii
	public void sendEmail(Rezervare r, Client c) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("mpaula937@gmail.com");// c.getEmail()
		String d = new SimpleDateFormat("dd-MM-yyyy").format(r.getData());
		msg.setSubject("Rezervarea dvs pentru data de " + d);
		Random ra = new Random(6);
		int codRezervare = ra.nextInt(999999);
		msg.setText("Rezervare dvs in data de " + d + " de la " + r.getPlecare() + " la " + r.getSosire()
				+ " a fost efectuata." + "\n Codul rezervarii este " + codRezervare);
		javaMailSender.send(msg);
	}

	// modifica datele pt o firma si salveaza modificarea in baza de date
	public void updateFirma(Firma f) {
		Optional<Firma> firma = repoFirme.findByUsername(f.getUsername());
		if (firma.isPresent()) {
			Firma fupdate = firma.get();
			fupdate.setCui(f.getCui());
			fupdate.setEmail(f.getEmail());
			fupdate.setName(f.getName());
			fupdate.setPhoneNumber(f.getPhoneNumber());
			fupdate.setPassword(BCrypt.hashpw(f.getPassword(), BCrypt.gensalt(12)));
			repoFirme.save(fupdate);
		}
	}

	// modifica datele pentru un client
	public void updateClient(Client c) {
		Optional<Client> client = repoClients.findByUsername(c.getUsername());
		if (client.isPresent()) {
			Client fupdate = client.get();
			fupdate.setEmail(c.getEmail());
			fupdate.setName(c.getName());
			fupdate.setPhoneNumber(c.getPhoneNumber());
			if (!c.getPassword().isBlank())
				fupdate.setPassword(BCrypt.hashpw(c.getPassword(), BCrypt.gensalt(12)));
			repoClients.save(fupdate);
		}
	}

	// genereaza factura la un anumit timp stabilit (o data pe luna)
	@Scheduled(cron = "0 05 09 * * *")
	public void genereazaFactura() {
		List<Firma> firme = repoFirme.findAll();
		for (Firma f : firme) {
			Factura ultimaFact = repoFacturi.findTopByFirmaOrderByDataGenerariiDesc(f);
			long nr;
			List<Rezervare> rezervari;
			if (ultimaFact != null) {
				nr = (new Date()).getTime() - ultimaFact.getDataGenerarii().getTime();
				rezervari = repoRezervari.findAllByCursaFirmaAndDataBetween(f, ultimaFact.getDataGenerarii(),
						new Date());
			} else {
				Rezervare r = repoRezervari.findTopByCursaFirmaAndStatusOrderByDataAsc(f, StatusType.COMPLETED);
				nr = (new Date()).getTime() - r.getData().getTime();
				rezervari = repoRezervari.findAllByCursaFirmaAndDataBetween(f, r.getData(), new Date());
			}
			int nrZile = (int) TimeUnit.DAYS.convert(nr, TimeUnit.MILLISECONDS);

			rezervari = modificaOraListaRezervari(rezervari);
			double suma = 0;
			for (Rezervare r : rezervari) {
				suma += 0.1 * r.getCursa().getPret();
			}
			Factura fact = new Factura(f, new Date(), suma, nrZile);
			repoFacturi.save(fact);
		}
	}

	public List<Rezervare> findRezervariByCursaIdAndData(Long id, String data) throws ParseException {
		Optional<Cursa> c = repoCurse.findById(id);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d = format.parse(data);
		List<Rezervare> rezervari = repoRezervari.findAllByCursaAndData(c.get(), d);
		rezervari = modificaOraListaRezervari(rezervari);
		return rezervari;
	}

}
