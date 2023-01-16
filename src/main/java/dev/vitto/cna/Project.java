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

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Project {
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
    public static final String DEFAULT_PROJECT_NAME = "Progetto senza titolo";
    private final PropertyChangeSupport mPcs = new PropertyChangeSupport(this);

    // ********************************* //

    private String projectName = DEFAULT_PROJECT_NAME;
    private int activeInstrument = 0;
    private boolean fillShapesActive = false;

    private List<Integer> strokesList = new ArrayList<>(5);
    private List<Color> colorsList = new ArrayList<>(8);

    private int activeStroke = 0;
    private int activeColor = 0;

    private boolean drawToolbarVisibility = true;
    private boolean csToolbarVisibility = true; // cs: color and stroke

    private boolean objectListSidebarVisibility = true;
    private boolean objectPropertiesSidebarVisibility = true;

    // ********************************* //

    public Project() {
        // defaut strokes
        strokesList.add(3);
        strokesList.add(5);
        strokesList.add(7);
        strokesList.add(9);
        strokesList.add(11);

        // default colors
        colorsList.add(Color.red);
        colorsList.add(Color.green);
        colorsList.add(Color.blue);
        colorsList.add(Color.cyan);
        colorsList.add(Color.yellow);
        colorsList.add(Color.magenta);
        colorsList.add(Color.black);
        colorsList.add(Color.white);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }

    // ********************************* //

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
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
      0 -> Nessun Comando
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
        List<Integer> oldStrokesList = this.strokesList;
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
        List<Integer> oldStrokesList = this.strokesList;
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
        List<Color> oldColorsList = this.colorsList;
        if (colorsList.size() != 8) {
            return;
        }
        this.colorsList = colorsList;
        mPcs.firePropertyChange(COLORS_LIST, oldColorsList, colorsList);
    }

    public void setColorsList(Color color, int index) {
        List<Color> oldColorsList = this.colorsList;
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
}