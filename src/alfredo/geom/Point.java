package alfredo.geom;

public class Point {
    public float x, y;
    
    public Point() { x = 0; y = 0; }
    
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Point(Point point) {
        this.x = point.x;
        this.y = point.y;
    }
}