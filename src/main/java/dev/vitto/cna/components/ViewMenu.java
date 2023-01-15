package dev.vitto.cna.components;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewMenu {

    public static JMenu get() {
        JMenu menu = new JMenu("Visualizza (V)");
        JCheckBoxMenuItem menuItem;
        menu.setMnemonic(KeyEvent.VK_V);
        menu.getAccessibleContext().setAccessibleDescription("Gestione dell'interfaccia");

        menuItem = new JCheckBoxMenuItem("Toolbar strumenti");
        menuItem.setSelected(true);
        menu.add(menuItem);

        menuItem = new JCheckBoxMenuItem("Toolbar colori");
        menuItem.setSelected(true);
        menu.add(menuItem);

        menuItem = new JCheckBoxMenuItem("Toolbar tratti");
        menuItem.setSelected(true);
        menu.add(menuItem);

        return menu;
    }

}
