/*

* UserService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import org.springframework.stereotype.Service;
import com.progetto.fumetteria.dao.UserDao;
import com.progetto.fumetteria.model.User;

@Service("customerService")
public class UserService extends GenericService<User> {

	public UserService() {
		super(new UserDao());
	}

}
