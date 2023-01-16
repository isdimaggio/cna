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

public class ColorStrokeToolBar {

    Project project;
    JToolBar toolBar;
    JButton stroke1 = new JButton();
    JButton stroke2 = new JButton();
    JButton stroke3 = new JButton();
    JButton stroke4 = new JButton();
    JButton stroke5 = new JButton();
    JButton color1 = new JButton();
    JButton color2 = new JButton();
    JButton color3 = new JButton();
    JButton color4 = new JButton();
    JButton color5 = new JButton();
    JButton color6 = new JButton();
    JButton color7 = new JButton();
    JButton color8 = new JButton();

    JMenu jMenu = new JMenu("Colori e Tratto (C)");

    Border defaultBorder;
    Border selectedBorder = BorderFactory.createLineBorder(Color.GREEN, 3);

    public ColorStrokeToolBar(Project project) {

        // bottoni toolbar
        toolBar = new JToolBar("Disegna");
        this.project = project;

        stroke1.setToolTipText("SLOT SPESSORE 1");
        stroke1.setIcon(IconLoader.STROKE_1);
        //stroke1.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(stroke1);

        defaultBorder = stroke1.getBorder();
        stroke1.setBorder(selectedBorder);

        stroke2.setToolTipText("SLOT SPESSORE 2");
        stroke2.setIcon(IconLoader.STROKE_2);
        //stroke2.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(stroke2);

        stroke3.setToolTipText("SLOT SPESSORE 3");
        stroke3.setIcon(IconLoader.STROKE_3);
        //stroke3.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(stroke3);

        stroke4.setToolTipText("SLOT SPESSORE 4");
        stroke4.setIcon(IconLoader.STROKE_4);
        //stroke4.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(stroke4);

        stroke5.setToolTipText("SLOT SPESSORE 5");
        stroke5.setIcon(IconLoader.STROKE_5);
        //stroke5.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(stroke5);

        toolBar.addSeparator();

        color1.setToolTipText("SLOT COLORE 1");
        color1.setIcon(IconMaker.colorIcon32(1, Color.red));
        //color1.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color1);

        color1.setBorder(selectedBorder);

        color2.setToolTipText("SLOT COLORE 2");
        color2.setIcon(IconMaker.colorIcon32(2, Color.green));
        //color2.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color2);

        color3.setToolTipText("SLOT COLORE 3");
        color3.setIcon(IconMaker.colorIcon32(3, Color.blue));
        //color3.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color3);

        color4.setToolTipText("SLOT COLORE 4");
        color4.setIcon(IconMaker.colorIcon32(4, Color.cyan));
        //color4.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color4);

        color5.setToolTipText("SLOT COLORE 5");
        color5.setIcon(IconMaker.colorIcon32(5, Color.yellow));
        //color5.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color5);

        color6.setToolTipText("SLOT COLORE 6");
        color6.setIcon(IconMaker.colorIcon32(6, Color.magenta));
        //color6.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color6);

        color7.setToolTipText("SLOT COLORE 7");
        color7.setIcon(IconMaker.colorIcon32(7, Color.black));
        //color7.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color7);

        color8.setToolTipText("SLOT COLORE 8");
        color8.setIcon(IconMaker.colorIcon32(8, Color.white));
        //color8.addActionListener(e -> setActiveInstrument(0, true));
        toolBar.add(color8);

        // menu contestuale
        JMenuItem menuItem;
        jMenu.setMnemonic(KeyEvent.VK_C);
        jMenu.getAccessibleContext().setAccessibleDescription("Strumenti di disegno");

        menuItem = new JMenuItem("Slot Spessore 1", IconResize.resize(IconLoader.STROKE_1, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Spessore 1");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Spessore 2", IconResize.resize(IconLoader.STROKE_2, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Spessore 2");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Spessore 3", IconResize.resize(IconLoader.STROKE_3, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Spessore 3");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Spessore 4", IconResize.resize(IconLoader.STROKE_4, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Spessore 4");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Spessore 5", IconResize.resize(IconLoader.STROKE_5, 16, 16));
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Spessore 5");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        jMenu.addSeparator();

        menuItem = new JMenuItem("Slot Colore 1", IconResize.resize(
                IconMaker.colorIcon32(1, Color.red), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 1");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 2", IconResize.resize(
                IconMaker.colorIcon32(2, Color.green), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 2");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 3", IconResize.resize(
                IconMaker.colorIcon32(3, Color.blue), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 3");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 4", IconResize.resize(
                IconMaker.colorIcon32(4, Color.cyan), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 4");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 5", IconResize.resize(
                IconMaker.colorIcon32(5, Color.yellow), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 5");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 6", IconResize.resize(
                IconMaker.colorIcon32(6, Color.magenta), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 6");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 7", IconResize.resize(
                IconMaker.colorIcon32(7, Color.black), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 7");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);

        menuItem = new JMenuItem("Slot Colore 8", IconResize.resize(
                IconMaker.colorIcon32(8, Color.white), 16, 16)
        );
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_8, KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Slot Colore 8");
        //menuItem.addActionListener(e -> setActiveInstrument(1, true));
        jMenu.add(menuItem);
    }

    public JToolBar getJToolBar() {
        return toolBar;
    }

    public JMenu getJMenu() {
        return jMenu;
    }

}
