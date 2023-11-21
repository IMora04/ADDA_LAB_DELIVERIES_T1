package exercisesP2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import us.lsi.common.Pair;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;
import us.lsi.tiposrecursivos.Tree;
import us.lsi.tiposrecursivos.Tree.TEmpty;
import us.lsi.tiposrecursivos.Tree.TLeaf;
import us.lsi.tiposrecursivos.Tree.TNary;

public class Exercise3 {
	
	
	public static Boolean isBalanced(BinaryTree<Character> tree) {
		return isBalancedAux(tree, 0).first();
	}
	
	public static Pair<Boolean,Integer> isBalancedAux(BinaryTree<Character> tree, Integer height) {
		return switch(tree) {
			case BEmpty<Character> t-> {
				yield Pair.of(true,height);
			}
			case BLeaf<Character> t-> {
				yield Pair.of(true,height);
			}
			case BTree<Character> t-> {
				height +=1;
				Pair<Boolean, Integer> tL = isBalancedAux(t.left(), height);
				Pair<Boolean, Integer> tR = isBalancedAux(t.right(), height);
				yield Pair.of(
						tL.first() && tR.first() && (Math.abs(tL.second() - tR.second()) <= 1), 
						Math.max(tL.second(), tR.second()));
			}
		};
	}	
	
	public static Boolean isBalanced(Tree<Character> tree) {
		return isBalancedAux(tree, 0).first();
	}
	
	public static Pair<Boolean, Integer> isBalancedAux(Tree<Character> tree, Integer height) {
		return switch(tree) {
			case TEmpty<Character> t -> {
				yield Pair.of(true, height);
			}
			case TLeaf<Character> t -> {
				yield Pair.of(true, height);
			}
			case TNary<Character> t -> {
				height += 1;
				List<Integer> heights = new ArrayList<>();
				
				for(Tree<Character> c:t.children()) {
					Pair<Boolean, Integer> iB = isBalancedAux(c, height);
					if(!iB.first()) {
						yield Pair.of(false, 0);
					}
					heights.add(iB.second());
				}
				Collections.sort(heights, Collections.reverseOrder());
				yield Pair.of(Math.abs(heights.get(0) - heights.get(heights.size() -1)) <= 1, heights.get(0));
			}
		};
	}
	
}
