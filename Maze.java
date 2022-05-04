package graph;

import java.util.Collection;
import java.util.HashSet;

//class that representing the maze, implementing the GraphInterface with Place type
public class Maze implements GraphInterface<Place> {
	private Place start, end; // start and end points of the maze
	private Place[][] maze;

	public Maze(int size, int startx, int starty, int endx, int endy) { // Constructor
		maze = new Place[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				maze[i][j] = new Place(i, j, size); // new place object at every cell of maze
		start = maze[startx][starty]; // start point
		end = maze[endx][endy]; // end point
	}

	public boolean addWall(int x, int y) {
		/*
		 * Put a wall in the given place. Here, too, the deviation will be thrown away
		 * If the wall is outside the maze. If in the place you want to place The wall
		 * already has a wall, either that's the starting point or Finally, the method
		 * will return false and not add it. Otherwise it will return true.
		 */
		if (!isInMaze(x, y)) // if x or y isn't in the maze bounds
			throw new IllegalArgumentException();
		if (maze[x][y] == null) // if this is wall already
			return false;
		if (maze[x][y].equals(start) || maze[x][y].equals(end)) // if this is the start or end point
			return false;
		maze[x][y] = null; // make a wall
		return true;
	}

	@Override
	public String toString() {
		// toString method according to the given format
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (start.getX() == i && start.getY() == j)
					b.append("S"); // start point
				else if (end.getX() == i && end.getY() == j)
					b.append("E"); // end point
				else if (maze[i][j] == null)
					b.append("@"); // wall
				else
					b.append("."); // none of the previous one
			}
			b.append("\n");
		}
		return b.toString();
	}

	public boolean isSolvable() {
		/*
		 * Create <Place> Graph, insert the graph induced from the maze , and check Is
		 * it possible to get from the starting point to the end point using the
		 * connected method
		 */
		Graph<Place> graph = new Graph<>();
		addAllVertex(graph); // add all vertex
		addAllEdges(graph); // add all edges
		try {
			return graph.connected(start, end); // return if they are connected
		} catch (GraphException e) {
			e.printStackTrace(); // in case of GraphException exception
		}
		return false;
	}

	private void addAllVertex(Graph<Place> graph) {
		// add the all vertex to the graph
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] != null) { // isn't wall
					try {
						graph.addVertex(maze[i][j]); // add the current Place to the graph
					} catch (GraphException e) {
						e.printStackTrace(); // in case of GraphException exception
					}
				}
			}
		}
	}

	private void addAllEdges(Graph<Place> graph) {
		// add the all edges to the graph
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] != null) { // isn't wall
					// add the all edges if possible(the all neighbours)
					addSpecEdge(graph, i, j, 1, 0);
					addSpecEdge(graph, i, j, -1, 0);
					addSpecEdge(graph, i, j, 0, 1);
					addSpecEdge(graph, i, j, 0, -1);

				}
			}
		}
	}

	private boolean isInMaze(int x, int y) {
		// return true if x and y position is in the graph, otherwise return false
		if (x < 0 || x > maze.length - 1 || y < 0 || y > maze.length - 1)
			return false;
		return true;
	}

	private void addSpecEdge(Graph<Place> graph, int i, int j, int iMove, int jMove) {
		// method that append specific edge to the graph if possible
		if (isInMaze(i + iMove, j + jMove) && maze[i + iMove][j + jMove] != null) { // in maze bounds and not a wall
			try {
				if (!(graph.hasEdge(maze[i][j], maze[i + iMove][j + jMove]))) // isn't already in the graph
					graph.addEdge(maze[i][j], maze[i + iMove][j + jMove]);// add the edge to the graph
			} catch (GraphException e) {
				e.printStackTrace();// in case of GraphException exception
			}
		}
	}

	@Override
	public Collection<Place> neighbours(Place v) {
		// method return collection of the all neighbors of v
		Collection<Place> hashSetV = new HashSet<>();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (v.equals(maze[i][j])) { // v found
					// add the all nrighbours if exists and not a wall
					if (isInMaze(i + 1, j) && maze[i + 1][j] != null)
						hashSetV.add(maze[i + 1][j]);
					if (isInMaze(i - 1, j) && maze[i - 1][j] != null)
						hashSetV.add(maze[i - 1][j]);
					if (isInMaze(i, j + 1) && maze[i][j + 1] != null)
						hashSetV.add(maze[i][j + 1]);
					if (isInMaze(i, j - 1) && maze[i][j - 1] != null)
						hashSetV.add(maze[i][j - 1]);
				}
			}
		}
		return hashSetV;
	}
}
