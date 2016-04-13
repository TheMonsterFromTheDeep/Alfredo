package alfredo.paint;

/**
 * A basic, unmoving Camera. Screen coordinates translate directly to world coordinates.
 * @author TheMonsterFromTheDeep
 */
public class StaticCamera extends Camera {

    public StaticCamera(int width, int height) {
        super(width, height);
    }
    
    @Override
    public float xToScreen(float x) {
        return x;
    }

    @Override
    public float yToScreen(float y) {
        return y;
    }

    @Override
    public float xToWorld(float x) {
        return x;
    }

    @Override
    public float yToWorld(float y) {
        return y;
    }
    
}