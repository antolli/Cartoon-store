/*

* Genre: class Model Genre
*

* version 1.0

*

* 01/08/2015

*

* ©iTrio

*/
package com.progetto.fumetteria.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre", catalog = "fumetteria")
public class Genre implements java.io.Serializable {

	// attributi
	private Integer id;
	private String name;
	private Set<Comic> comics = new HashSet<Comic>(0);

	public Genre() {
	}

	public Genre(String name) {
		this.name = name;
	}

	public Genre(String name, Set<Comic> comics) {
		this.name = name;
		this.comics = comics;
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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "comic_genre", catalog = "fumetteria", joinColumns = {
			@JoinColumn(name = "genre_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "comic_id", nullable = false, updatable = false) })
	public Set<Comic> getComics() {
		return this.comics;
	}

	public void setComics(Set<Comic> comics) {
		this.comics = comics;
	}

}
