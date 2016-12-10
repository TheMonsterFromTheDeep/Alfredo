package alfredo.gfx;

import alfredo.geom.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A Graphic that represents a single image.
 * @author TheMonsterOfTheDeep
 */
public class ImageGraphic extends Graphic {

    private final BufferedImage image;
    public Vector pivot;
    
    public static ImageGraphic createRectangle(int color, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(new Color(color));
        g.fillRect(0, 0, width, height);
        return new ImageGraphic(image, new Vector());
    }
    
    public ImageGraphic(BufferedImage image, Vector pivot) {
        this.image = image;
        this.pivot = pivot;
    }
    
    @Override
    public BufferedImage getRender() {
        return image;
    }

    @Override
    public Vector getPivot() {
        return pivot;
    }

    @Override
    public void setPivot(Vector v) {
        pivot = v;
    }
}
