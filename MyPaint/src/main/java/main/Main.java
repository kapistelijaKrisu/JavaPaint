package main;

import app.ControlUnit;
import java.awt.EventQueue;
import ui.NewWindow;

public class Main {

    public static void main(String[] args) {
        ControlUnit app = new ControlUnit(777, 777);
        NewWindow window = new NewWindow(app, 1200, 900);
        EventQueue.invokeLater(app);

    }
}
