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
import dev.vitto.cna.utils.Misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class OptionsMenu extends JMenu {

    public OptionsMenu(Project project, char meta_mask, JFrame parent) {
        super("Opzioni Progetto (S)");
        setMnemonic(KeyEvent.VK_S);
        getAccessibleContext().setAccessibleDescription("Opzioni del CAD");

        JMenuItem colorOptionsMenuItem = new JMenuItem("Opzioni colori", IconLoader.COLOR_ICON);
        colorOptionsMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, meta_mask + KeyEvent.ALT_DOWN_MASK));
        colorOptionsMenuItem.getAccessibleContext().setAccessibleDescription("Modifica colori");
        colorOptionsMenuItem.addActionListener(e -> {
            // crea lista delle scelte
            Object[] choices = {1, 2, 3, 4, 5, 6, 7, 8};
            // invoca dialog di selezione
            Integer i = (Integer) JOptionPane.showInputDialog(
                    parent,
                    "Seleziona lo slot colore da modificare:",
                    "Modifica dello slot colore",
                    JOptionPane.PLAIN_MESSAGE,
                    IconLoader.COLOR_ICON,
                    choices,
                    1);

            if (i != null) {
                i = i - 1;
                // apri dialog di selezione colore
                Color newColor = JColorChooser.showDialog(
                        null, "Modifica dello slot colore " + i, project.getColorsList().get(i));
                if (newColor != null) {
                    project.setColorsList(newColor, i);
                }
            }
        });
        add(colorOptionsMenuItem);

        JMenuItem strokeOptionsMenuItem = new JMenuItem("Opzioni tratti", IconLoader.STROKE_ICON);
        strokeOptionsMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask + KeyEvent.ALT_DOWN_MASK));
        strokeOptionsMenuItem.getAccessibleContext().setAccessibleDescription("Modifica tratti");
        strokeOptionsMenuItem.addActionListener(e -> {
            Object[] choices = {1, 2, 3, 4, 5};
            Integer i = (Integer) JOptionPane.showInputDialog(
                    parent,
                    "Seleziona lo slot spessore da modificare:",
                    "Modifica dello slot spessore",
                    JOptionPane.PLAIN_MESSAGE,
                    IconLoader.STROKE_ICON,
                    choices,
                    1);

            if (i != null) {
                i = i - 1;
                choices = new Object[30];
                for (int j = 1; j < 31; j++) {
                    choices[j - 1] = j;
                }
                Integer newStroke = (Integer) JOptionPane.showInputDialog(
                        parent,
                        "Inserisci un valore di spessore che vada da 1 a 30px",
                        "Modifica dello slot spessore",
                        JOptionPane.PLAIN_MESSAGE,
                        IconLoader.STROKE_ICON,
                        choices,
                        1);
                if (newStroke != null) {
                    project.setStrokesList(newStroke, i);
                }
            }
        });
        add(strokeOptionsMenuItem);

        addSeparator();

        JMenuItem renameProjectMenuItem = new JMenuItem("Rinomina progetto", IconLoader.SETTINGS_ICON);
        renameProjectMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, meta_mask));
        renameProjectMenuItem.getAccessibleContext().setAccessibleDescription("Rinomina il progetto");
        renameProjectMenuItem.addActionListener(e -> {
            String s = (String) JOptionPane.showInputDialog(
                    parent,
                    "Inserisci il nuovo nome del progetto:",
                    "Rinomina progetto",
                    JOptionPane.PLAIN_MESSAGE,
                    IconLoader.SETTINGS_ICON,
                    null,
                    project.getProjectName());

            if (s != null) {
                if (s.length() > 1) {
                    project.setProjectName(s);
                } else {
                    JOptionPane.showMessageDialog(parent,
                            "Impossibile cambiare il nome del progetto",
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(renameProjectMenuItem);

        addSeparator();

        JMenuItem softwareInfoMenuItem = new JMenuItem("Informazioni sul software", IconLoader.INFO_ICON);
        softwareInfoMenuItem.getAccessibleContext().setAccessibleDescription("Credits");
        softwareInfoMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(parent,
                Misc.SOFTWARE_INFO,
                "Informazioni sul software",
                JOptionPane.INFORMATION_MESSAGE,
                IconLoader.PROGRAM_ICON));

        add(softwareInfoMenuItem);

        JMenuItem gitHubMenuItem = new JMenuItem("Repository GitHub", IconLoader.GH_ICON);
        gitHubMenuItem.getAccessibleContext().setAccessibleDescription("Repository GitHub");
        gitHubMenuItem.addActionListener(e -> Misc.launchBrowser("https://github.com/isdimaggio/cna"));
        add(gitHubMenuItem);
    }
}
