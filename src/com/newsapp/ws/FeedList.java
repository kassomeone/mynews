package com.newsapp.ws;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "FeedList")
public class FeedList {


	private ArrayList<Feed> feedList;

	public ArrayList<Feed> getFeedList() {
		return feedList;
	}

	@XmlElement (name = "Feed")
	public void setFeedList(ArrayList<Feed> feedList) {
		this.feedList = feedList;
	}
	
	
}
