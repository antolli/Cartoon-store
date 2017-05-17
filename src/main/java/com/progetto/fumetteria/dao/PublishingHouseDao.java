/*

* PublishingHouseDao: class dao Casa editrice
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
import com.progetto.fumetteria.model.PublishingHouse;

@Repository("publishingHouseDao")
public class PublishingHouseDao extends GenericDao<PublishingHouse> {

	public PublishingHouseDao() {
		super(PublishingHouse.class);
	}

}
