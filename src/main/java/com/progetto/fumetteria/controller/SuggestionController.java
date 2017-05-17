/*

* SuggestionController : controller per gestire tutti i metodi della gestione di suggestion
*

* version 1.0

*

* 26/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.HibernateException;
import org.junit.runner.Request;
import org.omg.PortableInterceptor.RequestInfoOperations;
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
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.Suggestion;
import com.progetto.fumetteria.service.SuggestionService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("suggestion")
@RequestMapping("/admin/suggestion")
public class SuggestionController {

	/*
	 * l'attributo suggestionService serve per usare i metodi della classe
	 * SuggestionDao
	 */
	@Autowired
	private SuggestionService suggestionService;

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all suggestions for the list */
		List<Suggestion> suggestions = suggestionService.find();
		model.addAttribute("suggestions", suggestions);

		return "admin/suggestion/suggestion";
	}

	/* metodo per cancellare i suggerimenti */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			/* prendi ogni suggerimento per l'id */
			for (int id : ids) {
				suggestionService.delete("id", id);
			}

		} catch (HibernateException ex) {
			// crea messaggio di ritorno in caso di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "User exception:" + ex.getMessage());
			return "redirect:/admin/suggestion";
		}
		// crea messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		return "redirect:/admin/suggestion";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editSuggestion", method = RequestMethod.GET)
	public String getSuggestionUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		Suggestion suggestion = null;
		// ricupera il suggerimento appartenente a quell'Id
		if (id != null) {
			suggestion = suggestionService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			suggestion = new Suggestion();
		}
		model.addAttribute("suggestion", suggestion);

		return "admin/suggestion/update";
	}

	/*
	 * metodo che esegue la chiamata della pagina di inserimento di un fumetto
	 * passando tutti i valori del suggerimento
	 */
	@RequestMapping(value = "/editSuggestion", method = RequestMethod.POST)
	public String getSuggestionUpdate(@Valid @ModelAttribute("suggestion") Suggestion suggestion, BindingResult result,
			Model model, HttpSession session) {
		// crea un nuovo fumetto con i dati del suggerimento
		Comic comic = new Comic();
		comic.setTitle(suggestion.getTitle());
		comic.setVol(suggestion.getVol());
		comic.setEdition(suggestion.getEdition());
		comic.setPublication(suggestion.getPublication());
		comic.setDescription(suggestion.getDescription());
		// crea una session passando l'oggetto fumetto
		session.setAttribute("suggestion", comic);

		try {
			// redirect to insertComic
			return "redirect:/admin/comic/insertComic";

		} catch (HibernateException ex) {
			// crea messaggio di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Suggestion exception:" + ex.getMessage());
			return "redirect:/admin/suggestion";

		}
	}

}
