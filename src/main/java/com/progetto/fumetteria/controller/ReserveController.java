/*

* ReserveController : controller per gestire tutti i metodi della 
* gestione di prenotazione ed anche eseguire una prenotazione
*

* version 1.0

*

* 16/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.model.Reserve;
import com.progetto.fumetteria.service.ComicService;
import com.progetto.fumetteria.service.ReserveService;
import com.progetto.fumetteria.service.UserService;

@Controller
@SessionAttributes("reserve")
@RequestMapping("/admin/reserve")
public class ReserveController {

	/*
	 * l'attributo reserveService serve per usare i metodi della classe
	 * ReserveDao
	 */
	@Autowired
	private ReserveService reserveService;

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	/*
	 * l'attributo comicService serve per usare i metodi della classe ComicDao
	 */
	@Autowired
	private ComicService comicService;

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insertReserve", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpServletRequest request) {

		model.addAttribute("users", userService.restart().find());
		model.addAttribute("comics", comicService.restart().find());

		// verifica da dove arriva la richiesta
		if (model.asMap().get("reserve") != null && request.getParameter("message") != null) {
			return new ModelAndView("admin/reserve/insert");
		} else {
			return new ModelAndView("admin/reserve/insert", "reserve", new Reserve());
		}
	}

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all reserves for the list */
		List<Reserve> reserves = reserveService.find();
		model.addAttribute("reserves", reserves);

		return "admin/reserve/reserve";
	}

	/* metodo per cancellare prenotazioni */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			/* prendi ogni prenotazione per l'id */
			for (int id : ids) {

				reserveService.delete("id", id);
			}

		} catch (HibernateException ex) {
			// crea messaggio di ritorno in caso di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Reserve exception:" + ex.getMessage());
			return "redirect:/admin/reserve";
		}
		// crea messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		return "redirect:/admin/reserve";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editReserve", method = RequestMethod.GET)
	public String getReserveUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		Reserve reserve = null;
		model.addAttribute("users", userService.restart().find());
		model.addAttribute("comics", comicService.restart().find());

		// ricupera la prenotazione appartenente a quell'Id
		if (id != null) {
			reserve = reserveService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			reserve = new Reserve();
		}
		model.addAttribute("reserve", reserve);

		return "admin/reserve/update";
	}

	/* metodo che esegue l'update */
	@RequestMapping(value = "/editReserve", method = RequestMethod.POST)
	public String getReserveUpdate(@Valid @ModelAttribute("reserve") Reserve reserve, BindingResult result,
			Model model) {
		if (reserve.getQty() <= reserve.getComic().getStock()) {
			Long id = reserve.getId().longValue();
			if (reserveService.restart().filterBy("id", reserve.getId()).pick().getStatus() != reserve.getStatus()) {
				reserveService.update(id, "status", reserve.getStatus());
				if (reserve.getStatus() == 1) {
					/*
					 * chiama metodo per inviare la mail di avviso in caso di
					 * cambiamento status prenotazione = disponibile
					 */
					this.sentEmail(reserve.getUser().getEmail());
				}
			}
			// salva la prenotazione
			reserveService.update(id, "data", reserve.getData());
			reserveService.update(id, "user", reserve.getUser());
			reserveService.update(id, "comic", reserve.getComic());
			reserveService.update(id, "value", reserve.getValue());
			// prendi nota del fumetto che deve essere riservato
			Comic comic = comicService.restart().filterBy("id", reserve.getComic().getId()).pick();
			// prendi nota della prenotazione (old)
			int oldQty = reserveService.restart().filterBy("id", reserve.getId()).pick().getQty();

			// inicializa la variabile aux
			int auxQty = 0;

			// verifica se la quantità di questo update è diversa della quantita
			// della antica prenotazione
			if (oldQty < reserve.getQty()) {
				// prendi nota della differenza tra le due, per poter sottrarre
				// questa differenza dello stock del fumetto
				auxQty = (reserve.getQty()) - oldQty;
				// esegue l'update del fumetto, sottraendo la quantità messa in
				// più nella prenotazione
				comic.setStock(comic.getStock() - auxQty);
				comicService.update(comic.getId().longValue(), "stock", comic.getStock());
			} else if (oldQty > reserve.getQty()) {// caso in cui la
													// l'amministratore cambia
													// la quantita: sara minore
													// rispetto all'antica

				// prendi nota della differenza tra le due, per poter sottrarre
				// questa differenza dello stock del fumetto
				auxQty = oldQty - (reserve.getQty());
				// esegue l'update del fumetto, aggiug. la quantità messa di
				// meno nella prenotazione
				comic.setStock(comic.getStock() + auxQty);
				comicService.update(comic.getId().longValue(), "stock", comic.getStock());
			}

			// calcola di nuovo il totale, perche per qualche motivo non viene
			// corretto dalla view (?)
			BigDecimal totalCalculator = new BigDecimal(reserve.getQty() * reserve.getComic().getValue().floatValue());
			reserveService.update(id, "qty", reserve.getQty());
			reserveService.update(id, "total", totalCalculator);
			// crea messaggio di ritorno
			model.addAttribute("reserve", new Reserve());
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Updated reserve details");
			return "redirect:/admin/reserve";
		} else {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Quantity not available!");
			return "redirect:/admin/reserve/editReserve?id=" + reserve.getId();
		}
	}

	/* metodo per eseguire l'iserimento della prenotazione */
	@RequestMapping(value = "/insertReserve", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("reserve") Reserve reserve, BindingResult result, Model model) {
		if (reserve.getQty() <= reserve.getComic().getStock()) {
			// salva la prenotazione
			reserveService.save(reserve);
			model.addAttribute("reserve", new Reserve());
			// crea messaggio di ritorno
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Saved reserve details");
			return "redirect:/admin/reserve";
		} else {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Quantity not available!");
			return "redirect:/admin/reserve/insertReserve";
		}
	}

	// metodo per inviare la mail
	public void sentEmail(String destinatario) {

		Properties props = new Properties();
		/** Parametri di conessione con il server Gmail */
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("comics.negozio@gmail.com", "progettoIS2");
			}
		});

		/** Debug for session */
		session.setDebug(true);

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("comics.negozio@gmail.com")); // mitente

			Address[] toUser = InternetAddress // destinatario
					.parse(destinatario);

			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject("Avviso disponibilità fumetto - PRENOTAZIONE");// contenuto
			message.setText(
					"Salve! I/Il suo(i) fumetto(i) è(sono) disponibile(i) al negozio per il ritiro. Cordiali saluti"); // messaggio
			/** metodo che invia il messaggio creato */
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	@RequestMapping(value = { "/reserve/ajaxSetValue", "/ajaxSetValue" }, method = RequestMethod.GET)
	public @ResponseBody String setValue(@RequestParam Integer comicId) {
		Comic comic = comicService.restart().filterBy("id", comicId).pick();

		return comic.getValue().toString();
	}

}
