package exercisesTestsP3;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import auxTypesP3.TaskRelation;
import exercisesP3.Exercise3;
import exercisesP3.Utils;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class Exercise3Test {
	
	public static void test3a(Graph<String, TaskRelation> g, String route) {
		List<String> vLs = Exercise3.toDoAllList(g);
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v,
				e -> "",
				v -> GraphColors.colorIf(Color.red, vLs.get(0).equals(v)),
				e -> GraphColors.color(Color.black)
				);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The order in which tasks can be done, following the precedence relations, is:");
		System.out.println(vLs + "\n");
	}
	
	public static void test3b(Graph<String, TaskRelation> g, String route, String toDo) {
		Set<String> vLs = Exercise3.toDoOneList(g, toDo);
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v,
				e -> "",
				v -> GraphColors.colorIf(Color.red, vLs.contains(v)),
				e -> GraphColors.color(Color.black)
				);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The order that must be followed to complete the task " + toDo +" is:");
		System.out.println(vLs + "\n");
	}
	
	public static void test3c(Graph<String, TaskRelation> g, String route) {
		String vS = Exercise3.mostDeterminative(g);
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v,
				e -> "",
				v -> GraphColors.colorIf(Color.red, vS == v),
				e -> GraphColors.color(Color.black)
				);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The most determinative task is: " + vS + "\n");
	}

	public static void main(String[] args) {
		
		for(int i = 1; i < 4; i++) {
			GraphColors.toDot(
					Utils.readGraph3("filesP3/ejercicio3_"+i+".txt"),
					"ficheros_generadosP3/ejercicio3_"+i+".gv",
					v -> v,
					s -> "",
					v -> GraphColors.color(Color.black),
					e -> GraphColors.color(Color.black)
					);
		}
		
		System.out.println("----- TEST 3A -----");
		System.out.println("(Generated graphs only contains the first element colored) \n");
		test3a(Utils.readGraph3("filesP3/ejercicio3_1.txt"), "ejercicio3_1_3a");
		test3a(Utils.readGraph3("filesP3/ejercicio3_2.txt"), "ejercicio3_2_3a");
		test3a(Utils.readGraph3("filesP3/ejercicio3_3.txt"), "ejercicio3_3_3a");
		
		System.out.println("----- TEST 3B -----");
		test3b(Utils.readGraph3("filesP3/ejercicio3_1.txt"), "ejercicio3_1_3b", "Tarea5");
		test3b(Utils.readGraph3("filesP3/ejercicio3_2.txt"), "ejercicio3_2_3b", "Tarea8");
		test3b(Utils.readGraph3("filesP3/ejercicio3_3.txt"), "ejercicio3_3_3b", "Tarea8");

		System.out.println("----- TEST 3C -----");
		test3c(Utils.readGraph3("filesP3/ejercicio3_1.txt"), "ejercicio3_1_3c");
		test3c(Utils.readGraph3("filesP3/ejercicio3_2.txt"), "ejercicio3_2_3c");
		test3c(Utils.readGraph3("filesP3/ejercicio3_3.txt"), "ejercicio3_3_3c");

	}
	
}
