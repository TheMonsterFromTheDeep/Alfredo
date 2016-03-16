package alfredo.geom;

import alfredo.geom.Point;

public class Rectangle {
    public int x;
    public int y;
    public int width;
    public int height;
    
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public Rectangle(Rectangle r) {
        this.x = r.x;
        this.y = r.y;
        this.width = r.width;
        this.height = r.height;
    }
    
    public Rectangle(Point position, Point size) {
        this.x = position.x;
        this.y = position.y;
        this.width = size.x;
        this.height = size.y;
    }
    
    public int left() { return x; }
    public int top() { return y; }
    public int right() { return x + width; }
    public int bottom() { return y + height; }
    
    public void setLeft(int x) { this.width += (this.x - x); this.x = x; }
    public void setTop(int y) { this.height += (this.y - y); this.y = y; }
    public void setRight(int x) { this.width = (x - this.x); }
    public void setBottom(int y) { this.height = (y - this.y); }
    
    public void shift(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public boolean contains(int x, int y) {
        return (x >= this.x && x < this.x + width && y >= this.y && y < this.y + width);
    }
    
    public boolean intersects(Rectangle r) {
        int thisx = x;
        int thisy = y;
        int thiswidth = width;
        int thisheight = height;
        int rx = r.x;
        int ry = r.y;
        int rwidth = r.width;
        int rheight = r.height;
        return ((thisx + thiswidth > rx && thisx + thiswidth < rx + rwidth) || (rx + rwidth > thisx && rx + rwidth < thisx + thiswidth)) && ((thisy + thisheight > ry && thisy + thisheight < ry + rheight) || (ry + rheight > thisy && ry + rheight < thisy + thisheight));
    }
}