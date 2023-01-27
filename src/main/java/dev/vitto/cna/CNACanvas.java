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

import dev.vitto.cna.objects.Rectangle;
import dev.vitto.cna.objects.Shape;
import dev.vitto.cna.objects.*;
import dev.vitto.cna.utils.IconLoader;
import dev.vitto.cna.utils.Misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class CNACanvas extends JPanel {

    JMenuItem xyDisplay;
    Project project;
    BufferedImage gridTextureImage = (BufferedImage) IconLoader.GRID_TEXTURE.getImage();
    final TexturePaint texture = new TexturePaint(
            gridTextureImage, new java.awt.Rectangle(10, 10));
    // stadi inserimento
    int stageRegister = 0;
    Point pointRegisterA = new Point(-10, -10);
    //Point pointRegisterC = new Point(-10,-10);

    boolean gridVisible = true;
    Point pointRegisterB = new Point(-10, -10);

    public CNACanvas(JMenuItem xyDisplay, Project project) {
        this.xyDisplay = xyDisplay;
        this.project = project;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switch (project.getActiveInstrument()) {
                    case 0 -> // strumento manina, aggiorna solo lo xyDisplay con le nuove coordinate
                            xyDisplay.setText(
                                    "X: " + Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()) +
                                            " | Y: " + Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()));
                    case 1 -> {
                        // strumento punto
                        project.addShapeToShapesList(new Shape(
                                Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                project.getColorsList().get(
                                        project.getActiveColor()
                                ),
                                project.getStrokesList().get(
                                        project.getActiveStroke()
                                )
                        ));
                        repaint();
                    }
                    case 2 -> {
                        // strumento linea
                        if (stageRegister == 0) {
                            pointRegisterA = new Point(
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled())
                            );
                            stageRegister++;
                            xyDisplay.setText("[INSERT] Punto fine");
                            repaint();
                        } else {
                            project.addShapeToShapesList(new Line(
                                    (int) pointRegisterA.getX(),
                                    (int) pointRegisterA.getY(),
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                    project.getColorsList().get(
                                            project.getActiveColor()
                                    ),
                                    project.getStrokesList().get(
                                            project.getActiveStroke()
                                    )
                            ));
                            resetStageRegisters();
                            repaint();
                        }
                    }
                    case 3 -> {
                        // strumento rettangolo
                        if (stageRegister == 0) {
                            pointRegisterA = new Point(
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled())
                            );
                            stageRegister++;
                            xyDisplay.setText("[INSERT] Punto fine");
                            repaint();
                        } else {
                            project.addShapeToShapesList(new Rectangle(
                                    (int) pointRegisterA.getX(),
                                    (int) pointRegisterA.getY(),
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                    project.getColorsList().get(
                                            project.getActiveColor()
                                    ),
                                    project.getStrokesList().get(
                                            project.getActiveStroke()
                                    ),
                                    project.isFillShapesActive()
                            ));
                            resetStageRegisters();
                            repaint();
                        }
                    }
                    case 4 -> {
                        // strumento cerchio
                        if (stageRegister == 0) {
                            pointRegisterA = new Point(
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled())
                            );
                            stageRegister++;
                            xyDisplay.setText("[INSERT] Punto fine");
                            repaint();
                        } else {
                            project.addShapeToShapesList(new Circle(
                                    (int) pointRegisterA.getX(),
                                    (int) pointRegisterA.getY(),
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                    project.getColorsList().get(
                                            project.getActiveColor()
                                    ),
                                    project.getStrokesList().get(
                                            project.getActiveStroke()
                                    ),
                                    project.isFillShapesActive()
                            ));
                            resetStageRegisters();
                            repaint();
                        }
                    }
                    case 5 -> {
                        // ellisse
                        if (stageRegister == 0) {
                            pointRegisterA = new Point(
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled())
                            );
                            stageRegister++;
                            xyDisplay.setText("[INSERT] Punto raggio 1");
                            repaint();
                        } else if (stageRegister == 1) {
                            pointRegisterB = new Point(
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled())
                            );
                            stageRegister++;
                            xyDisplay.setText("[INSERT] Punto raggio 2");
                            repaint();
                        } else {
                            project.addShapeToShapesList(new Ellipse(
                                    (int) pointRegisterA.getX(),
                                    (int) pointRegisterA.getY(),
                                    (int) pointRegisterB.getX(),
                                    (int) pointRegisterB.getY(),
                                    Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                    Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                    project.getColorsList().get(
                                            project.getActiveColor()
                                    ),
                                    project.getStrokesList().get(
                                            project.getActiveStroke()
                                    ),
                                    project.isFillShapesActive()
                            ));
                            resetStageRegisters();
                            repaint();
                        }
                    }
                    case 6 -> {
                        // strumento testo
                        project.addShapeToShapesList(new SingleLineText(
                                Misc.objectSnapCalc(e.getX(), project.isObjectSnappingEnabled()),
                                Misc.objectSnapCalc(e.getY(), project.isObjectSnappingEnabled()),
                                project.getColorsList().get(
                                        project.getActiveColor()
                                ),
                                project.getTextHeightForInsert(),
                                project.getTextContentForInsert()
                        ));
                        repaint();
                    }
                }
            }
        });
        setBackground(Color.WHITE);
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
        if (gridVisible) {
            g2.setPaint(texture);
            g2.fillRect(0, 0, getWidth(), getHeight());
        }

        RenderingEngine.render(
                project.getShapesList(),
                g2,
                pointRegisterA,
                pointRegisterB,
                project.isObjectBoundariesEnabled()
        );

    }

    public boolean isGridVisible() {
        return gridVisible;
    }

    public void setGridVisible(boolean gridVisible) {
        this.gridVisible = gridVisible;
        repaint();
    }

    public BufferedImage exportToImage() {
        setGridVisible(true);
        BufferedImage imageToExport = new BufferedImage(
                this.getSize().width,
                this.getSize().height,
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics g = imageToExport.createGraphics();
        this.paint(g);
        g.dispose();
        return imageToExport;
    }

    private void resetStageRegisters() {
        stageRegister = 0;
        pointRegisterA = new Point(-10, -10);
        pointRegisterB = new Point(-10, -10);
        xyDisplay.setText("[INSERT] Punto root");
    }

}
