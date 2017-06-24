/*
 * The MIT License
 *
 * Copyright 2017 TheMonsterOfTheDeep.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package alfredo.geom;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Transform extends Vector {
    public double direction = 0;
    
    public Transform(float x, float y, double direction) {
        super(x, y);
        this.direction = direction;
    }
    
    public Transform(Vector pos, double direction) {
        this(pos.x, pos.y, direction);
    }
    
    public Transform(Vector pos) {
        this(pos, 0);
    }
    
    public Transform(double direction) {
        this(0, 0, direction);
    }
    
    public Transform(Transform dupli) {
        this(dupli, dupli.direction);
    }
    
    public Transform() {
        this(0);
    }
    
    public void set(Transform t) {
        super.set(t);
        direction = t.direction;
    }
    
    public void set(float x, float y, double direction) {
        super.set(x, y);
        this.direction = direction;
    }
    
    public void direct(double dir) {
        this.direction = dir;
    }
    
    @Override
    public void rotate(float px, float py, double theta) {
        super.rotate(px, py, theta);
        direction += theta;
    }
    
    public void rotate(double theta) {
        direction += theta;
    }
    
    public void move(float distance) {
        move(distance, direction);
    }
    
    public void moveNormal(float distance) {
        move(distance, direction + 90);
    }
    
    public void add(Transform t) {
        super.add(t);
        direction += t.direction;
    }
    
    public void subtract(Transform t) {
        super.subtract(t);
        direction -= t.direction;
    }
    
    @Override
    public Transform plus(Vector v) {
        return new Transform(x + v.x, y + v.y, direction);
    }
    
    public Transform plus(Transform t) {
        return new Transform(x + t.x, y + t.y, direction + t.direction);
    }
    
    @Override
    public Transform minus(Vector v) {
        return new Transform(x - v.x, y - v.y, direction);
    }
    
    public Transform minus(Transform t) {
        return new Transform(x - t.x, y - t.y, direction - t.direction);
    }
    
    public Vector directed() { //TODO: get better name for this
        return Vector.fromDirection(1, (float) direction);
    }
}
