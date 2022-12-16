import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HTMLHandler {

	private String urlstr;
	
	public HTMLHandler(String url){
    	this.urlstr = url;
    }
	
	public String getHtml() throws IOException{
		
		URL url = new URL(urlstr);
		URLConnection conn = url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
	
		String retVal = "";
		String line = null;
		
		while ((line = br.readLine()) != null) {
		    retVal = retVal + line + "\n";
		}
	
		return retVal;
    }
	

//	public String getBody() {
//		
//	}
//	
	public String getPageName() throws IOException {
		
		String pageName = "";
		String content = getHtml();
		
		int head = content.indexOf("<title>");
		int tail = content.indexOf("</title>");
		
		if(head != -1 && tail != -1) {
			pageName = content.substring(head + 7, tail);
		} else {
			return null;
		}
		
		return pageName;
	}
//    
//  public String getSubLink() {
//    	
//  }

}
