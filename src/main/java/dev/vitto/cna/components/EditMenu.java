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
import dev.vitto.cna.windows.MainWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditMenu extends JMenu {

    Project project;
    MainWindow parent;
    char meta_mask;

    public EditMenu(Project project, char meta_mask, MainWindow parent) {
        super("Modifica (E)");

        this.project = project;
        this.parent = parent;
        this.meta_mask = meta_mask;

        setMnemonic(KeyEvent.VK_E);
        getAccessibleContext().setAccessibleDescription("Menù per la modifica degli elementi nel canvas");

        JMenuItem undoMenuItem = new JMenuItem("Annulla", IconLoader.UNDO_ICON);
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, meta_mask));
        undoMenuItem.getAccessibleContext().setAccessibleDescription("Annulla l'ultima modifica fatta");
        undoMenuItem.addActionListener(e -> {
            try {
                project.removeShapeFromShapesList(project.getShapesList().size() - 1);
            } catch (Exception ignored) {
            }
        });
        add(undoMenuItem);

        /*
            TODO: implementare ripeti

            JMenuItem redoMenuItem = new JMenuItem("Ripeti", IconLoader.REDO_ICON);
            redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, meta_mask + KeyEvent.SHIFT_DOWN_MASK));
            redoMenuItem.getAccessibleContext().setAccessibleDescription("Ripeti l'ultima modifica annullata");
            redoMenuItem.addActionListener(e -> project.redo());
            add(redoMenuItem);
        */

        addSeparator();

        JMenuItem selectAllMenuItem = new JMenuItem("Seleziona tutto", IconLoader.SELECT_ALL_ICON);
        selectAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, meta_mask));
        selectAllMenuItem.getAccessibleContext().setAccessibleDescription("Seleziona tutti gli elementi del disegno");
        selectAllMenuItem.addActionListener(e -> parent.selectAll());
        add(selectAllMenuItem);

        JMenuItem renameMenuItem = new JMenuItem("Rinomina oggetto", IconLoader.STROKE_ICON);
        renameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, meta_mask));
        renameMenuItem.getAccessibleContext().setAccessibleDescription("Rinomina l'elemento selezionato");
        //-1 per segnalare di prendere dal parent
        renameMenuItem.addActionListener(e -> parent.renameObjects(new int[]{-1}));
        add(renameMenuItem);

        JMenuItem deleteMenuItem = new JMenuItem("Cancella elementi", IconLoader.DELETE_ICON);
        if (meta_mask == KeyEvent.META_DOWN_MASK) {
            deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, meta_mask));
        } else {
            deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, meta_mask));
        }
        deleteMenuItem.getAccessibleContext().setAccessibleDescription("Cancella gli elementi selezionati");
        deleteMenuItem.addActionListener(e -> parent.deleteSelectedObjects());
        add(deleteMenuItem);
    }

}
