package com.newsapp.ws;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;

import org.xmlpull.v1.XmlPullParserException;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		if("imgs".equalsIgnoreCase(request.getParameter("pathType"))){
			response.sendRedirect(response.encodeRedirectURL(System.getProperty("user.dir")+File.separator+"temp2"));
		}
		if("feedxml".equalsIgnoreCase(request.getParameter("pathType"))){
			
			
			//out.print(System.getProperty("user.dir")+File.separator+"newsfeedxmls.xml");
			InputStream is = new FileInputStream(System.getProperty("user.dir")+File.separator+"newsfeedxmls.xml");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bis = new BufferedReader(isr);
			String s;
			while( (s= bis.readLine()) != null){
				out.println(s);
			}
			
			//response.sendRedirect(response.encodeRedirectURL(System.getProperty("user.dir")+File.separator+"newsfeedxmls.xml"));
		}
		else{
			
			try {
				MainTest.RunTask(getServletContext());
			} catch (JAXBException | XmlPullParserException | URISyntaxException e) {
				e.printStackTrace();
			}			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
