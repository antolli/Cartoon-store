/*

* ComicFrontController : controller per gestire le funzionalità in relazione con un fumetto -> front-office
*

* version 1.0

*

* 25/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.math.BigDecimal;
import java.rmi.server.ServerCloneException;
import java.util.Date;
import java.util.HashSet;
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
import javax.servlet.ServletContext;
import javax.servlet.ServletSecurityElement;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.ComicAuthor;
import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.model.Note;
import com.progetto.fumetteria.model.Notice;
import com.progetto.fumetteria.model.Reserve;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.ComicService;
import com.progetto.fumetteria.service.GenreService;
import com.progetto.fumetteria.service.NoteService;
import com.progetto.fumetteria.service.NoticeService;
import com.progetto.fumetteria.service.ReserveService;
import com.progetto.fumetteria.service.UserService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@RequestMapping("/comic")
public class ComicFrontController {

	/*
	 * l'attributo comicService serve per usare i metodi della classe ComicDao
	 */
	@Autowired
	private ComicService comicService;

	/*
	 * l'attributo servletContext serve per usare i metodi che abbiamo bisogno
	 * per carica l' immagine di copertina
	 */
	@Autowired
	protected ServletContext servletContext;

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	/*
	 * l'attributo reserveService serve per usare i metodi della classe
	 * ReserveDao
	 */
	@Autowired
	private ReserveService reserveService;

	/* l'attributo noteService serve per usare i metodi della classe NoteDao */
	@Autowired
	private NoteService noteService;

	/*
	 * l'attributo noticeService serve per usare i metodi della classe NoticeDao
	 */
	@Autowired
	private NoticeService noticeService;

	/*
	 * l'attributo genreService serve per usare i metodi della classe GenreDao
	 */
	@Autowired
	private GenreService genreService;

	// metodo che crea la visualizzazione di un singolo fumetto
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewComic(@RequestParam(required = false) Integer id, Model model) {

		// ricupero del singolo fumetto
		Comic comic = comicService.restart().filterBy("id", id).pick();
		model.addAttribute("comic", comic);

		// ricupero degli autori
		Set<Author> editor = new HashSet<Author>(0);
		Set<Author> finalist = new HashSet<Author>(0);
		Set<Author> writer = new HashSet<Author>(0);
		for (ComicAuthor comicAuthor : comic.getComicAuthors()) {
			if (comicAuthor.getType().equals("Editor")) {
				editor.add(comicAuthor.getAuthor());
			} else if (comicAuthor.getType().equals("Finalist Art")) {
				finalist.add(comicAuthor.getAuthor());
			} else {
				writer.add(comicAuthor.getAuthor());
			}
		}
		model.addAttribute("authorsEditor", editor);
		model.addAttribute("authorsFinalist", finalist);
		model.addAttribute("authorsWritter", writer);

		/* return list of all genres for the rapid menu */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		// ricupero dell'immagine di copertina
		model.addAttribute("imageurl", ValuesUtils.PROJECT_FILES_URL + "/" + comic.getUrlImageCover());

		// chiama la pagina di visualizzazione
		return "frontend/comic/view";
	}

	// metodo che crea una prenotazione
	@RequestMapping(value = "/doReserve", method = RequestMethod.GET)
	public @ResponseBody String doReserve(@RequestParam Integer comicId, @RequestParam Integer qty,
			HttpSession session) {
		// prendi nota del fumetto che deve essere riservato
		Comic comic = comicService.restart().filterBy("id", comicId).pick();

		/*
		 * verifica se in quel momento esiste ancora la quantità di fumetti
		 * desiderata
		 */
		if (qty > comic.getStock()) {
			return "reserveFail";
		}
		// verifica se l'utente è loggato
		if (session.getAttribute("loggedUser") != null) {

			/* salva la nuova quantita del fumetto */
			int newQty = comic.getStock() - qty;

			/* crea la prenotazione */
			User user = (User) session.getAttribute("loggedUser");
			Reserve reserve = new Reserve();
			reserve.setData(new Date());
			reserve.setUser(user);
			reserve.setComic(comic);
			reserve.setQty(qty);
			reserve.setStatus(0);
			reserve.setValue(comic.getValue());
			BigDecimal bigdecimal = comic.getValue();
			Integer integer = qty;
			int i = integer.intValue();
			BigDecimal temp = new BigDecimal(i);
			BigDecimal resultado = bigdecimal.multiply(temp);
			reserve.setTotal(resultado);
			reserveService.save(reserve);

			/* invia la mail di successo */
			sentEmail(user.getEmail(), comic.getTitle());

			try {
				// salva la nuova quantità di fumetti nello stock
				comic.setStock(newQty);
				comicService.update(comicId.longValue(), "stock", comic.getStock());
			} catch (HibernateException ex) {
				return "redirect:/home";
			}
			// return a flag : reserve was realized or not
			return "reserveOk";
		} else {
			return "notlogged";
		}
	}

	// metodo che crea un nuovo commento
	@RequestMapping(value = "/doComment", method = RequestMethod.GET)
	public @ResponseBody String doComment(@RequestParam String comment, @RequestParam String comicId,
			HttpSession session) {
		// verifica se è un utente loggato
		if (session.getAttribute("loggedUser") != null) {

			// salva il commento
			User user = (User) session.getAttribute("loggedUser");
			Note note = new Note();
			note.setNote(comment);
			note.setComic(comicService.restart().filterBy("id", Integer.parseInt(comicId)).pick());
			note.setUser(user);
			note.setStatus(0);
			noteService.save(note);

			// return a flag: commento salvato oppure utente non loggato
			return "commentOk";
		} else {
			return "notlogged";
		}
	}

	// metodo che fa la verifica se un utente è loggato o meno
	@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
	public @ResponseBody String isLogged(HttpSession session) {

		if (session.getAttribute("loggedUser") != null) {
			return "loginOk";
		} else {
			return "notLogged";
		}

	}

	// metodo che crea esegue il login di un utente
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public @ResponseBody String doLogin(@RequestParam String email, @RequestParam String password,
			HttpSession session) {
		// verifica se non è già loggato
		User loggedUser = userService.restart().filterBy("email", email).pick();
		if (loggedUser != null) {
			// verifica se le credenziali sono ok
			if (loggedUser.getPassword().equals(ValuesUtils.encrypt(password))) {
				session.setAttribute("loggedUser", loggedUser);
				return "loginOk";

			} else {
				return "notOk";
			}
		}
		return "loginOk";
	}

	// metodo che fa l'invio di un avviso di prenotazione eseguita con successo
	public void sentEmail(String destinatario, String comic) {

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
			message.setSubject("Avviso prenotazione eseguita");// contenuto
			message.setText("Salve! La sua prenotazione è stata eseguita con successo! Grazie e tanti saluti!"); // messaggio
			/** metodo che invia il messaggio creato */
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	// metodo che crea un nuovo registro di interesse nel fumetto out of stock
	@RequestMapping(value = "/outemail", method = RequestMethod.GET)
	public @ResponseBody String outemail(@RequestParam String email, @RequestParam Integer comicId,
			HttpSession session) {
		Notice notice = new Notice();
		notice.setComic(comicService.restart().filterBy("id", comicId).pick());
		notice.setEmail(email);
		noticeService.save(notice);

		return "emailok";

	}

}
