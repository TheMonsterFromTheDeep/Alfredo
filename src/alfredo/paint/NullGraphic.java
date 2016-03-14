package alfredo.paint;

import java.awt.Graphics;

/**
 * Represents a null or improperly created Graphic. Does not draw anything.
 * @author 104063995
 */
public class NullGraphic implements Graphic {
    @Override
    public void draw(Graphics graphics, int x, int y) { }
}