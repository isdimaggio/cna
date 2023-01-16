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

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewMenu {

    public static JMenu get() {
        JMenu menu = new JMenu("Visualizza (V)");
        JCheckBoxMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription("Gestione dell'interfaccia");

        menuItem = new JCheckBoxMenuItem("Toolbar Disegno");
        menuItem.setSelected(true);
        menu.add(menuItem);

        menuItem = new JCheckBoxMenuItem("Toolbar Colori e Tratto");
        menuItem.setSelected(true);
        menu.add(menuItem);


        return menu;
    }

}
