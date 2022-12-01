
public class KeywordCounter {

	private String content;
    private Keyword[] keyList; 
    
    public KeywordCounter(String content, Keyword[] k){
    	this.content = content;
    	this.keyList = k;
    }
    
    public int count(String T, String P){
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
        		i = i + P.length() - min(j, l + 1);
            	j = P.length() - 1;
        	}
        }
        
        return result;
    }

    public int last(char c, String P){
    	for(int i = P.length() - 1; i >= 0; i--) {
    		if(P.charAt(i) == c) {
    			return i;
    		}
    	}
        return -1;
    }

    public int min(int a, int b){
        if (a <= b)
            return a;
        else
            return b;
    }
    
    public double countScore() {
    	double score = 0;
    	for(int i = 0; i < keyList.length; i++) {
    		score += count(content, keyList[i].getName()) * keyList[i].getWeight();
    	}
    	
    	return score;
    }
}
