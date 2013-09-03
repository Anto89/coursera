import java.util.Comparator;

public class Point implements Comparable<Point> {

	// compare points by slope
	public final Comparator<Point> SLOPE_ORDER = new BySlope();

	private final int x; // x coordinate
	private final int y; // y coordinate

	// create the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// plot this point to standard drawing
	public void draw() {
		StdDraw.point(x, y);
	}

	// draw line between this point and that point to standard drawing
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// slope between this point and that point
	public double slopeTo(Point that) {
		if (this.x == that.x) {
			if (this.y == that.y)
				return Double.NEGATIVE_INFINITY;
			else
				return Double.POSITIVE_INFINITY;
		} else if (this.y == that.y)
			return 0;

		return ((double) that.y - (double) this.y) / ((double) that.x - (double) this.x);
	}

	// is this point lexicographically smaller than that one?
	// comparing y-coordinates and breaking ties by x-coordinates
	public int compareTo(Point that) {
		if (this.y != that.y)
			return Integer.valueOf(this.y).compareTo(Integer.valueOf(that.y));
		else
			return Integer.valueOf(this.x).compareTo(Integer.valueOf(that.x));
	}

	// return string representation of this point
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// unit test
	public static void main(String[] args) {
		/* YOUR CODE HERE */
	}
	
	public boolean equals(Object o) {
		Point otherPoint = (Point) o;
		if (this.x == otherPoint.x && this.y == otherPoint.y)
			return true;
		else return false;
	}

	private class BySlope implements Comparator<Point> {

		@Override
		public int compare(Point q, Point r) {
			return Double.valueOf(slopeTo(q)).compareTo(slopeTo(r));
		}

	}
}