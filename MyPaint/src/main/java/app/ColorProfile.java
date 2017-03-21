
package app;

import java.awt.Color;

public class ColorProfile {
    private int currentColor;

    public ColorProfile() {
        currentColor = Color.black.getRGB(); //default
    }

    public void setCurrentColor(int currentColor) {
        this.currentColor = currentColor;
    }

    public int getCurrentColor() {
        return currentColor;
    }
    
    
}
