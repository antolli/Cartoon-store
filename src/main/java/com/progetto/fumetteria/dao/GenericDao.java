/*

* GenericDao: classe padre che fa la conessione con il 
* database uttilizzando il frameword Hibernate e contiene 
* tutti i metodi di manipolazione degli oggetti
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.progetto.fumetteria.util.HibernateUtil;

abstract public class GenericDao<T> {

	private interface CriterionSelector<U> {

		public Criterion expr(String propertyName, U value);

	}

	public static class OrderType {

		public static final String ASC = "ASC";

		public static final String DESC = "DESC";

	}

	private Criteria _criteria = null;

	private final Map<String, Criteria> _subCriterias = new HashMap<String, Criteria>();

	private final Class<T> clazz;

	private Session session = null;

	private StatelessSession statelessSession = null;

	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	public GenericDao<T> beginStateless() {
		if (statelessSession == null) {
			session = null;
			statelessSession = HibernateUtil.getSessionFactory().openStatelessSession();
			_criteria = statelessSession.createCriteria(clazz);
			_subCriterias.clear();
		}
		return this;
	}

	public Integer count() throws HibernateException {
		final Long count = (Long) criteria().setProjection(Projections.rowCount()).uniqueResult();
		criteria().setProjection(null);
		criteria().setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		return count.intValue();
	}

	protected Criteria criteria() {
		if (_criteria == null || (session == null || !session.isOpen()) && statelessSession == null) {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			_criteria = session.createCriteria(clazz);
			_subCriterias.clear();
		}
		return _criteria;
	}

	public void endStateless() {
		if (statelessSession != null) {
			statelessSession.close();
			statelessSession = null;
			session = null;
			restart();
		}
	}

	public <U> GenericDao<T> exceptIf(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.ne(propertyName, value);
			}

		});
	}

	public <U> GenericDao<T> exceptIf(String propertyName, U[] values) {
		return filter(propertyName, values, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.ne(propertyName, value);
			}

		});
	}

	private <U> GenericDao<T> filter(String propertyName, U value, CriterionSelector<U> cs) {
		if (!isBlank(propertyName) && !isBlank(value)) {
			if (propertyName.contains(".")) {
				propertyName = getInPropertyName(propertyName);
			}
			criteria().add(cs.expr(propertyName, value));
		} else if (!isBlank(propertyName)) {
			criteria().add(cs.expr(propertyName, value));
		}
		return this;
	}

	private <U> GenericDao<T> filter(String propertyName, U[] values, CriterionSelector<U> cs) {
		if (!isBlank(propertyName) && !isBlank(values)) {
			if (values.length == 1) {
				return filter(propertyName, values[0], cs);
			}
			if (propertyName.contains(".")) {
				propertyName = getInPropertyName(propertyName);
			}
			final Criterion lhs = cs.expr(propertyName, values[0]);
			final Criterion rhs = cs.expr(propertyName, values[1]);
			criteria().add(Restrictions.or(lhs, rhs));
		}
		return this;
	}

	public <U> GenericDao<T> filterBy(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.eq(propertyName, value);
			}

		});
	}

	public <U> GenericDao<T> filterBy(String propertyName, U[] values) {
		return filter(propertyName, values, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.eq(propertyName, value);
			}

		});
	}

	@SuppressWarnings("unchecked")
	public List<T> find() throws HibernateException {
		return criteria().list();
	}

	private Pair<List<String>, String> getInPair(String propertyName) {
		final List<String> in = Arrays.asList(propertyName.split("\\."));
		return Pair.of(in.subList(0, in.size() - 1), in.get(in.size() - 1));
	}

	private String getInPropertyName(String propertyName) {
		final Pair<List<String>, String> inPair = getInPair(propertyName);
		final String alias = getSubCriteria(inPair.getLeft()).getAlias();
		return alias + "." + inPair.getRight();
	}

	private Criteria getSubCriteria(List<String> in) {
		Criteria subCriteria = criteria();
		for (int i = 0; i < in.size(); i++) {
			final String associationPath = in.get(i);
			if (!_subCriterias.containsKey(associationPath)) {
				final String alias = RandomStringUtils.randomAlphanumeric(8);
				subCriteria = subCriteria.createCriteria(associationPath, alias);
				_subCriterias.put(associationPath, subCriteria);
			} else {
				subCriteria = _subCriterias.get(associationPath);
			}
		}
		return subCriteria;
	}

	public <U> GenericDao<T> greaterThan(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.gt(propertyName, value);
			}

		});
	}

	public <U> GenericDao<T> greaterThanOrEqual(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.ge(propertyName, value);
			}

		});
	}

	private <U> Boolean isBlank(U value) {
		if (value != null && !(value instanceof String && ((String) value).trim().length() == 0)) {
			return false;
		}
		return true;
	}

	private <U> Boolean isBlank(U[] values) {
		if (values == null) {
			return true;
		}
		if (values.length > 0) {
			for (final U value : values) {
				if (isBlank(value)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public <U> GenericDao<T> isNull(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.isNull(propertyName);
			}

		});
	}

	public <U> GenericDao<T> lessThan(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.lt(propertyName, value);
			}

		});
	}

	public <U> GenericDao<T> lessThanOrEqual(String propertyName, U value) {
		return filter(propertyName, value, new CriterionSelector<U>() {

			@Override
			public Criterion expr(String propertyName, U value) {
				return Restrictions.le(propertyName, value);
			}

		});
	}

	public GenericDao<T> orderBy(String propertyName, String orderType) {
		if (!isBlank(propertyName) && !isBlank(orderType)) {
			if (propertyName.contains(".")) {
				propertyName = getInPropertyName(propertyName);
			}
			if (orderType.equals(OrderType.DESC)) {
				criteria().addOrder(Order.desc(propertyName));
			} else {
				criteria().addOrder(Order.asc(propertyName));
			}
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	public T pick() throws HibernateException {
		return (T) criteria().uniqueResult();
	}

	public GenericDao<T> restart() {
		_criteria = null;
		_subCriterias.clear();
		return this;
	}

	public void save(T object) throws HibernateException {
		HibernateUtil.getSessionFactory().getCurrentSession().saveOrUpdate(object);

	}

	public GenericDao<T> searchFor(String propertyName, String value) {
		if (!isBlank(propertyName) && !isBlank(value)) {
			if (propertyName.contains(".")) {
				propertyName = getInPropertyName(propertyName);
			}
			criteria().add(Restrictions.ilike(propertyName, value, MatchMode.ANYWHERE));
		}
		return this;
	}

	public GenericDao<T> setPage(Integer page, Integer itemsPerPage) {
		if (!isBlank(page) && !isBlank(itemsPerPage)) {
			criteria().setFirstResult((page - 1) * itemsPerPage).setMaxResults(itemsPerPage);
		}
		return this;
	}

	public <U> void update(Long id, String propertyName, U value) throws HibernateException {
		if (!isBlank(id) && !isBlank(propertyName) && !isBlank(value)) {
			final String queryString = "update %s set %s = :value where id = :id";
			HibernateUtil.getSessionFactory().getCurrentSession()
					.createQuery(String.format(queryString, clazz.getSimpleName(), propertyName))
					.setParameter("value", value).setLong("id", id).executeUpdate();
		}
	}

	public void delete(String propertyName, int id) throws HibernateException {
		final String queryString = "delete from %s where %s = :id";
		HibernateUtil.getSessionFactory().getCurrentSession()
				.createQuery(String.format(queryString, clazz.getSimpleName(), propertyName)).setLong("id", id)
				.executeUpdate();
	}

}
