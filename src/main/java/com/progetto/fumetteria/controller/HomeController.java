/*

* HomeController : controller per gestire tutti i metodi della gestione di generi
*

* version 1.0

*

* 20/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.PublishingHouse;
import com.progetto.fumetteria.service.AuthorService;
import com.progetto.fumetteria.service.ComicService;
import com.progetto.fumetteria.service.GenreService;
import com.progetto.fumetteria.service.PublishingHouseService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("home")
public class HomeController {

	/*
	 * l'attributo genreService serve per usare i metodi della classe GenreDao
	 */
	@Autowired
	private GenreService genreService;

	/*
	 * l'attributo comicService serve per usare i metodi della classe ComicDao
	 */
	@Autowired
	private ComicService comicService;

	/*
	 * l'attributo authorService serve per usare i metodi della classe AuthorDao
	 */
	@Autowired
	private AuthorService authorService;

	/*
	 * l'attributo publishinghouseService serve per usare i metodi della classe
	 * PublishingHouseDao
	 */
	@Autowired
	private PublishingHouseService publishinghouseService;

	/*
	 * l'attributo servletContext serve per usare i metodi che abbiamo bisogno
	 * per carica l' immagine di copertina
	 */
	@Autowired
	protected ServletContext servletContext;

	// metodo che crea la visualizzazione della home page
	@RequestMapping(value = { "/home", "/" }, method = RequestMethod.GET)
	public String home(Model model) {

		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		/* return list of comics enabled to show at home page */
		List<Comic> comics = comicService.findTheShowHome();
		model.addAttribute("comics", comics);

		/* return list of authors for the select */
		List<Author> authors = authorService.find();
		model.addAttribute("authors", authors);

		/* return list of publishing houses for the select */
		List<PublishingHouse> phs = publishinghouseService.find();
		model.addAttribute("publishinghouses", phs);
		model.addAttribute("baseUrl", ValuesUtils.PROJECT_FILES_URL);
		return "index";
	}

	/* filtro per nome */
	@RequestMapping(value = { "/home/search", "" }, method = RequestMethod.GET)
	public String searchByTitle(Model model, @RequestParam(value = "title", required = false) String title) {

		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		/* return list of comics enabled to show at home page */
		List<Comic> comics = comicService.findByTitle(title);
		model.addAttribute("comics", comics);

		/* return list of authors for the select */
		List<Author> authors = authorService.find();
		model.addAttribute("authors", authors);

		/* return list of publishing houses for the select */
		List<PublishingHouse> phs = publishinghouseService.find();
		model.addAttribute("publishinghouses", phs);
		model.addAttribute("baseUrl", ValuesUtils.PROJECT_FILES_URL);
		return "index";
	}

	/* menu veloce di genere */
	@RequestMapping(value = { "/home/category", "" }, method = RequestMethod.GET)
	public String homeCategory(Model model, @RequestParam(value = "genre", required = false) Integer genre) {

		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		/* return list of comics enabled to show at home page */
		List<Comic> comics = comicService.findByGenre(genre);
		model.addAttribute("comics", comics);

		/* return list of authors for the select */
		List<Author> authors = authorService.find();
		model.addAttribute("authors", authors);

		/* return list of publishing houses for the select */
		List<PublishingHouse> phs = publishinghouseService.find();
		model.addAttribute("publishinghouses", phs);
		model.addAttribute("baseUrl", ValuesUtils.PROJECT_FILES_URL);
		return "index";
	}

	@RequestMapping(value = { "/home/ajaxFilterHome", "/ajaxFilterHome" }, method = RequestMethod.GET)
	public @ResponseBody String getComics(@RequestParam Integer genreId, @RequestParam Integer authorId,
			@RequestParam Integer publishingId, @RequestParam BigDecimal priceFrom, @RequestParam BigDecimal priceTo) {
		// filtra i fumetti d'accordo con la scelta dell'utente
		List<Comic> comics = comicService.homeFilter(genreId, authorId, publishingId, priceFrom, priceTo);
		// risultato visuale
		String result = "<br>";
		if (comics.isEmpty()) {
			result += "<tr class='danger'><td>There are no products to this specification.</td></tr>";
		} else {

			int i = 1;
			result += "<tr class='info'>";

			for (Comic comic : comics) {
				result += "<td>";
				result += "<table><tr><td>";
				result += "<img src='" + servletContext.getContextPath() + "/files/" + comic.getUrlImageCover()
						+ "' alt='" + comic.getTitle() + "' width='170' height='270'/>";
				result += "</tr></td>";
				result += "<tr><td><p>" + comic.getTitle() + "</p></td></tr>";
				result += "<tr><td><p class='price'>£ " + comic.getValue() + "</p></td></tr>";
				result += "<tr><td><p><a href='" + servletContext.getContextPath() + "/comic/view?id=" + comic.getId()
						+ "'><img src='" + servletContext.getContextPath()
						+ "/assets/img/btn-detail.png' alt='view details'/></a></p></td></tr>";
				result += "<tr><td>";
				result += "<p class='genre-category'>";
				Set<Genre> genres = comic.getGenres();
				for (Genre genre : genres) {
					result += "-" + genre.getName() + "-";
				}
				result += "</p>";
				result += "</tr></td>";
				result += "</table></td>";
				if (i % 4 == 0) {
					result += "</tr><tr class='info'>";
				}
				i++;
			}
			result += "</tr>";
		}
		return result;
	}

}
