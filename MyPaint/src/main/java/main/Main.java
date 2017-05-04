package main;

import app.ControlUnit;
import ui.TheWindow;

public class Main {

    public static void main(String[] args) {
        ControlUnit app = new ControlUnit();
        TheWindow window = new TheWindow(app, 1200, 900);
        window.setVisible(true);

    }
}
