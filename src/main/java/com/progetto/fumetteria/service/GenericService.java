/*

* GenericService : classe intermedio tra DAO e controller (crea indipendeza)
*

* version 1.0

*

* 04/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.progetto.fumetteria.dao.GenericDao;

abstract public class GenericService<T> {

	protected final GenericDao<T> dao;

	public GenericService(GenericDao<T> dao) {
		this.dao = dao;
	}

	public GenericService<T> beginStateless() {
		dao.beginStateless();
		return this;
	}

	public Integer count() {
		return dao.count();
	}

	public GenericService<T> endStateless() {
		dao.endStateless();
		return this;
	}

	public <U> GenericService<T> exceptIf(String propertyName, U value) {
		dao.exceptIf(propertyName, value);
		return this;
	}

	public <U> GenericService<T> exceptIf(String propertyName, U[] values) {
		dao.exceptIf(propertyName, values);
		return this;
	}

	public <U> GenericService<T> filterBy(String propertyName, U value) {
		dao.filterBy(propertyName, value);
		return this;
	}

	public <U> GenericService<T> filterBy(String propertyName, U[] values) {
		dao.filterBy(propertyName, values);
		return this;
	}

	public List<T> find() throws HibernateException {
		return dao.find();
	}

	public <U> GenericService<T> greaterThan(String propertyName, U value) {
		dao.greaterThan(propertyName, value);
		return this;
	}

	public <U> GenericService<T> greaterThanOrEqual(String propertyName, U value) {
		dao.greaterThanOrEqual(propertyName, value);
		return this;
	}

	public <U> GenericService<T> isNull(String propertyName, U values) {
		dao.isNull(propertyName, values);
		return this;
	}

	public <U> GenericService<T> lessThan(String propertyName, U value) {
		dao.lessThan(propertyName, value);
		return this;
	}

	public <U> GenericService<T> lessThanOrEqual(String propertyName, U value) {
		dao.lessThanOrEqual(propertyName, value);
		return this;
	}

	public GenericService<T> orderBy(String propertyName, String orderByDirection) {
		dao.orderBy(propertyName, orderByDirection);
		return this;
	}

	public T pick() throws HibernateException {
		return dao.pick();
	}

	public GenericService<T> restart() {
		dao.restart();
		return this;
	}

	@Transactional(readOnly = false)
	public void save(T object) throws HibernateException {
		dao.save(object);
	}

	public GenericService<T> searchFor(String propertyName, String value) {
		dao.searchFor(propertyName, value);
		return this;
	}

	public GenericService<T> setPage(Integer currentPage, Integer itemsPerPage) {
		dao.setPage(currentPage, itemsPerPage);
		return this;
	}

	@Transactional(readOnly = false)
	public <U> void update(Long id, String propertyName, U value) throws HibernateException {
		dao.update(id, propertyName, value);
	}

	public void delete(String propertyName, int id) throws HibernateException {
		dao.delete(propertyName, id);
	}

}
