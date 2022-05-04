package graph;

//The class will describe a two-dimensional location within a square
public class Place {
	private int x, y;

	public Place(int x, int y, int bound) { // Constructor
		if (x < 0 || x > bound - 1 || y < 0 || y > bound - 1) // if x or y outside of the bounds
			throw new IllegalArgumentException();
		this.x = x;
		this.y = y;
	}

	public int getX() { // get x position
		return x;
	}

	public int getY() { // get y position
		return y;
	}

	@Override
	public boolean equals(Object other) {
		// method that override the Object equals method
		if (other instanceof Place) {
			Place o = (Place) other; // casting
			if (x == o.getX() && y == o.getY()) // if they have the same x and y positions
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// method that override that hashCode of Object
		return Integer.hashCode(x) * 31 + Integer.hashCode(y);
	}
}
