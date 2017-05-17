/*

* NoteController : controller per gestire tutti i metodi della gestione di note
*

* version 1.0

*

* 23/09/2015

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
import com.progetto.fumetteria.model.Note;
import com.progetto.fumetteria.service.NoteService;
import com.progetto.fumetteria.util.ValuesUtils;

@Controller
@SessionAttributes("note")
@RequestMapping("/admin/note")
public class NoteController {

	/* l'attributo noteService serve per usare i metodi della classe NoteDao */
	@Autowired
	private NoteService noteService;

	// metodo per ritornare la pagina di listaggio
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String viewList(Model model) {
		/* return list of all notes for the list */
		List<Note> notes = noteService.find();
		model.addAttribute("notes", notes);

		return "admin/note/note";
	}

	// metodo per ritornare la pagina di listaggio con determinato filtro
	@RequestMapping(value = "/filter", method = RequestMethod.GET)
	public String filter(Model model, @RequestParam(value = "status", required = false) String status) {
		/* return list of notes with specific status */

		int st = Integer.parseInt(status);
		List<Note> notes = noteService.findByStatus(st);

		model.addAttribute("notes", notes);

		return "admin/note/note";
	}

	/* metodo per chiamare la pagina di update */
	@RequestMapping(value = "/editNote", method = RequestMethod.GET)
	public String getNoteUpdate(Model model, @RequestParam(value = "id", required = false) String id) {
		Note note = null;

		if (id != null) {
			note = noteService.restart().filterBy("id", Integer.parseInt(id)).pick();
		} else {
			note = new Note();
		}
		model.addAttribute("note", note);

		return "admin/note/update";
	}

	// metodo che esegue l'aggiornamento di un commento (cambia lo status)
	@RequestMapping(value = "/editNote", method = RequestMethod.POST)
	public String getNoteUpdate(@Valid @ModelAttribute("note") Note note, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "admin/note/update";
		} else {
			Long id = note.getId().longValue();
			noteService.update(id, "status", note.getStatus());
			model.addAttribute("typeMessage", 1);
			model.addAttribute("message", "Updated note details");
			return "redirect:/admin/note";
		}
	}

}
