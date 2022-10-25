package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V> {
	private Set<V> vertices = new HashSet<>();// holds the set of vertices.
	private Map<V, Set<V>> edges = new HashMap<>();// hold the set of neighbors for each vertex.

	public void addVertex(V v) throws GraphException {
		// adds the vertex to the graph.
		// if the vertex already exists, throws the exception.
		if (vertices.contains(v))
			throw new GraphException("Vertex already exists!");
		vertices.add(v);
		Set<V> neighbors = new HashSet<>();
		edges.put(v, neighbors);
	}

	public void addEdge(V v1, V v2) throws GraphException {
		// Adds a new edge to the graph.
		// the edge will connect the two given vertecies.
		// throws exception if the edge already exists.
		if (hasEdge(v1, v2) || !vertices.contains(v1) || !vertices.contains(v2))
			throw new GraphException("Edge already exists!");
		edges.get(v1).add(v2);
		edges.get(v2).add(v1);
	}

	public boolean hasEdge(V v1, V v2) {
		// returns true if there is an edge between v1 and v2.
		if (edges.get(v1).contains(v2) && edges.get(v2).contains(v1)) {
			return true;
		}
		return false;
	}

	public boolean connected(V v1, V v2) throws GraphException {
		// returns true if there is a path between v1 and v2.
		// if one of the vertices does not exist in the graph , exception is thrown.
		Set<V> visited = new HashSet<>(); // set for the visited vertices.
		if (!vertices.contains(v1) || !vertices.contains(v2)) {
			throw new GraphException("Vertices do not exist !");
		}
		return VISIT(v1, v2, visited);
	}

	private boolean VISIT(V v1, V v2, Set<V> visited) {
		// recursive neighbors visit.
		if (v1.equals(v2)) {
			return true;
		}
		for (V v : edges.get(v1)) {
			if (!visited.contains(v)) {
				visited.add(v);
				VISIT(v, v2, visited);
			}
		}
		return visited.contains(v2);
	}

}
