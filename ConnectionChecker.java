package graph;

import java.util.HashSet;
import java.util.Set;

/*This is a class that is initialized by some graph (the interface) and knows how to check if two vertices are connected in the graph.*/
public class ConnectionChecker<V> {
	private GraphInterface<V> g;

	public ConnectionChecker(GraphInterface<V> g) { // constructor
		this.g = g;
	}

	public boolean check(V v1, V v2) {
		// Returns whether it is possible to get from v1 to v2
		Set<V> hashSetV = new HashSet<>(); // new hashSet
		return checkNeighbor(v1, v2, hashSetV);
	}

	private boolean checkNeighbor(V v1, V v2, Set<V> hashSetV) {
		if (v1.equals(v2))// if v1 and v2 are equal
			return true;
		if (g.neighbours(v1).contains(v2))// if v2 is in v1 neighbours
			return true;
		for (V v : g.neighbours(v1)) {// loop over all v1 neighbours
			if (!(hashSetV.contains(v))) {
				hashSetV.add(v);
				if (checkNeighbor(v, v2, hashSetV))// check if v2 can be reached from v
					return true;
			}
		}
		return false;
	}
}
