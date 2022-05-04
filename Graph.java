package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//generic class that representing a graph
public class Graph<V> {
	private Set<V> vertices = new HashSet<>(); // hashSet of all vertices
	private Map<V, Set<V>> edges = new HashMap<>(); // hashMap of all edges of all vertices

	public void addVertex(V v) throws GraphException {
		// add vertex to the graph
		if (!(vertices.add(v)))
			throw new GraphException("Vertex Already exists");// if vertex already exists
		edges.put(v, new HashSet<V>()); // add new edge
	}

	public void addEdge(V v1, V v2) throws GraphException {
		/*
		 * Will add an edge connecting the two vertices, and throw Exception with an
		 * appropriate message, in case you typed Already exists, or if one of the
		 * vertices does not exist.
		 */
		if (edges.get(v1).contains(v2)) // edge already exists
			throw new GraphException("Edge already exists");
		if (!(vertices.contains(v1)) || !(vertices.contains(v2))) // one (or both) of the vertices isn't exists
			throw new GraphException("No existing Vertex");
		edges.get(v1).add(v2); // add the edge to v1
		edges.get(v2).add(v1); // add the edge to v2
	}

	public boolean hasEdge(V v1, V v2) {
		// Will return true if there is an edge between v1 and v2.
		return edges.get(v1).contains(v2);
	}

	public boolean connected(V v1, V v2) throws GraphException {
		/*
		 * Returns true if can be reached from vertex v1 to Vertex v2 .If one of the
		 * vertices v1 or v2 is not Are in the graph, the exception will be thrown.
		 */
		Set<V> hashSetV = new HashSet<>(); // new hashSet
		if (!(vertices.contains(v1)) || !(vertices.contains(v2))) // v1 or v2 (or both) not exists
			throw new GraphException("No existing Vertex");
		return checkNeighbor(v1, v2, hashSetV);
	}

	private boolean checkNeighbor(V v1, V v2, Set<V> hashSetV) {
		// recursive method that check if v2 can be reached from v1
		if (v1.equals(v2)) // if v1 and v2 are equal
			return true;
		if (edges.get(v1).contains(v2)) // if v2 is in v1 neighbours
			return true;
		for (V v : edges.get(v1)) { // loop over all v1 neighbours
			if (!(hashSetV.contains(v))) {
				hashSetV.add(v);
				if (checkNeighbor(v, v2, hashSetV)) // check if v2 can be reached from v
					return true;
			}
		}
		return false;
	}
}
