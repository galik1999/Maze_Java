package graph;

public class Place {
	private int x, y;

	public Place(int x, int y, int bound) {
		// Constructor which saves the x and y value.
		// throws exception if one of them is not between 0 and bound-1.
		if (x >= 0 && x < bound && y >= 0 && y < bound) {
			this.x = x;
			this.y = y;
		} else
			throw new IllegalArgumentException();
	}

	public int getX() {
		// returns the x value of the place.
		return x;
	}

	public int getY() {
		// returns the y value of the place;
		return y;
	}

	@Override
	public boolean equals(Object object) {
		// Overriding the known equals method.
		if (object instanceof Place)
			return x == ((Place) object).x && y == ((Place) object).y;
		return false;
	}

	@Override
	public int hashCode() {
		// some hash function.
		return (int) (31 * x + 17 * y) % 31;
	}

	@Override
	public String toString() {
		// returns a string implementation of the class Place.
		return "[" + x + "," + y + "]";
	}

}
