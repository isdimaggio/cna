package dev.vitto.cna.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.InputStream;

public class IconLoader {

    public static ImageIcon NEW_ICON;

    static {
        try {
            NEW_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/new-icon.png")));
        } catch (Exception e) {
            NEW_ICON = new ImageIcon();
        }
    }

    public static ImageIcon SAVE_ICON;

    static {
        try {
            SAVE_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/save-icon.png")));
        } catch (Exception e) {
            SAVE_ICON = new ImageIcon();
        }
    }

    public static ImageIcon SAVE_AS_ICON;

    static {
        try {
            SAVE_AS_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/save-as-icon.png")));
        } catch (Exception e) {
            SAVE_AS_ICON = new ImageIcon();
        }
    }

    public static ImageIcon OPEN_ICON;

    static {
        try {
            OPEN_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/open-icon.png")));
        } catch (Exception e) {
            OPEN_ICON = new ImageIcon();
        }
    }

    public static ImageIcon PRINT_ICON;

    static {
        try {
            PRINT_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/print-icon.png")));
        } catch (Exception e) {
            PRINT_ICON = new ImageIcon();
        }
    }

    public static ImageIcon UNDO_ICON;

    static {
        try {
            UNDO_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/undo-icon.png")));
        } catch (Exception e) {
            UNDO_ICON = new ImageIcon();
        }
    }

    public static ImageIcon REDO_ICON;

    static {
        try {
            REDO_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/redo-icon.png")));
        } catch (Exception e) {
            REDO_ICON = new ImageIcon();
        }
    }

    public static ImageIcon CUT_ICON;

    static {
        try {
            CUT_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/cut-icon.png")));
        } catch (Exception e) {
            CUT_ICON = new ImageIcon();
        }
    }

    public static ImageIcon COPY_ICON;

    static {
        try {
            COPY_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/copy-icon.png")));
        } catch (Exception e) {
            COPY_ICON = new ImageIcon();
        }
    }

    public static ImageIcon PASTE_ICON;

    static {
        try {
            PASTE_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/paste-icon.png")));
        } catch (Exception e) {
            PASTE_ICON = new ImageIcon();
        }
    }

    public static ImageIcon SELECT_ALL_ICON;

    static {
        try {
            SELECT_ALL_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/select-all-icon.png")));
        } catch (Exception e) {
            SELECT_ALL_ICON = new ImageIcon();
        }
    }

    public static ImageIcon STROKE_ICON;

    static {
        try {
            STROKE_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/stroke-icon.png")));
        } catch (Exception e) {
            STROKE_ICON = new ImageIcon();
        }
    }

    public static ImageIcon COLOR_ICON;

    static {
        try {
            COLOR_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/color-icon.png")));
        } catch (Exception e) {
            COLOR_ICON = new ImageIcon();
        }
    }

    public static ImageIcon INFO_ICON;

    static {
        try {
            INFO_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/info-icon.png")));
        } catch (Exception e) {
            INFO_ICON = new ImageIcon();
        }
    }

    public static ImageIcon GH_ICON;

    static {
        try {
            GH_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/git-small-icon.png")));
        } catch (Exception e) {
            GH_ICON = new ImageIcon();
        }
    }

    public static ImageIcon PROGRAM_ICON;

    static {
        try {
            PROGRAM_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/program-icon.png")));
        } catch (Exception e) {
            PROGRAM_ICON = new ImageIcon();
        }
    }

    private static InputStream getFileFromResourceAsStream(String fileName) {

        ClassLoader classLoader = IconLoader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }
}