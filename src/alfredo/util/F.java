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
package alfredo.util;

import alfredo.geom.Vector;

/**
 * Provides wrapper methods around Math functions that cast their results to floats.
 * @author TheMonsterOfTheDeep
 */
public class F {
    public static float threshold = 1e-6f;
    
    public static boolean equals(float a, float b) {
        return Math.abs(b - a) < threshold;
    }
    
    public static float max(float... args) {
        float max = args[0];
        for(float x : args) {
            if(x > max) { max = x; }
        }
        return max;
    }
    
    public static float min(float... args) {
        float min = args[0];
        for(float x : args) {
            if(x < min) { min = x; }
        }
        return min;
    }
    
    public static float rsin(float x) {
        return (float)Math.sin(x);
    }
    
    public static float rcos(float x) {
        return (float)Math.cos(x);
    }
    
    public static float rtan(float x) {
        return (float)Math.tan(x);
    }
    
    public static float rad(float deg) {
        return (float)Math.toRadians(deg);
    }
    
    public static float sin(float x) {
        return rsin(rad(x));
    }
    
    public static float cos(float x) {
        return rcos(rad(x));
    }
    
    public static float tan(float x) {
        return rtan(rad(x));
    }
    
    public static float sqrt(float x) {
        return (float)Math.sqrt(x);
    }
    
    public static float euler(float x, float y) {
        return x * x + y * y;
    }
    
    public static float euler(Vector v) {
        return euler(v.x, v.y);
    }
    
    public static float sq(float x) {
        return x * x;
    }

    public static int floor(float f) {
        return (int) Math.floor(f);
    }
    
    public static int ceil(float f) {
        return (int) Math.ceil(f);
    }
    
    public static int round(float f) {
        return (int) Math.round(f);
    }
}
