/*

* AuthorService
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
import com.progetto.fumetteria.model.Author;

@Service("authorService")
public class AuthorService extends GenericService<Author> {

	public AuthorService() {
		super(new AuthorDao());
	}
	
	

}
