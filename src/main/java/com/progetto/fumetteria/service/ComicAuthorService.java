/*

* ComicAuthorService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import org.springframework.stereotype.Service;
import com.progetto.fumetteria.dao.AuthorDao;
import com.progetto.fumetteria.dao.ComicAuthorDao;
import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.ComicAuthor;

@Service("comicAuthorService")
public class ComicAuthorService extends GenericService<ComicAuthor> {

	public ComicAuthorService() {
		super(new ComicAuthorDao());
	}
}
