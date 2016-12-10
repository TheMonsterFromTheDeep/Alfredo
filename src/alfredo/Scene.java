package alfredo;

import alfredo.gfx.Renderer;
import alfredo.phx.Physics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Scene {
    
    private static ArrayList<Scene> scenes;
    private static Scene current = null;
    
    public static <T extends Scene> void open(Class<T> sceneClass) {
        if(scenes == null) {
            scenes = new ArrayList();
        }
        else {
            for(Scene s : scenes) {
                if(s.getClass() == sceneClass) {
                    current = s;
                    return;
                }
            }
        }
        try {
            current = sceneClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Error setting scene: " + ex);
        }
        scenes.add(current);
    }
    
    public static Scene getCurrent() { return current; }
    
    public Scene() { }
    
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
