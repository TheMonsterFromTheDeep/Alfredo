package alfredo.sprite;

import alfredo.geom.Point;

/**
 * Sprites are a specialized class meant to basically be inherited singletons. In fact,
 * Sprites are basically a container for logic for interacting Skeletons.
 * 
 * They can have their own logic or have it defined externally.
 * 
 * Sprites are used for automatic drawing of handling; the SpriteBatch will draw
 * Sprites with the proper rotation and Cameras will draw Sprites with
 * the proper offscreen offset in scrolling games.
 * @author TheMonsterFromTheDeep
 */
public class Sprite extends Entity {
    public Skeleton current;
    
    public final void changeSkeleton(Skeleton s) {
        s.copyTransformOf(current);
        current = s;
    }
    
    public void update() { }
    
    public boolean touches(Sprite s) {
        return current.touches(s.current);
    }
    
    public boolean touches(Skeleton s) {
        return current.touches(s);
    }

    @Override
    public float getLocalX() {
        return current.getLocalX();
    }

    @Override
    public float getLocalY() {
        return current.getLocalY();
    }

    @Override
    public double getLocalDirection() {
        return current.getLocalDirection();
    }
}