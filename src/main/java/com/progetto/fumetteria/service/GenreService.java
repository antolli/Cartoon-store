/*

* GenreService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import org.springframework.stereotype.Service;

import com.progetto.fumetteria.dao.GenreDao;
import com.progetto.fumetteria.model.Genre;

@Service("genreService")
public class GenreService extends GenericService<Genre> {

	public GenreService() {
		super(new GenreDao());
	}

}
