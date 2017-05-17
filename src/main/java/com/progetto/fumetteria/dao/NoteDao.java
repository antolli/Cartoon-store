/*

* NoteDao: class dao Note
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.Note;

@Repository("noteDao")
public class NoteDao extends GenericDao<Note> {

	public NoteDao() {
		super(Note.class);
	}

	// filtraggio per status
	public List<Note> findByStatus(int status) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("status", status));
		return criteria.list();
	}

}
