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
@author "Gianluca Santoro"
*/

package dev.vitto.cna.objects;

import dev.vitto.cna.utils.Misc;

import java.awt.*;
import java.util.Objects;

public class SingleLineText extends Shape {

    private String textContent;

    private int textHeight;

    public SingleLineText(int startX, int startY, Color color, int textHeight, String textContent) {
        super(startX, startY, color, 0);
        this.textHeight = textHeight;
        this.textContent = textContent;
        if (textContent.length() > 17) {
            setName("Testo " + Misc.getNextObjNum() + ": " + textContent.substring(0, 16) + "...");
        } else {
            setName("Testo " + Misc.getNextObjNum() + ": " + textContent + "...");
        }
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        if (Objects.equals(textContent, "")) {
            return;
        }
        this.textContent = textContent;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public void setTextHeight(int textHeight) {
        if (textHeight < 0)
            return;
        this.textHeight = textHeight;
    }

}
