package alfredo.gfx;

import alfredo.Component;
import alfredo.Entity;
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
    
    private int width, height;
    
    Sprite(int index, Vector pivot, int width, int height) {
        this.index = index;
        this.pivot = pivot;
        this.width = width;
        this.height = height;
    }
    
    public int getIndex() { return index; }
    public Vector getPivot() { return pivot; }
    
    @Override
    public void draw(Spriter s) {
        if(active) {
            paint(s);
        }
    }
    
    public Sprite copy() {
        return new Sprite(index, pivot.copy(), width, height);
    }
    
    public boolean contains(Vector point) {
        return point.x >= parent.transform.x && point.x <= parent.transform.x + width &&
               point.y >= parent.transform.y && point.y <= parent.transform.y + height;
    }
    
    public boolean contains(Vector point, Vector center) {
        return point.x >= center.x - width / 2 && point.x <= center.x + width / 2 &&
               point.y >= center.y  - height / 2&& point.y <= center.y + height / 2;
    }
    
    public void paint(Spriter s) {
        new Context(this).paint(s);
    }
    
    public Context at(float x, float y) {
        return new Context(this).at(x, y);
    }
    
    public Context at(Vector pos) {
        return new Context(this).at(pos);
    }
    
    public Context at(Entity e) {
        return new Context(this).at(e);
    }
    
    public Context alpha(float alpha) {
        return new Context(this).alpha(alpha);
    }
    
    public Context scale(float scale) {
        return new Context(this).scale(scale);
    }
    
    public Context dir(double dir) {
        return new Context(this).dir(dir);
    }
}
