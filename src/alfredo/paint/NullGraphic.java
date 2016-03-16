package alfredo.paint;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 * Represents a null or improperly created Graphic. Does not draw anything.
 * @author 104063995
 */
public class NullGraphic implements Graphic {
    private static final BufferedImage NULL_IMG = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    
    @Override
    public BufferedImage render() { return NULL_IMG; }
}