package alfredo.inpt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Mouse {
    
    public static final MouseListener mouseListener = new MouseListener() {
        @Override
        public void mousePressed(MouseEvent e) {
            int button = e.getButton();
            (button == MouseEvent.BUTTON1 ? LMB :      //I think this is probably cleaner than a switch
             button == MouseEvent.BUTTON2 ? MMB : RMB).press();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int button = e.getButton();
            (button == MouseEvent.BUTTON1 ? LMB :
             button == MouseEvent.BUTTON2 ? MMB : RMB).release();
        }
        
        @Override public void mouseClicked(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    };
    
    public static final MouseMotionListener motionListener = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) {
            pointer.set(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            pointer.set(e.getX(), e.getY());
        }
    };
    
    public static final Button.Source LMB = new Button.Source();
    public static final Button.Source MMB = new Button.Source();
    public static final Button.Source RMB = new Button.Source();
    public static final Cursor.Source pointer = new Cursor.Source();
}
