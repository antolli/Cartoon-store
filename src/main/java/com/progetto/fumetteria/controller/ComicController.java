/*

* ComicController : controller per gestire i fumetti
*

* version 1.0

*

* 21/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.ComicAuthor;
import com.progetto.fumetteria.model.ComicAuthorId;
import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.model.Note;
import com.progetto.fumetteria.model.Notice;
import com.progetto.fumetteria.service.AuthorService;
import com.progetto.fumetteria.service.ComicAuthorService;
import com.progetto.fumetteria.service.ComicService;
import com.progetto.fumetteria.service.GenreService;
import com.progetto.fumetteria.service.NoticeService;
import com.progetto.fumetteria.service.PublishingHouseService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("comic")
@RequestMapping("/admin/comic")

public class ComicController {
	/* l'attributo genreService serve per usare i metodi della classe GenreDao*/
	@Autowired
	private GenreService genreService;
	
	/* l'attributo authorService serve per usare i metodi della classe AuthorDao*/
	@Autowired
	private AuthorService authorService;
	
	/* l'attributo comicService serve per usare i metodi della classe ComicDao*/
	@Autowired
	private ComicService comicService;
	
	/* l'attributo comicAuthorService serve per usare i metodi della classe ComicAuthorDao*/
	@Autowired
	private ComicAuthorService comicAuthorService;

	/* l'attributo publishingHouseService serve per usare i metodi della classe PublishingHouseDao*/
	@Autowired
	private PublishingHouseService publishingHouseService;
	
	/* l'attributo noticeService serve per usare i metodi della classe NoticeDao*/
	@Autowired
	private NoticeService noticeService;

	/* l'attributo servletContext  serve per usare i metodi che abbiamo bisogno 
	 * per carica l' immagine di copertina*/
	@Autowired
	protected ServletContext servletContext;

	/*metodo che carica la view di inserimento di nuovi fumetti*/
	@RequestMapping(value = "/insertComic", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpSession session,
			@RequestParam(value = "origine", required = false) String origine) {
		/*verificazione della sessione suggestion: 
		 * qui sappiamo se il metodo è chiamato dall'accetazione
		 * di una suggestione oppure della creazione di un
		 * nuovo fumetto */
		if (session.getAttribute("suggestion") != null) {
			if (origine != null) {
				session.setAttribute("suggestion", null);
				model.addAttribute("comic", new Comic());
			} else {
				model.addAttribute("comic", session.getAttribute("suggestion"));
			}
		} else {
			model.addAttribute("comic", new Comic());
		}
		//contenuto dei selectbox
		model.addAttribute("authors", authorService.restart().find());
		model.addAttribute("genres", genreService.restart().find());
		model.addAttribute("publishingHouses", publishingHouseService.restart().find());
		
		//chiama la pagina di inserimento 
		return new ModelAndView("admin/comic/insert");
	}
	
	/*metodo che fa il filtraggio di fumetti per prenotazioni e scarsa disponibilità*/
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public String filter(Model model, @RequestParam(value = "type", required = false) String type ) {
		
		/* return list of comics with specific filter */
		/*-Types- */
		/* 0 = comics with a reserve */
		/* 1 = comics out of availability */
		
		List<Comic> comics = new ArrayList<Comic>();
		if(type.equals("0")){
			System.out.println("ENTREI");
			comics = comicService.findByOutOfStock();
			System.out.println("size:" + comics.size());
		}else if(type.equals("1")){
			comics = comicService.findByReserve();
		}
		
		//contenuto dei selectbox
		model.addAttribute("comics", comics);
		model.addAttribute("authors", authorService.restart().find());
		model.addAttribute("genres", genreService.restart().find());
		
		//chiama la pagina di listaggio
		return "admin/comic/comic";
	}
	
	/*metodo che fa il filtraggio di fumetti per genre o authori*/
	@RequestMapping(value = "/filter", method = RequestMethod.POST)
	public String filter(Model model, @RequestParam(value = "type", required = false) String type, @RequestParam(value = "id", required = false) String id ) {
		
		/* return list of comics with specific filter */
		/*-Types- */
		/* 2 = comics by genre */
		/* 3 = comics by author */
		List<Comic> comics = new ArrayList<Comic>();
		if(type.equals("2")){
			comics = comicService.findByGenre(Integer.parseInt(id));
		}else if(type.equals("3")){
			comics = comicService.findByAuthor(Integer.parseInt(id));
		}
		//contenuto dei selectbox
		model.addAttribute("comics", comics);
		model.addAttribute("authors", authorService.restart().find());
		model.addAttribute("genres", genreService.restart().find());
		
		//chiama la pagina di listaggio
		return "admin/comic/comic";
	}

	/*metodo che ricupera tutti i fumetti (listaggio iniziale)*/
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		;
		/* return list of all genres for the list */
		List<Comic> comics = comicService.find();
		
		//contenuti dei selectbox
		model.addAttribute("comics", comics);
		model.addAttribute("authors", authorService.restart().find());
		model.addAttribute("genres", genreService.restart().find());
		
		//chiama la pagina di listaggio
		return "admin/comic/comic";
	}

	/*metodo che realizza il cancellamento di fumetti del database*/
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			//cancella i dati del database
			for (int id : ids) {

				comicService.delete("id", id);
			}

		} catch (HibernateException ex) {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Genre exception:" + ex.getMessage());
			return "redirect:/admin/comic";
		}
		//crea il messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		
		//richiama la pagina di listaggio
		return "redirect:/admin/comic";
	}

	/*Metodo di ricupero di un fumetto per id*/
	@ModelAttribute("comic")
	public Comic getComic(@RequestParam(value = "id", required = false) String id) {
		Comic comic = null;

		if (id != null) {
			comic = comicService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			comic = new Comic();
		}

		return comic;
	}

	/*metodo che fa l'inserimento di fummeti nuovi nel database*/
	@RequestMapping(value = "/insertComic", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("comic") Comic comic, @RequestParam CommonsMultipartFile fileUpload,
			BindingResult result, Model model, HttpServletRequest request) throws IOException {
		
		if (!fileUpload.isEmpty()) {

			// fa upload nel PC
			String uploadDir = ValuesUtils.URL_UPLOAD_FILES; // servletContext.getRealPath("/files");
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			InputStream stream = fileUpload.getInputStream();
			OutputStream bos = new FileOutputStream(uploadDir + "/" + fileUpload.getOriginalFilename());
			int bytesRead;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			// fa upload nel apache
			uploadDir = servletContext.getRealPath("/files");
			dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			stream = fileUpload.getInputStream();
			bos = new FileOutputStream(uploadDir + "/" + fileUpload.getOriginalFilename());
			bytesRead = 0;
			buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
			
			//scrive nell'oggeto comic il percorso della sua imagine di copertina
			comic.setUrlImageCover(fileUpload.getOriginalFilename());

		}
		
		comicService.save(comic);
		
		/*abbiamo considerato i tre tipi principali di autori: 
		 * Finalist Art, Editor e Writer
		 * in seguito sono salvati gli autori */
		for (Integer authorId : comic.getComicAuthorsEditorId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Editor");
			comicAuthorService.save(ca);
		}
		
		 
		for (Integer authorId : comic.getComicAuthorsFinalistId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Finalist Art");
			comicAuthorService.save(ca);
		}

		for (Integer authorId : comic.getComicAuthorsWriterId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Writer");
			comicAuthorService.save(ca);
		}

		//salva i generi di quel fumetto (relazione comic_genre)
		for (Integer genreId : comic.getGenresIds()) {
			System.out.println("genre " + genreId);
			Genre g = genreService.restart().filterBy("id", genreId).pick();
			comic.getGenres().add(g);
		}
		//salva il fumetto
		comicService.save(comic);
		
		//crea messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Saved comic details");
		
		//chiama la pagina di ritorno
		return "redirect:/admin/comic";

	}

	/*metodo che carica il view di update dei fumetti*/
	@RequestMapping(value = "/editComic", method = RequestMethod.GET)
	public String editComic(Model model, @RequestParam(value = "id", required = false) String id) {
		Comic comic = null;
		//ricupero del fumetto appartenente a quel Id
		if (id != null) {
			comic = comicService.restart().filterBy("id", Integer.parseInt(id)).pick();

		} else {
			comic = new Comic();
		}
		
		//ricupera l'immagine di copertina del fumetto
		model.addAttribute("imageurl", ValuesUtils.PROJECT_FILES_URL + "/" + comic.getUrlImageCover());
		
		//set a right format date
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		model.addAttribute("publicationdate", dt.format(comic.getPublication()));
		
		//oggetto fumetto
		model.addAttribute("comic", comic);
		
		//ricupero degli autori di quel fumetto con suoi tipi
		model.addAttribute("authors", authorService.restart().find());
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
		model.addAttribute("authorsWriter", writer);
		
		//selectbox di generi
		model.addAttribute("genres", genreService.restart().find());
		
		//selectbox di case editrice
		model.addAttribute("publishingHouses", publishingHouseService.restart().find());
		model.addAttribute("author_id", 0);

		//chiama la pagina di ritorno
		return "admin/comic/update";
	}
	/*metodo che fa l'aggiornamento di fummeti nel database*/
	@RequestMapping(value = "/editComic", method = RequestMethod.POST)
	public String postomicUpdate(Model model, @Valid @ModelAttribute("comic") Comic comic,
			@RequestParam CommonsMultipartFile fileUpload, @RequestParam String phouse, BindingResult result,
			HttpServletRequest request) throws IOException {
		
		if (!fileUpload.isEmpty()) {

			// fa upload nel PC
			String uploadDir = ValuesUtils.URL_UPLOAD_FILES; // servletContext.getRealPath("/files");
			File dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			InputStream stream = fileUpload.getInputStream();
			OutputStream bos = new FileOutputStream(uploadDir + "/" + fileUpload.getOriginalFilename());
			int bytesRead;
			byte[] buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}

			// fa upload nel apache

			uploadDir = servletContext.getRealPath("/files");
			dirPath = new File(uploadDir);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
			stream = fileUpload.getInputStream();
			bos = new FileOutputStream(uploadDir + "/" + fileUpload.getOriginalFilename());
			bytesRead = 0;
			buffer = new byte[8192];
			while ((bytesRead = stream.read(buffer, 0, 8192)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			bos.close();
			stream.close();
			comic.setUrlImageCover(fileUpload.getOriginalFilename());

		}
		
		/*funzionalità notice (avvisami quando c'è) - avvisa gli utenti del cambiamento di stock*/
		if(comic.getStock() > 0){
			List<Notice> listNotice = noticeService.findById(comic.getId());
			for (Notice notice : listNotice) {
				this.sentEmail(notice.getEmail(), comic.getTitle());
				noticeService.delete("id", notice.getId());
			}
			
		}
		
		//update degli autori e della casa editrice
		comicAuthorService.delete("comicAuthorId.comic.id", comic.getId());
		comic.setPublishingHouse(publishingHouseService.restart().filterBy("id", Integer.parseInt(phouse)).pick());
		comicService.save(comic);
		for (Integer authorId : comic.getComicAuthorsEditorId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Editor");
			comicAuthorService.save(ca);
		}

		for (Integer authorId : comic.getComicAuthorsFinalistId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Finalist Art");
			comicAuthorService.save(ca);
		}

		for (Integer authorId : comic.getComicAuthorsWriterId()) {
			ComicAuthor ca = new ComicAuthor();
			ComicAuthorId ci = new ComicAuthorId(comic, authorService.restart().filterBy("id", authorId).pick());
			ca.setComicAuthorId(ci);
			ca.setType("Writer");
			comicAuthorService.save(ca);
		}
		
		//update dei generi del fumetto
		comic.setGenres(new HashSet<Genre>(0));
		for (Integer genreId : comic.getGenresIds()) {
			System.out.println("genre " + genreId);
			Genre g = genreService.restart().filterBy("id", genreId).pick();
			
			comic.getGenres().add(g);
		}
		
		//update del fumetto
		comicService.save(comic);
		
		//crea il messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Updated comic details");
		
		//chiama la pagina di ritorno
		return "redirect:/admin/comic";
	}

	//metodo che fa l'invio di email di avviso
	public void sentEmail(String destinatario, String comic) {

		Properties props = new Properties();
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
			message.setSubject("Avviso disponibilità fumetto - NOTICE");// contenuto
			message.setText("Salve! Il fumetto che era del suo interesse adesso è disponibile in stock! Fumetto:"
					+ comic + "; Tanti saluti! "); // messaggio
			/** metodo che invia il messaggio creato */
			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}

}
