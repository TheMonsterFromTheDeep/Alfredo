package alfredo.util;

import alfredo.paint.Graphic;
import alfredo.paint.ImageGraphic;
import alfredo.paint.NullGraphic;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Resources {
    public static final Graphic getImage(String path) {
        try {
            return new ImageGraphic(ImageIO.read(Resources.class.getResource(path)));
        }
        catch (IOException e) {
            System.err.println("Failed to load image resource (" + path + "): " + e.getLocalizedMessage());
            return new NullGraphic();
        }
    }
}