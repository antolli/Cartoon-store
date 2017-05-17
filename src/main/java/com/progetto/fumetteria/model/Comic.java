/*

* Comic: class model Fumetto
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "comic", catalog = "fumetteria")
public class Comic implements java.io.Serializable {

	// attributi
	private static final long serialVersionUID = 1L;
	private Integer id;
	private PublishingHouse publishingHouse;
	private String title;
	private int vol;
	private int edition;
	private Date publication;
	private String urlImageCover;
	private String description;
	private int stock;
	private BigDecimal value;
	private int showHome;
	private Set<Genre> genres = new HashSet<Genre>(0);
	private Set<Notice> notices = new HashSet<Notice>(0);
	private Set<ComicAuthor> comicAuthors = new HashSet<ComicAuthor>(0);
	private Set<Note> notes = new HashSet<Note>(0);
	private Set<Integer> comicAuthorsEditorId = new HashSet<Integer>(0);
	private Set<Integer> comicAuthorsFinalistId = new HashSet<Integer>(0);
	private Set<Integer> comicAuthorsWriterId = new HashSet<Integer>(0);
	private Set<Integer> genresIds = new HashSet<Integer>(0);
	private MultipartFile file;
	private Set<Reserve> reserveList;

	public Comic() {

	}

	public Comic(PublishingHouse publishingHouse, String title, int vol, int edition, Date publication,
			String description, int stock, BigDecimal value, int showHome) {
		this.publishingHouse = publishingHouse;
		this.title = title;
		this.vol = vol;
		this.edition = edition;
		this.publication = publication;
		this.description = description;
		this.stock = stock;
		this.value = value;
		this.showHome = showHome;
	}

	public Comic(PublishingHouse publishingHouse, String title, int vol, int edition, Date publication,
			String urlImageCover, String description, int stock, BigDecimal value, int showHome, Set<Genre> genres,
			Set<Notice> notices, Set<ComicAuthor> comicAuthors, Set<Note> notes) {
		this.publishingHouse = publishingHouse;
		this.title = title;
		this.vol = vol;
		this.edition = edition;
		this.publication = publication;
		this.urlImageCover = urlImageCover;
		this.description = description;
		this.stock = stock;
		this.value = value;
		this.showHome = showHome;
		this.genres = genres;
		this.notices = notices;
		this.comicAuthors = comicAuthors;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publishing_house_id", nullable = false)
	public PublishingHouse getPublishingHouse() {
		return this.publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
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

	@Column(name = "url_image_cover", length = 85)
	public String getUrlImageCover() {
		return this.urlImageCover;
	}

	public void setUrlImageCover(String urlImageCover) {
		this.urlImageCover = urlImageCover;
	}

	@Column(name = "description", nullable = false, length = 300)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "stock", nullable = false)
	public int getStock() {
		return this.stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name = "value", nullable = false, precision = 8)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Column(name = "show_home", nullable = false)
	public int getShowHome() {
		return this.showHome;
	}

	public void setShowHome(int showHome) {
		this.showHome = showHome;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "comic_genre", catalog = "fumetteria", joinColumns = {
			@JoinColumn(name = "comic_id", nullable = false, updatable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "genre_id", nullable = false, updatable = true) })
	public Set<Genre> getGenres() {
		return this.genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	public Set<Notice> getNotices() {
		return this.notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comicAuthorId.comic")
	public Set<ComicAuthor> getComicAuthors() {
		return this.comicAuthors;
	}

	public void setComicAuthors(Set<ComicAuthor> comicAuthors) {
		this.comicAuthors = comicAuthors;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comic")
	public Set<Note> getNotes() {
		return this.notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	@Transient
	public Set<Integer> getComicAuthorsEditorId() {
		return comicAuthorsEditorId;
	}

	public void setComicAuthorsEditorId(Set<Integer> comicAuthorsEditorId) {
		this.comicAuthorsEditorId = comicAuthorsEditorId;
	}

	@Transient
	public Set<Integer> getComicAuthorsFinalistId() {
		return comicAuthorsFinalistId;
	}

	public void setComicAuthorsFinalistId(Set<Integer> comicAuthorsFinalistId) {
		this.comicAuthorsFinalistId = comicAuthorsFinalistId;
	}

	@Transient
	public Set<Integer> getComicAuthorsWriterId() {
		return comicAuthorsWriterId;
	}

	public void setComicAuthorsWriterId(Set<Integer> comicAuthorsWriterId) {
		this.comicAuthorsWriterId = comicAuthorsWriterId;
	}

	@Transient
	public Set<Integer> getGenresIds() {
		return genresIds;
	}

	public void setGenresIds(Set<Integer> genresIds) {
		this.genresIds = genresIds;
	}

	@Transient
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@OneToMany(mappedBy = "comic")
	public Set<Reserve> getReserveList() {
		return reserveList;
	}

	public void setReserveList(Set<Reserve> reserveList) {
		this.reserveList = reserveList;
	}

}
