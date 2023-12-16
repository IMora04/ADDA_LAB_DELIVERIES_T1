package exercisesTestsP3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

import auxTypesP3.Neighbourship;
import auxTypesP3.Ride;
import exercisesP3.Exercise2;
import exercisesP3.Utils;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class Exercise2Test {
	
	public static void genBaseGraph(Graph<Ride, Neighbourship> g, String route) {
		GraphColors.toDot(g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.getName(),
				e -> "distance:" + String.valueOf(e.getDistance()) + ", time:" + String.valueOf(e.getTime())
				);
	}
	
	public static void test2a(Graph<Ride, Neighbourship> g, String ride1, String ride2, String route) {
		Graph<Ride, Neighbourship> gRes =  Exercise2.minPath(g, Exercise2.getFromName(g, ride1), Exercise2.getFromName(g, ride2));
		Set<Ride> vSet = gRes.vertexSet();
		Set<Neighbourship> eSet = gRes.edgeSet();
		GraphColors.toDot(g,
			"ficheros_generadosP3/" + route + ".gv",
			v -> v.getName(),
			e -> "distance:" + String.valueOf(e.getDistance()) + ", time:" + String.valueOf(e.getTime()),
			v -> GraphColors.colorIf(Color.orange,
					vSet.contains(v)),
			e -> GraphColors.colorIf(Color.orange,
					eSet.contains(e))
			);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The minimum path from " + ride1 + " to " + ride2 + " passes through:");
		gRes.vertexSet().forEach(t -> System.out.print(t.getName() + ", "));
		System.out.println("\n");
	}
	
	public static Color getColor(Ride v, List<Ride> vLs) {
		if(vLs.size() != 0 && vLs.contains(v)) {
			if(vLs.get(0).equals(v)) {
				return Color.orange;
			} else {
				return Color.blue;
			}
		} else {
			return Color.black;
		}
	}

	public static void test2b(Graph<Ride, Neighbourship> g, String route) {
		List<Ride> vLs =  Exercise2.lessTimeAllRides(g);
		
		Set<Neighbourship> eSet = new HashSet<>();
		for(int i = 0; i < vLs.size() - 1; i++) {
			eSet.addAll(g.getAllEdges(vLs.get(i), vLs.get(i+1)));
		}

		GraphColors.toDot(g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.getName(),
				e -> "distance:" + String.valueOf(e.getDistance()) + ", time:" + String.valueOf(e.getTime()),
				v -> GraphColors.color(getColor(v, vLs)),
				e -> GraphColors.colorIf(Color.blue,
						eSet.contains(e))
				);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The path that passes through every ride in less time is: ");
		vLs.forEach(t -> System.out.print(t.getName() + ", "));
		System.out.println("\n");
	}

	public static void test2c(Graph<Ride, Neighbourship> g, Integer h, String route) {
		List<Ride> vLs =  Exercise2.popularWithHours(g, h);
		Set<Neighbourship> eSet = new HashSet<>();
		for(int i = 0; i < vLs.size() - 1; i++) {
			eSet.addAll(g.getAllEdges(vLs.get(i), vLs.get(i+1)));
		}
		GraphColors.toDot(g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.getName() + ", pop=" + v.getPopularity(),
				e -> "distance:" + String.valueOf(e.getDistance()) + ", time:" + String.valueOf(e.getTime()),
				v -> GraphColors.color(getColor(v, vLs)),
				e -> GraphColors.colorIf(Color.blue,
						eSet.contains(e))
				);
		System.out.println("The file ficheros_generadosP3/" + route + ".gv has been generated.");
		System.out.println("The path visiting the most popular rides in " + h + " hours is:");
		vLs.forEach(t -> System.out.print(t.getName() + ", "));
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		
		for(int i = 1; i < 4; i++) {
			genBaseGraph(Utils.readGraph2("filesP3/ejercicio2_" + i + ".txt"), "ejercicio2_" + i);
		}
		
		System.out.println("----- TEST 2A -----");
		test2a(Utils.readGraph2("filesP3/ejercicio2_1.txt"), "Coches de choque", "Raton Vacilon", "ejercicio2_1_2a");
		test2a(Utils.readGraph2("filesP3/ejercicio2_2.txt"), "Coches de choque", "Patitos", "ejercicio2_2_2a");
		test2a(Utils.readGraph2("filesP3/ejercicio2_3.txt"), "Casa del Terror", "Pim pam pum", "ejercicio2_3_2a");
			
		System.out.println("----- TEST 2B -----");
		test2b(Utils.readGraph2("filesP3/ejercicio2_1.txt"), "ejercicio2_1_2b");
		test2b(Utils.readGraph2("filesP3/ejercicio2_2.txt"), "ejercicio2_2_2b");
		test2b(Utils.readGraph2("filesP3/ejercicio2_3.txt"), "ejercicio2_3_2b");
		
		System.out.println("----- TEST 2C -----");
		test2c(Utils.readGraph2("filesP3/ejercicio2_1.txt"), 5, "ejercicio2_1_2c");
		test2c(Utils.readGraph2("filesP3/ejercicio2_2.txt"), 2, "ejercicio2_2_2c");
		test2c(Utils.readGraph2("filesP3/ejercicio2_3.txt"), 3, "ejercicio2_3_2c");
		
	}
	
}
