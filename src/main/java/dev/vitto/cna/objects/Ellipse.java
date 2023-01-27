/*
Copyright 2023 Vittorio Lo Mele
Copyright 2023 I.T.E.T. Luigi Di Maggio

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

@author "Vittorio Lo Mele"
@author "Gianluca Santoro"
*/

package dev.vitto.cna.objects;

import dev.vitto.cna.utils.Misc;

import java.awt.*;

public class Ellipse extends Shape {

    private int firstPointX;
    private int firstPointY;
    private int secondPointX;
    private int secondPointY;
    private boolean fill;

    public Ellipse(int startX,
                   int startY,
                   int firstPointX,
                   int firstPointY,
                   int secondPointX,
                   int secondPointY,
                   Color color,
                   int stroke,
                   boolean fill
    ) {
        super(startX, startY, color, stroke);
        this.firstPointX = firstPointX;
        this.firstPointY = firstPointY;
        this.secondPointX = secondPointX;
        this.secondPointY = secondPointY;
        this.fill = fill;
        setName("Ellisse " + Misc.getNextObjNum());
    }

    public int getFirstPointX() {
        return firstPointX;
    }

    public void setFirstPointX(int firstPointX) {
        this.firstPointX = firstPointX;
    }

    public int getFirstPointY() {
        return firstPointY;
    }

    public void setFirstPointY(int firstPointY) {
        this.firstPointY = firstPointY;
    }

    public int getSecondPointX() {
        return secondPointX;
    }

    public void setSecondPointX(int secondPointX) {
        this.secondPointX = secondPointX;
    }

    public int getSecondPointY() {
        return secondPointY;
    }

    public void setSecondPointY(int secondPointY) {
        this.secondPointY = secondPointY;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

}
