package exercisesP3;

import org.jgrapht.Graph;

import auxTypesP3.User;
import auxTypesP3.UserRelation;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Utils {
	
	public static Graph<User, UserRelation> readGraph(String route) {
		
		return GraphsReader.newGraph(
				route,	//Route from which we read the graph
				User::ofFormat,	//Vertices factory
				UserRelation::ofFormat,	//Edges factory
				Graphs2::simpleDirectedGraph	//Graph factory
											//CTRL + SPACE TO SHOW OPTIONS USING ::
				);		
		
	}

}
