import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class HTMLHandler {

	private String urlstr;
	
	public HTMLHandler(String url){
    	this.urlstr = url;
    }
	
	public String getHtml() throws IOException{
		
		URL url = new URL(urlstr);
		InputStream in = url.openStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
	
		StringBuffer sb = new StringBuffer();
		String line = null;
		
		while ((line = br.readLine()) != null) {
		    sb.append(line + "\n");
		}
		
		br.close();
	
		return sb.toString();
    }
	
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
    
    public String[] getSublink() {
    	
    }
    
//	public String getBody() {
//	
//	}

}
