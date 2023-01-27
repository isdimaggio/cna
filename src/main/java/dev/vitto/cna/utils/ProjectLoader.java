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

import java.io.*;

public class ProjectLoader {

    // metoto di serializzazione
    public static void saveProject(Project project, File file) throws IOException {
        FileOutputStream fs = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fs);
        out.writeObject(project);
        out.close();
        fs.close();
    }

    public static Project loadProject(File file) throws Exception {
        // metodo di deserializzazione
        try {
            FileInputStream fs = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fs);
            Project prj = (Project) in.readObject();
            in.close();
            fs.close();
            return prj;
        } catch (IOException ex) {
            throw new Exception("File read error");
        } catch (ClassNotFoundException ex) {
            throw new Exception("Invalid project type");
        }
    }
}
