package alfredo;

import alfredo.geom.Vector;
import alfredo.gfx.Graphic;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Canvas {
    private BufferedImage buffer;
    private Graphics2D graphics;
    
    private void createBuffer(int width, int height) {
        BufferedImage old = buffer;
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        if(graphics != null) {
            graphics.dispose();
        }
        
        graphics = buffer.createGraphics();
        
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        
        if(old != null) {
            graphics.drawImage(old, 0, 0, null);
            old.flush();
        }
    }
    
    public Canvas(int width, int height) {
        createBuffer(width, height);
    }
    
    public void clear() {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
    }
    
    public void resize(int width, int height) {
        createBuffer(width, height);
    }
    
    public void draw(Graphic g, float x, float y, double angle) {
        AffineTransform old = graphics.getTransform();
        
        BufferedImage image = g.getRender();
        
        int w = image.getWidth() / 2;
        int h = image.getHeight() / 2;
        
        Vector pivot = g.getPivot();
        
        graphics.rotate(Math.toRadians(angle), 0, 0);
        graphics.translate(x - w - pivot.x, y - h - pivot.y);
        
        
        graphics.setPaint(new TexturePaint(image, new Rectangle2D.Float(0, 0, image.getWidth(), image.getHeight())));
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        //graphics.drawImage(image, (int)x, (int)y, null);
        
        graphics.setTransform(old);
    }
    
    public void fill(int color, float x, float y, float width, float height) {
        
        AffineTransform old = graphics.getTransform();
        
        graphics.translate(x, y);
        graphics.scale(width, height);
        
        graphics.setColor(new Color(color));
        graphics.fillRect(0, 0, 1, 1);
        
        graphics.setTransform(old);
    }
    
    public void draw(Graphic g, float x, float y) {
        draw(g, x, y, 0);
    }
    
    public BufferedImage getRender() {
        return buffer;
    }
    
    public int getWidth() { return buffer.getWidth(); }
    public int getHeight() { return buffer.getHeight(); }
}
