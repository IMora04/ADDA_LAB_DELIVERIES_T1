package exercisesTestsP1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import exercisesP1.P1E4;

public class P1E4Test {

	public static void Test4(String filePath) {
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of(filePath)).toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (String s : values) {
			Integer v1 = Integer.valueOf(s.split(",")[0]);
			Integer v2 = Integer.valueOf(s.split(",")[1]);
			System.out.println("---------------- TEST FOR FILE " + filePath + ". Values: [" + v1 + ", " + v2 + "] ----------------");
			System.out.println();
			System.out.println("Recursive without memory:");
			System.out.println(P1E4.g_rec_no_mem(v1, v2));
			System.out.println();
			System.out.println("Recursive with memory:");
			System.out.println(P1E4.g_rec_mem(v1, v2));
			System.out.println();
			System.out.println("Iterative (with mem):");
			System.out.println(P1E4.g_iterative(v1, v2));
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Test4("files/PI1Ej4DatosEntrada.txt");
	}

}
