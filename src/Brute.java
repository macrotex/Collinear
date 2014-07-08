import java.util.Arrays;


public class Brute {

    private static Point[] readInputFile(String filename) {

        
        // Step 1. Create the In object from the file name
        In in = new In(filename);
        
        // Step 1. Read the first line which will tell us the number
        // of points.
        int numberOfPoints = in.readInt();
        
        // Step 2. Initialize an array of arrays of length
        // numberOfPoints. 
        Point[] points = new Point[numberOfPoints];
        
        // Step 3. Each point is on a separate line. Read
        // them in one line at a time.
        for (int i = 0; i < numberOfPoints; ++i) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        return points;
    }
    
    public static void main(String[] args) {
        String filename = args[0];
        
        Point[] myPoints = Brute.readInputFile(filename);
        int numberOfPoints = myPoints.length;
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        
        /*
         * Construct all 4-tuples of points, testing to see if
         * they are collinear.
         * 
         * We choose 4 points at a time: a, b, c, d.
         */
        for (int a = 0; a < numberOfPoints; ++a){
            for (int b = a + 1; b < numberOfPoints; ++b) {
                for (int c = b + 1; c < numberOfPoints; ++c) {
                  for (int d = c + 1; d < numberOfPoints; ++d) {
                      Point pointA = myPoints[a];
                      Point pointB = myPoints[b];
                      Point pointC = myPoints[c];
                      Point pointD = myPoints[d];
                      
                      // We have our 4 points. Are they collinear?
                      double mAB = pointA.slopeTo(pointB);
                      double mBC = pointB.slopeTo(pointC);
                      double mCD = pointC.slopeTo(pointD);
                      
                      /*
                      System.out.println(String.format("mAB is %f", mAB));
                      System.out.println(String.format("mBC is %f", mBC));
                      System.out.println(String.format("mCD is %f", mCD));
                      */
                      if ((mAB == mBC) && (mBC == mCD)) {
                          // They are collinear
                          
                          // Order the points.
                          Point[] sortPoints = {
                                  pointA,
                                  pointB,
                                  pointC,
                                  pointD,
                          } ;

                          Arrays.sort(sortPoints);
                          
                          System.out.print(sortPoints[0].toString());
                          System.out.print(" -> ");
                          System.out.print(sortPoints[1].toString());
                          System.out.print(" -> ");
                          System.out.print(sortPoints[2].toString());
                          System.out.print(" -> ");
                          System.out.print(sortPoints[3].toString());
                          System.out.println("");
                          
                          // Draw the points
                          pointA.draw();
                          pointA.drawTo(pointB);
                      } else {
                          //System.out.println("no match!"); 
                      }
                      
                      
                  }
                }
                
            }
        }
    }

}
