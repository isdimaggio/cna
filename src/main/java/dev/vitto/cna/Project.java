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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Project {
    public static final String ACTIVE_INSTRUMENT = "activeInstrument";
    public static final String PROJECT_NAME = "projectName";
    public static final String FILL_SHAPES_ACTIVE = "fillShapesActive";
    public static final String DEFAULT_PROJECT_NAME = "Progetto senza titolo";
    private final PropertyChangeSupport mPcs = new PropertyChangeSupport(this);
    private String projectName = DEFAULT_PROJECT_NAME;
    private int activeInstrument = 0;

    private boolean fillShapesActive = false;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        mPcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        mPcs.removePropertyChangeListener(listener);
    }

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
}