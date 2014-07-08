import java.util.Arrays;


public class Fast {

  private static void printLineSegment(Point[] points) {
      for (int k = 0; k < points.length - 1; ++k){
          System.out.print(points[k].toString());
          System.out.print(" -> ");
      }
      System.out.print(points[points.length - 1].toString());
      System.out.println("\n");
      return ;
  }
  
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
  
  /**
   * Can assume that all points in input file are DISTINCT.
   * @param args
   */
    public static void main(String[] args) {
        String filename = args[0];
        
        Point[] myPoints = Fast.readInputFile(filename);
        int numberOfPoints = myPoints.length;
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        // Make a copy of myPoints suitable for sorting.
        Point[] sortedPoints = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; ++i) {
            sortedPoints[i] = myPoints[i];
        }
        
        for (int i = 0; i < numberOfPoints; ++i) {
            Point curPoint = myPoints[i];
            System.out.println(String.format("base point is %s", curPoint.toString()));
            
            // Sort the points according to their slope relative
            // to curPoint.
            Arrays.sort(sortedPoints, curPoint.SLOPE_ORDER);
            Fast.printLineSegment(sortedPoints);
            
            // Check that the first point in the sorted array
            // is curPoint.
            assert(sortedPoints[0].compareTo(curPoint) == 0);

            // Look for length 4 sub-arrays of sortedPoints with
            // the same slope.
            double targetSlope = Double.NEGATIVE_INFINITY;
            int numberWithSameSlope = -1;
            
            // Set up a potential line segment. It will always start
            // with curPoint.
            Point[] potentialLineSegment = new Point[numberOfPoints];
            // potentialLineSegment[0] = curPoint;
            
            for (int j = 0; j < numberOfPoints; ++j) {
                Point pointToCheck = sortedPoints[j];
                double curSlope = curPoint.slopeTo(pointToCheck);

                System.out.println(String.format("slope is %f", curSlope));
                if (curSlope == targetSlope) {
                    System.out.println("found a slope match");
                    // We have a match, so increment the counter.
                    numberWithSameSlope = numberWithSameSlope + 1;
                    potentialLineSegment[numberWithSameSlope] = pointToCheck;

                } else {
                    // Slopes don't match. If numberWithSameSlope is larger 
                    // than 3 we have found a line. Print it out.
                    if (numberWithSameSlope >= 3) {
                        System.out.println("slope change with at least 4 collinear");
                        // Copy the points that are collinear into a new
                        // array so we can sort them lexicographically.
                        Point[] lineSegment = new Point[numberWithSameSlope];
                        for (int k = 0; k < numberWithSameSlope; ++k){
                            lineSegment[k] = potentialLineSegment[k];
                        }
                        Arrays.sort(lineSegment, curPoint.SLOPE_ORDER);
                        for (int k = 0; k < numberWithSameSlope - 1; ++k){
                            System.out.print(lineSegment[k].toString());
                            System.out.print(" -> ");
                        }
                        System.out.print(lineSegment[numberWithSameSlope].toString());
                    }
                    // Reset targetSlope
                    targetSlope = curSlope;
                    System.out.println(String.format("resetting target slope to %f", targetSlope));

                }
            }
            
        }
        

    }

}
