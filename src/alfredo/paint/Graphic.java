package alfredo.paint;

import java.awt.Graphics;

/**
 * A Graphic is simply something that can be drawn to a Canvas.
 * 
 * It implements its own method that describes how it is drawn given a Graphics object and a position.
 * @author TheMonsterFromTheDeep
 */
public interface Graphic {
    void draw(Graphics graphics, int x, int y);
}