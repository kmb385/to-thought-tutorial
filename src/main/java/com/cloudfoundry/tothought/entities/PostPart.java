package com.cloudfoundry.tothought.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="POST_PART")
public class PostPart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="POST_PART_ID")
	private Integer postPartId;
	
	@Column(name="BODY")
	private String body;

	@OneToOne(mappedBy="postPart")
	private Post post;
	
	public Integer getPostPartId() {
		return postPartId;
	}

	public void setPostPartId(Integer postPartId) {
		this.postPartId = postPartId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
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
		PostPart other = (PostPart) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		return true;
	}

	
}
