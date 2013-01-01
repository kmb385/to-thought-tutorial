package com.cloudfoundry.tothought.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TAG")
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TAG_ID")
	private Integer tagId;
	
	@Column(name="NAME")
	private String name;

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
