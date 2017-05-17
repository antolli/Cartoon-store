/*

* NoteService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.progetto.fumetteria.dao.NoteDao;
import com.progetto.fumetteria.model.Note;

@Service("noteService")
public class NoteService extends GenericService<Note> {

	public NoteService() {
		super(new NoteDao());
	}

	public List<Note> findByStatus(int status) {
		return ((NoteDao) dao).findByStatus(status);
	}

}
