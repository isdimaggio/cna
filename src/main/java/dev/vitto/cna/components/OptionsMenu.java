package dev.vitto.cna.components;

import dev.vitto.cna.utils.IconLoader;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class OptionsMenu {

    public static JMenu get(char meta_mask) {
        JMenu menu = new JMenu("Opzioni (S)");
        JMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription("Opzioni del CAD");

        menuItem = new JMenuItem("Opzioni colori", IconLoader.STROKE_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, meta_mask + KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica colori");
        menu.add(menuItem);

        menuItem = new JMenuItem("Opzioni tratti", IconLoader.COLOR_ICON);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, meta_mask + KeyEvent.ALT_DOWN_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica tratti");
        menu.add(menuItem);

        menu.addSeparator();

        menuItem = new JMenuItem("Informazioni sul software", IconLoader.INFO_ICON);
        menuItem.getAccessibleContext().setAccessibleDescription("Credits");
        menu.add(menuItem);

        menuItem = new JMenuItem("Repository GitHub", IconLoader.GH_ICON);
        menuItem.getAccessibleContext().setAccessibleDescription("Modifica tratti");
        menu.add(menuItem);

        return menu;
    }
}
