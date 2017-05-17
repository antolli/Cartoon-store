/*

* ReserveDao: class dao Prenotazione
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

import com.progetto.fumetteria.model.Reserve;

@Repository("reserveDao")
public class ReserveDao extends GenericDao<Reserve> {

	public ReserveDao() {
		super(Reserve.class);
	}

	// filtraggio per id
	public Reserve findById(Integer id) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("id", id));
		return (Reserve) criteria.list().get(0);
	}

	// filtraggio per utente
	public List<Reserve> findByUser(Integer id) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("user.id", id));
		return criteria.list();
	}

}
