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
*/

package dev.vitto.cna;

import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CNACanvas extends JPanel {

    JMenuItem xyDisplay;
    BufferedImage bi = (BufferedImage) IconLoader.GRID_TEXTURE.getImage();
    final TexturePaint texture = new TexturePaint(
            bi, new Rectangle(8, 8));

    public CNACanvas(JMenuItem xyDisplay) {
        this.xyDisplay = xyDisplay;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xyDisplay.setText("X: " + e.getX() + " | Y: " + e.getY());
            }
        });
    }

    public void changeCursor(boolean insert) {
        if (insert)
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        else
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // sfondo STATICO area di lavoro
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(texture);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // da qua disegno DINAMICO
        // ora codice temporaneo

        Font f = new Font("Times Roman", Font.BOLD, 20);
        g.setFont(f);
        g.setColor(Color.black);
        g.fillOval(120, 220, 60, 60);        // una ruota piena
        g.fillOval(320, 220, 60, 60);        // altra ruota piena
        g.setColor(Color.blue);                                 // Il carrozzeria blu
        g.drawRect(50, 150, 400, 70);
        g.drawLine(170, 150, 200, 100);
        g.drawLine(330, 150, 300, 100);
        g.drawLine(300, 100, 200, 100);
        g.setColor(Color.yellow);                               //luci gialle
        g.fillRect(50, 170, 20, 30);
        g.setColor(Color.red);                                  //Il luci rosse
        g.fillRect(430, 150, 20, 20);
        g.setColor(Color.PINK);                                 //testo cyan
        g.drawString("Automobile", 200, 350);
    }

}
