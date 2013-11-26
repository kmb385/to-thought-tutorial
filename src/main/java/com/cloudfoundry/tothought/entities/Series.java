package com.cloudfoundry.tothought.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SERIES")
public class Series {

	@Id
	@GeneratedValue
	@Column(name = "SERIES_ID")
	private Integer seriesId;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "DESCRIPTION")
	private String description;

	@OneToMany(mappedBy = "series")
	private List<AbstractPost> posts;

	public Integer getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<AbstractPost> getPosts() {
		return posts;
	}

	public void setPosts(List<AbstractPost> posts) {
		this.posts = posts;
	}

}
