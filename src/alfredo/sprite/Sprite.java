package alfredo.sprite;

import alfredo.geom.Point;

/**
 * Sprites are containers that have their own position and direction. They can be used
 * to build constructs of multiple other Entities, such as Skeletons.
 * @author TheMonsterFromTheDeep
 */
public class Sprite extends Entity {
    protected Point position;
    protected double direction;
    
    public void update() { }
    public void draw(SpriteBatch sb) { }

    @Override
    public float getLocalX() {
        return position.x;
    }

    @Override
    public float getLocalY() {
        return position.y;
    }

    @Override
    public double getLocalDirection() {
        return direction;
    }

    @Override
    public void setLocalX(float x) {
        position.x = x;
    }

    @Override
    public void setLocalY(float y) {
        position.y = y;
    }

    @Override
    public void setLocalDirection(double dir) {
        direction = dir;
    }
}