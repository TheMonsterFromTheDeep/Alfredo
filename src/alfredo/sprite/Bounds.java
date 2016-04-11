package alfredo.sprite;

import alfredo.geom.Point;
import alfredo.geom.Polygon;

/**
 * A Bounds object represents a bounding box of some sort.
 * @author TheMonsterFromTheDeep
 */
public class Bounds extends Entity {
    private volatile Polygon base;
    public Polygon bounds; //The modified (translated, rotated, etc) version of the bounds data
    
    protected final void init(Polygon p) {
        base = p;
        bounds = base.copy();
    }
    
    protected Bounds() { location = new Point(0, 0); }
    
    public Bounds(Polygon p) {
        this();
        init(p);
    }
    
    /**
     * Initializes the Bounds by centering the Polygon on the specified point.
     * @param p The Polygon that forms the Bounds
     * @param center The Point that the Polygon should be centered on.
     */
    public Bounds(Polygon p, Point center) {
        this();
        base = p;
        base.translate(-center.x, -center.y);
        init(base);
    }
    
    @Override
    public void setX(float x) {
        //bounds.translate(x - location.x, 0);
        location.x = x;
    }
    
    @Override
    public void setY(float y) {
        //bounds.translate(0, y - location.y);
        location.y = y;
    }
    
    @Override
    public void setDirection(double dir) {
        //double oldDirection = direction;
        direction = dir;
        //bounds.rotate(oldDirection - direction, getWorldX(), getWorldY());
    }
    
    /**
     * A specialized transform copy method which cleans the Bound's transform's inaccuracies
     * by copying from the base polygon that forms the Bounds.
     * @param target The Transform to copy.
     */
    public void cleanCopyTransform(Transform target) {
        bounds.copyFrom(base); //Reset position, direction
        
        this.direction = target.direction; //Copy target direction, position
        this.location = target.location;
        
        bounds.rotate(direction, getX(), getY());
        bounds.translate(location.x, location.y); //Move to correct position
    }
    
    /**
     * Applies the global transformation of the Bounds object to its actual Polygon data.
     * 
     * This is called whenever the Bounds does an intersection check so that the Bounds has
     * the proper and correct data for everything that it is doing.
     */
    public void apply() {
        bounds.copyFrom(base);
        
        float x = getWorldX();
        float y = getWorldY();
        
        bounds.rotate(getWorldDirection(), 0, 0);
        bounds.translate(x, y); //Translate the bounds object to have global position (this should work... right?)
        //
        
    }
    
    /**
     * Determines whether this Bounds is touching the specified Bounds in any way - either
     * they are intersecting or one contains the other.
     * 
     * This uses the bounds of the Bounds to check for intersections.
     * @param b The Bounds to check with.
     * @return Whether the Bounds are touching.
     */
    public final boolean touches(Bounds b) {
        apply(); //Make sure bounds position/rotation is good
        return bounds.intersects(b.bounds) || bounds.contains(b.bounds) || b.bounds.contains(bounds);
    }
}