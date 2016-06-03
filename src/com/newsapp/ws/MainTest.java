package com.newsapp.ws;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.xmlpull.v1.XmlPullParserException;

public class MainTest {

	//public static void main(String[] args) throws JAXBException, IOException, XmlPullParserException, URISyntaxException {
	public static void RunTask(ServletContext context) throws JAXBException, IOException, XmlPullParserException, URISyntaxException {
		String pp[] = {
				"http://timesofindia.indiatimes.com/rssfeeds/4719148.cms",
				"http://indiatoday.intoday.in/rss/article.jsp?sid=120"
				};

		int i =1;
		ArrayList<Feed> feeds = new ArrayList<Feed>();
		Feed feed = null;
		for(String m : pp){
			InputStream s;
			URL url = new URL(m);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www-only-proxy.services.boeing.com",31061));
			HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
			s = con.getInputStream();
			feed = RSS.getData(s,context);
			feeds.add(feed);
		}
		
		FeedList list = new FeedList();
					
		list.setFeedList(feeds);
		JAXBContext JAXcontext = JAXBContext.newInstance(FeedList.class);
		Marshaller m = JAXcontext.createMarshaller();
		File file = new File(System.getProperty("user.dir")+File.separator+"newsfeedxmls.xml" );
		if(!file.exists()){
			file.createNewFile();
		}
		m.setProperty("jaxb.encoding", "UTF-8");
		m.marshal(list,file);
	}

}
