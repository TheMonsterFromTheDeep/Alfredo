package alfredo.phx;

import alfredo.Entity;
import alfredo.Scene;
import alfredo.geom.Vector;
import java.util.ArrayList;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Physics {
    public static Vector gravity = new Vector(0, 9.81f);
    
    public static void tick() {
        Entity[] entities = Entity.getAllEntities();
        
        ArrayList<Body> bodies = new ArrayList();
        
        for(Entity e : entities) {
            Body b = e.getComponent(Body.class);
            
            if(b != null) { bodies.add(b); }
        }
        
        Scene.getCurrent().tick();
        
        for(Body b : bodies) {
            b.accelerate(gravity);
        }
        
        for(Body b : bodies) {
            b.tick();
        }
    }
}
