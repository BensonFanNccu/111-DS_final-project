import java.io.IOException;

public class WebPage {
	private String url;
	private String name;
	private KeywordCounter counter;
	private HTMLHandler htmlHan;
	private double score;

	public WebPage(String url, String name) throws IOException {
		this.url = url;
		this.name = name;
		htmlHan = new HTMLHandler(url);
		counter = new KeywordCounter(htmlHan.getHtml(), null);  //user input要怎麼取得?
	}

	public String getName() {
		return this.name;
	}
	
	public void setScore() {
		this.score = counter.countScore();
	}
	
	public double getScore() {
		return this.score;
	}
}
