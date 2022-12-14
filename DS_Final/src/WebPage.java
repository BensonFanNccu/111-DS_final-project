import java.io.IOException;
import java.util.ArrayList;

public class WebPage {
	private String url;
	private String name;
	private KeywordCounter counter;
	private double score;

	public WebPage(String url, String name) {
		this.url = url;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setScore() {
		score = 0;
		this.score = counter.countScore();
	}
	
	public double getScore() {
		return this.score;
	}
}
