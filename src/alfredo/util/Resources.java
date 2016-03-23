package alfredo.util;

import alfredo.paint.Graphic;
import alfredo.paint.ImageGraphic;
import alfredo.paint.NullGraphic;
import javax.imageio.ImageIO;

public final class Resources {
    public static Graphic getImage(String path) {
        try {
            return new ImageGraphic(ImageIO.read(Resources.class.getResource(path)));
        }
        catch (Exception e) {
            System.err.println("Failed to load image resource (" + path + "): " + e.getLocalizedMessage());
            return new NullGraphic();
        }
    }
}