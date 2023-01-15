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
