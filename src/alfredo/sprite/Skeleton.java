package alfredo.sprite;

import alfredo.geom.Point;
import alfredo.geom.Polygon;
import alfredo.paint.Image;
import alfredo.util.Resources;
import java.awt.image.BufferedImage;

/**
 * A Skeleton makes up the skeleton of a Sprite. It handles everything including
 * position, graphics, and intersections. The Sprite is merely a wrapper for
 * a set of interchangeable Skeletons.
 * 
 * The Skeleton is deprecated and will soon be replaced or changed.
 * @author TheMonsterFromTheDeep
 */
public class Skeleton extends Bounds {
    public Image image; //The graphical appearance of the Skeleton
    
    private final Point origin; //The center of the graphic; used as the base for the center point
    private Point center; //The center where the bounds and graphic are rotated / drawn with
    //When being drawn, the coordinates for the Skeleton are where the center point of the image is drawn
    
    public static Skeleton loadFromPath(String path) {
        Image image = new Image();
        Image.load(path, image);
        return new Skeleton(image);
    }
    
    public static Skeleton loadFromPath(String path, Point center) {
        Image image = new Image();
        Image.load(path, image);
        return new Skeleton(image, center);
    }
    
    public Skeleton(Image image, Point center) {
        this.image = image;
        
        float halfWidth = image.image.getWidth() / 2.0f;
        float halfHeight = image.image.getHeight() / 2.0f;
        
        origin = new Point(halfWidth, halfHeight);
        this.center = center;
        //By default, construct a square bounding box
        
        bounds = new Polygon(new Point[] { new Point(center.x - halfWidth, center.y - halfHeight), new Point(center.x + halfWidth, center.y - halfHeight), new Point(center.x + halfWidth, center.y + halfHeight), new Point(center.x - halfWidth, center.y + halfHeight), new Point(center.x - halfWidth, center.y - halfHeight)}); 
    }
    
    public Skeleton(Image image) { this(image, new Point(0, 0)); }
    
    //Returns the relative position of the anchor point of the Skeleton. This should be subtracted from the position when drawing.
    public float getCenterX() { return origin.x + center.x; }
    public float getCenterY() { return origin.y + center.y; }
}