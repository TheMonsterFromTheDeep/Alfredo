package alfredo.paint;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ImageGraphic implements Graphic {
    BufferedImage image;
    
    public ImageGraphic(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage render() {
        return image;
    }
}