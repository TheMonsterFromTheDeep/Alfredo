package alfredo;

import alfredo.gfx.Renderer;
import alfredo.phx.Physics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Scene {
    private static HashMap<String, Scene> sceneMap;
    private static Scene current = null;
    
    public static void add(Scene s) {
        if(s == null) { return; }
        if(sceneMap == null) {
            sceneMap = new HashMap();
        }
        if(sceneMap.containsKey(s.name)) {
            throw new IllegalStateException("Scene named " + s.name + " already exists!");
        }
        sceneMap.put(s.name, s);
    }
    
    private static void open(Scene s) {
        current = s;
    }
    
    public static void open(String name) {
        if(name == null) { throw new NullPointerException("Scene name cannot be null!"); }
        if(sceneMap == null) { return; }
        Scene next = sceneMap.get(name);
        if(next == null) { throw new IllegalArgumentException("No scene named " + name + " exists!"); }
        open(next);
    }
    
    public static void begin(Scene s) {
        if(s == null) { return; }
        if(current != null) {
            throw new IllegalStateException("Trying to begin with scene " + s.name + ", but a scene is already loaded!");
        }
        add(s);
        open(s);
    }
    
    public static Scene getCurrent() { return current; }
    
    private final String name;
    
    protected Scene(String name) {
        this.name = name;
    }
    
    public final String getName() { return name; }
    
    public void render(Canvas c) {
        c.clear();
        
        Entity[] all = Entity.getAllEntities();
        Renderer r;
        for(Entity e : all) {
            r = e.getComponent(Renderer.class);
            if(r == null) { continue; }
            c.draw(r.graphic, e.position.x, e.position.y);
        }
        
        draw(c);
    }
    
    public void loop() {
        Physics.tick();
    }
    
    public void tick() { }
    public void draw(Canvas c) { }
}
