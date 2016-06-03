package com.newsapp.ws;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement (name = "FEED")
public class Feed {

	private ArrayList<FeedMessage> feedMsg;
	
	
	public ArrayList<FeedMessage> getFeedMsg() {
		return feedMsg;
	}
	@XmlElement (name = "FeedMessage")
	public void setFeedMsg(ArrayList<FeedMessage> feedMsg) {
		this.feedMsg = feedMsg;
	}

	
}
