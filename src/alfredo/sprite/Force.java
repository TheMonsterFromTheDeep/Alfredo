package alfredo.sprite;

import alfredo.geom.Line;
import alfredo.geom.Point;
import alfredo.geom.Polygon;

/**
 * A Force is a specialized object for sprite movement. It allows a Bounds object to move exactly to another Bounds object
 * based on a preexisting force. This is mainly for accurate collisions.
*/
public class Force {
    Line[] vectors;
    Point center; //The "new" center of the Force; e.g. the location that becomes a Bounds' location when apply() is called
    Bounds handle;
    
    public Force(Bounds bounds, float dx, float dy) {
        Point[] points = bounds.bounds.copyPoints();
        vectors = new Line[points.length];
        for(int i = 0; i < vectors.length; i++) {
            vectors[i] = new Line(points[i], new Point(points[i].x + dx, points[i].y + dy));
        }
        center = bounds.getPosition().getTranslation(dx, dy);
        
        handle = bounds; //The handle that this Force can be applied to.
    }
    
    /**
     * Simply initializes a Force object for use with a certain Bounds but does not actually create an applicable force.
     * @param bounds The relevant Bounds object.
     */
    public Force(Bounds bounds) {
        handle = bounds;
        vectors = new Line[bounds.bounds.points.length];
        for(int i = 0; i < vectors.length; i++) {
            vectors[i] = new Line();
        }
        center = new Point();
    }
    
    private static boolean equalPoints(Point a, Point b) {
        return (Math.abs(a.x - b.x) < 0.0001) && (Math.abs(a.y - b.y) < 0.0001);
    }
    
    /**
     * Causes the Force to "interact" with a Bounds.
     * 
     * Essentially, what this does is checks to see how the Force would cause its handle Bounds to
     * intersect with the specified Bounds object. If it would go inside it, (not having *originated*
     * inside it, of course) then the Force will modify itself to prevent that from happening.
     * 
     * Basically, if a Bounds has a Force applied that has interacted with other Bounds objects, the
     * applied Bounds will have accurate collisions to the extent of direction x/y movement.
     * 
     * It does not, however, interact in terms of other forces, including rotations and gravity.
     * @param b The Bounds to do an interaction with.
     */
    public void interact(Bounds b) {
        Polygon p = b.bounds; //Handle for convenience (compiler plz optimize)
        
        Point check = new Point(); //Passed to Line.intersects() because the intersection point is required for shifting the Force backwards
        
        for(Line vector : vectors) { //Iterate through lines to check for intersections
            for(Line pLine : p.lines) {
                if(vector.intersects(pLine, check)) { //The force is causing the parent Bounds to move into the new Bounds; take corrective action!
                    //Shorten the Force so that the interaction is pushed back; essentially, move *this* Line's point back
                    //to the intersection point with the other Bounds, and move all others back by an equal amount                        
                    float dx = check.x - vector.end.x;
                    float dy = check.y - vector.end.y;
                    for(Line l : vectors) { l.end.translate(dx, dy); } //Translate all lines for future interactions
                    center.translate(dx, dy); //translate the center
                }
            }
        }
    }
    
    /**
     * Applies the force to the Bounds object, causing it to take the new Position determined by the Force.
     */
    public void apply() {
        handle.setLocalPosition(center);
    }
    
    /**
     * Causes the Force, using its old handle as the parent, to load itself with updated positions of its
     * parent so that it can be used for interaction again.
     * 
     * This prevents having to allocate new memory for new Points and new Lines.
     * @param dx The new x translation.
     * @param dy The new y translation.
     */
    public void reforce(float dx, float dy) {
        for(int i = 0; i < vectors.length; i++) {
            vectors[i].start.copyFrom(handle.bounds.points[i]);
            handle.bounds.points[i].getTranslation(dx, dy, vectors[i].end);
        }
        handle.getPosition().getTranslation(dx, dy, center);
    }
}