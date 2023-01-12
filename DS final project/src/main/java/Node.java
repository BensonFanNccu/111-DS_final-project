import java.util.ArrayList;

public class Node {
	public Node parent;
	public WebPage webPage;
	public double nodeScore;
	
	public Node(WebPage page) {
		webPage = page;
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords){
		
		webPage.setScore(keywords);
		nodeScore = webPage.getScore();
	}
	
	public double getNodeScore() {
		return nodeScore;
	}
	
	public String getPageUrl() {
		return webPage.getUrl();
	}
	
	public String getPageName() {
		return webPage.getName();
	}
}