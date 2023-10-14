package deliverablesTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import deliverables.P1E1;

public class P1E1Test {
	
	public static void Test1() {
		String file = "files/PI1Ej1DatosEntrada.txt";
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of(file)).toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s:values) {
			System.out.println("-----------------------");
			Integer v1 = Integer.valueOf(s.split(",")[0]);
			Integer v2 = Integer.valueOf(s.split(",")[1]);
			System.out.println("Given solution:");
			System.out.println(P1E1.ejercicio1(v1, v2));
			System.out.println("Iterative solution:");
			System.out.println(P1E1.iterativeSol(v1, v2));
			System.out.println("Final recursive solution:");
			System.out.println(P1E1.finalRecursiveSol(v1, v2));
		}
	}
	
	public static void main(String[] args) {
		Test1();
	}

}
