/*

* ComicAuthor: classe model ComicAuthor -> tabella relazionale
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "comic_author", catalog = "fumetteria")
@AssociationOverrides({
		@AssociationOverride(name = "comicAuthorId.comic", joinColumns = @JoinColumn(name = "comic_id") ),
		@AssociationOverride(name = "comicAuthorId.author", joinColumns = @JoinColumn(name = "author_id") ) })
public class ComicAuthor implements java.io.Serializable {

	// attributo
	private ComicAuthorId comicAuthorId;

	public ComicAuthor() {
	}

	public ComicAuthor(ComicAuthorId id, Comic comic, Author author, String type) {
		this.comicAuthorId = id;
		this.comicAuthorId.setComic(comic);
		this.comicAuthorId.setAuthor(author);
		this.comicAuthorId.setType(type);
	}

	@EmbeddedId
	public ComicAuthorId getComicAuthorId() {
		return this.comicAuthorId;
	}

	public void setComicAuthorId(ComicAuthorId id) {
		this.comicAuthorId = id;
	}

	@Transient
	public Comic getComic() {
		return this.comicAuthorId.getComic();
	}

	public void setComic(Comic comic) {
		this.comicAuthorId.setComic(comic);
	}

	@Transient
	public Author getAuthor() {
		return this.comicAuthorId.getAuthor();
	}

	public void setAuthor(Author author) {
		this.comicAuthorId.setAuthor(author);
	}

	@Transient
	public String getType() {
		return this.comicAuthorId.getType();
	}

	public void setType(String type) {
		this.comicAuthorId.setType(type);
	}

}
