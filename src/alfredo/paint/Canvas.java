package alfredo.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;

public class Canvas {
    BufferedImage buffer;
    Graphics graphics;
    
    public final int width;
    public final int height;
    
    private final float offsetx;
    private final float offsety;
    
    private float translateX(float x) {
        return x + offsetx;
    }
    
    private float translateY(float y) {
        return y + offsety;
    }
    
    public Canvas(int width, int height) {
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        graphics = buffer.createGraphics();
        
        this.width = width;
        this.height = height;
        
        this.offsetx = width / 2f;
        this.offsety = height / 2f;
    }
    
    public void render(Graphics g, int x, int y, int width, int height) {
        g.drawImage(buffer, x, y, width, height, null);
    }
    
    public void fill(Color c) {
        graphics.setColor(c);
        graphics.fillRect(0, 0, width, height);
    }
    
    public void draw(Graphic g, float x, float y) {
        graphics.drawImage(g.render(), (int)translateX(x), height - (int)translateY(y), null);
    }
    
    /**
     * Draws the graphic with the specified rotation.
     * 
     * This method simply rotates at the image center.
     * @param g Graph
     * @param x
     * @param y
     * @param angle Rotation in degrees.
     */
    public void draw(Graphic g, float x, float y, double angle) {
        BufferedImage orig = g.render();
        
        draw(g, x, y, angle, orig.getWidth() / 2f, orig.getHeight() / 2f);
    }
    
    public void draw(Graphic g, float x, float y, double angle, float pivotx, float pivoty) {
        BufferedImage orig = g.render();
        
        int w = orig.getWidth();
        int h = orig.getHeight();
        
        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle), pivotx, pivoty);
        
        //Based off of http://stackoverflow.com/questions/10426883/affinetransform-truncates-image
        
        Point[] points = new Point[] {
            new Point(0, 0),
            new Point(w, 0),
            new Point(w, h),
            new Point(0, h)
        };
        
        at.transform(points, 0, points, 0, 4);
        
        Point min = new Point(points[0]);
        Point max = new Point(points[0]);
        
        for(Point p : points) {
            int px = p.x, py = p.y;
            
            if(px < min.x) { min.x = px; }
            else if(px > max.x) { max.x = px; }
            
            if(py < min.y) { min.y = py; }
            else if(py > max.y) { max.y = py; }
        }
        
        BufferedImage filter = new BufferedImage(max.x - min.x, max.y - min.y, orig.getType());
        
        at.preConcatenate(AffineTransform.getTranslateInstance(-min.x, -min.y));
        
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        op.filter(orig, filter);
        
        graphics.drawImage(filter, (int)translateX(x + min.x), (int)translateY(y + min.y), null);
    }
}