package alfredo.gfx;

import alfredo.Camera;
import alfredo.Debug;
import alfredo.Entity;
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
    
    public static Sprite[] retrieveAll(String path, String... names) {
        Sprite[] sprites = new Sprite[names.length];
        for(int i = 0; i < sprites.length; ++i) {
            sprites[i] = retrieve(path + names[i]).simple();
        }
        return sprites;
    }
    
    public static EditableSprite edit(String path) {
        return platformSpriter.getEditable(path);
    }
    
    public static EditableSprite edit(Vector size) {
        return platformSpriter.getEditable(size);
    }
    
    public static Texture texture(String path) {
        return platformSpriter.getTexture(path);
    }
    
    public static Texture[] textureAll(String path, String... names) {
        Texture[] textures = new Texture[names.length];
        for(int i = 0; i < textures.length; ++i) {
            textures[i] = texture(path + names[i]);
        }
        return textures;
    }
    
    protected static final void error(String path, Exception e) {
        Debug.error("Failed to load Sprite " + path + ": " + e.getLocalizedMessage());
    }
    
    protected abstract SpriteSource getSource(String path);
    protected abstract EditableSprite getEditable(String path);
    protected abstract EditableSprite getEditable(Vector size);
    protected abstract Texture getTexture(String path);
    
    protected abstract void drawImpl(Sprite s, float x, float y, float scale, double angle, float opacity);
    
    public final void draw(Sprite s, float x, float y, float scale, double angle, float opacity) {
        drawImpl(s, Camera.getMain().screenX(x), Camera.getMain().screenY(y), scale * Camera.getMain().getScale(), angle, opacity);
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
