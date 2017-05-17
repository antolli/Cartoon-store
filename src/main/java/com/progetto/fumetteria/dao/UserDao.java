/*

* UserDao: class dao Utente
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import org.springframework.stereotype.Repository;
import com.progetto.fumetteria.model.User;

@Repository("userDao")
public class UserDao extends GenericDao<User> {

	public UserDao() {
		super(User.class);
	}

}
