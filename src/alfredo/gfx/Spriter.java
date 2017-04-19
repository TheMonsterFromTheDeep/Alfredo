package alfredo.gfx;

import alfredo.Camera;
import alfredo.Debug;
import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public abstract class Spriter {
    private static Spriter platformSpriter;
    
    public static void setSpriter(Spriter s) {
        platformSpriter = s;
    }
    
    public static SpriteSource retrieve(String path) {
        return platformSpriter.getSource(path);
    }
    
    protected static final void error(String path, Exception e) {
        Debug.error("Failed to load Sprite " + path + ": " + e.getLocalizedMessage());
    }
    
    protected abstract SpriteSource getSource(String path);
    
    protected abstract void drawImpl(Sprite s, float x, float y, float scale, double angle, float opacity);
    
    public final void draw(Sprite s, float x, float y, float scale, double angle, float opacity) {
        drawImpl(s, Camera.getMain().screenX(x), Camera.getMain().screenY(y), scale * Camera.getMain().getScale(), angle, opacity);
    }
    
    public final void draw(Sprite s, float x, float y, double angle, float opacity) {
        draw(s, x, y, 1, angle, opacity);
    }
    
    public final void draw(Sprite s, float x, float y, double angle) {
        draw(s, x, y, 1, angle, s.getAlpha());
    }
    
    public final void draw(Sprite s, float x, float y) {
        draw(s, x, y, 0);
    }
    
    public final void draw(Sprite s, float x, float y, float opacity) {
        draw(s, x, y, 0, opacity);
    }
    
    public abstract void clear();
    protected abstract void fillImpl(int color, float x, float y, float width, float height);
    
    public final void fillRaw(int color, float x, float y, float width, float height) {
        fillImpl(color, Camera.getMain().screenX(x), Camera.getMain().screenY(y), width, height);
    }

    public final void fillRaw(int color, Vector pos, float width, float height) {
        fillRaw(color, pos.x, pos.y, width, height);
    }
    
    public final void fillRaw(int color, float x, float y, Vector size) {
        fillRaw(color, x, y, size.x, size.y);
    }
    
    public final void fillRaw(int color, Vector pos, Vector size) {
        fillRaw(color, pos.x, pos.y, size.x, size.y);
    }
    
    public final void fill(int color, float x, float y, float width, float height) {
        fillRaw(color | 0xff000000, x, y, width, height);
    }
    
    public final void fill(int color, Vector pos, float width, float height) {
        fill(color, pos.x, pos.y, width, height);
    }
    
    public final void fill(int color, float x, float y, Vector size) {
        fill(color, x, y, size.x, size.y);
    }
    
    public final void fill(int color, Vector pos, Vector size) {
        fill(color, pos.x, pos.y, size.x, size.y);
    }
    
    public void resize(int width, int height) {
        Camera main = Camera.getMain();
        
        if(main != null) main.resize(width, height);
        onResize(width, height);
        if(main != null) main.clip(this);
    }
    
    protected abstract void onResize(int width, int height);
    public abstract void clip(int x, int y, int width, int height);
}
