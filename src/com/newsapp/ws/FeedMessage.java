package com.newsapp.ws;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FeedMessage {

	private String title;
	private String link;
	private String description;
	private String img;
	private String pubDate;
	
	
	public String getTitle() {
		return title;
	}
	
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	
	public String getImg() {
		return img;
	}

	@XmlElement
	public void setImg(String img) {
		this.img = img;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	@XmlElement
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	
	@Override
	public String toString() {
	
		return title +"--->"+description +"--->"+link +"--->"+pubDate;
	}
	
}
