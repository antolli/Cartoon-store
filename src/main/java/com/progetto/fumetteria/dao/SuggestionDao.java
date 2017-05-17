/*

* SuggestionDao: class dao Suggestion
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import org.springframework.stereotype.Repository;
import com.progetto.fumetteria.model.Suggestion;

@Repository("SuggestionDao")
public class SuggestionDao extends GenericDao<Suggestion> {

	public SuggestionDao() {
		super(Suggestion.class);
	}

}
