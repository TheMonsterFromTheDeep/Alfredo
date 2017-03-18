package alfredo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public final class ScalarScreen implements Screen {

    public final int width, height;
    
    public ScalarScreen(int width, int height) {
        this.width = width;
        this.height = height;
        
        resize(width, height);
    }
    
    private int bufX, bufY, bufWidth, bufHeight;
    private double scale;
    
    BufferedImage buffer;
    
    @Override
    public void resize(int width, int height) {
        if((float)width / this.width > (float)height / this.height) {
            bufHeight = height;
            bufWidth = (int)(bufHeight * ((float)this.width / this.height));
            bufX = (width - bufWidth) / 2;
            bufY = 0;
        }
        else {
            bufWidth = width;
            bufHeight = (int)(bufWidth * ((float)this.height / this.width));
            bufY = (height - bufHeight) / 2;
            bufX = 0;
        }
        scale = (double)bufWidth / width;
    }

    @Override
    public void render(Graphics target) {
        target.drawImage(buffer, bufX, bufY, null);
    }

    @Override public int getWidth() { return width; }
    @Override public int getHeight() { return height; }

    @Override
    public int projectX(int x) {
        return (int)((x - bufX) * scale);
    }

    @Override
    public int projectY(int y) {
        return (int)((y - bufY) * scale);
    }
}