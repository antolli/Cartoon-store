/*

* PublishingHouse: class model PublishingHouse
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "publishing_house", catalog = "fumetteria")
public class PublishingHouse implements java.io.Serializable {

	// attributi
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private Set<Comic> comics = new HashSet<Comic>(0);

	public PublishingHouse() {
	}

	public PublishingHouse(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public PublishingHouse(String name, String email, String phone, Set<Comic> comics) {
		this.name = name;
		this.email = email;
		this.phone = phone;
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

	@Column(name = "email", nullable = false, length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", nullable = false, length = 14)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publishingHouse")
	public Set<Comic> getComics() {
		return this.comics;
	}

	public void setComics(Set<Comic> comics) {
		this.comics = comics;
	}

}
