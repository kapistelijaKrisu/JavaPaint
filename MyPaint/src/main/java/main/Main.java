package main;

import app.ControlUnit;
import ui.MyWindow;

public class Main {

    public static void main(String[] args) {
        ControlUnit app = new ControlUnit();
        app.init(500, 400);
        MyWindow w = new MyWindow(app);
        app.run();
    }
}
