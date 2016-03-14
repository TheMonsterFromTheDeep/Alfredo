package alfredo.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Keys class provides methods for capturing key events and interacting with them.
 * @author TheMonsterFromTheDeep
 */
public class Keys { 
    public class Handler implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
            lastChar = e.getKeyChar();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() < states.length) {
                states[e.getKeyCode()] = true;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() < states.length) {
                states[e.getKeyCode()] = false;
            }
        }
    }
    
    private boolean[] states;
    private final Handler handler;
    
    private char lastChar;
    
    private Keys() {
        states = new boolean[256];
        handler = new Handler();
    }
    
    private static Keys keys;
    
    public static Handler init() {
        if(keys == null) {
            keys = new Keys();
            return keys.handler;
        }
        return null;
    }
    
    public static boolean pressed(int keyCode) {
        return keys.states[keyCode];
    }
    
    public static boolean pressed(char keyChar) {
        return keys.states[KeyEvent.getExtendedKeyCodeForChar(keyChar)];
    }
    
    public static char getLastChar() {
        return keys.lastChar;
    }
}