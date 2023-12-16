package exercisesP3;

import org.jgrapht.Graph;

import auxTypesP3.Neighbourship;
import auxTypesP3.Ride;
import auxTypesP3.TaskRelation;
import auxTypesP3.User;
import auxTypesP3.UserRelation;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Utils {
	
	public static Graph<User, UserRelation> readGraph1(String route) {
		
		return GraphsReader.newGraph(
				route,	//Route from which we read the graph
				User::ofFormat,	//Vertices factory
				UserRelation::ofFormat,	//Edges factory
				Graphs2::directedWeightedMultigraph,	//Graph factory
				UserRelation::interactionIndex
											//CTRL + SPACE TO SHOW OPTIONS USING ::
				);		
	}
	
	public static Graph<Ride, Neighbourship> readGraph2(String route) {
		
		return GraphsReader.newGraph(
				route,	//Route from which we read the graph
				Ride::ofFormat,	//Vertices factory
				Neighbourship::ofFormat,	//Edges factory
				Graphs2::simpleWeightedGraph,	//Graph factory,
				Neighbourship::getDistance
				);		
		
	}

	public static Graph<String, TaskRelation> readGraph3(String route) {
		return GraphsReader.newGraph(
				route,
				s -> s[0],
				TaskRelation::ofFormat,
				Graphs2::simpleDirectedGraph
				);
	}

}
