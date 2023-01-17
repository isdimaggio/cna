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

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Misc {

    public static String SOFTWARE_INFO = """
            CNA, acronimo ricorsivo di (CNA's not AutoCAD) è un programma di CAD libero \s
            (Computer Assisted Drawing) realizzato a scopo didattico in Java, per apprendere\s
            il funzionamento del toolkit grafico AWT+Swing.\s
            Hanno partecipato al progetto: Vittorio Lo Mele, Adele Redina, Gianluca Santoro.
            """;

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

    public static String elaborateFileName(String projectName) {
        return projectName
                .toLowerCase()                                                  // tutto minuscolo
                .replaceAll("\\s+", "_")                     // cambia gli spazi in underscore
                .replaceAll("[^a-zA-Z0-9_-]", "");           // ripulisce tutto il resto
    }

}
