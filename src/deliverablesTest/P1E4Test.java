package deliverablesTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import deliverables.P1E4;

public class P1E4Test {
	
	public static void Test4() {
		List<String> values = null;
		try {
			values = Files.lines(Path.of("files/PI1Ej4DatosEntrada.txt")).toList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(String s:values) {
			Integer v1 = Integer.valueOf(s.split(",")[0]);
			Integer v2 = Integer.valueOf(s.split(",")[1]);
			System.out.println("----------------");
			System.out.println("Recursive without memory");
			System.out.println(P1E4.g_rec_no_mem(v1, v2));
			System.out.println("Recursive with memory");
			System.out.println(P1E4.g_rec_mem(v1,v2));
			System.out.println("Iterative (with mem)");
			System.out.println(P1E4.g_iterative(v1, v2));
		}
	}
	
	public static void main(String[] args) {
		Test4();
	}

}
