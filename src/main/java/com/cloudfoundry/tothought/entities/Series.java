package com.cloudfoundry.tothought.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SERIES")
public class Series {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SERIES_ID")
	private Integer seriesId;
	
	@Column(name="TITLE")
	private String title;
	
	@OneToMany(mappedBy="series")
	private List<Post> posts = new ArrayList<Post>();

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

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	
}
