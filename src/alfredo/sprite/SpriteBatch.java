package alfredo.sprite;

import alfredo.paint.Canvas;
import java.awt.Color;

public class SpriteBatch {
    public Canvas canvas;
    public Color background;
    
    float offsetx;
    float offsety;
    
    public static final int DEFAULT_COLOR = 0x9ecae8; //Default background color, skyish blue

    public SpriteBatch(Color color) {
        background = color;
    }
    
    public SpriteBatch() { this(new Color(DEFAULT_COLOR)); }
    
    public void begin(Canvas c) {
        canvas = c;
        canvas.fill(background);
        offsetx = c.width / 2f;
        offsety = c.height / 2f;
    }
    
    public void draw(Skeleton s) {
        canvas.draw(s.graphic, s.getX() + offsetx, s.getY() + offsety, s.getDirection(), s.getCenterX(), s.getCenterY());
    }
    
    public void draw(Sprite s) {
        draw(s.current);
    }
}