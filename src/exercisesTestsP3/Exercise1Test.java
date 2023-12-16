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
	
	public static void genBaseGraph(Graph<User, UserRelation> g, String route) {
		GraphColors.toDot(
				g,	//Graph
				"ficheros_generadosP3/" + route + ".gv",	//Route to store result (.gv)
				v -> v.userName(),	//Vertex label
				e -> String.valueOf(e.interactionIndex())	//Edge label
				);
	}
	
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
		System.out.println("File " + route + " has been generated");
		System.out.println("Users that follow 3 users, and whose average interaction index with" +
				"users he follows is greater than 2.5 are:");
		subG.vertexSet().forEach(t -> System.out.print(t.userName() + ", "));
		System.out.println("\n");
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
		Set<User> subG = Exercise1.questMinUsers(g);
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				v -> GraphColors.colorIf(Color.red, subG.contains(v)),
				e -> GraphColors.color(Color.black)
				);
		System.out.println("File " + route + " has been generated");
		System.out.println("Users to which the questionnaire has to be sent are:");
		subG.forEach(t -> System.out.print(t.userName() + ", "));
		System.out.println("\n");
	}
	
	public static void testE1d(Graph<User, UserRelation> g, String route) {
		Graph<User, UserRelation> subG = Exercise1.mostInteractiveUsers(g);
		GraphColors.toDot(
				g,
				"ficheros_generadosP3/" + route + ".gv",
				v -> v.userName(),
				e -> String.valueOf(e.interactionIndex()),
				v -> GraphColors.colorIf(Color.red, subG.vertexSet().contains(v)),
				e -> GraphColors.color(Color.black)
				);
		System.out.println("File " + route + " has been generated");
		System.out.println("The 2 users with the most average interaction among their followers, that have at least 5 followers, 4 hobbies and an activity"
				+ " higher than 4 are:");
		subG.vertexSet().forEach(t -> System.out.print(t.userName() + ", "));
		System.out.println("\n");
	}

	public static void main(String[] args) {
		
		List<String> routes1 = List.of("ejercicio1_1", "ejercicio1_2", "ejercicio1_3");

		for(String s:routes1) {
			genBaseGraph(Utils.readGraph1("filesP3/" + s + ".txt"), s);
		}
	
		for(String s:routes1) {
			Graph<User, UserRelation> g1 = Utils.readGraph1("filesP3/" + s + ".txt");
			testE1a(g1, s + "_1a");
			testE1b(g1, s + "_1b");
			testE1c(g1, s + "_1c");
			testE1d(g1, s + "_1d");
		}
	
	}
	
}
