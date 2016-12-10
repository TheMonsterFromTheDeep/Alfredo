package alfredo.gfx;

import alfredo.Component;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Renderer extends Component {
    public Graphic graphic;
    
    public Renderer(Graphic graphic) {
        this.graphic = graphic;
    }
    
    public Renderer() { this(new NullGraphic()); }
}
