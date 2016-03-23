package alfredo.sprite;

import alfredo.geom.Point;
import alfredo.geom.Polygon;
import alfredo.paint.Graphic;
import alfredo.util.Resources;
import java.awt.image.BufferedImage;

/**
 * A Skeleton makes up the skeleton of a Sprite. It handles everything including
 * position, graphics, and intersections. The Sprite is merely a wrapper for
 * a set of interchangeable Skeletons.
 * @author TheMonsterFromTheDeep
 */
public class Skeleton extends Entity {
    public Graphic graphic; //The graphical appearance of the Skeleton
    
    private final Polygon base; //The original copy of the Skeleton's bounds
    public Polygon bounds; //The modified (translated, rotated, etc) version of the Skeleton's bounds
    
    private final Point origin; //The center of the graphic; used as the base for the center point
    private Point center; //The center where the bounds and graphic are rotated / drawn with
    //When being drawn, the coordinates for the Skeleton are where the center point of the image is drawn
    
    private Point position;
    private double direction;
    
    public static Skeleton loadFromPath(String path) {
        return new Skeleton(Resources.getImage(path));
    }
    
    public static Skeleton loadFromPath(String path, Point center) {
        return new Skeleton(Resources.getImage(path), center);
    }
    
    public Skeleton(Graphic graphic, Point center) {
        this.graphic = graphic;
        
        BufferedImage render = graphic.render();
        origin = new Point(render.getWidth() / 2.0f, render.getHeight() / 2.0f);
        this.center = center;
        //By default, construct a square bounding box
        base = new Polygon(new Point[] { new Point(0, 0), new Point(render.getWidth(), 0), new Point(render.getWidth(), render.getHeight()), new Point(0, render.getHeight())});
        bounds = base.copy();
        
        position = new Point(0, 0);
    }
    
    public Skeleton(Graphic graphic) { this(graphic, new Point(0, 0)); }
    
    //Returns the position / direction of the Skeleton.
    @Override
    public float getLocalX() { return position.x; }
    @Override
    public float getLocalY() { return position.y; }
    @Override
    public double getLocalDirection() { return direction; }
    
    //Returns the relative position of the anchor point of the Skeleton. This should be subtracted from the position when drawing.
    public float getCenterX() { return origin.x + center.x; }
    public float getCenterY() { return origin.y + center.y; }
    
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
        bounds.translate(position.x - x, 0);
        position.x = x;
    }
    
    @Override
    public void setLocalY(float y) {
        bounds.translate(0, position.y - y);
        position.y = y;
    }
    
    /**
     * Moves the Skeleton in its current direction by the specified amount.
     * @param amount The amount to move the Skeleton forward by.
     */
    public void move(float amount) {
        float mX = (float)(Math.cos(Math.toRadians(direction)) * amount);
        float mY = (float)(Math.sin(Math.toRadians(direction)) * amount);
        bounds.translate(mX, mY);
        position.x += mX;
        position.y += mY;
    }
    
    public void pointTowards(Point p) {
        double oldDirection = direction;
        direction = Math.atan2(p.y - position.y, p.x - position.x);
        bounds.rotate(oldDirection - direction, getX(), getY());
    }
    
    public void copyTransformOf(Skeleton target) {
        bounds.copyFrom(base); //Reset position, direction
        
        this.direction = target.direction; //Copy target direction, position
        this.position = target.position;
        
        bounds.rotate(direction, getX(), getY());
        bounds.translate(position.x, position.y); //Move to correct position
    }
    
    /**
     * Determines whether this Skeleton is touching the specified Skeleton in any way - either
     * they are intersecting or one contains the other.
     * 
     * This uses the bounds of the Skeletons to check for intersections.
     * @param s The Skeleton to check with.
     * @return Whether the Skeletons are touching.
     */
    public boolean touches(Skeleton s) {
        return bounds.intersects(s.bounds) || bounds.contains(s.bounds) || s.bounds.contains(bounds);
    }
}