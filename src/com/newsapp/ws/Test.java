package com.newsapp.ws;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;


public class Test {

	public static String getCurrentPath()
	{
		return System.getProperty("user.dir");
	}
	public static void DownloadImages(ArrayList<String> imgs,ServletContext context) throws IOException, URISyntaxException {
		
		//saveImage(imgs);
		//System.out.println(context.getContextPath());
		String path = context.getRealPath("WEB-INF/../");
		File file2 = new File(path);
		String fullPathToYourWebappRoot = file2.getCanonicalPath();			
		//System.out.println(fullPathToYourWebappRoot);
		
		File file = new File(getCurrentPath()+File.separator+"temp");
		int i = 0;
		for (File source : file.listFiles()) {
			//File ff = new File(getCurrentPath()+File.separator+"temp2"+File.separator);
			File ff = new File(fullPathToYourWebappRoot+File.separator+"imgs"+File.separator+source.getName()+".png");
			
		    BufferedImage image = ImageIO.read(source);
		    BufferedImage small = Scalr.resize(image,
		                                       Method.BALANCED,
		                                       Mode.AUTOMATIC,
		                                       305, 140,
		                                       Scalr.OP_ANTIALIAS);
		 
/*		    BufferedImage cropimage = Scalr.crop(image, 1000, 1000, Scalr.OP_ANTIALIAS);
		    BufferedImage padding   = Scalr.pad(image, 200, Scalr.OP_DARKER);
*/		    ImageIO.write(small, "jpg", ff);
		    
		    
/*			Thumbnails.of(source)
			.crop(Positions.TOP_CENTER)
			.outputFormat("png")
			.outputQuality(1.0f)
			.size(305,140)
			.toFiles(ff, Rename.NO_CHANGE);	*/		
		}

	}

	public static void saveImage(ArrayList<String> imgs) throws IOException{

		/*String[] imgs = {
				"https://static01.nyt.com/images/2016/04/23/us/00wilkes-web01/00wilkes-web01-moth.jpg",
				"https://static01.nyt.com/images/2016/05/29/us/26emails-web/26emails-web-moth-v2.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//myanmar-story_305_052616032226.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//bull_305_052616023539.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//l_052616022730.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//daler-story-_305_052616021845.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//rajnath_story_305_052616015508.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//khattar-story_305_052616013447.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//swamy_story_305_052616010336.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//kejriwal-modi-story_305_052616010203.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//italian-marines2pti_305_052616124436.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//secual-abuse-story_305_052616123203.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//punjab-story--_305_052616122749.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//kejriwal-story_305_052616114921.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//roadrage-2-safdarjung-2_305_052616110850.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//desai-story_305_052616105608.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//bajrang-dal-2_305_052616104528.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//pranab_305_052616100422.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//trump-story_305_052616080952.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//delhi-bjp,-isis-module-1_305_052616092724.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//big-b,-navy-doctor-2-95_305_052616094522.jpg",
				"http://media2.intoday.in//indiatoday//images//stories//isis-2-china-9_305_052616090526.jpg"
		};*/

		InputStream s;
		int i = 1;
		for(String img : imgs){
				
			URL url = new URL(img);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("www-only-proxy.services.boeing.com",31061));
			HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
			s = con.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(s);
			//System.out.println(i +" "+ img);
			String filename = img.substring(img.lastIndexOf("/")+1, img.length());
			String name=getCurrentPath()+File.separator+"temp"+File.separator+filename;
			System.out.println("name::::"+name);
			File f = new File(name);
			if(!f.exists())
				f.createNewFile();
			
			OutputStream os = new FileOutputStream(f);
			int c;
			while ((c = bis.read()) != -1) {
				os.write(c);
			}
			os.flush();
			os.close();
			i++;
		}
	}

}
