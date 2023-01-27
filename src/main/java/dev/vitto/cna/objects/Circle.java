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

public class Circle extends Shape {

    // endX e endY si riferiscono alla fine del segmento che forma il raggio
    private int endX;
    private int endY;
    private boolean fill;

    public Circle(int startX, int startY, int endX, int endY, Color color, int stroke, boolean fill) {
        super(startX, startY, color, stroke);
        this.endX = endX;
        this.endY = endY;
        this.fill = fill;
        setName("Cerchio " + Misc.getNextObjNum());
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

}
