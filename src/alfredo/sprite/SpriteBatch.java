package alfredo.sprite;

import alfredo.paint.Canvas;
import java.awt.Color;

public class SpriteBatch {
    public Canvas canvas;
    public Color background;
    
    public static final int DEFAULT_COLOR = 0x9ecae8; //Default background color, skyish blue

    public SpriteBatch(Color color) {
        background = color;
    }
    
    public SpriteBatch() { this(new Color(DEFAULT_COLOR)); }
    
    public void begin(Canvas c) {
        canvas = c;
        canvas.fill(background);
    }
    
    public void draw(Skeleton s) {
        canvas.draw(s.graphic, s.getX() - s.getCenterX(), s.getY() - s.getCenterY(), s.getDirection(), s.getCenterX(), s.getCenterY());
    }
    
    public void draw(Sprite s) {
        s.draw(this);
    }
}