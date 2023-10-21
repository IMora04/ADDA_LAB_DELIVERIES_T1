package deliverablesTest;

import deliverables.P1E3;

public class P1E3Test {
	
	public static void Test3(String fileA, String fileB) {
		System.out.println();
		System.out.println("---------------- TEST FOR FILES " + fileA + " and" + fileB + " ----------------");
		System.out.println();
		System.out.println("Iterative solution:");
		System.out.println(P1E3.ex3_iterative(fileA, fileB));
		System.out.println();
		System.out.println("Final recursive solution:");
		System.out.println(P1E3.ex3_final_recursive(fileA, fileB));
		System.out.println();
		System.out.println("Functional solution:");
		System.out.println(P1E3.ex3_functional(fileA, fileB));
	}
	
	public static void main(String[] args) {
		Test3("files/PI1Ej3DatosEntrada1A.txt", "files/PI1Ej3DatosEntrada1B.txt");
		Test3("files/PI1Ej3DatosEntrada2A.txt", "files/PI1Ej3DatosEntrada2B.txt");
		Test3("files/PI1Ej3DatosEntrada3A.txt", "files/PI1Ej3DatosEntrada3B.txt");
	}
	
}