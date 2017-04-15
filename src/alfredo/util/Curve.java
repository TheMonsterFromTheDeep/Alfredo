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
 *
 * @author TheMonsterOfTheDeep
 */
public class Curve {
    public Vector[] controlPoints;
    
    public Curve(Vector... controlPoints) {
        this.controlPoints = controlPoints;
    }
    
    public static int binomialCoefficient(int n, int k) {
        float cof = 1;
        for(int i = 1; i <= k; ++i) {
            cof *= (n + 1 - i) / (float)i;
        }
        return (int)Math.round(cof);
    }
    
    public Vector evaluate(float timestep) {
        Vector point = new Vector();
        for(int i = 0; i < controlPoints.length; ++i) {
            int com = binomialCoefficient(controlPoints.length - 1, i);
            point.x += com * Math.pow((1 - timestep), controlPoints.length - i - 1) * Math.pow(timestep, i) * controlPoints[i].x;
            point.y += com * Math.pow((1 - timestep), controlPoints.length - i - 1) * Math.pow(timestep, i) * controlPoints[i].y;
        }
        return point;
    }
}
