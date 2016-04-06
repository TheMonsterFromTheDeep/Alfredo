package alfredo.sprite;

import alfredo.geom.Point;
import alfredo.geom.Polygon;
import alfredo.paint.Graphic;
import alfredo.util.Resources;
import java.awt.image.BufferedImage;

/**
 * A Skeleton makes up the skeleton of a Sprite. It handles everything including
 * position, graphics, and intersections. The Sprite is merely a wrapper for
 * a set of interchangeable Skeletons.
 * @author TheMonsterFromTheDeep
 */
public class Skeleton extends Bounds {
    public Graphic graphic; //The graphical appearance of the Skeleton
    
    private final Point origin; //The center of the graphic; used as the base for the center point
    private Point center; //The center where the bounds and graphic are rotated / drawn with
    //When being drawn, the coordinates for the Skeleton are where the center point of the image is drawn
    
    public static Skeleton loadFromPath(String path) {
        return new Skeleton(Resources.getImage(path));
    }
    
    public static Skeleton loadFromPath(String path, Point center) {
        return new Skeleton(Resources.getImage(path), center);
    }
    
    public Skeleton(Graphic graphic, Point center) {
        this.graphic = graphic;
        
        BufferedImage render = graphic.render();
        
        float halfWidth = render.getWidth() / 2.0f;
        float halfHeight = render.getHeight() / 2.0f;
        
        origin = new Point(halfWidth, halfHeight);
        this.center = center;
        //By default, construct a square bounding box
        
        bounds = new Polygon(new Point[] { new Point(center.x - halfWidth, center.y - halfHeight), new Point(center.x + halfWidth, center.y - halfHeight), new Point(center.x + halfWidth, center.y + halfHeight), new Point(center.x - halfWidth, center.y + halfHeight), new Point(center.x - halfWidth, center.y - halfHeight)}); 
    }
    
    public Skeleton(Graphic graphic) { this(graphic, new Point(0, 0)); }
    
    //Returns the relative position of the anchor point of the Skeleton. This should be subtracted from the position when drawing.
    public float getCenterX() { return origin.x + center.x; }
    public float getCenterY() { return origin.y + center.y; }
}