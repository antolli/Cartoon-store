/*

* NoticeController : controller per gestire le funzionalità del management di notifiche
*

* version 1.0

*

* 25/09/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.progetto.fumetteria.model.Notice;
import com.progetto.fumetteria.service.NoticeService;

@Controller
@SessionAttributes("notice")
@RequestMapping("/admin/notice")
public class NoticeController {

	/*
	 * l'attributo noticeService serve per usare i metodi della classe NoticeDao
	 */
	@Autowired
	private NoticeService noticeService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all notices for the list */
		List<Notice> notices = noticeService.find();
		model.addAttribute("notices", notices);

		return "admin/notice/notice";
	}

}
