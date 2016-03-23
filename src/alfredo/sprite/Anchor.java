package alfredo.sprite;

/**
 * The Anchor is the top-level class for Entities. It is the base for an Entity parent.
 * 
 * This allows the Empty to exist as a null parent.
 * 
 * @author TheMonsterFromTheDeep
 */
public interface Anchor {
    float getX();
    float getY();
    double getDirection();
}