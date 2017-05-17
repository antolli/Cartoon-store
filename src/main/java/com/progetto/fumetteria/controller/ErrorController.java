/*

* ErrorFrontController : controller per gestire le pagine di errori
*

* version 1.0

*

* 29/10/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value = "error")
	public String handleError(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		Logger log = Logger.getLogger(ErrorController.class);

		log.info(request.getAttribute("javax.servlet.forward.request_uri"));
		log.info(request.getAttribute("javax.servlet.error.exception"));

		// Print param
		for (Entry<String, String[]> map : request.getParameterMap().entrySet()) {
			for (int i = 0; i < map.getValue().length; i++)
				log.info(map.getKey() + '=' + map.getValue()[i].toString());
		}

		return "error";
	}
}
