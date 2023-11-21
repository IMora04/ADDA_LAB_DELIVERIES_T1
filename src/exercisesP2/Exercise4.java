package exercisesP2;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.List2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.BinaryTree.BEmpty;
import us.lsi.tiposrecursivos.BinaryTree.BLeaf;
import us.lsi.tiposrecursivos.BinaryTree.BTree;

public class Exercise4 {
	
	/*
	 * 4. Dado un árbol binario de enteros, diseñe un algoritmo que devuelva una lista de
caminos con aquellos caminos desde el nodo raíz hasta una hoja que cumplan que: la
suma de las etiquetas de los nodos del camino sea divisible entre la longitud de dicho
camino. Se considera que un camino se modela como una lista que incluye los enteros de
las etiquetas que componen el camino por orden (de la raíz a la hoja). Proporcione una
solución también para árboles n-arios.
	 */
	
	public static List<List<Integer>> pathList(BinaryTree<Integer> tree) {
		// sum of labels is divisible by length of path
		List<List<Integer>> ls = new ArrayList<>();
		pathList(tree, ls, List2.empty());
		return ls;
	}
	
	public static void pathList(BinaryTree<Integer> tree, List<List<Integer>> ls, List<Integer> res) {
		switch(tree) {
			case BEmpty<Integer> t -> {
				Integer sum = 0;
				for(Integer i:res) {
					sum += i;
				}
				if(sum%res.size() == 0) {
					ls.add(res);
				}
			}
			case BLeaf<Integer> t -> {
				Integer sum = 0;
				res.add(t.label());
				for(Integer i:res) {
					sum += i;
				}
				if(sum%res.size() == 0) {
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
	
	public static void pathListInt(BinaryTree<Integer> tree) {
		
	}
	
	public static void main(String[] args) {
	}

}
