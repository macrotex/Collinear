/*************************************************************************
 * Name: Fred Habster
 * Email: 
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {



    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private final double positiveZeroDouble = Double.parseDouble("+0");
    
    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        if (this.compareTo(that) == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        } else if (this.y == that.y)  {
            return positiveZeroDouble;
        } else {
            return ((double) (this.y - that.y)) / ((double) (this.x - that.x));
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    /**
     * Return -1 if this object is less than that
     * Return 0 if this object is equal to that
     * Return 1 id this object is greater than that
     */
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        } else if (this.y > that.y) {
            return 1;
        } else if (this.x < that.x) {
            return -1;
        } else if (this.x > that.x) {
            return 1;
        } else {
            return 0;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new BySlope();
    
    /**
     * 
     * @author Fred Habster
     * Compare points by the slopes they make with the invoking point (x0, y0). 
     * Formally, the point (x1, y1) is less than the point (x2, y2) 
     * if and only if the slope (y1 - y0) / (x1 - x0) is less than the 
     * slope (y2 - y0) / (x2 - x0). Treat horizontal, vertical, 
     * and degenerate line segments as in the slopeTo() method. 
     * 
     */
    private class BySlope implements Comparator<Point> {
        public int compare(Point point1, Point point2) {
            double m1 = Point.this.slopeTo(point1);
            double m2 = Point.this.slopeTo(point2);
            if (m1 == m2) {
                return 0;
            } else if (m1 < m2) {
                return -1;
            } else {
                return 1;
            }
        }
    }
    


    // unit test

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
