/*

* PhoneDao: class dao Phone
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
import com.progetto.fumetteria.model.Phone;
import com.progetto.fumetteria.model.User;

public class PhoneDao extends GenericDao<Phone> {

	public PhoneDao() {
		super(Phone.class);
	}

	// fintraggio dei telefoni di un utente
	public List<Phone> findAllPhonesUser(User user) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", user.getId()));
		return criteria.list();
	}

}
