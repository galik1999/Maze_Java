package graph;

import java.util.ArrayList;
import java.util.Collection;

public class Maze implements GraphInterface<Place> {

	private Place maze[][];
	private Place start;
	private Place end;

	public Maze(int size, int startx, int starty, int endx, int endy) {
		// Builder for maze, initializes the maze also
		maze = new Place[size][size];
		init(size);
		this.start = maze[startx][starty];
		this.end = maze[endx][endy];
	}

	private void init(int size) {
		// this method initializes the maze as a matrix where each index has a Place.
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				maze[i][j] = new Place(i, j, size);
	}

	public boolean addWall(int x, int y) {
		// adds a wall to the maze, if x or y are not in the range of the maze, throw an
		// exception.
		Place wall = new Place(x, y, maze.length);
		if (maze[x][y] == null)
			return false;
		if (wall.equals(start) == true)
			return false;
		if (wall.equals(end))
			return false;
		maze[x][y] = null; // if there is nothing in this index, set it to null.
		return true;
	}

	@Override
	public String toString() {
		// maze representation, @ means you can not go through there, S means start
		// point, E means
		// end point, . means a path.
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze.length; j++) {
				if (maze[i][j] == null)
					str.append("@");
				else if (maze[i][j].equals(start) && maze[i][j] != null)
					str.append("S");
				else if (maze[i][j].equals(end) && maze[i][j] != null)
					str.append("E");
				else
					str.append(".");
			}
			str.append("\n");
		}
		return str.toString();
	}

	public boolean isSolvable() {
		// we will create a copy of the maze, but put it in a graph and check if we can
		// reach the end point from the start point via "connected" method in graph.
		Graph<Place> GraphOfMaze = new Graph<>();
		addVertexes(GraphOfMaze);
		addEdges(GraphOfMaze);
		try {
			// return true if we can solve the maze(reach from start to end)
			return GraphOfMaze.connected(start, end);
		} catch (GraphException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void addEdges(Graph<Place> GraphOfMaze) {
		// adds all the edges to the graph.
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != null) {
					if (i < maze.length - 1 && maze[i + 1][j] != null) {
						try {
							GraphOfMaze.addEdge(maze[i][j], maze[i + 1][j]);
						} catch (GraphException e) {
							e.printStackTrace();
						}
					}
					if (j < maze.length - 1 && maze[i][j + 1] != null) {
						try {
							GraphOfMaze.addEdge(maze[i][j], maze[i][j + 1]);
						} catch (GraphException Edges) {
							Edges.printStackTrace();
						}
					}
				}
	}

	private void addVertexes(Graph<Place> GraphOfMaze) {
		// add all the vertices of the graph.
		for (int i = 0; i < maze.length; i++)
			for (int j = 0; j < maze.length; j++)
				if (maze[i][j] != null) {
					try {
						GraphOfMaze.addVertex(maze[i][j]);
					} catch (GraphException Vertexes) {
						Vertexes.printStackTrace();
					}
				}
	}

	@Override
	public Collection<Place> neighbours(Place p) {
		// checks all possibilities for neighbours in the maze
		// since the maze is a matrix, neighbours can we up down left right of the
		// current index
		// it can't be diagonal.
		Collection<Place> neighboursList = new ArrayList<>();
		// case up:
		if (p.getX() > 0 && maze[p.getX() - 1][p.getY()] != null)
			neighboursList.add(maze[p.getX() - 1][p.getY()]);
		// case left:
		if (p.getY() > 0 && maze[p.getX()][p.getY() - 1] != null)
			neighboursList.add(maze[p.getX()][p.getY() - 1]);
		// case down:
		if (p.getX() < maze.length - 1 && maze[p.getX() + 1][p.getY()] != null)
			neighboursList.add(maze[p.getX() + 1][p.getY()]);
		// case right:
		if (p.getY() < maze.length - 1 && maze[p.getX()][p.getY() + 1] != null)
			neighboursList.add(maze[p.getX()][p.getY() + 1]);

		return neighboursList;
	}
}
