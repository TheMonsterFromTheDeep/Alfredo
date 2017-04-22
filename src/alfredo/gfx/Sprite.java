package alfredo.gfx;

import alfredo.Component;
import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Sprite extends Component {  
    private final int index;
    public Vector pivot;
    
    public float alpha = 1;
    
    public boolean active = true;
    
    Sprite(int index, Vector pivot) {
        this.index = index;
        this.pivot = pivot;
    }
    
    public int getIndex() { return index; }
    public Vector getPivot() { return pivot; }
    
    @Override
    public void draw(Spriter s) {
        if(active) {
            s.draw(this, parent.position.x, parent.position.y, parent.direction);
        }
    }
}
