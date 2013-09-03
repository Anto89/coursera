import java.util.Arrays;

public class Brute {

	public static void main(String[] args) {
		In in = new In(args[0]);
		// Size of the array.
		int size = in.readInt();
		Point[] points = new Point[size];

		// Fill with input datas.
		for (int i = 0; i < size; i++) {
			Point p = new Point(in.readInt(), in.readInt());
			points[i] = p;
			// Draw each point.
			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);
			p.draw();
		}

		// Test of all combinations.
		for (int c1 = 0; c1 <= size - 4; c1++)
			for (int c2 = c1 + 1; c2 <= size - 3; c2++)
				for (int c3 = c2 + 1; c3 <= size - 2; c3++)
					for (int c4 = c3 + 1; c4 <= size - 1; c4++) {
						 StdOut.println(Integer.toString(c1) + Integer.toString(c2)+Integer.toString(c3)+Integer.toString(c4));
						if (isCollinear(points[c1], points[c2], points[c3], points[c4]))
							displayResults(points[c1], points[c2], points[c3], points[c4]);
					}
	}

	private static boolean isCollinear(Point p, Point q, Point r, Point s) {
		if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
			System.out.println(p.slopeTo(q) + " = " + p.slopeTo(r) + " = " + p.slopeTo(s));
			return true;
		}
		return false;
	}

	private static void displayResults(Point p, Point q, Point r, Point s) {
		Point[] line = new Point[] { p, q, r, s };
		Arrays.sort(line);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		line[0].drawTo(line[3]);
		StdOut.println(line[0].toString() + " -> " + line[1].toString()
				+ " -> " + line[2].toString() + " -> " + line[3].toString());
	}
}
