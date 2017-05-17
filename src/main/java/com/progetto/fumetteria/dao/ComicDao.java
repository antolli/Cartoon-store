/*

* ComicDao: class dao Comic
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.progetto.fumetteria.controller.PublishingHouseController;
import com.progetto.fumetteria.model.Comic;
import com.progetto.fumetteria.model.PublishingHouse;
import com.progetto.fumetteria.service.PublishingHouseService;
import com.progetto.fumetteria.util.HibernateUtil;

@Repository("comicDao")
public class ComicDao extends GenericDao<Comic> {

	// attributo service publishing house
	@Autowired
	private PublishingHouseService publishingHouseService;

	public ComicDao() {
		super(Comic.class);
	}

	// metodo per ricuperare i fumetti che devono esserci nella home
	public List<Comic> findTheShowHome() {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("showHome", 1));
		return criteria.list();
	}

	// ricupero fumetti out of stock
	public List<Comic> findByOutOfStock() {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.eq("stock", 0));
		return criteria.list();
	}

	// ricupero per title
	public List<Comic> findByTitle(String title) {
		this.restart();
		Criteria criteria = criteria();
		criteria.add(Restrictions.like("title", "%" + title + "%"));
		return criteria.list();
	}

	// ricupero fumetti con almeno una prenotazione
	public List<Comic> findByReserve() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(
				"SELECT distinct c.id, c.title,c.vol, c.edition, c.publication, c.url_image_cover, c.description, c.stock, c.publishing_house_id, c.value, c.show_home FROM COMIC c INNER JOIN RESERVE r ON r.comic_id = c.id;");

		List<Object[]> result = query.list();
		List<Comic> comics = new ArrayList<Comic>();
		for (Object[] res : result) {
			comics.add(convertComic(res));
		}
		return comics;
	}

	// ricupero fumetti per genere
	public List<Comic> findByGenre(Integer genreId) {
		this.restart();
		Criteria criteria = criteria();
		criteria.createAlias("genres", "genresAlias");
		criteria.add(Restrictions.eq("genresAlias.id", genreId));
		return criteria.list();
	}

	// ricupero fumetti per autore
	public List<Comic> findByAuthor(Integer authorId) {
		this.restart();
		Criteria criteria = criteria();
		criteria.createAlias("comicAuthors", "comicAuthorsAlias");
		criteria.add(Restrictions.eq("comicAuthorsAlias.comicAuthorId.author.id", authorId));
		return criteria.list();
	}

	// filtraggio homepage
	public List<Comic> homeFilter(Integer genreId, Integer authorId, Integer publishingId, BigDecimal priceFrom,
			BigDecimal priceTo) {
		this.restart();
		Criteria crit = criteria();
		if (genreId != 0) {
			crit.createAlias("genres", "genresAlias");
			crit.add(Restrictions.eq("genresAlias.id", genreId));
		}
		if (authorId != 0) {
			crit.createAlias("comicAuthors", "comicAuthorsAlias");
			crit.add(Restrictions.eq("comicAuthorsAlias.comicAuthorId.author.id", authorId));
		}
		if (publishingId != 0) {
			filterBy("publishingHouse.id", publishingId);
		}
		if (priceTo.doubleValue() != 0) {
			crit.add(Restrictions.between("value", priceFrom, priceTo));
		}

		crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	private Comic convertComic(Object[] object) {
		Comic comic = new Comic();
		comic.setId(object[0] == null ? 0 : Integer.parseInt(object[0].toString()));
		comic.setTitle(object[1] == null ? "" : object[1].toString());
		comic.setVol(Integer.parseInt(object[2] == null ? "0" : object[2].toString()));
		comic.setEdition(Integer.parseInt(object[3] == null ? "0" : object[3].toString()));
		comic.setUrlImageCover(object[5] == null ? "0" : object[5].toString());
		comic.setDescription(object[6] == null ? "0" : object[6].toString());
		comic.setStock(Integer.parseInt(object[7] == null ? "0" : object[7].toString()));
		// comic.setPublishingHouse(object[8] == null ? new PublishingHouse() :
		// publishingHouseService.restart().filterBy("id",
		// Integer.parseInt(object[8].toString())).pick());
		comic.setValue((BigDecimal) (object[9] == null ? 0 : object[9]));
		comic.setShowHome(Integer.parseInt(object[10] == null ? "0" : object[10].toString()));
		return comic;
	}

}
