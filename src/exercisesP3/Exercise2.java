package exercisesP3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.tour.HeldKarpTSP;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.traverse.DepthFirstIterator;

import auxTypesP3.Neighbourship;
import auxTypesP3.Ride;
import us.lsi.graphs.views.SubGraphView;

public class Exercise2 {
	
	public static Graph<Ride, Neighbourship> minPath(Graph<Ride, Neighbourship> g, Ride ride1, Ride ride2) {
		var alg = new DijkstraShortestPath<Ride, Neighbourship>(g).getPath(ride1, ride2);
		return SubGraphView.of(g, ve -> alg.getVertexList().stream().collect(Collectors.toSet()).contains(ve));
	}
	
	public static Ride getFromName(Graph<Ride, Neighbourship> g, String name) {
		for(Ride r:g.vertexSet())  {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
	
	public static List<Ride> lessTimeAllRides(Graph<Ride, Neighbourship> g) {
		
		g.edgeSet().stream().forEach(e -> g.setEdgeWeight(e, e.getTime()));
		List<Ride> alg = new HeldKarpTSP().getTour(g).getVertexList();
		g.edgeSet().stream().forEach(e -> g.setEdgeWeight(e, e.getDistance()));
		
		return alg;
	}	
	
	
	public static Ride getNextRide(Graph<Ride, Neighbourship> g, Ride r, Comparator<Ride> comp, List<Ride> path) {
		Set<Ride> vSet = new HashSet<>();
		for(Neighbourship n:g.edgesOf(r)) {
			vSet.add(getFromName(g, n.getRide1()));
			vSet.add(getFromName(g, n.getRide2()));
		}
		vSet.removeAll(path);
		return vSet.stream().max(comp).orElse(null);
	}
	
	public static List<Ride> popularWithHours(Graph<Ride, Neighbourship> g, Integer hours) {
		Integer mins = hours * 60;
		List<Ride> path = new ArrayList<>();
		Comparator<Ride> compPopularity = Comparator.comparing(v -> v.getPopularity());
		Ride r1 = g.vertexSet().stream().max(compPopularity).get();
		Ride r2 = r1;
		Double timePassed = 0.0;
		
		if(r1.getDuration() + r1.getWaitTime() < mins) {
			timePassed = (double) (r1.getDuration() + r1.getWaitTime());
			path.add(r1);
			r2 = getNextRide(g, r1, compPopularity, path);
		}
		while(r2 != null) {
			Double timeAdd = r2.getDuration() + r2.getWaitTime() + g.getEdge(r1, r2).getTime();
			if(timePassed + timeAdd < mins) {
				path.add(r2);
				timePassed += timeAdd;
				r1 = r2;
				r2 = getNextRide(g, r1, compPopularity, path);
			} else {
				break;
			}
		}
		return path;
	}

}
