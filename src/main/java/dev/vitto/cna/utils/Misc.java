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

package dev.vitto.cna.utils;

import dev.vitto.cna.Project;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Misc {

    private static int objCounter = 1;

    public static String SOFTWARE_INFO = """
            CNA, acronimo ricorsivo di (CNA's not AutoCAD) è un programma di CAD libero \s
            (Computer Assisted Drawing) realizzato a scopo didattico in Java, per apprendere\s
            il funzionamento del toolkit grafico AWT+Swing.\s
            Hanno partecipato al progetto: Vittorio Lo Mele, Adele Redina, Gianluca Santoro.
            """;

    // lancio cross-platform del browser web dato un URL
    public static void launchBrowser(String url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            String os = System.getProperty("os.name").toLowerCase();
            try {
                if (os.contains("win")) {
                    // windows NT >
                    runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (os.contains("mac") || os.contains("darwin")) {
                    runtime.exec("open " + url);
                } else {
                    runtime.exec("xdg-open " + url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // dal nome del progetto ricava il nome del file di esportazione/salvataggio
    public static String elaborateFileName(String projectName) {
        return projectName
                .toLowerCase()                                                  // tutto minuscolo
                .replaceAll("\\s+", "_")                     // cambia gli spazi in underscore
                .replaceAll("[^a-zA-Z0-9_-]", "");           // ripulisce tutto il resto
    }

    // questa funzione serve a generare un nome diverso di default per tutti i nuovi numeri aggiunti
    public static int getNextObjNum() {
        return objCounter++;
    }

    // questa funzione mostra il dialog per la scelta del contenuto e altezza testo
    public static boolean textDataInsertionDialog(JFrame parent, Project project) {
        String sHeight = (String) JOptionPane.showInputDialog(
                parent,
                "Inserisci l'altezza del testo in pixel:",
                "Inserisci testo",
                JOptionPane.PLAIN_MESSAGE,
                IconLoader.STROKE_ICON,
                null,
                project.getTextHeightForInsert());

        if (sHeight != null) {
            try {
                int height = Integer.parseInt(sHeight);
                String s = (String) JOptionPane.showInputDialog(
                        parent,
                        "Inserisci il contenuto del testo:",
                        "Inserisci testo",
                        JOptionPane.PLAIN_MESSAGE,
                        IconLoader.SETTINGS_ICON,
                        null,
                        project.getTextContentForInsert());

                if (s != null) {
                    if (s.length() > 1) {
                        project.setTextContentForInsert(s);
                        project.setTextHeightForInsert(height);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(
                                parent,
                                "Valore non valido!",
                                "Inserisci testo",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
            } catch (NumberFormatException ignored) {
                JOptionPane.showMessageDialog(
                        parent,
                        "Valore non valido!",
                        "Inserisci testo",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return false;
    }

    // calcola il multiplo di 10 più vicino per ancorare tutti i punti disegnati alla griglia 10x10px
    public static int objectSnapCalc(int coord, boolean round) {
        if (round) {
            // calcolo del multiplo più piccolo
            int a = (coord / 10) * 10;

            // calcolo del multiplo più grande
            int b = a + 10;

            // ritorna il più vicino dei due
            return (coord - a > b - coord) ? b : a;
        } else {
            return coord;
        }
    }

}
