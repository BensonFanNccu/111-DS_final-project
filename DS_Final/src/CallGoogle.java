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
	
	public CallGoogle(String searchKeyword) {
		
		this.searchKeyword = searchKeyword;
		try {
			content = getOriginalSearchPage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getOriginalSearchPage() throws IOException {
		
		String retVal = "";
		String encodedKeyword = URLEncoder.encode(searchKeyword, "UTF-8");
		
		URL u = new URL("http://www.google.com/search?q=" + encodedKeyword + "&oe=utf8&num=32");
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
	
	public ArrayList<String> callAlgorithm() throws IOException {

		if(content == null) {
			content = getOriginalSearchPage();
		}
		
		ArrayList<String> webpageList = new ArrayList<String>();
		
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
				
				webpageList.add(citeUrl.substring(7));
				
			} catch (IndexOutOfBoundsException e) {
//				e.printStackTrace();
			}
		}
		
		return webpageList;
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
