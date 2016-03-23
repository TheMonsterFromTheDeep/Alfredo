package alfredo.sprite;

import alfredo.geom.Point;

/**
 * An Entity represents an object that has a position and a direction.
 * 
 * Entities are useful in that they can be parented to one another.
 * @author TheMonsterFromTheDeep
 */
public abstract class Entity implements Anchor {
    protected Point position;
    protected double direction;
    
    private Anchor parent;
    
    public Entity() {
        parent = new Empty();
    }
    
    public final Point getPosition() {
        return new Point(getX(), getY());
    }
    
    @Override
    public final double getDirection() {
        return parent.getDirection() + getLocalDirection();
    }
    
    @Override
    public final float getX() {
        return parent.getX() + getLocalX();
    }
    
    @Override
    public final float getY() {
        return parent.getY() + getLocalY();
    }
    
    public abstract float getLocalX();
    public abstract float getLocalY();
    public abstract double getLocalDirection();
}