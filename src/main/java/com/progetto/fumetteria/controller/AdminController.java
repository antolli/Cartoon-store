/*

* AdminController : classe del tipo controller che è responsabile per controllare le richieste del admin
*

* version 1.0

*

* 07/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.List;

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

@Controller
@SessionAttributes("admin")
public class AdminController {

	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin() {
		/* chiama la pagina principale */
		return "/admin/index";
	}

}
