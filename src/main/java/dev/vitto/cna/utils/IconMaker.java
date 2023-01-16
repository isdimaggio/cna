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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IconMaker {

    public static ImageIcon colorIcon32(Integer number, Color color) {
        int width = 32;
        int height = 32;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // imposta sfondo
        g2d.setColor(color);
        g2d.fillRect(0, 0, width, height);

        // imposta quadrato
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 19, 23);
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, 16, 20);

        // Scrivi il colore selezionato
        Font f = new Font("Impact", Font.PLAIN, 16);
        g2d.setFont(f);
        g2d.setColor(Color.black);
        g2d.drawString(number.toString(), 3, 16);

        g2d.dispose();

        return new ImageIcon(bufferedImage);
    }

    public static int colorPixelToGrayscale(Color color) {
        return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    }

    public static Color invertColor(Color color) {
        return new Color(255 - color.getRed(), 255 - color.getGreen(), 255 - color.getBlue());
    }

    public static String colorToHEXCode(Color color) {
        return String.format(
                "#%02X%02X%02X",
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }

}
