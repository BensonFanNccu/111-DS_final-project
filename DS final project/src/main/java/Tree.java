import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Tree {
	private Node root;
	private ArrayList<Node> children;
	
	public Tree(WebPage rootPage) {
		root = new Node(rootPage);
		children = new ArrayList<Node>();
	}
	
	public void addChild(Node child) {
		children.add(child);
	}
	
	public void setTreeScore(ArrayList<Keyword> keywordList) {
		root.setNodeScore(keywordList);
		for(Node child: children) {
			child.setNodeScore(keywordList);
		}
	}
	
	public double getTreeScore() {
		double score = 0;
		score += root.getNodeScore();
		for(Node child: children) {
			score += child.getNodeScore();
		}
		
		return score;
	}
	
	public double calScore(String url, ArrayList<Keyword> keywordList) {
		WebPage wp = new WebPage(" ", url);
		wp.setScore(keywordList);
		return wp.getScore();
	}
	
	public String getNodeUrl() {
		return root.getPageUrl();	
	}
	
	public String getNodeName() {
		return root.getPageName();
	}
	
	public ArrayList<String> getAllChildrenURL() {
		ArrayList<String> urlList = new ArrayList<String>();
		for(Node child: children) {
			urlList.add(child.getPageUrl());
		}
		return urlList;
	}
	
	public void generateChildList(ArrayList<Keyword> keywords) throws IOException{
		
		int count = 0;
		String content = "";
		
		URL u = new URL(root.getPageUrl());
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/108.0.0.0");
		
		try {
			InputStream in = conn.getInputStream();
			InputStreamReader inReader = new InputStreamReader(in, "utf-8");
			BufferedReader bufReader = new BufferedReader(inReader);
			String line = null;
			
			while((line = bufReader.readLine()) != null) {
				content += line;
			}
		}catch(Exception e) {
			
		}
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("a[href]");
		for(Element link : lis) {
			if(count == 2) {
				break;
			}
			String str = link.toString();
			if(str.substring(9).startsWith("http")) {
				int finder = str.indexOf("\"", 9);
				String url = str.substring(9, finder);
				
				if(!url.endsWith("php") && calScore(url, keywords) != 0 && !getAllChildrenURL().contains(url)) {
					addChild(new Node(new WebPage("child", url)));
					count++;
				}
			}
		}
	}
	
	public String getChildUrl(int index) {
		if(children.isEmpty()) {
			return null;
		} else if(index >= children.size()) {
			return null;	
		} else {
			return children.get(index).getPageUrl();
		}
	}
	
}
