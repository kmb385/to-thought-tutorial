package com.cloudfoundry.tothought.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="POST")
public class ContentPost extends AbstractPost {

	@Column(name="CONTENT_URL")
	private String contentUrl;

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

}
