/*

* PhoneService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.progetto.fumetteria.dao.PhoneDao;
import com.progetto.fumetteria.model.Phone;
import com.progetto.fumetteria.model.User;

@Service("phoneService")
public class PhoneService extends GenericService<Phone> {

	public PhoneService() {
		super(new PhoneDao());
	}

	public List<Phone> findAllPhonesUser(User user) {
		return ((PhoneDao) dao).findAllPhonesUser(user);
	}

}
