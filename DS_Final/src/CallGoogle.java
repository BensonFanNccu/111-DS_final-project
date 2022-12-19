import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CallGoogle {
	
	private String searchKeyword;
	private String content;
	
	private ArrayList<WebPage> webList;
	
	public CallGoogle(String searchKeyword) throws IOException {
		
		this.searchKeyword = searchKeyword;
		content = getOriginalSearchPage();
		webList = new ArrayList<WebPage>();
	}
	
	public String getOriginalSearchPage() throws IOException {
		
		String retVal = "";
		String encodedKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
		
		URL u = new URL("http://www.google.com/search?q=" + encodedKeyword + "&oe=utf8&num=20");
		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null) {
			retVal += line;
		}
		
		return retVal;
	}
	
	public void callAlgorithm() throws IOException {

		callGoogle();
		callDefaultWebsite();
	}
	
	public void callGoogle() throws IOException {
		
		if(content == null) {
			content = getOriginalSearchPage();
		}
		
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div");
		lis = lis.select(".kCrYT");
		
		for(Element li : lis) {
			try {
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				
				if(title.equals("")) {
					continue;
				}
				
				webList.add(new WebPage(citeUrl.substring(7), title));
				
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
	}
	
	public void callDefaultWebsite() {
		
		//從我們預設的網站中找適合的webpage
		HTMLHandler han1 = new HTMLHandler("https://www.sportsv.net/football");
		HTMLHandler han2 = new HTMLHandler("https://www.goal.com/zh-cn?fbclid=IwAR25-TwTK4GW_KHn0ZYuyLLSbTfFNVRRWPwfqLR9ZT88W2y8E-LoJ7Pzev4");
		HTMLHandler han3 = new HTMLHandler("https://xn--2022-pc5fw22r14bz8dgx6e7qb.com/?fbclid=IwAR2zXv2SkGUzjWJQpRv3gzn1GUClJ5jf-OfgyLdOqvSvqQgpi4uz3WQDIgc");
		HTMLHandler han4 = new HTMLHandler("https://www.ctfa.com.tw/?fbclid=IwAR21Nuo1lLphYnXZq3JME9PnrNjuNg6wmxudL_DT2DkmFCwvUBEjzwmhCtk");
		
		for(String link: han1.getSublink()) {
			
		}
		
		for(String link: han2.getSublink()) {
			
		}
		
		for(String link: han3.getSublink()) {
			
		}

		for(String link: han4.getSublink()) {
	
		}
		
	}
	
	public ArrayList<WebPage> getWebList() {
		return webList;
	}
	
	public ArrayList<String> deriveRelateKeywords() throws IOException {
		
		ArrayList<String> relativeKeywordList = new ArrayList<String>();
		
		if(content.isEmpty()) {
			content = getOriginalSearchPage();
		}
		
		int finder = content.indexOf("<div class=\"s75CSd OhScic AB4WFF\">");
		content = content.substring(finder);
		
		while(finder != -1) {
			
			int head = content.indexOf("<b>");
			int tail = content.indexOf("</b>");
			
			String relKeyword = content.substring(head + 3, tail);
			relativeKeywordList.add(relKeyword);
			
			content = content.substring(tail + 4);
			finder = content.indexOf("<div class=\"s75CSd OhScic AB4WFF\">");
		}
		
		return relativeKeywordList;
	}
}
