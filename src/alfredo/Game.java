package alfredo;

import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Game {
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 400;
    
    private static Platform gamePlatform = null;
    
    public static interface Platform {
        void create(int width, int height);
        void title(String title);
        void size(float width, float height);
        void setIcon(String path);
        void run();
        void saveScreenshot(String path);
    }
    
    private static long tick = 0;
    private static Timer timer = null;
    
    public static <T extends Platform> void platform(Class<T> platformClass) {
        if(gamePlatform != null) {
            throw new IllegalStateException("Game platform already established!");
        }
        
        try {
            gamePlatform = platformClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) { }
        
        //canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        gamePlatform.create(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        timer = new Timer(33, (ActionEvent e) -> {
            Scene.getCurrent().loop();
            
            ++tick;
        });
    }
    
    private static void testPlatform() {
        if(gamePlatform == null) {
            throw new IllegalStateException("Cannot perform action until Game.platform() has been called!");
        }
    }
    
    public static void title(String title) {
        testPlatform();
        gamePlatform.title(title);
    }
    
    public static void size(float width, float height) {
        testPlatform();
        gamePlatform.size(width, height);
        //canvas.resize((int)width, (int)height);
    }
    
    public static void play() {        
        /* TODO: Reimplement this
        BufferedImage icon;
        try {
            icon = ImageGraphic.read("/resrc/img/icon.png");
            frame.setIconImage(icon);
        } catch (Exception ex) {
            System.out.println("No icon image found (resrc/img/icon.png)");
        }*/
        
        testPlatform();
        gamePlatform.run();
        timer.start();
    }
    
    public static void setDelay(int ms) {
        timer.setDelay(ms);
    }
    
    public static int getDelay() {
        return timer.getDelay();
    }
    
    public static void saveScreenshot(String path) {
        testPlatform();
        gamePlatform.saveScreenshot(path);
    }
    
    public static void setIcon(String path) {
        testPlatform();
        gamePlatform.setIcon(path);
    }
    
    public static long getTick() {
        return tick;
    }
    
    static void updateCamera() {
        testPlatform();
        //canvas.camera = Camera.getMain();
        //Camera.getMain().clip(canvas);
    }
}
