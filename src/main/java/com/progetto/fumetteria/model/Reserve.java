/*

* Reserve: class model per le Prenotazioni
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "reserve", catalog = "fumetteria")
public class Reserve implements java.io.Serializable {

	// attributi
	private Integer id;
	private User user;
	private Date data;
	private BigDecimal total;
	private Integer status;
	private Comic comic;
	private int qty;
	private BigDecimal value;

	public Reserve() {
	}

	public Reserve(User user, Date data, BigDecimal total) {
		this.user = user;
		this.data = data;
		this.total = total;
	}

	public Reserve(User user, Date data, BigDecimal total, Integer status, Comic comic, int qty, BigDecimal value) {
		this.user = user;
		this.data = data;
		this.total = total;
		this.status = status;
		this.comic = comic;
		this.qty = qty;
		this.value = value;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false, length = 0)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "total", nullable = false, precision = 8)
	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comic_id", nullable = false, insertable = true, updatable = true)
	public Comic getComic() {
		return this.comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	@Column(name = "qty", nullable = false)
	public int getQty() {
		return this.qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Column(name = "value", nullable = false, precision = 8)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
