/*

* NoticeService
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

import com.progetto.fumetteria.dao.NoticeDao;
import com.progetto.fumetteria.model.Notice;

@Service("noticeService")
public class NoticeService extends GenericService<Notice> {

	public NoticeService() {
		super(new NoticeDao());
	}

	public List<Notice> findById(Integer id) {
		return ((NoticeDao) dao).findById(id);
	}
}
