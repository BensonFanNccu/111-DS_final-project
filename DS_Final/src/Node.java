import java.io.IOException;
import java.util.ArrayList;

public class Node {
	public Node parent;
	public ArrayList<Node> children;
	public WebPage webPage;	
	public double nodeScore;//This node's score += all its children's nodeScore
	
	public Node(WebPage webPage){
		this.webPage = webPage;
		this.children = new ArrayList<Node>();
	}
	
	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException{
		//this method should be called in post-order mode
		
		//compute webPage score
		webPage.setScore();
		//set webPage score to nodeScore
		nodeScore = webPage.getScore();		
		
		//nodeScore += all children's nodeScore 
		for(Node child : children){
			nodeScore += child.nodeScore;
		}		
	}
	
	public void addChild(Node child){
		//add the Node to its children list
		this.children.add(child);
		child.parent = this;
	}
	
	public boolean isTheLastChild(){
		if(this.parent == null) return true;
		ArrayList<Node> siblings = this.parent.children;
		
		return this.equals(siblings.get(siblings.size() - 1));
	}
	
	public int getDepth(){
		int retVal = 1;
		Node currNode = this;
		while(currNode.parent != null){
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}
}
