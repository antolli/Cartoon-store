/*

* ReserveService
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

import com.progetto.fumetteria.dao.ComicDao;
import com.progetto.fumetteria.dao.ReserveDao;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.Reserve;

@Service("reserveService")
public class ReserveService extends GenericService<Reserve> {

	public ReserveService() {
		super(new ReserveDao());
	}

	public Reserve findById(Integer id) {
		return ((ReserveDao) dao).findById(id);
	}

	public List<Reserve> findByUser(Integer id) {

		return ((ReserveDao) dao).findByUser(id);

	}
}
