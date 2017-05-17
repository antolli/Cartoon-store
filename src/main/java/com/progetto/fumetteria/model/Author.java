/*

* Author: class model Author
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "author", catalog = "fumetteria")
public class Author implements java.io.Serializable {
	// attributi
	private Integer id;
	private String name;
	private Set<ComicAuthor> comicAuthors = new HashSet<ComicAuthor>(0);

	public static final String editor = "Editor";

	public Author() {
	}

	public Author(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Author(Integer id, String name, Set<ComicAuthor> comicAuthors) {
		this.id = id;
		this.name = name;
		this.comicAuthors = comicAuthors;

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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comicAuthorId.author")
	public Set<ComicAuthor> getComicAuthors() {
		return this.comicAuthors;
	}

	public void setComicAuthors(Set<ComicAuthor> comicAuthors) {
		this.comicAuthors = comicAuthors;
	}

}
