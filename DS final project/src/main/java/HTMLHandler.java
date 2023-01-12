import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class HTMLHandler {
	
	public HashMap<String, String> generateWebList(String content) throws IOException {
		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis) {
			try {
				String citeUrl = li.select("a").get(0).attr("href");
				int cut = citeUrl.indexOf("&sa=U&ved");
				citeUrl = citeUrl.substring(7, cut);
				citeUrl = citeUrl.replaceAll("%3F", "?");
				citeUrl = citeUrl.replaceAll("%3D", "=");
				citeUrl = citeUrl.replaceAll("%26", "&");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) {
					continue;
				}
				
				int finder = title.indexOf("- ");
				if(title.substring(finder + 2).startsWith("維基百科") || title.substring(finder + 2).startsWith("维基百科")) {
					if(title.charAt(finder - 1) == ' ') {
						String encodedKeyword = URLEncoder.encode(title.substring(0, finder - 1), "UTF-8");
						citeUrl = "https://zh.wikipedia.org/wiki/" + encodedKeyword;
					} else {
						String encodedKeyword = URLEncoder.encode(title.substring(0, finder), "UTF-8");
						citeUrl = "https://zh.wikipedia.org/wiki/" + encodedKeyword;
					}
				}
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) {
				
			}
		}
		return retVal;
	}
	
	public String fetchHTMLContent(String url) throws Exception{
		String content = "";
		
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/108.0.0.0");
		
		try {
			InputStream in = conn.getInputStream();
			InputStreamReader inReader = new InputStreamReader(in, "utf-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;
			
			while((line = bufReader.readLine()) != null) {
				content += line;
			}
		}catch(Exception e) {
			
		}
		return content;
	}
	
	
}
