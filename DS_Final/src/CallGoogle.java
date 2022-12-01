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
		keylist.add(new Keyword("���y", 4));
		keylist.add(new Keyword("�B��", 3));
		keylist.add(new Keyword("�y��", 3));
		keylist.add(new Keyword("�@��", 2.5));
		keylist.add(new Keyword("�@�ɬ�", 1));
		keylist.add(new Keyword("�ڰ��", 1.5));
		keylist.add(new Keyword("�ګa", 2));
		keylist.add(new Keyword("���w��", 2));
		keylist.add(new Keyword("Cù", 2));
		keylist.add(new Keyword("����", 2.5));
		keylist.add(new Keyword("�i�ڨ�", 2));
		keylist.add(new Keyword("������", 2));
		keylist.add(new Keyword("����", 1.5));
		keylist.add(new Keyword("�V��", 1.5));
		keylist.add(new Keyword("�ۥѲy", 0.5));
		keylist.add(new Keyword("���y", 1));
		keylist.add(new Keyword("���y�c", 1));
		keylist.add(new Keyword("���y��", 1));
		keylist.add(new Keyword("���y�@�H", 0.5));
		keylist.add(new Keyword("���y����", 0.5));
		keylist.add(new Keyword("�n�D", -1));
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
