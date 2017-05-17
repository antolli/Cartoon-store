/*

* ComicAuthorId: class Model ComicAuthorId -> keys della relazione (key composta)
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ComicAuthorId implements java.io.Serializable {

	// attributi
	private static final long serialVersionUID = 1L;
	private Comic comic;
	private Author author;
	private String type;

	public ComicAuthorId() {
	}

	public ComicAuthorId(Comic comicId, Author authorId) {
		this.comic = comicId;
		this.author = authorId;
	}

	@ManyToOne
	public Comic getComic() {
		return this.comic;
	}

	public void setComic(Comic comicId) {
		this.comic = comicId;
	}

	@ManyToOne
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author authorId) {
		this.author = authorId;
	}

	@Column(name = "type", nullable = false, length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ComicAuthorId that = (ComicAuthorId) o;

		if (comic != null ? !comic.equals(that.comic) : that.comic != null)
			return false;
		if (author != null ? !author.equals(that.author) : that.author != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (comic != null ? comic.hashCode() : 0);
		result = 31 * result + (author != null ? author.hashCode() : 0);
		return result;
	}

}
