package dev.vitto.cna.components;

import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class EditMenu {

    public static JMenu get(char meta_mask) {
        JMenu menu = new JMenu("Modifica (E)");
        JMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext().setAccessibleDescription("Menù per la modifica degli elementi nel canvas");

        menuItem = new JMenuItem("Annulla", IconLoader.UNDO_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Annulla l'ultima modifica fatta");
        menu.add(menuItem);

        menuItem = new JMenuItem("Ripeti", IconLoader.REDO_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, meta_mask + KeyEvent.SHIFT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Ripeti l'ultima modifica annullata");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Taglia", IconLoader.CUT_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Taglia l'elemento selezionato");
        menu.add(menuItem);

        menuItem = new JMenuItem("Copia", IconLoader.COPY_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Copia l'elemento selezionato");
        menu.add(menuItem);

        menuItem = new JMenuItem("Incolla", IconLoader.PASTE_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Incolla l'elemento selezionato");
        menu.add(menuItem);

        menuItem = new JMenuItem("Seleziona tutto", IconLoader.SELECT_ALL_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, meta_mask));
        menuItem.getAccessibleContext().setAccessibleDescription("Seleziona tutti gli elementi del disegno");
        menu.add(menuItem);

        return menu;
    }

}
