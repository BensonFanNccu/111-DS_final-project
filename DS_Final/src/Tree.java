import java.io.IOException;
import java.util.ArrayList;

public class Tree {
	public Node root;

	public Tree(WebPage rootPage) {
		this.root = new Node(rootPage);
	}

	public void setPostOrderScore(ArrayList<Keyword> keywords) throws IOException {
		setPostOrderScore(root, keywords);
	}

	private void setPostOrderScore(Node startNode, ArrayList<Keyword> keywords) throws IOException {
		//compute the score of children nodes via post-order, then setNodeScore for startNode
		//System.out.println(root.children.size());
		for (int i = 0; i < startNode.children.size(); i++) {
			setPostOrderScore(startNode.children.get(i), keywords);	
		}
		startNode.setNodeScore(keywords);
	}

	public void eularPrintTree() {
		eularPrintTree(root);
	}

	private void eularPrintTree(Node startNode) {
		int nodeDepth = startNode.getDepth();

		if (nodeDepth > 1)
			System.out.print("\n" + repeat("\t", nodeDepth - 1));

		System.out.print("(");
		System.out.print(startNode.webPage.getName() + "," + startNode.nodeScore);

		//print child via pre-order

		for (int i = 0; i < startNode.children.size(); i++) {
			eularPrintTree(startNode.children.get(i));
		}

		System.out.print(")");

		if (startNode.isTheLastChild())
			System.out.print("\n" + repeat("\t", nodeDepth - 2));
	}

	private String repeat(String str, int repeat) {
		String retVal = "";
		for (int i = 0; i < repeat; i++) {
			retVal += str;
		}
		return retVal;
	}
}