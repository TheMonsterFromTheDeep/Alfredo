package alfredo.gfx;

import alfredo.geom.Vector;
import java.awt.image.BufferedImage;

/**
 * A Graphic represents anything that can be rendered on a Canvas.
 * @author TheMonsterOfTheDeep
 */
public interface Graphic {
    /**
     * Returns a BufferedImage that represents the current state of this
     * Graphic. A return value of "null" means that the image is blank.
     * @return Rendered data the Canvas can draw.
     */
    public BufferedImage getRender();
    
    public Vector getPivot();
    
    public void setPivot(Vector v);
}
