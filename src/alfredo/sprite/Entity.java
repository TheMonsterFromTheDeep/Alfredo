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
        return (float)(parent.getX() + Math.cos(Math.toRadians(parent.getDirection() + 90)) * getLocalY() + Math.cos(Math.toRadians(parent.getDirection())) * getLocalX());
    }
    
    @Override
    public final float getY() {
        return (float)(parent.getY() + Math.sin(Math.toRadians(parent.getDirection() + 90)) * getLocalY() + Math.sin(Math.toRadians(parent.getDirection())) * getLocalX());
    }
    
    public abstract float getLocalX();
    public abstract float getLocalY();
    public abstract double getLocalDirection();
    public abstract void setLocalX(float x);
    public abstract void setLocalY(float y);
    public abstract void setLocalDirection(double dir);
    
    public void setLocalPosition(Point p) {
        setLocalX(p.x);
        setLocalY(p.y);
    }
    
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
            double newDir = getDirection();
            setLocalX(newX);
            setLocalY(newY);
            setLocalDirection(newDir);
        }
        parent = new Empty();
    }

}