package com.newsapp.ws;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;



public class RSS {
	

	public static Feed getData(InputStream input,ServletContext context) throws IOException, XmlPullParserException, URISyntaxException{

/*		URL url = new URL("http://indiatoday.intoday.in/rss/article.jsp?sid=41");
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www-only-proxy.services.boeing.com", 31061));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection(proxy);
*/		
//		InputStream is = connection.getInputStream();		
		InputStreamReader reader = new InputStreamReader(input);
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser parser = factory.newPullParser();		
		parser.setInput(reader);

		int eventType = parser.getEventType();
		boolean isHeader = true;
		boolean insideItem = false;
		boolean truefeed = false;
		Feed feed = new Feed();
		FeedMessage msg = null;
		ArrayList<FeedMessage> msgList = new ArrayList<FeedMessage>();
		ArrayList<String> src = new ArrayList<String>();
		while(eventType != XmlPullParser.END_DOCUMENT){
			String tagName = parser.getName();
			switch(eventType){
			case XmlPullParser.START_TAG :
				//System.out.println(tagName);
				if("item".equalsIgnoreCase(tagName)){
					msg = new FeedMessage();
					insideItem = true;
					isHeader = false;
				}
				if("title".equalsIgnoreCase(tagName)){
					if(isHeader){
						
					}else{
						if(insideItem){
							msg.setTitle(parser.nextText());							
						}
					}
				}
				if("description".equalsIgnoreCase(tagName)){
					if(isHeader){
						
					}else{
						if(insideItem){	
							String l = parser.nextText();
							l = l.replace("\n", "").replace("\r", "");
							if(l.indexOf("<") != -1 && l.indexOf("<") != 0 ){
								//System.out.println( (l.substring(0, l.indexOf("<")-1)).trim());
								msg.setDescription((l.substring(0, l.indexOf("<")-1)).trim());
							}else{
								//System.out.println((l.substring(l.lastIndexOf(">")+1, l.length())).trim());
								msg.setDescription((l.substring(l.lastIndexOf(">")+1, l.length())).trim());
							}

							if(l.lastIndexOf("src") != -1){
								truefeed = true;
								String subl = (	l.substring(l.lastIndexOf("src")+5	,	l.lastIndexOf(">")		)	).trim();
								int index = subl.indexOf("'");
								if(index == -1)
								 index = subl.indexOf("\"");
								String sub2 = subl.substring(0,index);
								//System.out.println(	"src --->   "+ sub2.substring(sub2.lastIndexOf("/")+1,sub2.length()));
								msg.setImg(sub2);
								src.add(msg.getImg());
							}
						}
					}
				}
				if("link".equalsIgnoreCase(tagName)){
					if(insideItem){
						msg.setLink(parser.nextText());
					}
				}
				if("pubDate".equalsIgnoreCase(tagName)){
					if(insideItem){
						msg.setPubDate(parser.nextText());
					}
				}					
				break;

			case XmlPullParser.END_TAG:

				if("item".equalsIgnoreCase(tagName) && insideItem && truefeed){
					msgList.add(msg);
					insideItem = false;
					truefeed = false;
				}
				break;
			}
			eventType = parser.next();
		}
		feed.setFeedMsg(msgList);
		Test.DownloadImages(src,context);
		return feed;
	}


}
