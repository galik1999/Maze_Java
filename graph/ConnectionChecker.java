package graph;

import java.util.HashSet;
import java.util.Set;

public class ConnectionChecker<V> {
	private GraphInterface<V> g;

	public ConnectionChecker(GraphInterface<V> g) {
		// constructor which initializes the class with the given graph.
		this.g = g;
	}

	public boolean check(V v1, V v2) {
		// returns true if v2 is reachable from v1.
		Set<V> visited = new HashSet<>();
		return VISIT(v1, v2, visited);

	}

	private boolean VISIT(V v1, V v2, Set<V> visited) {
		// recursive neighbors visit.
		if (v1.equals(v2)) {
			return true;
		}
		for (V v : g.neighbours(v1)) {
			if (!visited.contains(v)) {
				visited.add(v);
				VISIT(v, v2, visited);
			}
		}
		return visited.contains(v2);
	}

}
