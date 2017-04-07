package alfredo.gfx;

import alfredo.Component;
import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public abstract class Sprite extends Component {
    public abstract int getIndex();
    public abstract Vector getPivot();
    public abstract float getAlpha();
    
    @Override
    public void draw(Spriter s) {
        s.draw(this, parent.position.x, parent.position.y, parent.direction);
    }
}
