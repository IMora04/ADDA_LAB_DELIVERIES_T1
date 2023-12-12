package exercisesTestsP3;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.nio.Attribute;

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
				g,	//Graph
				"ficheros_generadosP3/" + route + ".gv",	//Route to store result (.gv)
				v -> v.userName(),	//Vertex label
				e -> String.valueOf(e.interactionIndex()),	//Edge label
				v -> GraphColors.colorIf(Color.red, subG.vertexSet().contains(v)),
				e -> GraphColors.colorIf(Color.blue, subG.vertexSet().contains(g.getEdgeSource(e)))
				);
		
		System.out.println("File " + route + " has been generated\n");
	}
	
	public static Map<String, Attribute> getColor(User v, List<Set<User>> groups) {
		Integer index = groups.size();
		for(int i = 0; i < groups.size(); i++) {
			if(groups.get(i).contains(v)) {
				index = i;
				break;
			}
		}
		return GraphColors.color(index);
	}
	
	public static void testE1b(Graph<User, UserRelation> g, String route) {
		
		List<Set<User>> groups = Exercise1.userGroups(g).connectedSets();
				
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				v -> getColor(v, groups), 
				e -> getColor(g.getEdgeSource(e), groups)
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
				v -> GraphColors.colorIf(Color.red, Exercise1.mostInteractiveUsers(g).vertexSet().contains(v)),
				e -> GraphColors.color(Color.black)
				);

		System.out.println("File " + route + " has been generated");
		System.out.println("The 2 users with the most average interaction among their followers, that have at least 5 followers, 4 hobbies and an activity"
				+ " higher than 4 are: \n"
				+ Exercise1.mostInteractiveUsers(g).vertexSet().stream().map(t -> t.userName()).collect(Collectors.toSet())
				+ "\n");
		
	}

	public static void main(String[] args) {
		
		List<String> routes1 = List.of("ejercicio1_1", "ejercicio1_2", "ejercicio1_3");

		for(String s:routes1) {
			Graph<User, UserRelation> g1 = Utils.readGraph1("filesP3/" + s + ".txt");
			testE1a(g1, s + "_1a");
			testE1b(g1, s + "_1b");
			testE1c(g1, s + "_1c");
			testE1d(g1, s + "_1d");
		}
	
	}
	
}
