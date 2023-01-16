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
import java.awt.event.KeyEvent;

public class OptionsMenu {

    public static JMenu get(char meta_mask) {
        JMenu menu = new JMenu("Opzioni (S)");
        JMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription("Opzioni del CAD");

        menuItem = new JMenuItem("Opzioni colori", IconLoader.STROKE_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, meta_mask + KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica colori");
        menu.add(menuItem);

        menuItem = new JMenuItem("Opzioni tratti", IconLoader.COLOR_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask + KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica tratti");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Impostazioni progetto", IconLoader.SETTINGS_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Impostazioni del progetto");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Informazioni sul software", IconLoader.INFO_ICON);
        menuItem.getAccessibleContext().setAccessibleDescription("Credits");
        menu.add(menuItem);

        menuItem = new JMenuItem("Repository GitHub", IconLoader.GH_ICON);
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica tratti");
        menu.add(menuItem);

        return menu;
    }
}
