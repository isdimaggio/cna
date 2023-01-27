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
import dev.vitto.cna.utils.ProjectLoader;
import dev.vitto.cna.windows.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.File;

public class FileMenu extends JMenu {

    Project project;
    MainWindow parent;
    char meta_mask;

    public FileMenu(Project project, char meta_mask, MainWindow parent) {
        super("File (F)");

        this.project = project;
        this.parent = parent;
        this.meta_mask = meta_mask;

        setMnemonic(KeyEvent.VK_F);
        getAccessibleContext().setAccessibleDescription("Menù per la gestione dei progetti");

        JMenuItem newDrawingMenuItem = new JMenuItem("Nuovo disegno", IconLoader.NEW_ICON);
        newDrawingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, meta_mask));
        newDrawingMenuItem.getAccessibleContext().setAccessibleDescription("Crea un nuovo disegno");
        newDrawingMenuItem.addActionListener(e -> this.project.clearProject());
        add(newDrawingMenuItem);

        JMenuItem openDrawingMenuItem = new JMenuItem("Apri disegno", IconLoader.OPEN_ICON);
        openDrawingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, meta_mask));
        openDrawingMenuItem.getAccessibleContext().setAccessibleDescription("Apri disegno precedentemente salvato");
        openDrawingMenuItem.addActionListener(e -> {
            // apre il file chooser
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                // prova a deserializzare l'oggetto
                try {
                    project.replaceSelf(ProjectLoader.loadProject(selectedFile));
                    parent.setCurrentFileName(selectedFile.getAbsolutePath());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Impossibile caricare il progetto: " + ex.getMessage(),
                            "Errore",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(openDrawingMenuItem);

        addSeparator();

        JMenuItem saveDrawingMenuItem = new JMenuItem("Salva disegno", IconLoader.SAVE_ICON);
        saveDrawingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask));
        saveDrawingMenuItem.getAccessibleContext().setAccessibleDescription("Salva il disegno correntemente caricato");
        saveDrawingMenuItem.addActionListener(e -> {
            // controlla se il file esiste
            File f = new File(parent.getCurrentFileName());
            if (f.exists() && !f.isDirectory()) {
                // sovrascrivi quello esistente
                try {
                    ProjectLoader.saveProject(project, f);
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(this,
                            "Impossibile salvare il progetto: " + e2.getLocalizedMessage(),
                            "Salvataggio progetto",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // il file non esiste a prescindere, mostra il dialog
                saveFileDialog();
            }
        });
        add(saveDrawingMenuItem);

        JMenuItem saveNameDrawingMenuItem = new JMenuItem("Salva disegno con nome", IconLoader.SAVE_AS_ICON);
        saveNameDrawingMenuItem.setAccelerator(
                KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask + InputEvent.SHIFT_DOWN_MASK));
        saveNameDrawingMenuItem.getAccessibleContext().setAccessibleDescription(
                "Salva il disegno specificando il nome");
        saveNameDrawingMenuItem.addActionListener(e -> saveFileDialog());
        add(saveNameDrawingMenuItem);

        addSeparator();

        JMenuItem printDrawingMenuItem = new JMenuItem("Stampa disegno", IconLoader.PRINT_ICON);
        printDrawingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, meta_mask));
        printDrawingMenuItem.getAccessibleContext().setAccessibleDescription("Stampa il disegno");
        printDrawingMenuItem.addActionListener(e -> {
            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setPrintable((graphics, pageFormat, pageIndex) -> {
                if (pageIndex != 0) {
                    return Printable.NO_SUCH_PAGE;
                }
                BufferedImage bi = parent.exportCanvasToImage();
                graphics.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), null);
                return Printable.PAGE_EXISTS;
            });
            try {
                printJob.printDialog();
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this,
                        "Impossibile stampare il progetto: " + e1.getLocalizedMessage(),
                        "Stampa progetto",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        add(printDrawingMenuItem);

        JMenuItem exportDrawingMenuItem = new JMenuItem("Esporta disegno in PNG", IconLoader.EXPORT_ICON);
        exportDrawingMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, meta_mask));
        exportDrawingMenuItem.getAccessibleContext().setAccessibleDescription("Esporta il disegno in PNG");
        exportDrawingMenuItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Esportazione in PNG del progetto");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Immagine .png", "png");
            fileChooser.setFileFilter(filter);
            fileChooser.setSelectedFile(new File(Misc.elaborateFileName(project.getProjectName()) + ".png"));

            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                String fname = fileToSave.getAbsolutePath();
                if (!fname.endsWith(".png")) {
                    fileToSave = new File(fname + ".png");
                }
                try {
                    ImageIO.write(parent.exportCanvasToImage(), "png", fileToSave);
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(this,
                            "Impossibile esportare il progetto: " + e2.getLocalizedMessage(),
                            "Esportazione progetto",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(exportDrawingMenuItem);
    }

    private void saveFileDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvataggio progetto");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Progetto CNA (.cna)", "cnaa");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File(Misc.elaborateFileName(project.getProjectName()) + ".cna"));

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String fname = fileToSave.getAbsolutePath();
            if (!fname.endsWith(".cna")) {
                fileToSave = new File(fname + ".cna");
            }
            try {
                ProjectLoader.saveProject(project, fileToSave);
                parent.setCurrentFileName(fileToSave.getAbsolutePath());
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(this,
                        "Impossibile salvare il progetto: " + e2.getLocalizedMessage(),
                        "Salvataggio progetto",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
