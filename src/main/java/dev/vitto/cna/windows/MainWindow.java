package dev.vitto.cna.windows;

import dev.vitto.cna.components.EditMenu;
import dev.vitto.cna.components.FileMenu;
import dev.vitto.cna.components.OptionsMenu;
import dev.vitto.cna.components.ViewMenu;
import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.event.InputEvent;
import java.io.IOException;

public class MainWindow extends JFrame {

    public MainWindow(boolean isMacOS) throws IOException {

        super("CNA (CNA's Not AutoCAD) - Nuovo progetto");

        char META_CTRL_MASK;

        if(isMacOS){
            META_CTRL_MASK = InputEvent.META_DOWN_MASK;
        }else{
            META_CTRL_MASK = InputEvent.CTRL_DOWN_MASK;
        }

        setSize(800, 600);
        setLocation(200, 200);
        setIconImage(IconLoader.PROGRAM_ICON.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setResizable(true);
        setVisible(true);

        //aggiungi tutti i menu nella barra superiore
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(FileMenu.get(META_CTRL_MASK));
        menuBar.add(EditMenu.get(META_CTRL_MASK));
        menuBar.add(ViewMenu.get());
        menuBar.add(OptionsMenu.get(META_CTRL_MASK));
        setJMenuBar(menuBar);

        //repaint della finestra
        validate();
        repaint();
    }

}
