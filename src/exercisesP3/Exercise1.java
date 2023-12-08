package exercisesP3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.vertexcover.RecursiveExactVCImpl;

import us.lsi.graphs.views.SubGraphView;

public class Exercise1 {
	
	public record User(String userName, double activityIndex, List<String> hobbies) {
		public User(String userName, double activityIndex, List<String> hobbies) {
			this.userName = userName;
			if(activityIndex >= 0 && activityIndex <= 5) {
				this.activityIndex = activityIndex;
			} else {
				throw new IllegalArgumentException("Invalid activity index value");
			}
			this.hobbies = hobbies;
		}
		public static User ofFormat(String[] values) {
			String[] listValues = values[2].replace("[", "").replace("]", "").split(";");
			return new User(values[0].trim(), Double.valueOf(values[1].trim()), Arrays.asList(listValues));
		}
	}
	
	public record UserRelation(String followerUser, String followedUser, double interactionIndex) {
		public UserRelation(String followerUser, String followedUser, double interactionIndex) {
			this.followedUser = followedUser;
			this.followerUser = followerUser;
			if(interactionIndex >= 0 && interactionIndex <= 5) {
				this.interactionIndex = interactionIndex;
			} else {
				throw new IllegalArgumentException("Invalid interaction index value");
			}
		}
		public static UserRelation ofFormat(String[] values) {
			return new UserRelation(values[0].trim(), values[1].trim(), Double.valueOf(values[2].trim()));
		}

	}
	
	
	
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
		//Ask for the minimum number of users representing all the relations (min vertices keeping all edges -> Vertex covering, RecursiveExactVCImpl).
		Graph<User, UserRelation> undirectedG = Graphs.undirectedGraph(g);
		return new RecursiveExactVCImpl<>(undirectedG).getVertexCover();
	}

	public static Graph<User, UserRelation> mostInteraciveUsers(Graph<User, UserRelation> g) {		
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
