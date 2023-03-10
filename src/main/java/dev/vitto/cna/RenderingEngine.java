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

import dev.vitto.cna.objects.Shape;
import dev.vitto.cna.objects.*;

import java.awt.*;
import java.util.List;

public class RenderingEngine {

    public static void render(List<Shape> shapeList, Graphics2D g2, Point regA, Point regB, boolean enableObjectBoundaryPoints) {
        // ************* MOTORE DI RENDERING *************
        for (dev.vitto.cna.objects.Shape shape : shapeList
        ) {
            g2.setColor(shape.getColor()); // il metodo per l'impostazione del colore vale per tutti
            if (shape instanceof dev.vitto.cna.objects.Rectangle rect) {
                // CALCOLO DELLE COORDINATE
                // utilizza la funzione Math.min() per determinare l'angolo superiore sinistro del rettangolo
                // e la funzione Math.abs() per garantire che la larghezza e l'altezza siano sempre positive,
                // indipendentemente dall'ordine delle coordinate in input
                int x = Math.min(rect.getRootX(), rect.getEndX());
                int y = Math.min(rect.getRootY(), rect.getEndY());
                int w = Math.abs(rect.getEndX() - rect.getRootX());
                int h = Math.abs(rect.getEndY() - rect.getRootY());
                // disegno del rettangolo
                if (rect.isFill()) {
                    //il rettangolo è pieno N.B.: lo stroke è ignorato
                    g2.fillRect(x, y, w, h);
                } else {
                    // rettangolo vuoto, considera lo stroke
                    g2.setStroke(new BasicStroke(rect.getStroke()));
                    g2.drawRect(x, y, w, h);
                }
                if (enableObjectBoundaryPoints) {
                    // disegna boundary points
                    boundaryPoint(g2, rect.getRootX(), rect.getRootY());
                    boundaryPoint(g2, rect.getEndX(), rect.getEndY());
                }
            } else if (shape instanceof Line line) {
                g2.setStroke(new BasicStroke(line.getStroke()));
                g2.drawLine(
                        line.getRootX(),
                        line.getRootY(),
                        line.getEndX(),
                        line.getEndY()
                );
                if (enableObjectBoundaryPoints) {
                    boundaryPoint(g2, line.getRootX(), line.getRootY());
                    boundaryPoint(g2, line.getEndX(), line.getEndY());
                }
            } else if (shape instanceof Circle circle) {
                // disegno del cerchio (codice copiato dal rettangolo perchè si tratta semplcemente di una...
                // ...circonferenza inscritta nel rettangolo (in questo caso quadrato)
                int startX = circle.getRootX() - (circle.getEndX() - circle.getRootX());
                int startY = circle.getRootY() - (circle.getEndY() - circle.getRootY());
                // distanza tra due punti con il teorema di pitagora
                double distance = Math.sqrt(
                        Math.pow(
                                circle.getEndX() - startX, 2
                        ) + Math.pow(
                                circle.getEndY() - startY, 2
                        )
                );
                int x = circle.getRootX() - ((int) distance / 2);
                int y = circle.getRootY() - ((int) distance / 2);

                if (circle.isFill()) {
                    //il cerchio è pieno N.B.: lo stroke è ignorato
                    g2.fillOval(x, y, (int) distance, (int) distance);
                } else {
                    g2.setStroke(new BasicStroke(circle.getStroke()));
                    g2.drawOval(x, y, (int) distance, (int) distance);
                }
                if (enableObjectBoundaryPoints) {
                    boundaryPoint(g2, circle.getRootX(), circle.getRootY());
                    boundaryPoint(g2, circle.getEndX(), circle.getEndY());
                }
            } else if (shape instanceof SingleLineText slt) {
                g2.setFont(new Font("Lucida Console", Font.PLAIN, slt.getTextHeight()));
                g2.drawString(slt.getTextContent(), slt.getRootX(), slt.getRootY());
                if (enableObjectBoundaryPoints) {
                    boundaryPoint(g2, slt.getRootX(), slt.getRootY());
                }
            } else if (shape instanceof Ellipse ellipse) {

                /*
                CALCOLO COORDINATE:
                La larghezza viene calcolata come distanza tra le coordinate x dei punti
                iniziale e finale dell'asse maggiore. L'altezza è calcolata come il doppio della
                distanza tra la coordinata y del co-vertice e il punto medio delle coordinate
                y dei punti iniziale e finale dell'asse maggiore.
                Le coordinate x e y del rettangolo sono calcolate come la coordinata x minima dei punti
                iniziale e finale dell'asse maggiore e il punto medio delle coordinate y dei punti iniziale
                e finale dell'asse maggiore meno metà dell'altezza rispettivamente.
                 */
                double width = Math.abs(ellipse.getFirstPointX() - ellipse.getRootX());
                double height = 2 * Math.abs(ellipse.getSecondPointY() - (ellipse.getRootY() + ellipse.getFirstPointY()) / 2);
                double x = Math.min(ellipse.getRootX(), ellipse.getFirstPointX());
                double y = ((float) ellipse.getRootY() + (float) ellipse.getFirstPointY()) / 2 - height / 2;

                if (ellipse.isFill()) {
                    //il cerchio è pieno N.B.: lo stroke è ignorato
                    g2.fillOval((int) x, (int) y, (int) width, (int) height);
                } else {
                    g2.setStroke(new BasicStroke(ellipse.getStroke()));
                    g2.drawOval((int) x, (int) y, (int) width, (int) height);
                }

                if (enableObjectBoundaryPoints) {
                    boundaryPoint(g2, ellipse.getRootX(), ellipse.getRootY());
                    boundaryPoint(g2, ellipse.getFirstPointX(), ellipse.getFirstPointY());
                    boundaryPoint(g2, ellipse.getSecondPointX(), ellipse.getSecondPointY());
                }
            } else {
                // disegna un cerchio sottraendo la metà dello spessore per centrarlo al punto d' inserimento
                g2.fillOval(
                        shape.getRootX() - (shape.getStroke() / 2),
                        shape.getRootY() - (shape.getStroke() / 2),
                        shape.getStroke(),
                        shape.getStroke()
                );
            }
        }
        // renderizza punti temporanei
        if (regA.getX() != -10 && regA.getY() != -10) {
            boundaryPoint(g2, (int) regA.getX(), (int) regA.getY());
        }
        if (regB.getX() != -10 && regB.getY() != -10) {
            boundaryPoint(g2, (int) regB.getX(), (int) regB.getY());
        }
    }

    private static void boundaryPoint(Graphics2D g, int x, int y) {
        // lo stroke per un punto di boundary sarà sempre 4 e il colore sempre nero
        Color previous = g.getColor();
        g.setColor(Color.BLACK);
        g.fillOval(
                x - 2,
                y - 2,
                4,
                4
        );
        // ripristiniamo il colore precedente
        g.setColor(previous);
    }
}
