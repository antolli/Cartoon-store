/*

* GenreController : controller per gestire tutti i metodi della gestione di generi
*

* version 1.0

*

* 20/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;

import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.service.GenreService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("genre")
@RequestMapping("/admin/genre")
public class GenreController {

	/*
	 * l'attributo genreService serve per usare i metodi della classe GenreDao
	 */
	@Autowired
	private GenreService genreService;

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insertGenre", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpServletRequest request) {
		// verifica da dove arriva la richiesta
		if (model.asMap().get("genre") != null && request.getParameter("message") != null) {
			return new ModelAndView("admin/genre/insert");
		} else {
			return new ModelAndView("admin/genre/insert", "genre", new Genre());
		}
	}

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all genres for the list */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		return "admin/genre/genre";
	}

	/* metodo per cancellare i generi */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			/* prendi ogni genere per l'id */
			for (int id : ids) {

				genreService.delete("id", id);
			}

		} catch (HibernateException ex) {
			// crea messaggio di ritorno in caso di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Genre exception:" + ex.getMessage());
			return "redirect:/admin/genre";
		}
		// crea messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");

		return "redirect:/admin/genre";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editGenre", method = RequestMethod.GET)
	public String getGenreUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		Genre genre = null;

		// ricupera il genere appartenente a quell'Id
		if (id != null) {
			genre = genreService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			genre = new Genre();
		}
		model.addAttribute("genre", genre);

		return "admin/genre/update";
	}

	/* metodo che esegue l'update */
	@RequestMapping(value = "/editGenre", method = RequestMethod.POST)
	public String getGenreUpdate(@Valid @ModelAttribute("genre") Genre genre, BindingResult result, Model model) {

		/*
		 * verifica se non ci sono errori oppure se non è richiesto di inserire
		 * un genere che già esiste (con lo stesso nome)
		 */
		if (result.hasErrors()) {
			return "admin/genre/update";
		} else if (genreService.restart().filterBy("name", genre.getName())
				.orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE).count() >= 1) {
			// crea messaggio di ritorno "fail"
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Genre Name exists. Try another genre name");
			return "redirect:/admin/genre/editGenre?id=" + genre.getId();
		} else {

			// salva il genere
			Long id = genre.getId().longValue();
			genreService.update(id, "name", genre.getName());
			model.addAttribute("genre", new Genre());
			// crea messaggio di ritorno
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Updated genre details");

			return "redirect:/admin/genre";
		}
	}

	/* metodo per eseguire l'iserimento del genere */
	@RequestMapping(value = "/insertGenre", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("genre") Genre genre, BindingResult result, Model model) {

		/*
		 * verifica se non ci sono errori oppure se non è richiesto di inserire
		 * un genere che già esiste (con lo stesso nome)
		 */
		if (result.hasErrors()) {
			return "admin/genre/insert";
		} else if (genreService.restart().filterBy("name", genre.getName())
				.orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE).count() >= 1) {
			// crea messaggio di ritorno "fail"
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Genre Name exists. Try another genre name");
			model.addAttribute("genre", genre);
			return "redirect:/admin/genre/insertGenre";
		} else {
			// salva il genere
			genreService.save(genre);
			model.addAttribute("genre", new Genre());
			// crea messaggio di ritorno
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Saved genre details");
			return "redirect:/admin/genre";
		}
	}

}
