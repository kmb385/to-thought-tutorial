package com.cloudfoundry.tothought.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag {

	@Id
	@Column(name = "TAG_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tagId;

	@Column(name = "NAME")
	private String name;
	
	@ManyToMany(mappedBy="tags")
	private List<Post> posts = new ArrayList<Post>();
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
