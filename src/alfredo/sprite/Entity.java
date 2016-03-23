package alfredo.sprite;

import alfredo.geom.Point;

/**
 * An Entity represents an object that has a position and a direction.
 * 
 * Entities are useful in that they can be parented to one another.
 * @author TheMonsterFromTheDeep
 */
public abstract class Entity implements Anchor {
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
        return parent.getY() + getLocalY();
    }
    
    @Override
    public final float getY() {
        return parent.getX() + getLocalX();
    }
    
    public abstract float getLocalX();
    public abstract float getLocalY();
    public abstract double getLocalDirection();
    public abstract void setLocalX(float x);
    public abstract void setLocalY(float y);
    
    public void setParent(Anchor p, boolean retainPosition) {
        if(p != null) {
            if(retainPosition) {
                float newX = getX() - p.getX();
                float newY = getY() - p.getY();
                setLocalX(newX);
                setLocalY(newY);
            }
            parent = p;
        }
    }
    
    public void clearParent(boolean retainPosition) {
        if(retainPosition) {
            float newX = getX(), newY = getY();
            setLocalX(newX);
            setLocalY(newY);
        }
        parent = new Empty();
    }
}