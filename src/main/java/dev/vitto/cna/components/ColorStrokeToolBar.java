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

package dev.vitto.cna.components;

import dev.vitto.cna.Project;
import dev.vitto.cna.utils.IconLoader;
import dev.vitto.cna.utils.IconMaker;
import dev.vitto.cna.utils.IconResize;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class ColorStrokeToolBar {

    Project project;
    JToolBar toolBar;

    JButton[] strokeButtons = new JButton[5];
    JButton[] colorButtons = new JButton[8];

    JMenuItem[] strokeMenuItems = new JMenuItem[5];
    JMenuItem[] colorMenuItems = new JMenuItem[8];

    JMenu jMenu = new JMenu("Colori e Tratto (C)");

    Border defaultBorder;
    Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 3);

    public ColorStrokeToolBar(Project project) {

        // bottoni toolbar
        toolBar = new JToolBar("Disegna");
        this.project = project;

        for (int i = 0; i < 5; i++) {
            strokeButtons[i] = new JButton();
            strokeButtons[i].setToolTipText("SLOT " + (i + 1) + ": " + project.getStrokesList().get(i) + "px");
            strokeButtons[i].setIcon(IconLoader.getStrokeIcon(i));
            int finalI = i;
            strokeButtons[i].addActionListener(e -> setActiveStroke(finalI, true));
            toolBar.add(strokeButtons[i]);
            if (i == 0) {
                defaultBorder = strokeButtons[i].getBorder();
                strokeButtons[i].setBorder(selectedBorder);
            }
        }

        toolBar.addSeparator();

        for (int i = 0; i < 8; i++) {
            colorButtons[i] = new JButton();
            colorButtons[i].setToolTipText(
                    "SLOT " + (i + 1) + ": " + IconMaker.colorToHEXCode(
                            project.getColorsList().get(i)
                    ));
            colorButtons[i].setIcon(IconMaker.colorIcon32(i + 1, project.getColorsList().get(i)));
            int finalI = i;
            colorButtons[i].addActionListener(e -> setActiveColor(finalI, true));
            toolBar.add(colorButtons[i]);
            if (i == 0) {
                colorButtons[i].setBorder(selectedBorder);
            }
        }

        // menu contestuale
        jMenu.setMnemonic(KeyEvent.VK_C);
        jMenu.getAccessibleContext().setAccessibleDescription("Strumenti di disegno");

        for (int i = 0; i < 5; i++) {
            strokeMenuItems[i] = new JMenuItem(
                    "Slot spessore " + (i + 1) + ": " + project.getStrokesList().get(i) + "px",
                    IconResize.resize(
                            IconLoader.getStrokeIcon(i), 16, 16));
            strokeMenuItems[i].setAccelerator(
                    KeyStroke.getKeyStroke(49 + i, KeyEvent.ALT_DOWN_MASK)); //49 sarebbe '1'
            strokeMenuItems[i].getAccessibleContext().setAccessibleDescription("Slot Spessore " + (i + 1));
            int finalI = i;
            strokeMenuItems[i].addActionListener(e -> project.setActiveStroke(finalI));
            jMenu.add(strokeMenuItems[i]);
        }

        jMenu.addSeparator();

        for (int i = 0; i < 8; i++) {
            colorMenuItems[i] = new JMenuItem(
                    "Slot colore " + (i + 1) + ": " + IconMaker.colorToHEXCode(project.getColorsList().get(i)),
                    IconResize.resize(
                            IconMaker.colorIcon32(i + 1, project.getColorsList().get(i)), 16, 16));
            colorMenuItems[i].setAccelerator(
                    KeyStroke.getKeyStroke(49 + i, KeyEvent.SHIFT_DOWN_MASK)); //49 sarebbe '1'
            colorMenuItems[i].getAccessibleContext().setAccessibleDescription("Slot Spessore " + (i + 1));
            int finalI = i;
            colorMenuItems[i].addActionListener(e -> project.setActiveColor(finalI));
            jMenu.add(colorMenuItems[i]);
        }
    }

    public JToolBar getJToolBar() {
        return toolBar;
    }

    public JMenu getJMenu() {
        return jMenu;
    }

    public void setNewStrokesList(List<Integer> strokesList) {
        if (strokesList.size() > 5) {
            return;
        }
        int i = 0;
        for (int stroke : strokesList
        ) {
            // update toolbar
            strokeButtons[i].setToolTipText("SLOT " + (i + 1) + ": " + stroke + "px");
            strokeButtons[i].setIcon(IconLoader.getStrokeIcon(i));

            // update jmenu
            strokeMenuItems[i].setText(
                    "Slot spessore " + (i + 1) + ": " + stroke);
            strokeMenuItems[i].setIcon(
                    IconResize.resize(IconLoader.getStrokeIcon(i), 16, 16));
            i++;
        }
    }

    public void setNewColorsList(List<Color> colorsList) {
        if (colorsList.size() > 8) {
            return;
        }
        int i = 0;
        for (Color color : colorsList
        ) {
            // update toolbar
            colorButtons[i].setToolTipText(
                    "SLOT " + (i + 1) + ": " + IconMaker.colorToHEXCode(
                            color
                    ));
            colorButtons[i].setIcon(IconMaker.colorIcon32(i + 1, color));

            // update jmenu
            strokeMenuItems[i].setText(
                    "Slot colore " + (i + 1) + ": " + IconMaker.colorToHEXCode(color));
            strokeMenuItems[i].setIcon(
                    IconResize.resize(
                            IconMaker.colorIcon32(i + 1, color), 16, 16));
            i++;
        }
    }

    private void clearActiveStrokes() {
        for (JButton btn : strokeButtons
        ) {
            btn.setBorder(defaultBorder);
        }
    }

    private void clearActiveColors() {
        for (JButton btn : colorButtons
        ) {
            btn.setBorder(defaultBorder);
        }
    }

    public void setActiveStroke(int activeStroke, boolean fireUpdate) {
        if (activeStroke < 0 || activeStroke > 7) {
            return;
        }
        clearActiveStrokes();
        strokeButtons[activeStroke].setBorder(selectedBorder);
        if (fireUpdate) {
            project.setActiveStroke(activeStroke);
        }
    }

    public void setActiveColor(int activeColor, boolean fireUpdate) {
        if (activeColor < 0 || activeColor > 7) {
            return;
        }
        clearActiveColors();
        colorButtons[activeColor].setBorder(selectedBorder);
        if (fireUpdate) {
            project.setActiveColor(activeColor);
        }
    }

}
