import java.util.ArrayList;
import java.util.Collections;
public class RankingManager {
	ArrayList<Tree> trees;
	ArrayList<Tree> treeList;
	
	public RankingManager(ArrayList<Tree> trees) {
		this.trees = trees;
		this.treeList = new ArrayList<Tree>();
	}
	
	public void sortTrees() {
		for(Tree tree : trees) {
			if(tree.getTreeScore()!= 0) {
				treeList.add(tree);
			}
		}
		sortTrees(0, treeList.size() - 1);
	}
	
	private void sortTrees(int leftbound, int rightbound){
		if (leftbound < rightbound) {
			int i = leftbound - 1;
			
			for (int j = leftbound; j <= rightbound - 1; j++) {
				if (treeList.get(j).getTreeScore() < treeList.get(rightbound).getTreeScore()) {
					i++;
					swap(i, j);
				}
			}
			swap((i+1), rightbound);

			sortTrees(leftbound, i);
			sortTrees(i + 2, rightbound);
		}
	}

	private void swap(int aIndex, int bIndex){
		Tree temp = treeList.get(aIndex);
		treeList.set(aIndex, treeList.get(bIndex));
		treeList.set(bIndex, temp);
	}
	
	public ArrayList<Tree> getWebList() {
		Collections.reverse(treeList);
		return treeList;
	}
}
