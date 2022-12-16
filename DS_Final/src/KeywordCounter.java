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
    	
    	keyList.add(new Keyword("���y", 4));
    	keyList.add(new Keyword("�B��", 3));
    	keyList.add(new Keyword("�y��", 3));
    	keyList.add(new Keyword("�@��", 2.5));
    	keyList.add(new Keyword("�@�ɬ�", 1));
    	keyList.add(new Keyword("�ڰ��", 1.5));
    	keyList.add(new Keyword("�ګa", 2));
    	keyList.add(new Keyword("���w��", 2));
    	keyList.add(new Keyword("Cù", 2));
    	keyList.add(new Keyword("����", 2.5));
    	keyList.add(new Keyword("�i�ڨ�", 2));
    	keyList.add(new Keyword("������", 2));
    	keyList.add(new Keyword("����", 1.5));
    	keyList.add(new Keyword("�V��", 1.5));
    	keyList.add(new Keyword("�ۥѲy", 0.5));
    	keyList.add(new Keyword("���y", 1));
    	keyList.add(new Keyword("���y�c", 1));
    	keyList.add(new Keyword("���y��", 1));
    	keyList.add(new Keyword("���y�@�H", 0.5));
    	keyList.add(new Keyword("���y����", 0.5));
    	keyList.add(new Keyword("�n�D", -1));
    	
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
