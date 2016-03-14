package alfredo.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Canvas {
    BufferedImage buffer;
    Graphics graphics;
    
    public final int width;
    public final int height;
    
    public Canvas(int width, int height) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = buffer.createGraphics();
        
        this.width = width;
        this.height = height;
    }
    
    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(buffer, x, y, width, height, null);
    }
    
    public void fill(Color c) {
        graphics.setColor(c);
        graphics.fillRect(0, 0, width, height);
    }
    
    public void draw(Graphic g, int x, int y) {
        g.draw(graphics, x, y);
    }
}