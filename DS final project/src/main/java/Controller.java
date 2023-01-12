import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class Controller {
	private ArrayList<Keyword> keyList;
	private ArrayList<Tree> trees;
	private ArrayList<Tree> sortedTree;
	private String input;
	private CallGoogle call;
	private RankingManager rank;

	public Controller(String inputKeyword) {
		input = inputKeyword;
		call = new CallGoogle();
		trees = new ArrayList<Tree>();
		sortedTree = new ArrayList<Tree>();
	}

	public void search() throws Exception {
		generateKeyList();
		generateTrees();
		getResult();
	}

	private void generateKeyList() {
		keyList = new ArrayList<Keyword>();
		String[] buffer = input.split("\\s+");

		for (String input : buffer) {
			keyList.add(new Keyword(input, 5));
		}
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

	public void generateTrees() throws Exception {
		HashMap<String, String> webs = call.generateWebList(input);

		for (Entry<String, String> entry : webs.entrySet()) {
			Tree tree = new Tree(new WebPage(entry.getKey(), entry.getValue()));
			tree.generateChildList(keyList);
			trees.add(tree);
		}
	}
	
	public void getResult() throws Exception {
		for (Tree tree : trees) {
			tree.setTreeScore(keyList);
		}
		rank = new RankingManager(trees);
		rank.sortTrees();
		sortedTree = rank.getWebList();
	}
	
	public ArrayList<Tree> getTreeList() {
		return sortedTree;
	}
	
	public HashMap<String, String> getRelativeKeywords() throws Exception{
		return call.deriveRelateKeywords(input);
	}
}
