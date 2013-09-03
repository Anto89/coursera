import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Fast {

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

		Point origin;
		Point[] duplicatedTab;
		LinkedList<Point> collinear = new LinkedList<Point>();
		List<Point> savedOrigins = new ArrayList<Point>();

		for (int i = 1; i < points.length; i++) {
			origin = points[i - 1];
			duplicatedTab = Arrays.copyOfRange(points, i, points.length);
			Arrays.sort(duplicatedTab, origin.SLOPE_ORDER);

			for (int j = 0; j < duplicatedTab.length; j++) {
				if (collinear.isEmpty())
					collinear.add(duplicatedTab[j]);
				else if (origin.slopeTo(collinear.getLast()) == origin
						.slopeTo(duplicatedTab[j]))
					collinear.add(duplicatedTab[j]);
				else {
					if (collinear.size() > 2) {
						collinear.add(origin);

						// Check sub-segment.
						if(!isSubSegment(savedOrigins, origin, collinear.getFirst())) {
							displayResults(collinear);
							savedOrigins.add(origin);
						}
					}
					collinear.clear();
					collinear.add(duplicatedTab[j]);
				}
			}
			if (collinear.size() > 2) {
				collinear.add(origin);

				// Check sub-segment.
				if(!isSubSegment(savedOrigins, origin, collinear.getFirst())) {
					displayResults(collinear);
					savedOrigins.add(origin);
				}
			}

			collinear.clear();
		}
	}

	private static boolean isSubSegment(List<Point> savedOrigins,
			Point newOrigin, Point anyPoint) {
		if (savedOrigins.isEmpty())
			return false;
		
		for (Point point : savedOrigins) {
			if (newOrigin.slopeTo(point) == newOrigin.slopeTo(anyPoint))
				return true;
		}
		return false;
	}

	private static void displayResults(List<Point> points) {
		Collections.sort(points);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		points.get(0).drawTo(points.get(points.size() - 1));

		StringBuffer result = new StringBuffer(points.get(0).toString());
		for (int i = 1; i < points.size(); i++) {
			result.append(" -> ").append(points.get(i));
		}
		StdOut.println(result);
	}
}
