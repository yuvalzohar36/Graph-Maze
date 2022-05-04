package graph;

import java.util.Collection;

//a generic Interface that representing a graph
public interface GraphInterface<V> {
	public Collection<V> neighbours(V v);
}
