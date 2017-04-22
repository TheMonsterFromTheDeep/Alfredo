package alfredo.gfx;

import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class SpriteSource {
    private final int index;
    
    public SpriteSource(int index) {
        this.index = index;
    }
    
    public Sprite simple() {
        return new Sprite(index, new Vector());
    }
}
