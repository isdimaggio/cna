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

    public static ImageIcon EXPORT_ICON;
    public static ImageIcon GRID_OFF_CMD;

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
    public static ImageIcon GRID_ON_CMD;

    public static ImageIcon GRID_TEXTURE;
    public static ImageIcon DELETE_ICON;
    public static ImageIcon SETTINGS_ICON;
    public static ImageIcon CENTER_RAD_CMD;
    public static ImageIcon ELLIPSE_CMD;
    public static ImageIcon FILL_OFF_CMD;
    public static ImageIcon FILL_ON_CMD;
    public static ImageIcon RENAME_ICON;

    static {
        try {
            EXPORT_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/export-icon.png")));
        } catch (Exception e) {
            EXPORT_ICON = new ImageIcon();
        }
    }

    static {
        try {
            PROGRAM_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/program.png")));
        } catch (Exception e) {
            PROGRAM_ICON = new ImageIcon();
        }
    }

    static {
        try {
            RENAME_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/rename-icon.png")));
        } catch (Exception e) {
            RENAME_ICON = new ImageIcon();
        }
    }

    public static ImageIcon HAND_CMD;
    public static ImageIcon LINE_CMD;
    public static ImageIcon POINT_CMD;
    public static ImageIcon RECT_CMD;
    public static ImageIcon TEXT_CMD;
    public static ImageIcon STROKE_1;
    public static ImageIcon STROKE_2;
    public static ImageIcon STROKE_3;
    public static ImageIcon STROKE_4;
    public static ImageIcon STROKE_5;

    static {
        try {
            GRID_TEXTURE = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/grid-texture.png")));
        } catch (Exception e) {
            GRID_TEXTURE = new ImageIcon();
        }
    }

    static {
        try {
            DELETE_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/delete-icon.png")));
        } catch (Exception e) {
            DELETE_ICON = new ImageIcon();
        }
    }

    static {
        try {
            SETTINGS_ICON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/settings-icon.png")));
        } catch (Exception e) {
            SETTINGS_ICON = new ImageIcon();
        }
    }

    static {
        try {
            CENTER_RAD_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/center-rad-cmd.bmp")));
        } catch (Exception e) {
            CENTER_RAD_CMD = new ImageIcon();
        }
    }

    static {
        try {
            ELLIPSE_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/ellipse-cmd.bmp")));
        } catch (Exception e) {
            ELLIPSE_CMD = new ImageIcon();
        }
    }

    static {
        try {
            FILL_OFF_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/fill-off.bmp")));
        } catch (Exception e) {
            FILL_OFF_CMD = new ImageIcon();
        }
    }

    static {
        try {
            FILL_ON_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/fill-on.bmp")));
        } catch (Exception e) {
            FILL_ON_CMD = new ImageIcon();
        }
    }

    static {
        try {
            GRID_OFF_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/grid-off.bmp")));
        } catch (Exception e) {
            GRID_OFF_CMD = new ImageIcon();
        }
    }

    static {
        try {
            GRID_ON_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/grid-on.bmp")));
        } catch (Exception e) {
            GRID_ON_CMD = new ImageIcon();
        }
    }

    static {
        try {
            HAND_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/hand-cmd.bmp")));
        } catch (Exception e) {
            HAND_CMD = new ImageIcon();
        }
    }

    static {
        try {
            LINE_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/line-cmd.bmp")));
        } catch (Exception e) {
            LINE_CMD = new ImageIcon();
        }
    }

    static {
        try {
            POINT_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/point-cmd.bmp")));
        } catch (Exception e) {
            POINT_CMD = new ImageIcon();
        }
    }

    static {
        try {
            RECT_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/rect-cmd.bmp")));
        } catch (Exception e) {
            RECT_CMD = new ImageIcon();
        }
    }

    static {
        try {
            TEXT_CMD = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/text-cmd.bmp")));
        } catch (Exception e) {
            TEXT_CMD = new ImageIcon();
        }
    }

    static {
        try {
            STROKE_1 = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/strokes/stroke1.bmp")));
        } catch (Exception e) {
            STROKE_1 = new ImageIcon();
        }
    }

    static {
        try {
            STROKE_2 = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/strokes/stroke2.bmp")));
        } catch (Exception e) {
            STROKE_2 = new ImageIcon();
        }
    }

    static {
        try {
            STROKE_3 = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/strokes/stroke3.bmp")));
        } catch (Exception e) {
            STROKE_3 = new ImageIcon();
        }
    }

    static {
        try {
            STROKE_4 = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/strokes/stroke4.bmp")));
        } catch (Exception e) {
            STROKE_4 = new ImageIcon();
        }
    }

    static {
        try {
            STROKE_5 = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/strokes/stroke5.bmp")));
        } catch (Exception e) {
            STROKE_5 = new ImageIcon();
        }
    }

    public static ImageIcon OSNAP_ON;
    public static ImageIcon OSNAP_OFF;
    public static ImageIcon BOUNDARY_ON;
    public static ImageIcon BOUNDARY_OFF;

    static {
        try {
            OSNAP_ON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/osnap-on.bmp")));
        } catch (Exception e) {
            OSNAP_ON = new ImageIcon();
        }
    }

    static {
        try {
            OSNAP_OFF = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/osnap-off.bmp")));
        } catch (Exception e) {
            OSNAP_OFF = new ImageIcon();
        }
    }

    static {
        try {
            BOUNDARY_ON = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/boundary-on.bmp")));
        } catch (Exception e) {
            BOUNDARY_ON = new ImageIcon();
        }
    }

    static {
        try {
            BOUNDARY_OFF = new ImageIcon(ImageIO.read(getFileFromResourceAsStream("images/commands/boundary-off.bmp")));
        } catch (Exception e) {
            BOUNDARY_OFF = new ImageIcon();
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

    public static ImageIcon getStrokeIcon(int index) {
        if (index < 0 || index > 4) return new ImageIcon();
        switch (index) {
            case 0 -> {
                return STROKE_1;
            }
            case 1 -> {
                return STROKE_2;
            }
            case 2 -> {
                return STROKE_3;
            }
            case 3 -> {
                return STROKE_4;
            }
            case 4 -> {
                return STROKE_5;
            }
        }
        return new ImageIcon();
    }
}