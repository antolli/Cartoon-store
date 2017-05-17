/*

* UserFrontController : controller per gestire tutti la pagina di gestione di utenti -> front-office
*

* version 1.0

*

* 27/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.progetto.fumetteria.model.Genre;
import com.progetto.fumetteria.model.Phone;
import com.progetto.fumetteria.model.Reserve;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.GenreService;
import com.progetto.fumetteria.service.PhoneService;
import com.progetto.fumetteria.service.ReserveService;
import com.progetto.fumetteria.service.UserService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@RequestMapping("/userfront")
public class UserFrontController {

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	/*
	 * l'attributo genreService serve per usare i metodi della classe GenreDao
	 */
	@Autowired
	private GenreService genreService;

	/*
	 * l'attributo reserveService serve per usare i metodi della classe
	 * ReserveDao
	 */
	@Autowired
	private ReserveService reserveService;
	// lista di prenotazioni
	private List<Reserve> reserves;

	/*
	 * l'attributo phoneService serve per usare i metodi della classe PhoneDao
	 */
	@Autowired
	private PhoneService phoneService;

	// attributi per gestire i telefoni
	private List<String> typeList;
	private List<Phone> phones;

	/* metodo per accessare la pagina "mio account" */
	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	public String viewMyAccount(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loggedUser");

		if (user.getId() != null) {
			/* return all phones list of a user */
			this.phones = phoneService.findAllPhonesUser(user);

		} else {
			user = new User();
		}

		model.addAttribute("phones", phones);
		model.addAttribute("typeList", typeList);
		model.addAttribute("user", user);

		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		return "frontend/user/update";
	}

	/* metodo per fare un update */
	@RequestMapping(value = "/myAccount", method = RequestMethod.POST)
	public String updateMyAccount(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			String[] phones, String[] types, String[] ids) {

		model.addAttribute("user", user);
		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		if (userService.restart().filterBy("email", user.getEmail()).exceptIf("id", user.getId())
				.orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE).count() >= 1) {

			/* error message */
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message",
					"This email already has been used by another user. Try another email, please.");

			/* go to the form */
			return "redirect:/frontend/user/myAccount";
		} else {
			if (user.getName() != "" && user.getLastName() != "" && user.getEmail() != "") {

				/* convert the id in long value */
				Long id = user.getId().longValue();
				/* save in database the modifies - user */
				userService.update(id, "name", user.getName());
				userService.update(id, "lastName", user.getLastName());
				userService.update(id, "email", user.getEmail());
				System.out.println("aqui");
				if (user.getPassword() != "") {
					user.setPassword(ValuesUtils.encrypt(user.getPassword()));
					userService.update(id, "password", user.getPassword());
				}
				System.out.println("aqui1");
				/* save in database the modifies - phones */
				for (int i = 0; i < types.length; i++) {
					phoneService.update(Long.parseLong(ids[i]), "type", types[i]);
					phoneService.update(Long.parseLong(ids[i]), "number", phones[i]);
				}
				System.out.println("aqui3");
				/* success message */
				model.addAttribute("typeMessage", 1);
				model.addAttribute("message", "Updated user details");
				System.out.println("aqui4");
			} else {
				/* error message */
				model.addAttribute("typeMessage", 0);
				model.addAttribute("message", "Please insert a value on all the fields");

				/* go to the form */
				return "redirect:/userfront/myAccount";
			}

			/* go to the users lists */
			return "redirect:/userfront/myAccount";

		}

	}

	// return user reserves
	@RequestMapping(value = "/myReserves", method = RequestMethod.GET)
	public String viewMyReserves(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("loggedUser");

		if (user.getId() != null) {
			/* return all phones list of a user */
			this.reserves = reserveService.findByUser(user.getId());

		} else {
			user = new User();
		}

		model.addAttribute("reserves", this.reserves);

		/* return list of all genres for the select */
		List<Genre> genres = genreService.find();
		model.addAttribute("genres", genres);

		return "frontend/user/listReserves";
	}

	// metodo per chiamare la view insert a new user
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public ModelAndView viewInsert(@RequestParam(required = false) Integer id, Model model) {
		return new ModelAndView("frontend/user/insert");
	}

	// metodo che esegue l'inserimento
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			HttpServletRequest request) throws IOException {
		if (userService.restart().filterBy("email", user.getEmail()).orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE)
				.count() >= 1) {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message",
					"This email already has been used by another user. Try another email, please.");
			return "redirect:/userfront/insert";
		} else {
			user.setTypeUser("Customer");
			user.setPassword(ValuesUtils.encrypt(user.getPassword()));
			userService.save(user);
			request.getSession().setAttribute("loggedUser", user);
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Account registered!");
			return "redirect:/home";
		}
	}
}
