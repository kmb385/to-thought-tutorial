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

	
}
