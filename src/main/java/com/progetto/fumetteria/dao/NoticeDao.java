/*

* NoticeDao: class dao Notice
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

import com.progetto.fumetteria.model.Notice;

@Repository("noticeDao")
public class NoticeDao extends GenericDao<Notice>{
	

	public NoticeDao() {
		super(Notice.class);
	}
	//filtraggio per id
	public List<Notice> findById(Integer id) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("comic.id", id));
		return criteria.list();
	}

}
