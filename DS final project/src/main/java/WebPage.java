import java.util.ArrayList;

public class WebPage {
	private String url;
	private String name;
	private double score;
	private KeywordCounter k;
	
	public WebPage(String name, String url) {
		this.name = name;
		this.url = url;
		score = 0;
	}
	
	public void setScore(ArrayList<Keyword> keywords) {
		
		k = new KeywordCounter(url);
		score = 0;
		
		for(Keyword key : keywords) {
			score += key.getWeight() * k.countKeyword(key.getName());
		}
	}
	
	public double getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}
}
