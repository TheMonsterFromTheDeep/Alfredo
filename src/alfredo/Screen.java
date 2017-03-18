package alfredo;

import java.awt.Graphics;

public interface Screen {
    void resize(int width, int height);
    void render(Graphics target);
    
    int getWidth();
    int getHeight();
    
    int projectX(int x);
    int projectY(int y);
}