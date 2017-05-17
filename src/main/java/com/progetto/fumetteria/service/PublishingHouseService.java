/*

* PublishingHouseService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import org.springframework.stereotype.Service;
import com.progetto.fumetteria.dao.PublishingHouseDao;
import com.progetto.fumetteria.model.PublishingHouse;

@Service("publishinghouseService")
public class PublishingHouseService extends GenericService<PublishingHouse> {

	public PublishingHouseService() {
		super(new PublishingHouseDao());
	}

}
