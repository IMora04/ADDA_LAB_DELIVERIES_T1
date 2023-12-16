package exercisesP3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;

import auxTypesP3.TaskRelation;

public class Exercise3 {
	
	public static List<String> toDoAllList(Graph<String, TaskRelation> g) {
		List<String> vLs = new ArrayList<>();
		Iterator<String> alg = new TopologicalOrderIterator(g);
		alg.forEachRemaining(v -> vLs.add(v));
		return vLs;
	}
	
	public static Set<String> addIncoming(Graph<String, TaskRelation> g, Set<String> vLs, String toDo) {
		for(TaskRelation t:g.incomingEdgesOf(toDo)) {
			String source = g.getEdgeSource(t);
			vLs.add(source);
			addIncoming(g, vLs, source);
		}
		return vLs;
	}
	
	public static Set<String> toDoOneList(Graph<String, TaskRelation> g, String toDo) {
		Set<String> vLs = new HashSet<>();
		addIncoming(g, vLs, toDo);
		return vLs;
	}
	
	public static String mostDeterminative(Graph<String, TaskRelation> g) {
		Map<String, Integer> m = new HashMap<>();
		List<List<String>> taskOfEach = new ArrayList<>();
		for(String s:g.vertexSet()) {
			taskOfEach.add(toDoOneList(g, s).stream().collect(Collectors.toList()));
		}
		return taskOfEach.stream()
				.flatMap(t -> t.stream())
				.collect(Collectors.groupingBy(t -> t, Collectors.counting()))
				.entrySet().stream()
				.max(Comparator.comparing(t -> t.getValue())).get().getKey();
	}
	
}
