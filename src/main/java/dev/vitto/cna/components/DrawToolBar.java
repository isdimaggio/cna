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
@author "Adele Rendina"
*/

package dev.vitto.cna.components;

import dev.vitto.cna.Project;
import dev.vitto.cna.utils.IconLoader;
import dev.vitto.cna.utils.IconResize;
import dev.vitto.cna.utils.Misc;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;

public class DrawToolBar {

    Project project;
    JFrame parent;

    JToolBar toolBar;
    JButton noCommandButton = new JButton();
    JButton pointButton = new JButton();
    JButton lineButton = new JButton();
    JButton rectangleButton = new JButton();
    JButton centerRadButton = new JButton();
    JButton ellipseButton = new JButton();
    JButton textButton = new JButton();

    JButton fillButton = new JButton();
    JButton canvasGridVisibilityButton = new JButton();
    JButton objectSnappingButton = new JButton();
    JButton objectBoundariesVisibilityButton = new JButton();

    Border defaultBorder;
    Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 3);

    JMenu jMenu = new JMenu("Disegna (D)");
    JCheckBoxMenuItem fillMenuItem;
    JCheckBoxMenuItem objectSnappingMenuItem;

    public DrawToolBar(JFrame parent, Project project) {

        // bottoni toolbar
        toolBar = new JToolBar("Disegna");
        this.project = project;
        this.parent = parent;

        noCommandButton.setToolTipText("Nessun comando");
        noCommandButton.setIcon(IconLoader.HAND_CMD);
        noCommandButton.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(noCommandButton);

        defaultBorder = noCommandButton.getBorder();
        noCommandButton.setBorder(selectedBorder);

        pointButton.setToolTipText("Punto");
        pointButton.setIcon(IconLoader.POINT_CMD);
        pointButton.addActionListener(e -> setActiveInstrument(1, true));
        toolBar.add(pointButton);

        lineButton.setToolTipText("Linea");
        lineButton.setIcon(IconLoader.LINE_CMD);
        lineButton.addActionListener(e -> setActiveInstrument(2, true));
        toolBar.add(lineButton);

        rectangleButton.setToolTipText("Rettangolo");
        rectangleButton.setIcon(IconLoader.RECT_CMD);
        rectangleButton.addActionListener(e -> setActiveInstrument(3, true));
        toolBar.add(rectangleButton);

        centerRadButton.setToolTipText("Cerchio Centro-Raggio");
        centerRadButton.setIcon(IconLoader.CENTER_RAD_CMD);
        centerRadButton.addActionListener(e -> setActiveInstrument(4, true));
        toolBar.add(centerRadButton);

        ellipseButton.setToolTipText("Ellisse");
        ellipseButton.setIcon(IconLoader.ELLIPSE_CMD);
        ellipseButton.addActionListener(e -> setActiveInstrument(5, true));
        toolBar.add(ellipseButton);

        textButton.setToolTipText("Testo Single-Line");
        textButton.setIcon(IconLoader.TEXT_CMD);
        textButton.addActionListener(e -> {
            if (Misc.textDataInsertionDialog(parent, project)) {
                setActiveInstrument(6, true);
            }
        });
        toolBar.add(textButton);

        toolBar.addSeparator();

        fillButton.setToolTipText("Attiva riempimento");
        fillButton.setIcon(IconLoader.FILL_OFF_CMD);
        fillButton.addActionListener(e -> setFillShapesActive(!project.isFillShapesActive(), true));
        toolBar.add(fillButton);

        canvasGridVisibilityButton.setToolTipText("Disattiva griglia");
        canvasGridVisibilityButton.setIcon(IconLoader.GRID_ON_CMD);
        canvasGridVisibilityButton.addActionListener(e -> setCanvasGridVisibility(!project.isCanvasGridVisibility(), true));
        toolBar.add(canvasGridVisibilityButton);

        objectSnappingButton.setToolTipText("Attiva object snapping");
        objectSnappingButton.setIcon(IconLoader.OSNAP_OFF);
        objectSnappingButton.addActionListener(e -> setObjectSnappingActive(!project.isObjectSnappingEnabled(), true));
        toolBar.add(objectSnappingButton);

        objectBoundariesVisibilityButton.setToolTipText("Attiva delimitatori oggetti");
        objectBoundariesVisibilityButton.setIcon(IconLoader.BOUNDARY_OFF);
        objectBoundariesVisibilityButton.addActionListener(e -> setObjectBoundariesVisibility(!project.isObjectBoundariesEnabled(), true));
        toolBar.add(objectBoundariesVisibilityButton);

        // menu contestuale
        JMenuItem menuItem;
        jMenu.setMnemonic(KeyEvent.VK_D);
        jMenu.getAccessibleContext().setAccessibleDescription("Strumenti di disegno");

        menuItem = new JMenuItem("Punto", IconResize.resize(IconLoader.POINT_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna un punto");
        menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Linea", IconResize.resize(IconLoader.LINE_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna una linea");
        menuItem.addActionListener(e -> setActiveInstrument(2, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Rettangolo", IconResize.resize(IconLoader.RECT_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna un rettangolo");
        menuItem.addActionListener(e -> setActiveInstrument(3, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Cerchio Centro-Raggio", IconResize.resize(IconLoader.CENTER_RAD_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna un cerchio definendo prima il centro e poi il raggio");
        menuItem.addActionListener(e -> setActiveInstrument(4, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Ellisse", IconResize.resize(IconLoader.ELLIPSE_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna un cerchio definendo prima il centro e poi il raggio");
        menuItem.addActionListener(e -> setActiveInstrument(5, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Testo Single-Line", IconResize.resize(IconLoader.TEXT_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disegna un cerchio definendo prima il centro e poi il raggio");
        menuItem.addActionListener(e -> {
            if (Misc.textDataInsertionDialog(parent, project)) {
                setActiveInstrument(6, true);
            }
        });
        jMenu.add(menuItem);

        jMenu.addSeparator();

        menuItem = new JMenuItem("Nessuno Strumento", IconResize.resize(IconLoader.HAND_CMD, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, KeyEvent.CTRL_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Disattiva strumenti");
        menuItem.addActionListener(e -> setActiveInstrument(0, true));
        jMenu.add(menuItem);

        fillMenuItem = new JCheckBoxMenuItem("Riempimento");
        fillMenuItem.setSelected(false);
        fillMenuItem.addActionListener(e -> setFillShapesActive(fillMenuItem.isSelected(), true));
        jMenu.add(fillMenuItem);

        objectSnappingMenuItem = new JCheckBoxMenuItem("Object snapping");
        objectSnappingMenuItem.setSelected(false);
        objectSnappingMenuItem.addActionListener(e -> setObjectSnappingActive(objectSnappingMenuItem.isSelected(), true));
        jMenu.add(objectSnappingMenuItem);

    }

    private void clear() {
        noCommandButton.setBorder(defaultBorder);
        pointButton.setBorder(defaultBorder);
        lineButton.setBorder(defaultBorder);
        rectangleButton.setBorder(defaultBorder);
        centerRadButton.setBorder(defaultBorder);
        ellipseButton.setBorder(defaultBorder);
        textButton.setBorder(defaultBorder);
    }

    public void setActiveInstrument(int instrumentNumber, boolean fireUpdate) {
        if (instrumentNumber < 0 || instrumentNumber > 6) {
            return;
        }
        clear();
        switch (instrumentNumber) {
            case 0 -> noCommandButton.setBorder(selectedBorder);
            case 1 -> pointButton.setBorder(selectedBorder);
            case 2 -> lineButton.setBorder(selectedBorder);
            case 3 -> rectangleButton.setBorder(selectedBorder);
            case 4 -> centerRadButton.setBorder(selectedBorder);
            case 5 -> ellipseButton.setBorder(selectedBorder);
            case 6 -> textButton.setBorder(selectedBorder);
        }
        if (fireUpdate) {
            project.setActiveInstrument(instrumentNumber);
        }
    }

    public void setFillShapesActive(boolean status, boolean fireUpdate) {

        if (status) {
            fillButton.setToolTipText("Disattiva riempimento");
            fillButton.setIcon(IconLoader.FILL_ON_CMD);
            fillMenuItem.setSelected(true);
        } else {
            fillButton.setToolTipText("Attiva riempimento");
            fillButton.setIcon(IconLoader.FILL_OFF_CMD);
            fillMenuItem.setSelected(false);
        }

        if (fireUpdate) {
            project.setFillShapesActive(status);
        }
    }

    public void setCanvasGridVisibility(boolean status, boolean fireUpdate) {
        if (status) {
            canvasGridVisibilityButton.setToolTipText("Disattiva griglia");
            canvasGridVisibilityButton.setIcon(IconLoader.GRID_ON_CMD);
        } else {
            canvasGridVisibilityButton.setToolTipText("Attiva griglia");
            canvasGridVisibilityButton.setIcon(IconLoader.GRID_OFF_CMD);
        }

        if (fireUpdate) {
            project.setCanvasGridVisibility(status);
        }
    }

    public void setObjectSnappingActive(boolean status, boolean fireUpdate) {

        if (status) {
            objectSnappingButton.setToolTipText("Disattiva object snapping");
            objectSnappingButton.setIcon(IconLoader.OSNAP_ON);
            objectSnappingMenuItem.setSelected(true);
        } else {
            objectSnappingButton.setToolTipText("Attiva object snapping");
            objectSnappingButton.setIcon(IconLoader.OSNAP_OFF);
            objectSnappingMenuItem.setSelected(false);
        }

        if (fireUpdate) {
            project.setObjectSnappingEnabled(status);
        }
    }

    public void setObjectBoundariesVisibility(boolean status, boolean fireUpdate) {
        if (status) {
            objectBoundariesVisibilityButton.setToolTipText("Disattiva delimitatori oggetti");
            objectBoundariesVisibilityButton.setIcon(IconLoader.BOUNDARY_ON);
        } else {
            objectBoundariesVisibilityButton.setToolTipText("Attiva delimitatori oggetti");
            objectBoundariesVisibilityButton.setIcon(IconLoader.BOUNDARY_OFF);
        }

        if (fireUpdate) {
            project.setObjectBoundariesEnabled(status);
        }
    }

    public JToolBar getJToolBar() {
        return toolBar;
    }

    public JMenu getJMenu() {
        return jMenu;
    }

}
