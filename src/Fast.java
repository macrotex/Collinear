import java.util.Arrays;



public class Fast {
    private Node first;

    private class Node {
        Node next;
        String item;
        
        public Node(String item) {
            this.item     = item;
            this.next     = null;
        }
        
    }

    private Fast() {
        this.first = null;
    }
    
    private boolean addNode(Point[] lineSegment) {
        // Convert to a string.
        String stringVersion = Fast.lineSegmentToString(lineSegment);

        // Look for this string in the linked list.
        Node current = this.first;
        Node last    = null;

        while (current != null) {

            if (current.item.equals(stringVersion)) {
                return false;
            } else {
                last    = current;
                current = current.next;
            }
        }

        // If we get here, it was not in the list. So we can add a new node.
        Fast.Node newNode = new Node(stringVersion);

        if (last == null) {
            this.first = newNode;
        } else {
            last.next = newNode;
        }

        return true;
    }


    private static String lineSegmentToString(Point[] points) {
        String result = "";
        for (int k = 0; k < points.length - 1; ++k){
            result = result + points[k].toString();
            result = result + " -> ";
        }
        result = result + points[points.length - 1].toString();
        return result;
    }
    
    private static void printLineSegment(Point[] points) {
        System.out.print(Fast.lineSegmentToString(points));
        System.out.println("");
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
        //System.out.println("** ** ** ** ** ** ** ** ** ** ** ** ** ** ** ** **");
        String filename = args[0];

        Point[] myPoints = Fast.readInputFile(filename);
        int numberOfPoints = myPoints.length;

        Fast fast = new Fast();

        // StdDraw.setXscale(0, 32768);
        // StdDraw.setYscale(0, 32768);

        // Make a copy of myPoints suitable for sorting.
        Point[] sortedPoints = new Point[numberOfPoints];
        for (int i = 0; i < numberOfPoints; ++i) {
            sortedPoints[i] = myPoints[i];
        }

        for (int i = 0; i < numberOfPoints; ++i) {
            Point curPoint = myPoints[i];
            //System.out.println(String.format("base point is %s", curPoint.toString()));

            // Sort the points according to their slope relative
            // to curPoint.
            Arrays.sort(sortedPoints, curPoint.SLOPE_ORDER);
            //Fast.printLineSegment(sortedPoints);

            // Check that the first point in the sorted array
            // is curPoint.
            assert(sortedPoints[0].compareTo(curPoint) == 0);

            // Look for length 4 sub-arrays of sortedPoints with
            // the same slope.
            double targetSlope = Double.NEGATIVE_INFINITY;
            int potentialLineSegmentIndex = 0;

            // Set up a potential line segment. It will always start
            // with curPoint.
            Point[] potentialLineSegment = new Point[numberOfPoints];
            potentialLineSegment[potentialLineSegmentIndex] = curPoint;

            for (int j = 0; j < numberOfPoints; ++j) {
                Point pointToCheck = sortedPoints[j];
                double curSlope = curPoint.slopeTo(pointToCheck);

                if (curSlope != targetSlope) {
                    //System.out.println(String.format("curSlope %f <> targetSlope %f",  curSlope, targetSlope));
                    //System.out.println(String.format("numberwithSameSlope: %d", potentialLineSegmentIndex+1));
                    // The slopes do not match. So, we have to reset our 
                    // potential line segment. But before we do that, see if
                    // we have at least 3 points in the current line segment.
                    if (potentialLineSegmentIndex >= 3) {
                        //System.out.println("slope change with at least 4 collinear");
                        // There are at least 4 points.
                        Point[] lineSegment = new Point[potentialLineSegmentIndex + 1];

                        for (int k = 0; k < potentialLineSegmentIndex + 1; ++k){
                            lineSegment[k] = potentialLineSegment[k];
                        }
                        //Fast.printLineSegment(lineSegment);

                        Arrays.sort(lineSegment);

                        if (fast.addNode(lineSegment)) {
                            Fast.printLineSegment(lineSegment);
                        }
                    }

                    // We need to reset our potential line segment.
                    potentialLineSegmentIndex = 0;
                    potentialLineSegment[potentialLineSegmentIndex] = curPoint;
                    potentialLineSegmentIndex = potentialLineSegmentIndex + 1;
                    potentialLineSegment[potentialLineSegmentIndex] = pointToCheck;

                    targetSlope = curSlope;
                } else {
                    // The slopes match.
                    //System.out.println(String.format("found a slope match for targetSlope %f", targetSlope));
                    potentialLineSegmentIndex = potentialLineSegmentIndex + 1;
                    potentialLineSegment[potentialLineSegmentIndex] = pointToCheck;
                }
            }
        }

    }

}
