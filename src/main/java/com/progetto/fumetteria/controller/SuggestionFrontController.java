/*

* SuggestionFrontController : controller per gestire la creazione di una suggestione nel Front-office
*

* version 1.0

*

* 25/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.progetto.fumetteria.model.Suggestion;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.SuggestionService;
import com.progetto.fumetteria.service.UserService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@RequestMapping("/suggestion")
public class SuggestionFrontController {

	/*
	 * l'attributo suggestionService serve per usare i metodi della classe
	 * SuggestionDao
	 */
	@Autowired
	private SuggestionService suggestionService;

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView viewInsert(@RequestParam(required = false) Integer id, Model model) {
		return new ModelAndView("frontend/suggestion/insert");
	}

	/* metodo per eseguire l'iserimento del suggerimento */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("suggestion") Suggestion suggestion, BindingResult result, Model model,
			HttpServletRequest request) throws IOException {
		// verifica se l'utente è loggatto
		User user = (User) request.getSession().getAttribute("loggedUser");
		suggestion.setUser(user);
		// salva
		suggestionService.save(suggestion);
		// crea messaggio ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Saved suggestion details");
		return "redirect:/suggestion/insert";
	}

	// verifica se l'utente è loggatto o meno
	@RequestMapping(value = "/isLogged", method = RequestMethod.GET)
	public @ResponseBody String isLogged(HttpSession session) {
		if (session.getAttribute("loggedUser") != null) {
			return "loginOk";
		} else {
			return "notOk";
		}
	}

	// esegue login
	@RequestMapping(value = "/doLogin", method = RequestMethod.POST)
	public @ResponseBody String doLogin(@RequestParam String email, @RequestParam String password,
			HttpSession session) {
		User loggedUser = userService.restart().filterBy("email", email).pick();
		if (loggedUser != null) {

			if (loggedUser.getPassword().equals(ValuesUtils.encrypt(password))) {
				session.setAttribute("loggedUser", loggedUser);
				if (loggedUser.getTypeUser().equals("Admin")) {
					return "loginOk";
				} else {
					return "loginOk";
				}

			} else {
				return "notOk";
			}
		}
		return "loginOk";
	}
}
