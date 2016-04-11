package alfredo.util;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public final class Resources {
    public static BufferedImage getImage(String path) {
        try {
            return ImageIO.read(Resources.class.getResource(path));
        }
        catch (Exception e) {
            System.err.println("Failed to load image resource (" + path + "): " + e.getLocalizedMessage());
            return null;
        }
    }
}