/*

* SuggestionService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import org.springframework.stereotype.Service;
import com.progetto.fumetteria.dao.SuggestionDao;
import com.progetto.fumetteria.model.Suggestion;

@Service("suggestionsService")
public class SuggestionService extends GenericService<Suggestion> {

	public SuggestionService() {
		super(new SuggestionDao());
	}

}
