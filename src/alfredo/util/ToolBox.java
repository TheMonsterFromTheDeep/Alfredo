package alfredo.util;

import alfredo.Game;
import alfredo.input.KeyDownEvent;
import alfredo.input.Keys;
import com.sun.glass.events.KeyEvent;

/**
 * The ToolBox provides simple static functions, for example: making F11 into a fullscreen control.
 * @author TheMonsterFromTheDeep
 */
public class ToolBox {
    public static void addDefaultFullscreen() {
        Keys.watch((KeyDownEvent) (int key) -> {
            if(key == KeyEvent.VK_F11) { Game.game.setFullscreen(!Game.game.isFullscreen()); }
        });
    }
}