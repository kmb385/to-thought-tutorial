package com.cloudfoundry.tothought.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHOR")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AUTHOR_ID")
	private Integer authorId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SPONSOR_ID")
	private Author sponsor;

	@OneToMany(mappedBy = "sponsor")
	private List<Author> sponsoredAuthors = new ArrayList<Author>();

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Author getSponsor() {
		return sponsor;
	}

	public void setSponsor(Author sponsor) {
		this.sponsor = sponsor;
	}

	public List<Author> getSponsoredAuthors() {
		return sponsoredAuthors;
	}

	public void setSponsoredAuthors(List<Author> sponsoredAuthors) {
		this.sponsoredAuthors = sponsoredAuthors;
	}

}
