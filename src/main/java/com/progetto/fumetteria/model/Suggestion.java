/*

* Suggestion: class model Suggestion
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

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
@Table(name = "suggestion", catalog = "fumetteria")
public class Suggestion implements java.io.Serializable {

	// attributi
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private int vol;
	private int edition;
	private Date publication;
	private String description;
	private User user;

	public Suggestion() {

	}

	public Suggestion(User user, String title, int vol, int edition, Date publication, String description) {
		this.user = user;
		this.title = title;
		this.vol = vol;
		this.edition = edition;
		this.publication = publication;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	@Column(name = "title", nullable = false, length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "vol", nullable = false)
	public int getVol() {
		return this.vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	@Column(name = "edition", nullable = false)
	public int getEdition() {
		return this.edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "publication", nullable = false, length = 0)
	public Date getPublication() {
		return this.publication;
	}

	public void setPublication(Date publication) {
		this.publication = publication;
	}

	@Column(name = "description", nullable = false, length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
