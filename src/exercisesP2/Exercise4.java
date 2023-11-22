package exercisesP2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.List2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Exercise4 {
	
	public static List<List<Integer>> pathList(BinaryTree<Integer> tree) {
		List<List<Integer>> ls = new ArrayList<>();
		pathList(tree, ls, List2.empty());
		return ls;
	}
	
	public static void pathList(BinaryTree<Integer> tree, List<List<Integer>> ls, List<Integer> res) {
		switch(tree) {
		
			case BEmpty<Integer> t -> {
				Integer sum = 0;
				res.add(null);
				for(Integer i:res) {
					if(i != null) {
						sum += i;
					}
				}
				if(sum%(res.size()-1) == 0) {
					ls.add(res);
				}
			}
			
			case BLeaf<Integer> t -> {
				Integer sum = 0;
				res.add(t.label());
				for(Integer i:res) {
					if(i != null) {
						sum += i;
					}
				}
				if(sum%(res.size()-1) == 0) {
					ls.add(res);
				}
			}
			
			case BTree<Integer> t -> {
				res.add(t.label());
				pathList(t.left(), ls, new ArrayList<>(res));
				pathList(t.right(), ls, new ArrayList<>(res));
			}
			
		};	
	}
	
	public static List<List<Integer>> pathList(Tree<Integer> tree) {
		List<List<Integer>> ls = new ArrayList<>();
		pathList(tree, ls, List2.empty());
		return ls;
	}
	
	public static void pathList(Tree<Integer> tree, List<List<Integer>> ls, List<Integer> res) {
		switch(tree) {
		
			case TEmpty<Integer> t -> {
				Integer sum = 0;
				res.add(null);
				for(Integer i:res) {
					if(i != null) {
						sum += i;
					}
				}
				if(sum%(res.size()-1) == 0) {
					ls.add(res);
				}
			}
			
			case TLeaf<Integer> t -> {
				Integer sum = 0;
				res.add(t.label());
				for(Integer i:res) {
					if(i != null) {
						sum += i;
					}
				}
				if(sum%(res.size()-1) == 0) {
					ls.add(res);
				}
			}
			
			case TNary<Integer> t -> {
				res.add(t.label());
				for(Tree<Integer> c:t.children()) {
					pathList(c, ls, new ArrayList<>(res));
				}
			}
			
		};
	}
}
