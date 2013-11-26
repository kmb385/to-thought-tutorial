package com.cloudfoundry.tothought.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="POST")
@DiscriminatorColumn(name="POST_TYPE", discriminatorType=DiscriminatorType.STRING)
public abstract class AbstractPost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	protected Integer postId;

	@Column(name = "TITLE")
	protected String title;

	@Column(name = "POST_DATE")
	protected Date postDate;

	@Embedded
	protected Stamp stamp;

	@ManyToOne
	@JoinColumn(name = "SERIES_ID")
	private Series series;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public Stamp getStamp() {
		return stamp;
	}

	public void setStamp(Stamp stamp) {
		this.stamp = stamp;
	}

	public Series getSeries() {
		return series;
	}

	public void setSeries(Series series) {
		this.series = series;
	}

}
