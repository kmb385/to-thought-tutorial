package com.cloudfoundry.tothought.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMMENT")
public class Comment {

	@Id
	@Column(name="COMMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer commentId;
	
	@Embedded
	private Stamp stamp;
	
	@Column(name="BODY")
	private String body;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="POST_ID")
	private Post post;
	
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Stamp getStamp() {
		return stamp;
	}

	public void setStamp(Stamp stamp) {
		this.stamp = stamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((stamp == null) ? 0 : stamp.hashCode());
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
		Comment other = (Comment) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (stamp == null) {
			if (other.stamp != null)
				return false;
		} else if (!stamp.equals(other.stamp))
			return false;
		return true;
	}
	
	
}
