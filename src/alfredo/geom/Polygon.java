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
        if(!bounds.intersects(check.bounds)) { return false; }

        for(Line line1 : lines) {
            for(Line line2 : check.lines) {
                if(line1.intersects(line2)) { return true; }
            }
        }
        
        for(Point p : points) {
            int i, j;
            for (i = 0, j = points.length - 1; i < points.length; j = i++) {
              if ( ((points[i].y > p.y) != (points[j].y > p.y)) &&
                (p.x < (points[j].x-points[i].x) * (p.y-points[i].y) / (points[j].y-points[i].y) + points[i].x) )
                { return true; } //Any intersection at all means that the polygons do in fact intersect
            }
        }
        
        return false;
    }
}