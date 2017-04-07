package alfredo;

import alfredo.gfx.Spriter;
import alfredo.phx.Physics;
import java.util.ArrayList;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Scene {
    
    private static ArrayList<Scene> scenes;
    private static Scene current = null;
    
    protected int bgcolor;
    
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
    
    public static <T extends Scene> T get(Class<T> sceneClass) {
        if(scenes == null) {
            return null;
        }
        for(Scene s : scenes) {
            if(s.getClass() == sceneClass) {
                return (T) s;
            }
        }
        return null;
    }
    
    public static Scene getCurrent() { return current; }
    
    public Scene() { }
    
    public void render(Spriter s) {
        s.clear();
        s.fill(bgcolor, 0, 0, Camera.getMain().getViewport());
        
        backdrop(s);
        
        Entity[] all = Entity.getAllEntities();
        for(Entity e : all) {
            for(Component co : e.getComponents()) {
                co.draw(s);
            }
        }
        
        draw(s);
    }
    
    public void loop() {
        Physics.tick();
    }
    
    public void tick() { }
    public void backdrop(Spriter s) { }
    public void draw(Spriter s) { }
}
