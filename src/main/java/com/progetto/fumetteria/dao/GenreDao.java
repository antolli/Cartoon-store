/*

* GenreDao: class dao Genre
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.progetto.fumetteria.model.Genre;

@Repository("genreDao")
public class GenreDao extends GenericDao<Genre> {

	public GenreDao() {
		super(Genre.class);
	}

}
