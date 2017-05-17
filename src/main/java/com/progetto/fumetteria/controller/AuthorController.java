/*

* AuthorController : controller per gestire tutti i metodi della gestione di autori
*

* version 1.0

*

* 20/08/2015

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

import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.AuthorService;

@Controller
@SessionAttributes("author")
@RequestMapping("/admin/author")
public class AuthorController {

	/*
	 * l'attributo authorService serve per usare i metodi della classe AuthorDao
	 */
	@Autowired
	private AuthorService authorService;

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insertAuthor", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpServletRequest request) {

		if (model.asMap().get("author") != null && request.getParameter("message") != null) {
			return new ModelAndView("admin/author/insert");
		} else {
			return new ModelAndView("admin/author/insert", "author", new Author());
		}

	}

	/* metodo per listare gli autori */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all authors for the list */
		List<Author> authors = authorService.find();
		model.addAttribute("authors", authors);

		return "admin/author/author";
	}

	/* metodo per cancellare gli autori */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		/* prendi ogni autore per l'id */
		try {
			for (int id : ids) {

				authorService.delete("id", id);
			}

		} catch (HibernateException ex) {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Author exception:" + ex.getMessage());
			return "redirect:/admin/author";
		}
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		return "redirect:/admin/author";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editAuthor", method = RequestMethod.GET)
	public String getAuthorUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		Author author = null;

		if (id != null) {
			author = authorService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			author = new Author();
		}
		model.addAttribute("author", author);

		return "admin/author/update";
	}

	/* metodo che esegue l'update */
	@RequestMapping(value = "/editAuthor", method = RequestMethod.POST)
	public String getAuthorUpdate(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "admin/author/update";
		} else {
			Long id = author.getId().longValue();
			authorService.update(id, "name", author.getName());
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Updated author details");
			return "redirect:/admin/author";
		}
	}

	/* metodo per eseguire l'iserimento dell'autore */
	@RequestMapping(value = "/insertAuthor", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("author") Author author, BindingResult result, Model model) {

		model.addAttribute("author", author);

		if (result.hasErrors()) {
			return "admin/author/insert";
		} else {
			if (author.getName() != "") {
				authorService.save(author);
				model.addAttribute("typeMessage", 1);
				model.addAttribute("message", "Saved author details");
				return "redirect:/admin/author";
			} else {
				model.addAttribute("typeMessage", 0);
				model.addAttribute("message", "Please insert a value on all the fields");
				return "redirect:/admin/user/insertAuthor";
			}
		}
	}

}
