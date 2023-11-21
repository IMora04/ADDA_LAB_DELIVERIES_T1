package exercisesP2;

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
		return switch(tree) {
			case BEmpty<Character> t -> {
				yield true;
			}
			case BLeaf<Character> t -> {
				yield true;
			}
			case BTree<Character> t -> {
				yield (t.left().height() - t.right().height() <= 1 &&
						t.left().height() - t.right().height() >= -1 &&
						isBalanced(t.left()) &&
						isBalanced(t.right()));
			}
		};
	}
	
	public static Boolean isBalanced2(BinaryTree<Character> tree) {
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
		return switch(tree) {
			case TEmpty<Character> t -> {
				yield true;
			}
			case TLeaf<Character> t -> {
				yield true;
			}
			case TNary<Character> t -> {
				for(int i = 0; i < t.children().size(); i++) {
					for(int j = i; j < t.children().size(); j++) {
						if(!(t.children().get(i).height() - t.children().get(j).height() <= 1 &&
								t.children().get(i).height() - t.children().get(j).height() >= -1 &&
								isBalanced(t.children().get(j)))) {
							yield false;
						}
					}
				}
				yield true;
			}
		};
	}
	
	public static void main(String[] args) {
		BinaryTree<Character> b1 = BinaryTree.parse("A(B(_,G(H,I)),C(D,E(F,_)))", s -> s.charAt(0));
		BinaryTree<Character> b2 = BinaryTree.parse("A(B(/_,G(H,I)),C(D,E(F,/_)))", s -> s.charAt(0));
		System.out.println(b1);
		System.out.println(isBalanced2(b1));
		System.out.println(isBalanced(b1));
		System.out.println(b2);
		System.out.println(isBalanced2(b2));
		System.out.println(isBalanced(b2));

	//	BinaryTree<Character> b2 = BinaryTree.parse("A(B(D,E),C(F,G(H,I)))", s -> s.charAt(0));
	//	System.out.println(isBalanced(b2));
	}

}
