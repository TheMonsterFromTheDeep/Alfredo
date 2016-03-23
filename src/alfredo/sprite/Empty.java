package alfredo.sprite;

/**
 * The Empty is an Entity with no unique properties; it is essentially used
 * as a null parent.
 * @author TheMonsterFromTheDeep
 */
public final class Empty implements Anchor {

    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public double getDirection() {
        return 0;
    }
    
}