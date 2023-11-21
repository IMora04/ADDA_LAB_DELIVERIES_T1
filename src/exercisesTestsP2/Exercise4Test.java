package exercisesTestsP2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import us.lsi.tiposrecursivos.BinaryTree;

public class Exercise4Test {
	
	public static void Test() {
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of("files/PI2Ej4DatosEntradaBinary.txt")).toList();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		for(String s:values) {
			System.out.println("----- TEST FOR " + s + " -----\n");
			System.out.println(exercisesP2.Exercise4.pathList(BinaryTree.parse(s, a -> Integer.valueOf(a))) + "\n");
		}
	}
	
	public static void main(String[] args) {
		Test();
	}

}
