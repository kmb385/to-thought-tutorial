package com.cloudfoundry.tothought.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "POST")
@NamedQueries(value = {
		@NamedQuery(name = "Post.findPosts", query = "select p from Post p"),
		@NamedQuery(name = "Post.teaser", query = "select " +
				"p.title, p.stamp.author, pp.body " +
				"from Post p join p.postPart pp"),
		@NamedQuery(name = "Post.teaser.constructor", query = "select " +
				"NEW com.cloudfoundry.tothought.Teaser(p.title, p.stamp.author, " +
				"SUBSTRING(pp.body, 1,200)) " +
				"from Post p join p.postPart pp" +
				" where p.stamp.author like :name")
		
})
public class Post extends AbstractPost {

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "POST_PART_ID")
	private PostPart postPart;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	@OrderBy("CREATED_DT ASC")
	private List<Comment> comments = new LinkedList<Comment>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "POST_TAG", joinColumns = { @JoinColumn(name = "POST_ID") }, inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
	private List<Tag> tags = new ArrayList<Tag>();

	@ElementCollection
	@Column(name = "URL")
	@CollectionTable(name = "POST_MENTION", joinColumns = { @JoinColumn(name = "POST_ID") })
	private List<String> urls = new ArrayList<String>();

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public PostPart getPostPart() {
		return postPart;
	}

	public void setPostPart(PostPart postPart) {
		this.postPart = postPart;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((postDate == null) ? 0 : postDate.hashCode());
		result = prime * result + ((stamp == null) ? 0 : stamp.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (postDate == null) {
			if (other.postDate != null)
				return false;
		} else if (!postDate.equals(other.postDate))
			return false;
		if (stamp == null) {
			if (other.stamp != null)
				return false;
		} else if (!stamp.equals(other.stamp))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}
