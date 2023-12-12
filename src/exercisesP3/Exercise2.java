package exercisesP3;

import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;

import auxTypesP3.Neighbourship;
import auxTypesP3.Ride;
import us.lsi.graphs.views.SubGraphView;

public class Exercise2 {
	
	/*
	 * 2. Se desea planificar una visita a un parque de atracciones. Se ha modelado dicho parque
como un grafo no dirigido en el que los vértices son las atracciones y las aristas
representan la relación de vecindad entre las atracciones. De cada relación de vecindad se
conoce su distancia (número real en kilómetros) y tiempo medio (número real en minutos)
que se tarda en recorrerla. De cada atracción se conoce su tiempo de espera medio (entero
en minutos), su popularidad (número real en el rango [0,10]), y su duración (entero en
minutos).

				a. Dadas dos atracciones, determine el camino de menor distancia para ir de una a
		otra. Muestre el grafo configurando su apariencia de forma que se resalte dicho
		camino.
		
		*/ 
	
	public static Graph<Ride, Neighbourship> minPath(Graph<Ride, Neighbourship> g, Ride ride1, Ride ride2) {
		var alg = new DijkstraShortestPath<Ride, Neighbourship>(g).getPath(ride1, ride2);
		return SubGraphView.of(g, alg.getVertexList().stream().collect(Collectors.toSet()));
	}
	
	public static Ride getFromName(Graph<Ride, Neighbourship> g, String name) {
		for(Ride r:g.vertexSet())  {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
	
		/*
		b. Determine el camino de menor tiempo medio que pase por todas las atracciones
		exactamente una vez y vuelva al origen. Muestre el grafo configurando su
		apariencia de forma que se resalte dicho camino, mostrando el vértice desde el
		que se parte en un color distinto al resto.
		*/
	
	public static Graph<Ride, Neighbourship> lessTimeAllRides(Graph<Ride, Neighbourship> g) {
		var alg = new KruskalMinimumSpanningTree<Ride, Neighbourship>(g);
	}	
	
	
		/*
		c. Dado un número entero de horas disponibles para la visita, determine un camino
		formado por una lista de atracciones a visitar de tal manera que se inicie por la
		atracción más popular (tomándola como punto de partida) y que, a continuación,
		se dirija a la atracción vecina más popular que aún no haya sido visitada, sin
		priorizar la proximidad, sino la popularidad. Este proceso debe repetirse,
		considerando el desplazamiento, el tiempo medio de espera y la duración de cada
		atracción, hasta que no quede tiempo suficiente para desplazarse, esperar y
		disfrutar de la siguiente atracción vecina más popular no visitada, o cuando no
		haya más atracciones vecinas por visitar. Muestre el grafo configurando su
		apariencia de forma que se resalte dicho camino, mostrando la atracción inicial
		más popular en un color distinto del resto.

	 */

}
