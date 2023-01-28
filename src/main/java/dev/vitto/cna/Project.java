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

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Project implements java.io.Serializable {
    /*
        LISTA COSTANTI NOMI EVENTI:
        Tutti gli eventi di cambio proprietà, quando vengono lanciati, sono corredati
        dal loro nome, per poter permettere ai listener registrati di capire se l'evento
        è indirizzato a loro o meno.
     */
    public static final String ACTIVE_INSTRUMENT = "activeInstrument";
    public static final String PROJECT_NAME = "projectName";
    public static final String FILL_SHAPES_ACTIVE = "fillShapesActive";
    public static final String STROKES_LIST = "strokesList";
    public static final String COLORS_LIST = "colorsList";
    public static final String ACTIVE_STROKE = "activeStroke";
    public static final String ACTIVE_COLOR = "activeColor";
    public static final String DRAW_TOOLBAR_VISIBILITY = "drawToolbarVisibility";
    public static final String CS_TOOLBAR_VISIBILITY = "csToolbarVisibility";
    public static final String OBJECTLIST_SIDEBAR_VISIBILITY = "objectListSidebarVisibility";
    public static final String OBJECTPROPERTIES_SIDEBAR_VISIBILITY = "objectPropertiesSidebarVisibility";
    public static final String CANVAS_GRID_VISIBILITY = "canvasGridVisibility";
    public static final String OBJECT_SNAPPING = "objectSnappingChanged";
    public static final String OBJECT_BOUNDARIES = "objectBoundaresChanged";
    public static final String SHAPES_LIST = "shapesList";
    public static final String DEFAULT_PROJECT_NAME = "Progetto senza titolo";

    /*
        PROPERTY CHANGE SUPPORT:
        Aggiunge il supporto per un sistema listener -> subscriber per allertare tutte le parti
        del programma di eventuali cambiamenti nel progetto.

        Cambio Proprietà --> Event Router (MainWindow) --> Dispatch eventi
     */
    private final PropertyChangeSupport mPcs = new PropertyChangeSupport(this);

    // ********************************* //

    private String projectName = DEFAULT_PROJECT_NAME;
    private int activeInstrument = 0; // strumento attivo di disegno
    private boolean fillShapesActive = false; // riempimento delle forme

    private List<Integer> strokesList = new ArrayList<>(5); // lista degli spessori selezionabili
    private List<Color> colorsList = new ArrayList<>(8); // lista dei colori selezionabili

    private int activeStroke = 0; // spessore attivo (index della lista)
    private int activeColor = 0; // colore attivo (index della lista)

    private boolean drawToolbarVisibility = true; // visibilità della toolbar di disegno
    private boolean csToolbarVisibility = true; // visibilità della toolbar colore e spessore

    private boolean objectListSidebarVisibility = true; // visibilità della sezione lista oggetti nella barra laterale
    private boolean objectPropertiesSidebarVisibility = true; // visibilità della sez. proprietà

    private boolean canvasGridVisibility = true; // visibilità della griglia nel canvas

    private boolean objectSnappingEnabled = false; // se lo snapping alla griglia è attivato
    private boolean objectBoundariesEnabled = false; // se la visualizzazione dei punti di boundaries è abilitata

    private List<Shape> shapesList = new ArrayList<>(); // lista di tutte le figure geometriche da renderizzare

    private String textContentForInsert = "Lorem ipsum dolor sit amet";
    private int textHeightForInsert = 13;

    // ********************************* //

    public Project() {
        loadDefaults();
    }

    public Project(Project p2) {
        replaceSelf(p2);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }

    private void loadDefaults() {
        // caricamento spessori predefiniti
        strokesList.add(3);
        strokesList.add(5);
        strokesList.add(7);
        strokesList.add(9);
        strokesList.add(11);

        // caricamento colori predefiniti
        colorsList.add(Color.red);
        colorsList.add(Color.green);
        colorsList.add(Color.blue);
        colorsList.add(Color.cyan);
        colorsList.add(Color.yellow);
        colorsList.add(Color.magenta);
        colorsList.add(Color.black);
        colorsList.add(Color.white);

        textContentForInsert = "Lorem ipsum dolor sit amet";
        textHeightForInsert = 13;
    }

    public void clearProject() {
        projectName = DEFAULT_PROJECT_NAME;
        activeInstrument = 0;
        fillShapesActive = false;
        strokesList = new ArrayList<>(5);
        colorsList = new ArrayList<>(8);
        activeStroke = 0;
        activeColor = 0;
        drawToolbarVisibility = true;
        csToolbarVisibility = true;
        objectListSidebarVisibility = true;
        objectPropertiesSidebarVisibility = true;
        canvasGridVisibility = true;
        shapesList = new ArrayList<>();
        objectSnappingEnabled = false;
        objectBoundariesEnabled = false;
        loadDefaults();
        fireAllPropertyChanges();
    }

    // questa funzione è necessaria siccome rimpiazzare l'oggetto project significherebbe rompere
    // tutti i listener registrati per l'applicazione
    public void replaceSelf(Project newProject) {
        projectName = newProject.projectName;
        activeInstrument = newProject.activeInstrument;
        fillShapesActive = newProject.fillShapesActive;
        strokesList = newProject.strokesList;
        colorsList = newProject.colorsList;
        activeStroke = newProject.activeStroke;
        activeColor = newProject.activeColor;
        drawToolbarVisibility = newProject.drawToolbarVisibility;
        csToolbarVisibility = newProject.csToolbarVisibility;
        objectListSidebarVisibility = newProject.objectListSidebarVisibility;
        objectPropertiesSidebarVisibility = newProject.objectPropertiesSidebarVisibility;
        canvasGridVisibility = newProject.canvasGridVisibility;
        shapesList = newProject.shapesList;
        textContentForInsert = newProject.textContentForInsert;
        textHeightForInsert = newProject.textHeightForInsert;
        objectBoundariesEnabled = newProject.objectBoundariesEnabled;
        objectSnappingEnabled = newProject.objectSnappingEnabled;
        fireAllPropertyChanges();
    }

    public void fireAllPropertyChanges() {
        mPcs.firePropertyChange(PROJECT_NAME, null, projectName);
        mPcs.firePropertyChange(ACTIVE_INSTRUMENT, null, activeInstrument);
        mPcs.firePropertyChange(FILL_SHAPES_ACTIVE, null, fillShapesActive);
        mPcs.firePropertyChange(STROKES_LIST, null, strokesList);
        mPcs.firePropertyChange(COLORS_LIST, null, colorsList);
        mPcs.firePropertyChange(ACTIVE_STROKE, null, activeStroke);
        mPcs.firePropertyChange(ACTIVE_COLOR, null, activeColor);
        mPcs.firePropertyChange(DRAW_TOOLBAR_VISIBILITY, null, drawToolbarVisibility);
        mPcs.firePropertyChange(CS_TOOLBAR_VISIBILITY, null, csToolbarVisibility);
        mPcs.firePropertyChange(OBJECTLIST_SIDEBAR_VISIBILITY, null, objectListSidebarVisibility);
        mPcs.firePropertyChange(OBJECTPROPERTIES_SIDEBAR_VISIBILITY, null, objectListSidebarVisibility);
        mPcs.firePropertyChange(CANVAS_GRID_VISIBILITY, null, canvasGridVisibility);
        mPcs.firePropertyChange(SHAPES_LIST, null, shapesList);
        mPcs.firePropertyChange(OBJECT_SNAPPING, null, objectSnappingEnabled);
        mPcs.firePropertyChange(OBJECT_BOUNDARIES, null, objectBoundariesEnabled);
    }

    // ********************************* //

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        // controlla validità del nome del progetto
        if (projectName == null || projectName.length() < 1) {
            return;
        }
        String oldValue = this.projectName;
        this.projectName = projectName;
        mPcs.firePropertyChange(PROJECT_NAME, oldValue, projectName);
    }

    public int getActiveInstrument() {
        return activeInstrument;
    }

    /*
      LISTA STRUMENTI
      0 -> Nessun Comando (Manina)
      1 -> Punto
      2 -> Linea
      3 -> Rettangolo
      4 -> Cerchio Centro-Raggio
      5 -> Ellisse
      6 -> Testo
     */
    public void setActiveInstrument(int activeInstrument) {
        int oldValue = this.activeInstrument;
        this.activeInstrument = activeInstrument;
        mPcs.firePropertyChange(ACTIVE_INSTRUMENT, oldValue, activeInstrument);
    }

    public boolean isFillShapesActive() {
        return fillShapesActive;
    }

    public void setFillShapesActive(boolean fillShapesActive) {
        this.fillShapesActive = fillShapesActive;
        mPcs.firePropertyChange(FILL_SHAPES_ACTIVE, !fillShapesActive, fillShapesActive);
    }

    public List<Integer> getStrokesList() {
        return strokesList;
    }

    public void setStrokesList(List<Integer> strokesList) {
        // la lista spessori non può superare i 5 elementi
        // gli spessori non possono superare i 30px
        List<Integer> oldStrokesList = new ArrayList<>(strokesList);
        if (strokesList.size() != 5) {
            return;
        }
        for (int stroke : strokesList
        ) {
            if (stroke > 30) return;
        }
        this.strokesList = strokesList;
        mPcs.firePropertyChange(STROKES_LIST, oldStrokesList, strokesList);
    }

    public void setStrokesList(int stroke, int index) {
        // vedi annotazioni metodo
        List<Integer> oldStrokesList = new ArrayList<>(strokesList);
        if (index < 0 || index > 4) {
            return;
        }
        if (stroke > 30) {
            return;
        }
        this.strokesList.set(index, stroke);
        mPcs.firePropertyChange(STROKES_LIST, oldStrokesList, strokesList);
    }

    public List<Color> getColorsList() {
        return colorsList;
    }

    public void setColorsList(List<Color> colorsList) {
        // la lista colori non può superare gli 8 elementi
        List<Color> oldColorsList = new ArrayList<>(colorsList);
        if (colorsList.size() != 8) {
            return;
        }
        this.colorsList = colorsList;
        mPcs.firePropertyChange(COLORS_LIST, oldColorsList, colorsList);
    }

    public void setColorsList(Color color, int index) {
        List<Color> oldColorsList = new ArrayList<>(colorsList);
        if (index < 0 || index > 7) {
            return;
        }
        this.colorsList.set(index, color);
        mPcs.firePropertyChange(COLORS_LIST, oldColorsList, colorsList);
    }

    public int getActiveStroke() {
        return activeStroke;
    }

    public void setActiveStroke(int newActiveStroke) {
        int oldActiveStroke = activeStroke;
        if (newActiveStroke < 0 || newActiveStroke > 5) {
            return;
        }
        this.activeStroke = newActiveStroke;
        mPcs.firePropertyChange(ACTIVE_STROKE, oldActiveStroke, activeStroke);
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int newActiveColor) {
        int oldActiveColor = activeColor;
        if (newActiveColor < 0 || newActiveColor > 7) {
            return;
        }
        this.activeColor = newActiveColor;
        mPcs.firePropertyChange(ACTIVE_COLOR, oldActiveColor, activeColor);
    }

    public boolean isDrawToolbarVisibility() {
        return drawToolbarVisibility;
    }

    public void setDrawToolbarVisibility(boolean drawToolbarVisibility) {
        this.drawToolbarVisibility = drawToolbarVisibility;
        mPcs.firePropertyChange(DRAW_TOOLBAR_VISIBILITY, !drawToolbarVisibility, drawToolbarVisibility);
    }

    public boolean isCsToolbarVisibility() {
        return csToolbarVisibility;
    }

    public void setCsToolbarVisibility(boolean csToolbarVisibility) {
        this.csToolbarVisibility = csToolbarVisibility;
        mPcs.firePropertyChange(CS_TOOLBAR_VISIBILITY, !csToolbarVisibility, csToolbarVisibility);
    }

    public boolean isObjectListSidebarVisibility() {
        return objectListSidebarVisibility;
    }

    public void setObjectListSidebarVisibility(boolean objectListSidebarVisibility) {
        this.objectListSidebarVisibility = objectListSidebarVisibility;
        mPcs.firePropertyChange(OBJECTLIST_SIDEBAR_VISIBILITY, !objectListSidebarVisibility, objectListSidebarVisibility);
    }

    public boolean isObjectPropertiesSidebarVisibility() {
        return objectPropertiesSidebarVisibility;
    }

    public void setObjectPropertiesSidebarVisibility(boolean objectPropertiesSidebarVisibility) {
        this.objectPropertiesSidebarVisibility = objectPropertiesSidebarVisibility;
        mPcs.firePropertyChange(
                OBJECTPROPERTIES_SIDEBAR_VISIBILITY, !objectPropertiesSidebarVisibility, objectPropertiesSidebarVisibility);
    }

    public boolean isCanvasGridVisibility() {
        return canvasGridVisibility;
    }

    public void setCanvasGridVisibility(boolean canvasGridVisibility) {
        this.canvasGridVisibility = canvasGridVisibility;
        mPcs.firePropertyChange(
                CANVAS_GRID_VISIBILITY, !canvasGridVisibility, canvasGridVisibility);
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    public void setShapesList(List<Shape> shapesList) {
        List<Shape> oldShapesList = new ArrayList<>(shapesList);
        this.shapesList = shapesList;
        mPcs.firePropertyChange(
                SHAPES_LIST, oldShapesList, shapesList);
    }

    public void setShapesList(Shape shape, int index) {
        List<Shape> oldShapesList = new ArrayList<>(shapesList);
        this.shapesList.set(index, shape);
        mPcs.firePropertyChange(
                SHAPES_LIST, oldShapesList, shapesList);
    }

    public void addShapeToShapesList(Shape shape) {
        List<Shape> oldShapesList = new ArrayList<>(shapesList);
        this.shapesList.add(shape);
        mPcs.firePropertyChange(
                SHAPES_LIST, oldShapesList, shapesList);
    }

    public void removeShapeFromShapesList(Shape shape) {
        List<Shape> oldShapesList = new ArrayList<>(shapesList);
        this.shapesList.remove(shape);
        mPcs.firePropertyChange(
                SHAPES_LIST, oldShapesList, shapesList);
    }

    public void removeShapeFromShapesList(int index) {
        List<Shape> oldShapesList = new ArrayList<>(shapesList);
        this.shapesList.remove(index);
        mPcs.firePropertyChange(
                SHAPES_LIST, oldShapesList, shapesList);
    }

    public void fireShapeListPropChange() {
        mPcs.firePropertyChange(
                SHAPES_LIST, null, shapesList);
    }

    public String getTextContentForInsert() {
        return textContentForInsert;
    }

    public void setTextContentForInsert(String textContentForInsert) {
        // non interessa il propertyChangeSupport essendo una proprietà inizializzata...
        // ...a ogni click del bottone "testo"
        this.textContentForInsert = textContentForInsert;
    }

    public int getTextHeightForInsert() {
        return textHeightForInsert;
    }

    public void setTextHeightForInsert(int textHeightForInsert) {
        // non interessa il propertyChangeSupport essendo una proprietà inizializzata...
        // ...a ogni click del bottone "testo"
        this.textHeightForInsert = textHeightForInsert;
    }

    public boolean isObjectSnappingEnabled() {
        return objectSnappingEnabled;
    }

    public void setObjectSnappingEnabled(boolean objectSnappingEnabled) {
        this.objectSnappingEnabled = objectSnappingEnabled;
        mPcs.firePropertyChange(
                OBJECT_SNAPPING, !objectSnappingEnabled, objectSnappingEnabled
        );
    }

    public boolean isObjectBoundariesEnabled() {
        return objectBoundariesEnabled;
    }

    public void setObjectBoundariesEnabled(boolean objectBoundariesEnabled) {
        this.objectBoundariesEnabled = objectBoundariesEnabled;
        mPcs.firePropertyChange(
                OBJECT_BOUNDARIES, !objectBoundariesEnabled, objectBoundariesEnabled
        );
    }
}