/*

* User: class model per gli Utenti
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
@Table(name = "user", catalog = "fumetteria")
public class User implements java.io.Serializable {

	// attributi
	private Integer id;
	private String name;
	private String email;
	private String lastName;
	private String password;
	private String typeUser;
	private Set<Phone> phones = new HashSet<Phone>(0);
	private Set<Reserve> reserves = new HashSet<Reserve>(0);
	private Set<Note> notes = new HashSet<Note>(0);
	private String token;

	public User() {
	}

	public User(String name, String email, String lastName, String password, String typeUser) {
		this.name = name;
		this.email = email;
		this.lastName = lastName;
		this.password = password;
		this.typeUser = typeUser;
	}

	public User(String name, String email, String lastName, String password, String typeUser, Set<Phone> phones,
			Set<Reserve> reserves, Set<Note> notes) {
		this.name = name;
		this.email = email;
		this.lastName = lastName;
		this.password = password;
		this.typeUser = typeUser;
		this.phones = phones;
		this.reserves = reserves;
		this.notes = notes;
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

	@Column(name = "name", nullable = false, length = 60)
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

	@Column(name = "last_name", nullable = false, length = 45)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "password", nullable = false, length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "type_user", nullable = false, length = 45)
	public String getTypeUser() {
		return this.typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Phone> getPhones() {
		return this.phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Reserve> getReserves() {
		return this.reserves;
	}

	public void setReserves(Set<Reserve> reserves) {
		this.reserves = reserves;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	@Column(name = "token", nullable = true, length = 255)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
