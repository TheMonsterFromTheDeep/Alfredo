package alfredo.gfx;

import alfredo.geom.Vector;
import java.awt.image.BufferedImage;

/**
 * A Graphic that represents a single image.
 * @author TheMonsterOfTheDeep
 */
public class ImageGraphic implements Graphic {

    private final BufferedImage image;
    public Vector pivot;
    
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
