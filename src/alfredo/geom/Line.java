package alfredo.geom;

public class Line {
    public Point start;
    public Point end;
    
    public Line() {
        start = new Point();
        end = new Point();
    }
    
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    
    public Line(float sx, float sy, float ex, float ey) {
        start = new Point(sx, sy);
        end = new Point(ex, ey);
    }
    
    public boolean horizontal() { return Math.abs(end.y - start.y) < 0.001; }
    public boolean vertical() { return Math.abs(end.x - start.x) < 0.001; }
    
    public float slope() {
        if(vertical()) { throw new IllegalArgumentException("[Line.slope] Cannot return slope if vertical! Check for verticality first."); }
        return (start.y - end.y) / (start.x - end.x);
    }
    
    /**
     * 
     * @return The y-intercept of the line.
     */
    public float y_intercept() {
        return (-start.x * slope()) + start.y;
    }
    
    /**
     * Gets the y coordinate of a point along this Line with the specified x coordinate.
     * 
     * Will return a value regardless of whether the point is within bounds.
     * @param x The x coordinate of the point.
     * @return The y coordinate of the the point.
     */
    public float getY(float x) {
        return y_intercept() + (x * slope());
    }
    
    private static boolean areEqual(float a, float b) {
        return Math.abs(a - b) < 0.0001;
    }
    
    private static boolean greaterOrEqual(float a, float b) {
        return areEqual(a, b) || a > b;
    }
    
    private static boolean lessOrEqual(float a, float b) {
        return areEqual(a, b) || a < b;
    }
    
    /**
     * 
     * @param check The point to check.
     * @return Whether the point is inside the bounds of the line.
     */
    public boolean bounds(Point check) {
        return ((greaterOrEqual(check.x, start.x) && lessOrEqual(check.x, end.x)) || (greaterOrEqual(check.x, end.x) && lessOrEqual(check.x, start.x))) &&
               ((greaterOrEqual(check.y, start.y) && lessOrEqual(check.y, end.y)) || (greaterOrEqual(check.y, end.y) && lessOrEqual(check.y, start.y)));
    }
        
    public boolean intersects(Line check) {
        Point test = new Point(); //The point to test for
        if(vertical()) {
            if(check.vertical()) {
                return check.bounds(start) || check.bounds(end);
            }
            test.x = start.x;
            test.y = check.getY(test.x);
        }
        else if(check.vertical()) {
            test.x = check.start.x;
            test.y = getY(test.x);
        }
        else {
            float mySlope = slope();
            float checkSlope = check.slope();
            test.x = ((checkSlope * check.start.x) + check.start.y + (mySlope * start.x) - start.y) / (mySlope - checkSlope);
            test.y = getY(test.x);
        }
        return bounds(test) && check.bounds(test);
        
        //int xdif1 = end.x - start.x;
        //int xdif2 = check.end.x - check.start.x;
        
        /*if(xdif1 == 0 || xdif2 == 0) {
            System.out.println("0 slope branch");
            if(xdif1 == xdif2) {
                System.out.println("all zeroes");
                return ((start.y >= check.start.y && start.y <= check.end.y) || (start.y <= check.start.y && start.y >= check.end.y));
            }
            if(xdif1 == 0) {
                return ((start.x >= check.start.x && start.x <= check.end.x) || (start.x <= check.start.x && start.x >= check.end.x));
            }
            else {
                return ((check.start.x >= start.x && check.start.x <= end.x) || (check.start.x <= start.x && check.start.x >= end.x));
            }
        }
        else if (xdif1 == xdif2) {
            System.out.println("Parallel branch");
            return ((start.x >= check.start.x && start.x <= check.end.x) || (start.x <= check.start.x && start.x >= check.end.x));
        }
        else {
            System.out.println("Normal branch");
            float m1, m2, checkx;
            
            m1 = (float)(end.y - start.y) / xdif1;
            m2 = (float)(check.end.y - check.start.y) / xdif2;
            checkx = ((check.start.y - (m2 * check.start.x)) - (start.y - (m1 * start.x))) / (m1 - m2);
            return ((checkx >= check.start.x && checkx <= check.end.x) || (checkx <= check.start.x && checkx >= check.end.x));
        }*/
        
    }
}