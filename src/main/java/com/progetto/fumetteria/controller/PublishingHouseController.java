/*

* PublishingHouseController : controller per gestire tutti i metodi della gestione di casa editrice
*

* version 1.0

*

* 23/08/2015

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
import com.progetto.fumetteria.model.PublishingHouse;
import com.progetto.fumetteria.service.PublishingHouseService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("publishinghouse")
@RequestMapping("/admin/publishinghouse")
public class PublishingHouseController {

	/*
	 * l'attributo publishinghouseService serve per usare i metodi della classe
	 * PublishingHouseDao
	 */
	@Autowired
	private PublishingHouseService publishinghouseService;

	/* metodo per chiamare la pagina di inserimento */
	@RequestMapping(value = "/insertPublishingHouse", method = RequestMethod.GET)
	public ModelAndView viewInsert(Model model, HttpServletRequest request) {
		// verifica da dove arriva la richiesta
		if (model.asMap().get("publishinghouse") != null && request.getParameter("message") != null) {
			return new ModelAndView("admin/publishinghouse/insert");
		} else {
			return new ModelAndView("admin/publishinghouse/insert", "publishinghouse", new PublishingHouse());
		}

	}

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all publishinghouses for the list */
		List<PublishingHouse> publishinghouses = publishinghouseService.find();
		model.addAttribute("publishinghouses", publishinghouses);

		return "admin/publishinghouse/publishinghouse";
	}

	/* metodo per cancellare gli oggetti publishing house */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam(value = "id", required = false) int[] ids, Model model) {

		try {
			/* prendi ogni cada editrice per l'id */
			for (int id : ids) {

				publishinghouseService.delete("id", id);
			}

		} catch (HibernateException ex) {
			// crea messaggio di ritorno in caso di errore
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Publishing House exception:" + ex.getMessage());
			return "redirect:/admin/publishinghouse";
		}
		// crea messaggio di ritorno
		model.addAttribute("typeMessage", 1);
		model.addAttribute("message", "Deleted itens.");
		return "redirect:/admin/publishinghouse";
	}

	/* metodo per chiamare la pagina per fare un update */
	@RequestMapping(value = "/editPublishingHouse", method = RequestMethod.GET)
	public String getPublishingHouseUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		PublishingHouse publishinghouse = null;

		// ricupera la casa ed. appartenente a quell'Id
		if (id != null) {
			publishinghouse = publishinghouseService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			publishinghouse = new PublishingHouse();
		}
		model.addAttribute("publishinghouse", publishinghouse);

		return "admin/publishinghouse/update";
	}

	/* metodo che esegue l'update */
	@RequestMapping(value = "/editPublishingHouse", method = RequestMethod.POST)
	public String getPublishingHouseUpdate(@Valid @ModelAttribute("publishinghouse") PublishingHouse publishinghouse,
			BindingResult result, Model model) {

		/*
		 * verifica se non ci sono errori oppure se non è richiesto di inserire
		 * una casa editrice che già esiste (con lo stesso nome)
		 */
		if (result.hasErrors()) {
			return "admin/publishinghouse/update";
		} else if (publishinghouseService.restart().filterBy("name", publishinghouse.getName())
				.exceptIf("id", publishinghouse.getId()).orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE)
				.count() >= 1) {
			// crea messaggio di ritorno "fail"
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Publishing House Name exists. Try another name");
			return "redirect:/admin/publishinghouse/editPublishingHouse?id=" + publishinghouse.getId();
		} else {
			// salva
			Long id = publishinghouse.getId().longValue();
			publishinghouseService.update(id, "name", publishinghouse.getName());
			publishinghouseService.update(id, "email", publishinghouse.getEmail());
			publishinghouseService.update(id, "phone", publishinghouse.getPhone());
			// crea messaggio di ritorno
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Updated publishing house details");
			return "redirect:/admin/publishinghouse";
		}
	}

	/* metodo per eseguire l'iserimento della casa editrice */
	@RequestMapping(value = "/insertPublishingHouse", method = RequestMethod.POST)
	public String insert(@Valid @ModelAttribute("publishinghouse") PublishingHouse publishinghouse,
			BindingResult result, Model model) {

		model.addAttribute("publishinghouse", publishinghouse);
		/*
		 * verifica se non ci sono errori oppure se non è richiesto di inserire
		 * una casa editrice già esistente (con lo stesso nome)
		 */
		if (result.hasErrors()) {

			return "admin/publishinghouse/insert";

		} else if (publishinghouseService.restart().filterBy("name", publishinghouse.getName())
				.orderBy("name", ValuesUtils.ORDENACAO_ASCENDENTE).count() >= 1) {
			// crea messaggio di ritorno "fail"
			model.addAttribute("typeMessage", 0);
			model.addAttribute("message", "Publishing House Name exists. Try another name");
			model.addAttribute("publishinghouse", publishinghouse);

			return "redirect:/admin/publishinghouse/insertPublishingHouse";
		} else {
			// verifica se ci sono tutti gli attributi
			if (publishinghouse.getName() != "" && publishinghouse.getEmail() != ""
					&& publishinghouse.getPhone() != "") {
				// salva
				publishinghouseService.save(publishinghouse);
				// crea messaggio di ritorno
				model.addAttribute("typeMessage", 1);
				model.addAttribute("message", "Saved publishing house details");

				return "redirect:/admin/publishinghouse";
			} else {
				/*
				 * crea messaggio di ritorno "fail" nel caso di non esserci
				 * qualche campo obbligatorio
				 */
				model.addAttribute("typeMessage", 0);
				model.addAttribute("message", "Please insert a value on all the fields");

				return "redirect:/admin/publishinghouse/insertPublishingHouse";
			}
		}
	}

}
