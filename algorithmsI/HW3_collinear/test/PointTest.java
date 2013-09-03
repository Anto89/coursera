import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class PointTest {

    @Test
    public void testSlopTo() {

	Point p = new Point(1, 1);

	// Same Point.
	Point q = new Point(1, 1);
	assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q), 0);

	// Vertical Line.
	Point r = new Point(1, 2);
	assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(r), 0);

	// Horizontal Line.
	Point s = new Point(2, 1);
	assertEquals(0, p.slopeTo(s), 0);

	// Other
	Point t = new Point(2, 2);
	assertEquals(1, p.slopeTo(t), 0);

	// Other
	Point u = new Point(0, 0);
	assertEquals(1, p.slopeTo(u), 0);

	// Other
	Point v = new Point(0, 3);
	assertEquals(-2, p.slopeTo(v), 0);
    }

    @Test
    public void testCompareTo() {
	Point p = new Point(1, 1);

	// Same Point.
	Point q = new Point(1, 1);
	assertEquals(0, p.compareTo(q));

	// Greater Point.
	Point r = new Point(2, 1);
	assertEquals(-1, p.compareTo(r));

	// Greater Point.
	Point s = new Point(1, 2);
	assertEquals(-1, p.compareTo(s));

	// Smaller Point.
	Point t = new Point(0, 1);
	assertEquals(1, p.compareTo(t));

	// Smaller Point.
	Point u = new Point(1, 0);
	assertEquals(1, p.compareTo(u));

	// Array - Natural sorting.
	Point[] points = new Point[] { p, r, s, t, u };
	Arrays.sort(points);
	assertArrayEquals(new Point[] { u, t, p, r, s }, points);
	
	// Array - Sorting by slope.
	Arrays.sort(points, p.SLOPE_ORDER);
	System.out.println(Arrays.toString(points));
	assertArrayEquals(new Point[] { p, t, r, u, s }, points);
    }

}
