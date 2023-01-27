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
import dev.vitto.cna.objects.Shape;
import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.util.List;

public class MainWindow extends JFrame {

    JMenuItem xyDisplay = new JMenu("X: 0 | Y: 0");
    DrawToolBar drawToolBar;
    ColorStrokeToolBar colorStrokeToolBar;
    ViewMenu viewMenu;
    JMenu optionsMenu;
    JMenu fileMenu;
    JMenu editMenu;
    JScrollPane objectListPanel;
    PropertyPane objectPropertiesPanel;
    int[] selectedIndexes = {};
    JSplitPane sidePanel;
    JSplitPane mainPanel;
    CNACanvas cnaCanvas;
    Project project;
    DefaultListModel<Shape> listModel;
    JList<Shape> guiObjectList;

    public MainWindow(boolean isMacOS, Project project) {

        super("CNA (CNA's Not AutoCAD) - " + project.getProjectName());
        this.project = project;

        char shortcut_mask;

        // se macos inverti i tasti CTRL e CMD
        if (isMacOS) {
            shortcut_mask = InputEvent.META_DOWN_MASK;
        } else {
            shortcut_mask = InputEvent.CTRL_DOWN_MASK;
        }

        // impostazioni generali della finestra
        setSize(1280, 720);
        setPreferredSize(new Dimension(1280, 720));
        setLocation(200, 200);
        setIconImage(IconLoader.PROGRAM_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

        /*
          inizializzazione delle toolbar del programma
          le toolbar hanno bisogno del
             - progetto corrente (per impartire modifiche agli strumenti od oggetti)
             - il tasto accessorio per gli shortcut (solo menu a tendina)
             - la finestra parent (per aprire i dialog)
        */
        drawToolBar = new DrawToolBar(this, project);
        colorStrokeToolBar = new ColorStrokeToolBar(project);
        viewMenu = new ViewMenu(project);
        optionsMenu = new OptionsMenu(project, shortcut_mask, this);
        fileMenu = new FileMenu(project, shortcut_mask, this);
        editMenu = new EditMenu(project, shortcut_mask, this);

        // aggiungi tutti i menu nella barra superiore
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(drawToolBar.getJMenu());
        menuBar.add(colorStrokeToolBar.getJMenu());
        menuBar.add(optionsMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(xyDisplay);
        setJMenuBar(menuBar);

        //Disegna l'interfaccia grafica
        setLayout(new BorderLayout());
        add(drawToolBar.getJToolBar(), BorderLayout.PAGE_START);
        add(colorStrokeToolBar.getJToolBar(), BorderLayout.PAGE_END);

        // crea componente lista oggetti
        listModel = new DefaultListModel<>();
        listModel.addAll(project.getShapesList());

        // crea pannello proprietà
        objectPropertiesPanel = new PropertyPane();
        objectPropertiesPanel.setBorder(BorderFactory.createTitledBorder("Proprietà oggetti selezionati"));

        guiObjectList = new JList<>(listModel); //data has type Object[]
        guiObjectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        guiObjectList.setLayoutOrientation(JList.VERTICAL);
        guiObjectList.setVisibleRowCount(-1);
        // selection listener per aggiornare propertypane
        guiObjectList.addListSelectionListener(e -> {
            objectPropertiesPanel.viewObjectProperties(
                    project.getShapesList(),
                    guiObjectList.getSelectedIndices()
            );
            selectedIndexes = guiObjectList.getSelectedIndices();
        });
        // double click listener per il rinomino
        guiObjectList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 2) {
                    // dialog per rinominare oggetto
                    int[] indexes = {list.locationToIndex(evt.getPoint())};
                    renameObjects(indexes);
                }
            }
        });

        // crea pannello scrollabile della lista oggetti
        objectListPanel = new JScrollPane(guiObjectList);
        objectListPanel.setPreferredSize(new Dimension(250, 80)); // togli magari??
        objectListPanel.setBorder(BorderFactory.createTitledBorder("Lista oggetti"));

        sidePanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, objectListPanel, objectPropertiesPanel);
        sidePanel.setResizeWeight(1);
        sidePanel.setDividerLocation(300);

        cnaCanvas = new CNACanvas(xyDisplay, project);

        mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidePanel, cnaCanvas);
        mainPanel.setResizeWeight(1);
        mainPanel.setDividerLocation(300);
        add(mainPanel, BorderLayout.CENTER);

        // listener per i cambi di variabile nel progetto
        project.addPropertyChangeListener((PropertyChangeEvent event) -> {
            if (Project.PROJECT_NAME.equals(event.getPropertyName())) {
                setTitle("CNA (CNA's Not AutoCAD) - " + event.getNewValue());
            }
            if (Project.ACTIVE_INSTRUMENT.equals(event.getPropertyName())) {
                drawToolBar.setActiveInstrument((Integer) event.getNewValue(), false);
                cnaCanvas.changeCursor((Integer) event.getNewValue() > 0);
                if ((int) event.getNewValue() > 0) {
                    // AGGIORNAMENTO testo xyDisplay
                    xyDisplay.setText("[INSERT] Punto root");
                } else {
                    xyDisplay.setText("X: 0 | Y: 0");
                }
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
                objectListPanel.setVisible((boolean) event.getNewValue());
                updateSidePanelBoundaries();
            }
            if (Project.OBJECTPROPERTIES_SIDEBAR_VISIBILITY.equals(event.getPropertyName())) {
                objectPropertiesPanel.setVisible((boolean) event.getNewValue());
                updateSidePanelBoundaries();
            }
            if (Project.CANVAS_GRID_VISIBILITY.equals(event.getPropertyName())) {
                cnaCanvas.setGridVisible((boolean) event.getNewValue());
                drawToolBar.setCanvasGridVisibility((boolean) event.getNewValue(), false);
                viewMenu.setCanvasGridVisibility((boolean) event.getNewValue(), false);
            }
            if (Project.SHAPES_LIST.equals(event.getPropertyName())) {
                cnaCanvas.repaint();
                listModel.removeAllElements();
                listModel.addAll(project.getShapesList());
            }
        });

        //repaint della finestra
        validate();
        repaint();

    }

    /*
        CAMBIO DI VISIBILITÀ DEI PANNELLI LATERALI:
        Dopo questo evento, bisogna aggiornare le dimensioni dei pannelli per evitare di lasciare
        buchi nell'interfaccia grafica quando vengono nascosti pezzi di essa.
     */
    void updateSidePanelBoundaries() {
        // controlla se solo uno dei due pannelli è visibile
        if (objectListPanel.isVisible() ^ objectPropertiesPanel.isVisible()) {
            sidePanel.setVisible(true);
            mainPanel.setDividerLocation(mainPanel.getWidth() / 5);
            if (objectListPanel.isVisible()) {
                // imposta divider alla fine per dare tutto lo spazio al pannello superiore
                sidePanel.setDividerLocation(sidePanel.getHeight() - 1);
            } else {
                // imposta divider all'inizio
                sidePanel.setDividerLocation(1);
            }
        } else if (objectListPanel.isVisible() && objectPropertiesPanel.isVisible()) {
            // sono tutti e due visibili
            sidePanel.setVisible(true);
            // imposta divisione a metà per il side e 1/5 per il main
            mainPanel.setDividerLocation(mainPanel.getWidth() / 5);
            sidePanel.setDividerLocation((sidePanel.getHeight() / 2) + 1);
        } else {
            // nessuno dei due visibili, nascondi tutto
            sidePanel.setVisible(false);
            mainPanel.setDividerLocation(0);
        }
    }

    /*
        Wrapper della funzione del canvas per renderla disponibile a tutti gli oggetti
        con un riferimento a questa finestra.
    */
    public BufferedImage exportCanvasToImage() {
        return cnaCanvas.exportToImage();
    }

    public void deleteSelectedObjects() {
        for (int index : selectedIndexes
        ) {
            try {
                project.removeShapeFromShapesList(index);
            } catch (Exception ignored) {
            }
        }
    }

    public void renameObjects(int[] index) {
        if (index.length != 1) {
            //dialog errore
            JOptionPane.showMessageDialog(this,
                    "Per favore, seleziona solo e solamente un'oggetto",
                    "Rinomina oggetto",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        int i;
        if (index[0] == -1) {
            i = selectedIndexes[0];
        } else {
            i = index[0];
        }

        String s = (String) JOptionPane.showInputDialog(
                this,
                "Inserisci il nuovo nome dell' oggetto:",
                "Rinomina oggetto",
                JOptionPane.PLAIN_MESSAGE,
                IconLoader.STROKE_ICON,
                null,
                project.getShapesList().get(i).getName());

        if (s != null) {
            if (s.length() > 1) {
                project.getShapesList().get(i).setName(s);
                project.fireShapeListPropChange();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Impossibile cambiare il nome dell'oggetto",
                        "Errore",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void selectAll() {
        int end = listModel.getSize() - 1;
        if (end >= 0) {
            guiObjectList.setSelectionInterval(0, end);
        }
    }

}
