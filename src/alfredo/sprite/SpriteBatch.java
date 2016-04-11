package alfredo.sprite;

import alfredo.paint.Canvas;
import alfredo.paint.Image;
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
    
    
    public void draw(Image i, Drawable d) {
        canvas.draw(i, d.getDrawX(), d.getDrawY(), d.getDrawDirection(), d.getDrawPivotX(), d.getDrawPivotY());
    }
    
     public void draw(Sprite s) {
        draw(s.image, s); //Draw the Sprite's image onto the Sprite
    }
}