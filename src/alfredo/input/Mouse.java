package alfredo.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class Mouse {
    public class Handler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            switch(e.getButton()) {
                case MouseEvent.BUTTON1:
                    lmbDown = true;
                    break;
                case MouseEvent.BUTTON2:
                    mmbDown = true;
                    break;
                case MouseEvent.BUTTON3:
                    rmbDown = true;
                    break;
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            switch(e.getButton()) {
                case MouseEvent.BUTTON1:
                    lmbDown = false;
                    break;
                case MouseEvent.BUTTON2:
                    mmbDown = false;
                    break;
                case MouseEvent.BUTTON3:
                    rmbDown = false;
                    break;
            }
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            scrollTilt += e.getPreciseWheelRotation();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            mousex = (int)((e.getX() - screenX) / screenScale);
            mousey = (int)((e.getY() - screenY) / screenScale);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousex = (int)((e.getX() - screenX) / screenScale);
            mousey = (int)((e.getY() - screenY) / screenScale);
        }
    }
    
    private boolean lmbDown;
    private boolean mmbDown;
    private boolean rmbDown;
    
    private double screenScale; //Used to properly project mouse coordinates to the screen
    private int screenX;
    private int screenY;
    
    private int mousex, mousey;
    
    private double scrollTilt;
    
    private final Handler handler;
    
    private static Mouse mouse;
    
    private Mouse() {
        lmbDown = false;
        mmbDown = false;
        rmbDown = false;
        
        mousex = mousey = 0;
        
        scrollTilt = 0;
        handler = new Handler();
    }
    
    public static Handler init() {
        if(mouse == null) {
            mouse = new Mouse();
            return mouse.handler;
        }
        return null;
    }
    
    /**
     * Gets the Mouse with the specified Handler.
     * 
     * Meant to be used by the Game so that it can properly
     * update how the mouse updates its position.
     * @param handler The Handler to validate.
     * @return The static mouse object, if the Handler is valid; otherwise null.
     */
    public static Mouse getMouse(Handler handler) {
        if(handler == mouse.handler) {
            return mouse;
        }
        return null;
    }
    
    public void updateScreen(int screenX, int screenY, double scale) {
        this.screenX = screenX;
        this.screenY = screenY;
        this.screenScale = scale;
    }
    
    public static boolean isLMBDown() { return mouse.lmbDown; }
    public static boolean isMMBDown() { return mouse.mmbDown; }
    public static boolean isRMBDown() { return mouse.rmbDown; }
    
    public static int getMouseX() { return mouse.mousex; }
    public static int getMouseY() { return mouse.mousey; }
    
    public static double getScrollTilt() { return mouse.scrollTilt; }
    public static void resetScrollTilt() { mouse.scrollTilt = 0; }
}