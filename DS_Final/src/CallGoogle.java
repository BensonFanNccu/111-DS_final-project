import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CallGoogle {

	private ArrayList<Keyword> keylist = new ArrayList<Keyword>();
	
	public CallGoogle() {
		setKeylist();
	}
	
	public void setKeylist() {
		keylist.add(new Keyword("足球", 4));
		keylist.add(new Keyword("運動", 3));
		keylist.add(new Keyword("球類", 3));
		keylist.add(new Keyword("世足", 2.5));
		keylist.add(new Keyword("世界盃", 1));
		keylist.add(new Keyword("歐國盃", 1.5));
		keylist.add(new Keyword("歐冠", 2));
		keylist.add(new Keyword("美洲盃", 2));
		keylist.add(new Keyword("C羅", 2));
		keylist.add(new Keyword("梅西", 2.5));
		keylist.add(new Keyword("姆巴佩", 2));
		keylist.add(new Keyword("內馬爾", 2));
		keylist.add(new Keyword("分數", 1.5));
		keylist.add(new Keyword("越位", 1.5));
		keylist.add(new Keyword("自由球", 0.5));
		keylist.add(new Keyword("角球", 1));
		keylist.add(new Keyword("足球鞋", 1));
		keylist.add(new Keyword("足球衣", 1));
		keylist.add(new Keyword("足球護脛", 0.5));
		keylist.add(new Keyword("足球長襪", 0.5));
		keylist.add(new Keyword("緋聞", -1));
	}
	
	public ArrayList<Keyword> getKeylist() {
		return keylist;
	}
	
	public String callAlgorithm(String searchKeyword) throws IOException {
		keylist.add(new Keyword(searchKeyword, 5));
		
		String targetUrl = "http://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=20";
		String retVal = "";

		URL u = new URL(targetUrl);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		
		return retVal;
	}
}
