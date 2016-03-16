package alfredo.geom;

public class Line {
    public Point start;
    public Point end;
    
    public Line() {
        start = new Point();
        end = new Point();
    }
    
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    
    public Line(int sx, int sy, int ex, int ey) {
        start = new Point(sx, sy);
        end = new Point(ex, ey);
    }
    
    public boolean intersects(Line check) {

        
        int xdif1 = end.x - start.x;
        int xdif2 = check.end.x - check.start.x;
        
        if(xdif1 == 0 || xdif2 == 0) {
            System.out.println("0 slope branch");
            if(xdif1 == xdif2) {
                System.out.println("all zeroes");
                return ((start.y >= check.start.y && start.y <= check.end.y) || (start.y <= check.start.y && start.y >= check.end.y));
            }
            if(xdif1 == 0) {
                return ((start.x >= check.start.x && start.x <= check.end.x) || (start.x <= check.start.x && start.x >= check.end.x));
            }
            else {
                return ((check.start.x >= start.x && check.start.x <= end.x) || (check.start.x <= start.x && check.start.x >= end.x));
            }
        }
        else if (xdif1 == xdif2) {
            System.out.println("Parallel branch");
            return ((start.x >= check.start.x && start.x <= check.end.x) || (start.x <= check.start.x && start.x >= check.end.x));
        }
        else {
            System.out.println("Normal branch");
            float m1, m2, checkx;
            
            m1 = (float)(end.y - start.y) / xdif1;
            m2 = (float)(check.end.y - check.start.y) / xdif2;
            checkx = ((check.start.y - (m2 * check.start.x)) - (start.y - (m1 * start.x))) / (m1 - m2);
            return ((checkx >= check.start.x && checkx <= check.end.x) || (checkx <= check.start.x && checkx >= check.end.x));
        }
        
    }
}