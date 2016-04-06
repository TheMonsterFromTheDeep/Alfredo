package alfredo.sprite;

import alfredo.geom.Point;
import alfredo.geom.Polygon;

/**
 * A Bounds object represents a bounding box of some sort.
 * @author TheMonsterFromTheDeep
 */
public class Bounds extends Entity {
    private Polygon base;
    public Polygon bounds; //The modified (translated, rotated, etc) version of the bounds data
    
    private Point position;
    private double direction;
    
    protected final void init(Polygon p) {
        base = p;
        bounds = base.copy();
    }
    
    protected Bounds() { position = new Point(0, 0); }
    
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
    
    
    
    //Returns the position / direction of the Skeleton.
    @Override
    public float getLocalX() { return position.x; }
    @Override
    public float getLocalY() { return position.y; }
    @Override
    public double getLocalDirection() { return direction; }
    
    public void rotate(double degrees) {
        direction += degrees;
        bounds.rotate(degrees, getX(), getY());
    }
    
    public void moveX(float amount) {
        bounds.translate(amount, 0);
        position.x += amount;
    }
    
    public void moveY(float amount) {
        bounds.translate(0, amount);
        position.y += amount;
    }
    
    @Override
    public void setLocalX(float x) {
        bounds.translate(x - position.x, 0);
        position.x = x;
    }
    
    @Override
    public void setLocalY(float y) {
        bounds.translate(0, y - position.y);
        position.y = y;
    }
    
    @Override
    public void setLocalDirection(double dir) {
        double oldDirection = direction;
        direction = dir;
        bounds.rotate(oldDirection - direction, getX(), getY());
    }
    
    public void move(float amount) {
        float mX = (float)(Math.cos(Math.toRadians(direction + 90)) * amount);
        float mY = (float)(Math.sin(Math.toRadians(direction + 90)) * amount);
        bounds.translate(mX, mY);
        position.x += mX;
        position.y += mY;
    }
    
    public void pointTowards(Point p) {
        double oldDirection = direction;
        direction = Math.toDegrees(Math.atan2(p.y - position.y, p.x - position.x)) - 90;
        bounds.rotate(oldDirection - direction, getX(), getY());
    }
    
    public void copyTransformOf(Bounds target) {
        bounds.copyFrom(base); //Reset position, direction
        
        this.direction = target.direction; //Copy target direction, position
        this.position = target.position;
        
        bounds.rotate(direction, getX(), getY());
        bounds.translate(position.x, position.y); //Move to correct position
    }
    
    /**
     * Determines whether this Bounds is touching the specified Bounds in any way - either
     * they are intersecting or one contains the other.
     * 
     * This uses the bounds of the Bounds to check for intersections.
     * @param b The Bounds to check with.
     * @return Whether the Bounds are touching.
     */
    public boolean touches(Bounds b) {
        return bounds.intersects(b.bounds) || bounds.contains(b.bounds) || b.bounds.contains(bounds);
    }
}