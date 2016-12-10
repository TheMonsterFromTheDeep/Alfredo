package alfredo;

import alfredo.geom.Vector;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Entity {
    private static ArrayList<Entity> entities;
    
    public final Vector position;
    private final ArrayList<Component> components;
    
    public static <T extends Entity> T create(Vector position) {
        T newEntity = (T)new Entity(position);
        newEntity.init();
        
        if(entities == null) {
            entities = new ArrayList();
        }
        entities.add(newEntity);
        
        return newEntity;
    }
    
    public static <T extends Entity> T create(float x, float y) {
        return create(new Vector(x, y));
    }
    
    public static <T extends Entity> T create() {
        return create(new Vector());
    }
    
    protected Entity(Vector position) {
        this.position = position;
        components = new ArrayList();
    }
    
    protected void init() { }
    
    public <T extends Component> T getComponent(Class<T> type) {
        for(Component c : components) {
            if(c.getClass() == type) {
                return (T)c;
            }
        }
        return null;
    }
    
    public <T extends Component> T addComponent(T c) {
        components.add(c);
        c.parent = this;
        return c;
    }
    
    public Entity chain(Component c) {
        components.add(c);
        c.parent = this;
        return this;
    }
    
    public <T extends Component> T removeComponent(Class<T> type) {
        Component c = null;
        for(Iterator<Component> i = components.iterator(); i.hasNext(); c = i.next()) {
            if(c == null) { continue; }
            if(c.getClass() == type) {
                i.remove();
                return (T)c;
            }
        }
        return null;
    }
    
    public final void destroy() {
        entities.remove(this);
    }
    
    public static Entity[] getAllEntities() {
        return entities.toArray(new Entity[0]);
    }
}
