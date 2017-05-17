/*

* ComicAuthorDao: class dao ComicAuthor
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import org.springframework.stereotype.Repository;
import com.progetto.fumetteria.model.ComicAuthor;

@Repository("comicAuthorDao")
public class ComicAuthorDao extends GenericDao<ComicAuthor> {

	public ComicAuthorDao() {
		super(ComicAuthor.class);
	}

}
