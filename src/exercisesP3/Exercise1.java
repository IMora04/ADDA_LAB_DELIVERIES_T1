package exercisesP3;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;

import auxTypesP3.User;
import auxTypesP3.UserRelation;
import us.lsi.graphs.views.SubGraphView;

public class Exercise1 {
	
	public static Graph<User, UserRelation> viewMoreThan3(Graph<User, UserRelation> g) {
		Graph<User, UserRelation> subG = SubGraphView.of(g,
				g.vertexSet().stream()
				.filter(v -> g.outgoingEdgesOf(v).size() > 3 && 
						g.outgoingEdgesOf(v).stream().mapToDouble(x->x.interactionIndex()).average().getAsDouble() > 2.5)
				.collect(Collectors.toSet()));
		return subG;
	}
		
	public static ConnectivityInspector<User, UserRelation> userGroups(Graph<User, UserRelation> g) {
		var alg = new ConnectivityInspector<>(g);
		return alg;
	}
	
	public static Set<User> questMinUsers(Graph<User, UserRelation> g) {
		Graph<User, UserRelation> undirectedG = Graphs.undirectedGraph(g);
		return new RecursiveExactVCImpl<>(undirectedG).getVertexCover();
	}

	public static Graph<User, UserRelation> mostInteractiveUsers(Graph<User, UserRelation> g) {		
		Graph<User, UserRelation> subG = SubGraphView.of(
				g,
				g.vertexSet().stream()
					.filter(v -> g.incomingEdgesOf(v).size() >= 5 && 
						v.hobbies().size() > 3 &&
						v.activityIndex() > 4)
					.sorted(
						Comparator.comparing(
							v -> g.incomingEdgesOf(v).stream()
							.mapToDouble(t -> t.interactionIndex())
							.average().getAsDouble(),
						Comparator.reverseOrder()))
					.limit(2)
					.collect(Collectors.toSet())
				);
		
		return subG;

	}

}
