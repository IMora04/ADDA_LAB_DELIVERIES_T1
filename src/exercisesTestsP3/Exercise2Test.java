package exercisesTestsP3;

import java.util.Set;

import org.jgrapht.Graph;

import auxTypesP3.Neighbourship;
import auxTypesP3.Ride;
import exercisesP3.Exercise2;
import exercisesP3.Utils;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class Exercise2Test {
	
	public static void test2a(Graph<Ride, Neighbourship> g, String ride1, String ride2, String route) {
		Set<Ride> vSet = Exercise2.minPath(g, Exercise2.getFromName(g, ride1), Exercise2.getFromName(g, ride2)).vertexSet();
		GraphColors.toDot(g,
			"ficheros_generadosP3/" + route + ".txt",
			v -> v.getName(),
			e -> "distance:" + String.valueOf(e.getDistance()) + ", time:" + String.valueOf(e.getTime()),
			v -> GraphColors.colorIf(Color.orange,
					vSet.contains(v)),
			e -> GraphColors.colorIf(Color.orange,
					vSet.contains(g.getEdgeSource(e)) && vSet.contains(g.getEdgeTarget(e)))
			);
	}
	
	public static void main(String[] args) {
		
		test2a(Utils.readGraph2("filesP3/ejercicio2_1.txt"), "Coches de choque", "Raton Vacilon", "ejercicio2_1_2a");
		test2a(Utils.readGraph2("filesP3/ejercicio2_2.txt"), "Coches de choque", "Patitos", "ejercicio2_2_2a");
		test2a(Utils.readGraph2("filesP3/ejercicio2_3.txt"), "Casa del Terror", "Pim pam pum", "ejercicio2_3_2a");
	
	}


}
