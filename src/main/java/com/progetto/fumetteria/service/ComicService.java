/*

* ComicService
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.progetto.fumetteria.dao.ComicDao;
import com.progetto.fumetteria.model.Author;
import com.progetto.fumetteria.model.Comic;

@Service("comicsService")
public class ComicService extends GenericService<Comic> {

	public ComicService() {
		super(new ComicDao());
	}

	public List<Comic> findTheShowHome() {
		return ((ComicDao) dao).findTheShowHome();
	}

	public List<Comic> findByOutOfStock() {
		return ((ComicDao) dao).findByOutOfStock();
	}

	public List<Comic> findByReserve() {
		return ((ComicDao) dao).findByReserve();
	}

	public List<Comic> findByGenre(Integer genreId) {
		return ((ComicDao) dao).findByGenre(genreId);
	}

	public List<Comic> findByAuthor(Integer authorId) {
		return ((ComicDao) dao).findByAuthor(authorId);
	}

	public List<Comic> homeFilter(Integer genreId, Integer authorId, Integer publishingId, BigDecimal priceFrom,
			BigDecimal priceTo) {
		return ((ComicDao) dao).homeFilter(genreId, authorId, publishingId, priceFrom, priceTo);
	}

	public List<Comic> findByTitle(String title) {

		return ((ComicDao) dao).findByTitle(title);

	}

}
