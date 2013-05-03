package com.cloudfoundry.tothought;

/**
 * @author Kevin Bowersox
 * 
 */
public class Teaser {

	private String title;
	private String author;
	private String teaserText;
	
	
	public Teaser(String title, String author, String teaserText) {
		super();
		this.title = title;
		this.author = author;
		this.teaserText = teaserText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

}
