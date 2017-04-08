package alfredo.gfx;

import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class SpriteSource {
    private int index;
    
    public SpriteSource(int index) {
        this.index = index;
    }
    
    public Sprite simple() {
        return new Sprite() {
            @Override
            public int getIndex() {
                return index;
            }

            @Override
            public Vector getPivot() {
                return new Vector();
            }

            @Override
            public float getAlpha() {
                return 1;
            }
        };
    }
}
