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

import dev.vitto.cna.utils.IconMaker;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PropertyPane extends JPanel {

    JTextArea content;

    public PropertyPane() {
        setLayout(new BorderLayout());
        content = new JTextArea("Nessun oggetto selezionato...");
        content.setFont(new Font("Lucida Console", Font.PLAIN, 13));
        content.setEditable(false);
        add(content);
    }

    public void viewObjectProperties(List<dev.vitto.cna.objects.Shape> shapeList, int[] indexes) {
        StringBuilder data = new StringBuilder();
        for (int index : indexes
        ) {
            dev.vitto.cna.objects.Shape shape = shapeList.get(index);
            data.append(index)
                    .append(": ")
                    .append(shape.getName())
                    .append('\n');

            data.append("    ")
                    .append("rootX:  ")
                    .append(shape.getRootX())
                    .append('\n');

            data.append("    ")
                    .append("rootY:  ")
                    .append(shape.getRootY())
                    .append('\n');

            data.append("    ")
                    .append("color:   ")
                    .append(IconMaker.colorToHEXCode(shape.getColor()))
                    .append('\n');

            data.append("    ")
                    .append("stroke: ")
                    .append(shape.getStroke())
                    .append('\n');

            if (shape instanceof dev.vitto.cna.objects.Rectangle rect) {
                data.append("    ")
                        .append("endX:    ")
                        .append(rect.getEndX())
                        .append('\n');

                data.append("    ")
                        .append("endY:    ")
                        .append(rect.getEndY())
                        .append('\n');

                data.append("    ")
                        .append("fill:       ")
                        .append(rect.isFill())
                        .append('\n');

            } else if (shape instanceof dev.vitto.cna.objects.Line line) {
                data.append("    ")
                        .append("endX:    ")
                        .append(line.getEndX())
                        .append('\n');

                data.append("    ")
                        .append("endY:    ")
                        .append(line.getEndY())
                        .append('\n');

            } else if (shape instanceof dev.vitto.cna.objects.Circle circle) {
                data.append("    ")
                        .append("endX:    ")
                        .append(circle.getEndX())
                        .append('\n');

                data.append("    ")
                        .append("endY:    ")
                        .append(circle.getEndY())
                        .append('\n');

                data.append("    ")
                        .append("fill:       ")
                        .append(circle.isFill())
                        .append('\n');

            } else if (shape instanceof dev.vitto.cna.objects.SingleLineText slt) {
                data.append("    ")
                        .append("textHeight: ")
                        .append(slt.getTextHeight())
                        .append('\n');

                data.append("    ")
                        .append("textContent: ")
                        .append(slt.getTextContent())
                        .append('\n');

            } else if (shape instanceof dev.vitto.cna.objects.Ellipse ellipse) {
                data.append("    ")
                        .append("firstPointX: ")
                        .append(ellipse.getFirstPointX())
                        .append('\n');

                data.append("    ")
                        .append("firstPointY: ")
                        .append(ellipse.getFirstPointY())
                        .append('\n');

                data.append("    ")
                        .append("secondPointX: ")
                        .append(ellipse.getSecondPointX())
                        .append('\n');

                data.append("    ")
                        .append("secondPointY: ")
                        .append(ellipse.getSecondPointY())
                        .append('\n');

                data.append("    ")
                        .append("fill: ")
                        .append(ellipse.isFill())
                        .append('\n');
            }

            data.append('\n');
        }
        content.setText(data.toString());
    }
}
