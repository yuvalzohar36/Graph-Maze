package graph;

//individual exception that extends Exception
public class GraphException extends Exception {
	private static final long serialVersionUID = 1L;

	public GraphException(String msg) { // constructor
		super(msg); // call super constructor
	}
}
