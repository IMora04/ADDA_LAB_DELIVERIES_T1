package exercisesTestsP3;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;
import org.jgrapht.nio.DefaultAttribute;

import exercisesP3.Utils;
import exercisesP3.Exercise1;
import auxTypesP3.User;
import auxTypesP3.UserRelation;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;

public class Exercise1Test {

	public static void testE1a(Graph<User, UserRelation> g, String route) {
		Graph<User, UserRelation> subG = Exercise1.viewMoreThan3(g);
		GraphColors.toDot(
				subG,	//Graph
				"ficheros_generadosP3/" + route + ".gv",	//Route to store result (.gv)
				v -> v.userName(),	//Vertex label
				e -> String.valueOf(e.interactionIndex())	//Edge label
				);
	}
	
	public static void testE1b(Graph<User, UserRelation> g, String route) {
		
		var groups = Exercise1.userGroups(g).connectedSets();
		
		Function<User, Map<String, Attribute>> getColor = v -> {
			int index = groups.size();
			for(int i = 0; i < groups.size(); i++) {
				if(groups.get(i).contains(v)) {
					index = i;
					break;
				}
			}
			return Map.of("color", DefaultAttribute.createAttribute(String.valueOf(Arrays.asList(Color.values()).get(index))));
		};
		
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				getColor, 
				e -> GraphColors.color(Color.black)
				);
		
		System.out.println("File " + route + " has been generated");
		System.out.println("The graph has " + groups.size() + " groups.\n");
		
	}
	
	public static void testE1c(Graph<User, UserRelation> g, String route) {	
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				v -> GraphColors.colorIf(Color.red, Exercise1.questMinUsers(g).contains(v)),
				e -> GraphColors.color(Color.black)
				);
		
		System.out.println("File " + route + " has been generated\n");

	}
	
	public static void testE1d(Graph<User, UserRelation> g, String route) {
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				v -> GraphColors.colorIf(Color.yellow, Exercise1.mostInteraciveUsers(g).vertexSet().contains(v)),
				e -> GraphColors.color(Color.black)
				);
		
		System.out.println("File " + route + " has been generated\n");
		System.out.println("The 2 users with the most average interaction among their followers, that have at least 5 followers, 4 hobbies and an activity"
				+ " higher than 4 are: \n"
				+ Exercise1.mostInteraciveUsers(g).vertexSet().stream().map(t -> t.userName()).collect(Collectors.toSet()));
		
	}

	public static void main(String[] args) {

		Graph<User, UserRelation> g = Utils.readGraph("filesP3/ejercicio1_1.txt");
		
		testE1a(g, "ejercicio1_1_1a");
		testE1b(g, "ejercicio1_1_1b");
		testE1c(g, "ejercicio1_1_1c");
		testE1d(g, "ejercicio1_1_1d");
		
	}
	
}
