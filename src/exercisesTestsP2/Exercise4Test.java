package exercisesTestsP2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class Exercise4Test {
	
	public static void testBin() {
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of("files/PI2Ej4DatosEntradaBinary.txt")).toList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		for(String s:values) {
			System.out.println("----- TEST FOR BINARY TREE " + s + " -----\n");
			for(List<Integer> l:exercisesP2.Exercise4.pathList(BinaryTree.parse(s, a -> Integer.valueOf(a)))) {
				System.out.println(l);
			}
			System.out.println();
		}
	}
	
	public static void testNAry() {
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of("files/PI2Ej4DatosEntradaNary.txt")).toList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		for(String s:values) {
			System.out.println("----- TEST FOR TREE " + s + " -----\n");
			for(List<Integer> l:exercisesP2.Exercise4.pathList(Tree.parse(s, a -> Integer.valueOf(a)))) {
				System.out.println(l);
			}
			System.out.println();
		}
	}

	
	public static void main(String[] args) {
		testBin();
		testNAry();
	}

}
