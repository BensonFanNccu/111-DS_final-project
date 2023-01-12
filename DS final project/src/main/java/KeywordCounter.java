import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;

public class KeywordCounter {
	private String urlStr;
    private String content;
    
    public KeywordCounter(String urlStr){
    	this.urlStr = urlStr;
    }
    
    private String fetchContent() {
    	String retVal = "";
    	try {
    		this.urlStr = URLDecoder.decode(this.urlStr , "utf-8");
        	URL url = new URL(this.urlStr);
        	
    		URLConnection conn = url.openConnection();
    		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
    		InputStream in = conn.getInputStream();
    		BufferedReader br = new BufferedReader(new InputStreamReader(in));
    		
    		String line = null;
    		
    		while ((line = br.readLine()) != null){
    		    retVal = retVal + line + "\n";
    		}
    		
    	} catch(Exception e) {
    		
    	}
    	
		return retVal;
    }
    
    public int BoyerMoore(String T, String P){
    	if(P.length() == 0 || T.length() == 0) {
    		return -1;
    	}
    	
    	int i = P.length() -1;
        int j = P.length() -1;
        int result = 0;
        
        while(i <= T.length() - 1) {
        	if(T.charAt(i) == P.charAt(j)) {
        		if(j == 0) {
        			result++;
        			i = i + P.length() - j;
        			j = P.length() - 1;
        		} else {
        			i--;
        			j--;
        		}
        	} else {
        		int l = last(T.charAt(i), P);
        		if(l == -1) {
        			i = i + P.length();
        			j = P.length() - 1;
        		} else {
        			i = i + P.length() - min(j, l + 1);
            		j = P.length() - 1;
        		}
        	}
        }
        
        return result;

    }

    public int last(char c, String P){
        for (int i = P.length()-1; i >= 0; i--){
            if (P.charAt(i) == c){
                return i;
            }
        }
        return -1;
    }

    public int min(int a, int b){
        if (a < b)
            return a;
        else if (b < a)
            return b;
        else 
            return a;
    }
    
    public int countKeyword(String keyword) {
		if (content == null){
		    content = fetchContent();
		}
		
		content = content.toUpperCase();
		keyword = keyword.toUpperCase();
		
		return BoyerMoore(content, keyword);
    }
}
