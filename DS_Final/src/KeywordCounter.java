import java.util.ArrayList;

public class KeywordCounter {

	private String content;

	private ArrayList<Keyword> keyList = new ArrayList<Keyword>();
    
    public KeywordCounter(String content, String userInput){
    	
    	this.content = content;
    	
		setKeylist();
		keyList.add(new Keyword(userInput, 5));
    }
    
    public void setKeylist() {
    	
    	keyList.add(new Keyword("足球", 4));
    	keyList.add(new Keyword("運動", 3));
    	keyList.add(new Keyword("球類", 3));
    	keyList.add(new Keyword("世足", 2.5));
    	keyList.add(new Keyword("世界盃", 1));
    	keyList.add(new Keyword("歐國盃", 1.5));
    	keyList.add(new Keyword("歐冠", 2));
    	keyList.add(new Keyword("美洲盃", 2));
    	keyList.add(new Keyword("C羅", 2));
    	keyList.add(new Keyword("梅西", 2.5));
    	keyList.add(new Keyword("姆巴佩", 2));
    	keyList.add(new Keyword("內馬爾", 2));
    	keyList.add(new Keyword("分數", 1.5));
    	keyList.add(new Keyword("越位", 1.5));
    	keyList.add(new Keyword("自由球", 0.5));
    	keyList.add(new Keyword("角球", 1));
    	keyList.add(new Keyword("足球鞋", 1));
    	keyList.add(new Keyword("足球衣", 1));
    	keyList.add(new Keyword("足球護脛", 0.5));
    	keyList.add(new Keyword("足球長襪", 0.5));
    	keyList.add(new Keyword("緋聞", -1));
    	
	}
    
    public int count(String text, String target){

        int result = 0;
        int finder = text.indexOf(target);
        
        while(finder != -1) {
        	result++;
        	finder = text.indexOf(target, finder + target.length());
        }
        
        return result;
    }
    
    public double countScore() {
    	
    	double score = 0;
    	
    	for(int i = 0; i < keyList.size(); i++) {
    		score += count(content, keyList.get(i).getName()) * keyList.get(i).getWeight();
    	}
    	
    	return score;
    }
}
