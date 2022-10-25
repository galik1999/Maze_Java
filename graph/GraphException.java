package graph;

@SuppressWarnings("serial")
public class GraphException extends Exception {
	// defining new exception class which inherits from exception.
	public GraphException(String msg) {
		// constructor with a msg about the GraphException.
		super(msg);
	}
}
