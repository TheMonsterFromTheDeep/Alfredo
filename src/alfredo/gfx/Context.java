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
package alfredo.gfx;

import alfredo.Entity;
import alfredo.geom.Vector;

/**
 *
 * @author TheMonsterOfTheDeep
 */
public class Context {
    private Sprite sprite;
    
    public final Vector position;
    public float alpha;
    public float scale;
    public double direction;
    
    public Context(Sprite s) {
        this.sprite = s;
        
        Entity parent = s.getParent();
        position = parent == null ? new Vector() : new Vector(parent.position);
        direction = parent == null ? 0 : parent.direction;
        
        alpha = s.alpha;
        scale = 1;
    }
    
    public Context at(float x, float y) {
        position.set(x, y);
        return this;
    }
    
    public Context at(Vector pos) {
        position.set(pos);
        return this;
    }
    
    public Context at(Entity e) {
        position.set(e.position);
        direction = e.direction;
        return this;
    }
    
    public Context alpha(float alpha) {
        this.alpha = alpha;
        return this;
    }
    
    public Context scale(float scale) {
        this.scale = scale;
        return this;
    }
    
    public Context dir(double direction) {
        this.direction = direction;
        return this;
    }
    
    public void paint(Spriter s) {
        s.draw(sprite, position.x, position.y, scale, direction, alpha);
    }
}
