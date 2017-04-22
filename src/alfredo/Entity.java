package alfredo;

import alfredo.geom.Vector;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Entity {
    //private static final ArrayList<Entity> entities = new ArrayList();
    private static Entity head = null;
    private static Entity tail = null;
    
    public final Vector position;
    public double direction;
    private final ArrayList<Component> components;
    private Entity previous = null;
    private Entity next = null;
    
    public int tag = 0;
    
    private static int count = 0;
    
    private static void add(Entity e) {
        if(head == null) {
            head = tail = e;
        }
        else {
            tail.next = e;
            e.previous = tail;
            tail = e;
        }
        ++count;
    }
    
    private static void insert(int index, Entity e) { 
        if(head == null) {
            if(index == 0) {
                add(e);
            }
            else {
                throw new IllegalArgumentException("Inserting Entity at too great of index!");
            }
            return;
        }
        
        Entity target = head;
        while(index > 0) {
            if(target.next == null) {
                throw new IllegalArgumentException("Inserting Entity at too great of index!");
            }
            target = target.next;
            --index;
        }
        if(target.previous != null) {
            target.previous.next = e;
            e.previous = target.previous;
        }
        e.next = target;
        target.previous = e;
        ++count; //Only increment count if successful
    }
    
    public static <T extends Entity> T create(Vector position, double direction) {
        T newEntity = (T)new Entity(position, direction);
        newEntity.init();
        
        add(newEntity);
        
        return newEntity;
    }
    
    public static <T extends Entity> T create(Vector position) {
        return create(position, 0);
    }
    
    public static <T extends Entity> T create(float x, float y) {
        return create(new Vector(x, y));
    }
    
    public static <T extends Entity> T insert(float x, float y) {
        T newEntity = (T)new Entity(new Vector(x, y), 0);
        newEntity.init();
        
        insert(0, newEntity);
        
        return newEntity;
    }
    
    public static <T extends Entity> T create(Entity old) {
        return create(old.position, old.direction);
    }
    
    public static <T extends Entity> T create() {
        return create(new Vector());
    }
    
    public static void clear() {
        tail = head = null;
        count = 0;
    }
    
    protected Entity(Vector position, double direction) {
        this.position = new Vector(position);
        this.direction = direction;
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
        
        c.ready();
        
        return c;
    }
    
    public Entity chain(Component c) {
        components.add(c);
        c.parent = this;
        
        c.ready();
        
        return this;
    }
    
    public Entity tag(int tag) {
        this.tag = tag;
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
    
    public final void move(float distance) {
        position.add((float)(distance * Math.cos(Math.toRadians(direction))), (float)(distance * Math.sin(Math.toRadians(direction))));
    }
    
    public final void moveNormal(float distance) {
        position.add((float)(distance * Math.sin(Math.toRadians(direction))), (float)(distance * Math.cos(Math.toRadians(direction))));
    }
    
    public final Component[] getComponents() {
        return components.toArray(new Component[0]);
    }
    
    public final void destroy() {
        for(Component c : components) {
            c.destroy();
        }
        if(previous != null) previous.next = next;
        if(next     != null) next.previous = previous;
        if(this == tail) {
            tail = previous;
        }
        count = 0;
    }
    
    public static Iterable<Entity> all() {
        return () -> new Iterator() {
            Entity current = head;
            
            @Override
            public boolean hasNext() {
                return current != null;
            }
            
            @Override
            public Object next() {
                Entity next = current;
                current = current.next;
                return next;
            }
        };
    }
    
    public static Entity random() {
        if(count == 0) { return null; }
        int index = Rand.i(0, count - 1);
        for(Entity e : all()) {
            if(index == 0) { return e; }
            --index;
        }
        return null;
    }
    
    private static class ClassIterator<T extends Component> implements Iterator {
        Entity current;
        Class<T> type;
        
        private void find() {
            if(current == null) {
                return;
            }
            
            while(current.getComponent(type) == null) {
                if(current.next == null) {
                    current = null;
                    return;
                }
                current = current.next;
            }
        }
        
        public ClassIterator(Class<T> type) {
            this.type = type;
            current = head;
            find();
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Entity next = current;
            current = current.next;
            find();
            return next;
        }
    }
    
    public static <T extends Component> Iterable<Entity> all(Class<T> type) {
        return () -> new ClassIterator(type);
    }
    
    public static <T extends Component> Entity random(Class<T> type) {
        Iterable<Entity> ents = all(type);
        Iterator i = ents.iterator();
        int index = 0;
        while(i.hasNext()) {
            ++index;
            i.next();
        }
        if(index == 0) { return null; }
        index = Rand.i(0, index - 1);
        for(Entity e : ents) {
            if(index == 0) {
                return e;
            }
            --index;
        }
        return null;
    }
    
    private static class TagIterator implements Iterator {
        Entity current;
        int tag;
        
        private void find() {
            if(current == null) {
                return;
            }
            
            while(current.tag != tag) {
                if(current.next == null) {
                    current = null;
                    return;
                }
                current = current.next;
            }
        }
        
        public TagIterator(int tag) {
            this.tag = tag;
            current = head;
            find();
        }
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Entity next = current;
            current = current.next;
            find();
            return next;
        }
    }
    
    public static Iterable<Entity> all(int tag) {
        return () -> new TagIterator(tag);
    }
    
    public static Entity first() {
        return head;
    }
    
    public static <T extends Component> Entity first(Class<T> type) {
        for(Entity e : all()) {
            if(e.getComponent(type) != null) { return e; }
        }
        return null;
    }
    
    public static Entity first(int tag) {
        for(Entity e : all()) {
            if(e.tag == tag) { return e; }
        }
        return null;
    }
}
