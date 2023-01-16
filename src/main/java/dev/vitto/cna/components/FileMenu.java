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

import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class FileMenu {


    public static JMenu get(char meta_mask) {
        JMenu menu = new JMenu("File (F)");
        JMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription("Menù per la gestione dei progetti");

        menuItem = new JMenuItem("Nuovo disegno", IconLoader.NEW_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Crea un nuovo disegno");
        menu.add(menuItem);

        menuItem = new JMenuItem("Apri disegno", IconLoader.OPEN_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Apri disegno precedentemente salvato");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Salva disegno", IconLoader.SAVE_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Salva il disegno correntemente caricato");
        menu.add(menuItem);

        menuItem = new JMenuItem("Salva disegno con nome", IconLoader.SAVE_AS_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask + InputEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Salva il disegno specificando il nome");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Stampa disegno", IconLoader.PRINT_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, meta_mask ));
        menuItem.getAccessibleContext().setAccessibleDescription("Stampa il disegno");
        menu.add(menuItem);

        return menu;
    }

}
