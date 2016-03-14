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
            mousex = e.getX();
            mousey = e.getY();
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mousex = e.getX();
            mousey = e.getY();
        }
    }
    
    private boolean lmbDown;
    private boolean mmbDown;
    private boolean rmbDown;
    
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
        mouse = new Mouse();
        return mouse.handler;
    }
    
    public static boolean isLMBDown() { return mouse.lmbDown; }
    public static boolean isMMBDown() { return mouse.mmbDown; }
    public static boolean isRMBDown() { return mouse.rmbDown; }
    
    public static int getMouseX() { return mouse.mousex; }
    public static int getMouseY() { return mouse.mousey; }
    
    public static double getScrollTilt() { return mouse.scrollTilt; }
    public static void resetScrollTilt() { mouse.scrollTilt = 0; }
}