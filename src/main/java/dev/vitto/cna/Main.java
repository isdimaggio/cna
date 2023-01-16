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

package dev.vitto.cna;

import dev.vitto.cna.windows.MainWindow;

import java.util.Locale;

public class Main {

    public static Project currentProject;

    public static void main(String[] args) {

        boolean isMacOS = false;

        // controlliamo se il sistema operativo è MacOS per modificare le shortcut che usano
        // CTRL in shortcut che usano CMD (tasto alternativo a CTRL per i mac)
        String detectedOS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((detectedOS.contains("mac")) || (detectedOS.contains("darwin"))) {
            isMacOS = true;
        }

        currentProject = new Project();
        new MainWindow(isMacOS, currentProject);

    }
}