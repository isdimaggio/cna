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

package dev.vitto.cna.windows;

import dev.vitto.cna.CNACanvas;
import dev.vitto.cna.Project;
import dev.vitto.cna.components.*;
import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainWindow extends JFrame {

    JMenuItem debug = new JMenu("X: 0 | Y: 0");
    DrawToolBar drawToolBar;
    ColorStrokeToolBar colorStrokeToolBar;
    JMenu viewToolBar;
    JScrollPane listScroller;
    JPanel propertyScroller;
    JSplitPane pannelloLaterale;
    JSplitPane pannelloPadre;

    public MainWindow(boolean isMacOS, Project project) {

        super("CNA (CNA's Not AutoCAD) - " + project.getProjectName());

        char META_CTRL_MASK;

        if (isMacOS) {
            META_CTRL_MASK = InputEvent.META_DOWN_MASK;
        } else {
            META_CTRL_MASK = InputEvent.CTRL_DOWN_MASK;
        }

        // impostazioni generali della finestra
        setSize(1280, 720);
        setPreferredSize(new Dimension(1280, 720));
        setLocation(200, 200);
        setIconImage(IconLoader.PROGRAM_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setResizable(true);
        setVisible(true);

        drawToolBar = new DrawToolBar(project, META_CTRL_MASK);
        colorStrokeToolBar = new ColorStrokeToolBar(project);
        viewToolBar = new ViewMenu(project);

        // aggiungi tutti i menu nella barra superiore
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(FileMenu.get(META_CTRL_MASK));
        menuBar.add(EditMenu.get(META_CTRL_MASK));
        menuBar.add(viewToolBar);
        menuBar.add(drawToolBar.getJMenu());
        menuBar.add(colorStrokeToolBar.getJMenu());
        menuBar.add(OptionsMenu.get(META_CTRL_MASK));
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(debug);
        setJMenuBar(menuBar);

        //Disegna l'interfaccia grafica
        setLayout(new BorderLayout());
        add(drawToolBar.getJToolBar(), BorderLayout.PAGE_START);
        add(colorStrokeToolBar.getJToolBar(), BorderLayout.PAGE_END);

        // crea componente lista oggetti
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (int i = 0; i < 10; i++) {
            listModel.addElement("Oggetto di esempio " + i);
        }

        JList list = new JList(listModel); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        listScroller.setBorder(BorderFactory.createTitledBorder("Lista oggetti"));

        propertyScroller = new JPanel();
        propertyScroller.setBorder(BorderFactory.createTitledBorder("Proprietà oggetto selezionato"));

        pannelloLaterale = new JSplitPane(JSplitPane.VERTICAL_SPLIT, listScroller, propertyScroller);
        pannelloLaterale.setResizeWeight(1);
        pannelloLaterale.setDividerLocation(300);

        CNACanvas cnaCanvas = new CNACanvas(debug);

        pannelloPadre = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pannelloLaterale, cnaCanvas);
        pannelloPadre.setResizeWeight(1);
        pannelloPadre.setDividerLocation(300);
        add(pannelloPadre, BorderLayout.CENTER);

        // listener per i cambi di variabile nel progetto
        project.addPropertyChangeListener((PropertyChangeEvent event) -> {
            if (Project.PROJECT_NAME.equals(event.getPropertyName())) {
                setTitle("CNA (CNA's Not AutoCAD) - " + event.getNewValue());
            }
            if (Project.ACTIVE_INSTRUMENT.equals(event.getPropertyName())) {
                drawToolBar.setActiveInstrument((Integer) event.getNewValue(), false);
                cnaCanvas.changeCursor((Integer) event.getNewValue() > 0);
            }
            if (Project.FILL_SHAPES_ACTIVE.equals(event.getPropertyName())) {
                drawToolBar.setFillShapesActive((boolean) event.getNewValue(), false);
            }
            if (Project.STROKES_LIST.equals(event.getPropertyName())) {
                colorStrokeToolBar.setNewStrokesList((List<Integer>) event.getNewValue());
            }
            if (Project.COLORS_LIST.equals(event.getPropertyName())) {
                colorStrokeToolBar.setNewColorsList((List<Color>) event.getNewValue());
            }
            if (Project.ACTIVE_STROKE.equals(event.getPropertyName())) {
                colorStrokeToolBar.setActiveStroke((Integer) event.getNewValue(), false);
            }
            if (Project.ACTIVE_COLOR.equals(event.getPropertyName())) {
                colorStrokeToolBar.setActiveColor((Integer) event.getNewValue(), false);
            }
            if (Project.DRAW_TOOLBAR_VISIBILITY.equals(event.getPropertyName())) {
                drawToolBar.getJToolBar().setVisible((boolean) event.getNewValue());
            }
            if (Project.CS_TOOLBAR_VISIBILITY.equals(event.getPropertyName())) {
                colorStrokeToolBar.getJToolBar().setVisible((boolean) event.getNewValue());
            }
            if (Project.OBJECTLIST_SIDEBAR_VISIBILITY.equals(event.getPropertyName())) {
                listScroller.setVisible((boolean) event.getNewValue());
                updateSidebarBoundaries();
            }
            if (Project.OBJECTPROPERTIES_SIDEBAR_VISIBILITY.equals(event.getPropertyName())) {
                propertyScroller.setVisible((boolean) event.getNewValue());
                updateSidebarBoundaries();
            }
        });

        //repaint della finestra
        validate();
        repaint();

    }

    void updateSidebarBoundaries() {
        // controlla se solo uno dei due pannelli è visibile
        if (listScroller.isVisible() ^ propertyScroller.isVisible()) {
            pannelloLaterale.setVisible(true);
            pannelloPadre.setDividerLocation(pannelloPadre.getWidth() / 5);
            if (listScroller.isVisible()) {
                // imposta divider alla fine per dare tutto lo spazio al pannello superiore
                pannelloLaterale.setDividerLocation(pannelloLaterale.getHeight() - 1);
            } else {
                // imposta divider all'inizio
                pannelloLaterale.setDividerLocation(1);
            }
        } else if (listScroller.isVisible() && propertyScroller.isVisible()) {
            pannelloLaterale.setVisible(true);
            pannelloPadre.setDividerLocation(pannelloPadre.getWidth() / 5);
            // imposta divisione a metà
            pannelloLaterale.setDividerLocation((pannelloLaterale.getHeight() / 2) + 1);
        } else {
            // nessuno dei due visibili, nascondi tutto
            pannelloLaterale.setVisible(false);
            pannelloPadre.setDividerLocation(0);
        }
    }

}
