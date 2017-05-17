/*

* UserController : controller per gestire tutti i metodi della gestione di utenti
*

* version 1.0

*

* 12/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.progetto.fumetteria.model.Phone;
import com.progetto.fumetteria.model.User;
import com.progetto.fumetteria.service.UserService;
import com.progetto.fumetteria.service.PhoneService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("user")
@RequestMapping("/admin/user")
public class UserController {

	/* l'attributo userService serve per usare i metodi della classe UserDao */
	@Autowired
	private UserService userService;

	/*
	 * l'attributo phoneService serve per usare i metodi della classe PhoneDao
	 */
	@Autowired
	private PhoneService phoneService;

	/* questi attributi servono per gestire i telefoni */
	private List<String> typeList;
	private List<Phone> phones;

	/* costruttore della controller */
	public UserController() {
		this.typeList = new ArrayList<String>();
		this.typeList.add("Admin");
		this.typeList.add("Customer");
	}

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insertUser", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpServletRequest request) {
		model.addAttribute("typeList", typeList);
		// verifica da dove arriva la richiesta
		if (model.asMap().get("user") != null && request.getParameter("message") != null) {
			return new ModelAndView("admin/user/insert");
		} else {
			return new ModelAndView("admin/user/insert", "user", new User());
		}

	}

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all users for the list */
		List<User> users = userService.find();
		model.addAttribute("users", users);

		return "admin/user/user";
	}

	/* metodo per cancellare l'utente */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			/* prendi ogni utente per l'id */
			for (int id : ids) {

				userService.delete("id", id);
			}

		} catch (HibernateException ex) {
			// crea messaggio di ritorno in caso di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "User exception:" + ex.getMessage());
			return "redirect:/admin/user";
		}
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		return "redirect:/admin/user";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editUser", method = RequestMethod.GET)
	public String getUserUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		User user = null;

		// ricupera l'utente appartenente a quell'Id
		if (id != null) {
			user = userService.restart().filterBy("id", Integer.parseInt(id)).pick();
			/* return all phones list of a user */
			// this.phones = phoneService.findAllPhonesUser(user);

		} else {
			user = new User();
		}

		// model.addAttribute("phones", phones);
		model.addAttribute("typeList", typeList);
		model.addAttribute("user", user);

		return "admin/user/update";
	}

	/* metodo che esegue l'update */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String getUserUpdate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model,
			String[] phones, String[] types, String[] ids) {

		model.addAttribute("user", user);

		if (userService.restart().filterBy("email", user.getEmail()).exceptIf("id", user.getId())
				.orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE).count() >= 1) {

			/* error message */
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message",
					"This email already has been used by another user. Try another email, please.");

			/* go to the form */
			return "redirect:/admin/user/editUser?id=" + user.getId();
		} else {
			if (user.getName() != "" && user.getLastName() != "" && user.getEmail() != ""
					&& user.getTypeUser().equalsIgnoreCase("NONE") == false) {

				/* convert the id in long value */
				Long id = user.getId().longValue();

				/* save in database the modifies - user */
				userService.update(id, "name", user.getName());
				userService.update(id, "lastName", user.getLastName());
				userService.update(id, "email", user.getEmail());
				if (user.getPassword() != "") {
					user.setPassword(ValuesUtils.encrypt(user.getPassword()));
					userService.update(id, "password", user.getPassword());
				}
				userService.update(id, "typeUser", user.getTypeUser());

				/* save in database the modifies - phones */
				for (int i = 0; i < types.length; i++) {
					phoneService.update(Long.parseLong(ids[i]), "type", types[i]);
					phoneService.update(Long.parseLong(ids[i]), "number", phones[i]);
				}

				/* success message */
				model.addAttribute("typeMessage", 1);
				model.addAttribute("message", "Updated user details");
				/* new model of user */
				model.addAttribute("user", new User());
			} else {
				/* error message */
				model.addAttribute("typeMessage", 0);
				model.addAttribute("message", "Please insert a value on all the fields");

				/* go to the form */
				return "redirect:/admin/user/editUser?id=" + user.getId();
			}
			/* go to the users lists */
			return "redirect:/admin/user";
		}
	}

	/* metodo per eseguire l'iserimento dell'utente */
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, String[] phones,
			String[] types) {

		model.addAttribute("user", user);
		/*
		 * verifica se non ci sono errori oppure se non è richiesto di inserire
		 * un utente che già esiste (con lo stesso email)
		 */
		if (userService.restart().filterBy("email", user.getEmail()).orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE)
				.count() >= 1) {
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message",
					"This email already has been used by another user. Try another email, please.");
			return "redirect:/admin/user/insertUser";
		} else {
			if (user.getName() != "" && user.getLastName() != "" && user.getEmail() != "" && user.getPassword() != ""
					&& user.getTypeUser().equalsIgnoreCase("NONE") == false) {

				user.setPassword(ValuesUtils.encrypt(user.getPassword()));
				userService.save(user);

				// add phones for the user
				for (int i = 0; i < types.length; i++) {

					Phone phone = new Phone(user, phones[i], types[i]);
					phoneService.save(phone);

				}
				// crea messaggio di ritorno
				model.addAttribute("typeMessage", 1);
				model.addAttribute("message", "Saved user details");
				model.addAttribute("user", new User());
			} else {
				// crea messaggio di ritorno in caso di errore
				model.addAttribute("typeMessage", 0);
				model.addAttribute("message", "Please insert a value on all the fields");
				return "redirect:/admin/user/insertUser";
			}

			return "redirect:/admin/user";
		}
	}

}
