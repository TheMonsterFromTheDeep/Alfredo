package alfredo.paint;

import java.awt.Graphics;
import java.awt.Image;

public class ImageGraphic implements Graphic {
    Image image;
    
    public ImageGraphic(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Graphics graphics, int x, int y) {
        graphics.drawImage(image, x, y, null);
    }
}