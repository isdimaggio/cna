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

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewMenu extends JMenu {

    Project project;
    JMenuItem canvasGridMenuItem;

    public ViewMenu(Project project) {
        super("Visualizza (V)");

        this.project = project;
        setMnemonic(KeyEvent.VK_V);
        getAccessibleContext().setAccessibleDescription("Gestione dell'interfaccia");

        JMenuItem drawToolbarMenuItem = new JCheckBoxMenuItem("Toolbar Disegno");
        drawToolbarMenuItem.setSelected(true);
        drawToolbarMenuItem.addActionListener(e -> project.setDrawToolbarVisibility(drawToolbarMenuItem.isSelected()));
        add(drawToolbarMenuItem);

        JMenuItem csToolbarMenuItem = new JCheckBoxMenuItem("Toolbar Colori e Tratto");
        csToolbarMenuItem.setSelected(true);
        csToolbarMenuItem.addActionListener(e -> project.setCsToolbarVisibility(csToolbarMenuItem.isSelected()));
        add(csToolbarMenuItem);

        JMenuItem objectListSidebar = new JCheckBoxMenuItem("Lista Oggetti");
        objectListSidebar.setSelected(true);
        objectListSidebar.addActionListener(e -> project.setObjectListSidebarVisibility(objectListSidebar.isSelected()));
        add(objectListSidebar);

        JMenuItem propertiesSidebar = new JCheckBoxMenuItem("Proprietà oggetto selezionato");
        propertiesSidebar.setSelected(true);
        propertiesSidebar.addActionListener(e -> project.setObjectPropertiesSidebarVisibility(propertiesSidebar.isSelected()));
        add(propertiesSidebar);

        canvasGridMenuItem = new JCheckBoxMenuItem("Griglia canvas");
        canvasGridMenuItem.setSelected(true);
        canvasGridMenuItem.addActionListener(e -> setCanvasGridVisibility(canvasGridMenuItem.isSelected(), true));
        add(canvasGridMenuItem);
    }

    public void setCanvasGridVisibility(boolean status, boolean fireUpdate) {
        canvasGridMenuItem.setSelected(status);
        if (fireUpdate) {
            project.setCanvasGridVisibility(status);
        }
    }

}
