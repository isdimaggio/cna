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

public class Shape implements java.io.Serializable {

    private int rootX;
    private int rootY;
    private Color color;

    private String name;
    private int stroke;

    /**
     * Da questa classe vengono estese tutte le figure geometriche, se non estesa
     * rappresenta un punto.
     */
    public Shape(int x, int y, Color color, int stroke) {
        this.rootX = x;
        this.rootY = y;
        this.color = color;
        this.stroke = stroke;
        setName("Punto " + Misc.getNextObjNum());
    }

    public int getRootX() {
        return rootX;
    }

    public void setRootX(int rootX) {
        this.rootX = rootX;
    }

    public int getRootY() {
        return rootY;
    }

    public void setRootY(int rootY) {
        this.rootY = rootY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}
