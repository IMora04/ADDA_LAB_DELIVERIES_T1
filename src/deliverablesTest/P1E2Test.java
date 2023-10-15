package deliverablesTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import deliverables.P1E2;

public class P1E2Test {
	
	public static void Test2() {
		String file = "files/PI1Ej2DatosEntrada.txt";
		List<String> values = new ArrayList<>();
		try {
			values = Files.lines(Path.of(file)).toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s:values) {
			Integer v1 = Integer.valueOf(s.split(",")[0]);
			Integer v2 = Integer.valueOf(s.split(",")[1]);
			System.out.println("-----------------------");
			System.out.println("Non-final recursive solution:");
			System.out.println(P1E2.f_non_final(v1, v2));
			System.out.println("Iterative with while solution:");
			System.out.println(P1E2.f_iterative(v1, v2));
			System.out.println("Final recursive solution:");
			System.out.println(P1E2.f_final(v1, v2));
			System.out.println("Functional solution:");
			System.out.println(P1E2.f_functional(v1, v2));


		}
	}
	
	public static void main(String[] args) {
		Test2();
	}


}
