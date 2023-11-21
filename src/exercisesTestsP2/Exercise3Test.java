package exercisesTestsP2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;

public class Exercise3Test {
	
	public static void testBin() {
		List<String> values = new ArrayList<String>();
		try {
			values = Files.lines(Path.of("files/PI2Ej3DatosEntradaBinary.txt")).toList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		for(String s:values) {
			System.out.println("----- TEST FOR TREE: " + s + " -----");
			System.out.println(exercisesP2.Exercise3.isBalanced(BinaryTree.parse(s, a -> a.charAt(0))));
		}
	}

	
	public static void testBin2() {
		List<String> values = new ArrayList<String>();
		try {
			values = Files.lines(Path.of("files/PI2Ej3DatosEntradaBinary.txt")).toList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		for(String s:values) {
			System.out.println("----- TEST FOR TREE: " + s + " -----");
			System.out.println(exercisesP2.Exercise3.isBalanced2(BinaryTree.parse(s, a -> a.charAt(0))));
		}
	}
	
	public static void testNary() {
		
	}
	
	public static void main(String[] args) {
		testBin();
		testBin2();
		//testBin2();
	}

}
