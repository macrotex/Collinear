import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


public class PointTesting {

    private Point origin;
    private Point north;
    private Point south;
    private Point east;
    private Point west;
    private Point northeast;
    private Point southeast;

    @Before
    public void setUp() {
        origin = new Point( 0,  0);
        north  = new Point( 0,  1);
        south  = new Point( 0, -1);
        east   = new Point( 1,  0);
        west   = new Point(-1,  0);
        northeast = new Point(1, 1);
        southeast = new Point(1, -1);
    }

    
    @Test
    public void testCompareTo() {
       assertTrue(origin.compareTo(origin) == 0);
       assertTrue(origin.compareTo(east)  < 0);
       assertTrue(origin.compareTo(west)  > 0);
       assertTrue(origin.compareTo(north) < 0);
       assertTrue(origin.compareTo(south) > 0);
       assertTrue(north.compareTo(north) == 0);
       
       origin.draw();
       
    }

    @Test
    public void testSlopeTo() {
        assertTrue(origin.slopeTo(east)   == 0.0);
        assertTrue(origin.slopeTo(west)   == 0.0);
        assertTrue(origin.slopeTo(north)  == Double.POSITIVE_INFINITY);
        assertTrue(origin.slopeTo(origin) == Double.NEGATIVE_INFINITY);
        assertTrue(north.slopeTo(north)   == Double.NEGATIVE_INFINITY);
        assertTrue(origin.slopeTo(southeast)   == -1.0) ;
        assertTrue(Math.abs(north.slopeTo(east) + 1.0)  < 0.001);
        assertTrue(Math.abs(north.slopeTo(west) - 1.0)  < 0.001);
    }
    
    @Test
    public void testCompareSlopeOrder() {
        Point[] myPoints1 = {
                origin, 
                northeast,
                east,
                southeast,
                south
        } ;

        Arrays.sort(myPoints1, origin.SLOPE_ORDER);
        System.out.println(myPoints1[0].toString());
        System.out.println(myPoints1[1].toString());
        
        System.out.println(String.format("slope is %f", origin.slopeTo(southeast)));
        System.out.println(String.format("slope is %f", origin.slopeTo(northeast)));
        System.out.println(northeast.toString());
        
        assertTrue(myPoints1[0].compareTo(origin)    == 0);
        assertTrue(myPoints1[1].compareTo(southeast) == 0);
        assertTrue(myPoints1[2].compareTo(east)      == 0);
        assertTrue(myPoints1[3].compareTo(northeast) == 0);
        assertTrue(myPoints1[4].compareTo(south)     == 0);

        Point[] myPoints2 = {
                new Point(10, 10),
                new Point(15, 10),
                new Point(5, 10),
                new Point(-1, 10),
                new Point(30, 10),
                new Point(3,4),
        } ;
        
        Arrays.sort(myPoints1, origin.SLOPE_ORDER);
        
    }
    
}
