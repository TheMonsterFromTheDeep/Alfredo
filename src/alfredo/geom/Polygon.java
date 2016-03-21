package alfredo.geom;

public class Polygon {
    Point[] points;
    Line[] lines;
    
    Rectangle bounds;
    
    private void calculateBounds() {
        bounds = new Rectangle(0, 0, 0, 0);
        for(Point p : points) {
            if(p.x < bounds.left()) { bounds.setLeft(p.x); }
            if(p.x > bounds.right()) { bounds.setRight(p.x); }
            if(p.y < bounds.top()) { bounds.setTop(p.y); }
            if(p.y > bounds.bottom()) { bounds.setBottom(p.y); }
        }
    }
    
    public Polygon(Point[] points) {
        this.points = points;
        
        lines = new Line[points.length - 1];
        for(int i = 0; i < lines.length; i++) {
            lines[i] = new Line(points[i], points[i + 1]);
        }
        
        calculateBounds();
    }
    
    public boolean contains(Point p) {
        //Thanks to http://stackoverflow.com/questions/217578/how-can-i-determine-whether-a-2d-point-is-within-a-polygon#answer-2922778
        int i, j;
        boolean c = false;
        for (i = 0, j = points.length - 1; i < points.length; j = i++) {
          if ( ((points[i].y > p.y) != (points[j].y > p.y)) &&
            (p.x < (points[j].x-points[i].x) * (p.y-points[i].y) / (points[j].y-points[i].y) + points[i].x) )
            { c = !c; }
        }
        return c;
    }
    
    /**
     * Returns whether this polygon intersects the other polygon,
     * in the sense that their edges are intersecting or one
     * lies totally within the other.
     * @param check The polygon to test for intersection.
     * @return Whether the polygons intersect.
     */
    public boolean intersects(Polygon check) {
        if(!bounds.intersects(check.bounds)) { System.out.println("No bounds!"); return false; }

        for(Line line1 : lines) {
            for(Line line2 : check.lines) {
                if(line1.intersects(line2)) { return true; }
            }
        }
        
        for(Point p : points) {
            if(check.contains(p)) { return true; }
        }
        
        for(Point p : check.points) {
            if(contains(p)) { return true; }
        }
        
        return false;
    }
    
    /**
     * Rotates the polygon around the specified anchor point by the
     * specified number of degrees.
     * 
     * This method modifies the Polygon in-place.
     * @param degrees The amount, in degrees, to rotate the polygon
     * @param ax The anchor point x
     * @param ay The anchor point y
     */
    public void rotate(double degrees, float ax, float ay) {
        //IMPORTANT: It is not necessary to update the Lines because they all contain a direct pointer to the points.
        for(Point point : points) {
            double angle = Math.atan2(point.y - ay, point.x - ax);
            point.x = (float)(ax + Math.cos(angle + Math.toRadians(degrees)));
            point.y = (float)(ay + Math.sin(angle + Math.toRadians(degrees)));
        }
        calculateBounds();
    }
    
    /**
     * Translates the polygon by the specified amount.
     * @param dx The amount to translate the Polygon along the x axis.
     * @param dy The amount to translate the Polygon along the y axis.
     */
    public void translate(float dx, float dy) {
        //IMPORTANT: It is not necessary to update the Lines because they all contain a direct pointer to the points.
        for(Point point : points) {
            point.x += dx;
            point.y += dy;
        }
        calculateBounds();
    }
    
    public void printBounds() {
        System.out.println("Bounds: " + bounds.x + ", " + bounds.y + "; " + bounds.right() + ", " + bounds.bottom());
    }
}