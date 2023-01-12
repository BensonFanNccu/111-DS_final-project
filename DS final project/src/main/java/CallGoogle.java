import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CallGoogle {
	private HTMLHandler handler;
	
	public CallGoogle() {
		handler = new HTMLHandler();
	}
	
	private String fetchSearchContent(String input) throws Exception{
		String searchResult = "";
		String encodedKeyword = URLEncoder.encode(input, "UTF-8");
		
		URL u = new URL("http://www.google.com/search?q=" + encodedKeyword + "\"足球\"" + "&oe=utf8&num=20");
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/108.0.0.0");
		
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;
		
		while((line = bufReader.readLine()) != null) {
			searchResult += line;
		}
		
		return searchResult;
	}
	
	public HashMap<String, String> generateWebList(String input) throws Exception{
		return handler.generateWebList(fetchSearchContent(input));
	}
	
	public HashMap<String, String> deriveRelateKeywords(String input) throws Exception{
		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc;
		doc = Jsoup.parse(fetchSearchContent(input));
		
		Elements lis = doc.select("div");
		lis = lis.select(".BNeawe");
		lis = lis.select(".s3v9rd");
		lis = lis.select(".AP7Wnd");
		lis = lis.select(".lRVwie");
		
		for(Element li : lis) {
			try {
				String title = "";
				String citeUrl = "";
				
				if(!li.toString().contains("span")) {
					title = li.text();
					String encodedKeyword = URLEncoder.encode(title, "UTF-8");
					citeUrl = "http://www.google.com/search?q=" + encodedKeyword + "&oe=utf8";
				}
				
				if(title.equals("")) {
					continue;
				}
				
				retVal.put(title, citeUrl);
			} catch (IndexOutOfBoundsException e) {
				
			}
		}
		
		if(retVal.isEmpty()) {
			retVal.put(input, "http://www.google.com/search?q=" + URLEncoder.encode(input, "UTF-8") + "&oe=utf8");
			retVal.put(input + "足球", "http://www.google.com/search?q=" + URLEncoder.encode(input + "足球", "UTF-8") + "&oe=utf8");
			retVal.put("足球", "http://www.google.com/search?q=" + URLEncoder.encode("足球", "UTF-8") + "&oe=utf8");
			retVal.put(input + "與足球", "http://www.google.com/search?q=" + URLEncoder.encode(input + "與足球", "UTF-8") + "&oe=utf8");
			retVal.put("世足", "http://www.google.com/search?q=" + URLEncoder.encode("世足", "UTF-8") + "&oe=utf8");
			retVal.put("足球賽事", "http://www.google.com/search?q=" + URLEncoder.encode("足球賽事", "UTF-8") + "&oe=utf8");
		}
		
		return retVal;
	}
}
