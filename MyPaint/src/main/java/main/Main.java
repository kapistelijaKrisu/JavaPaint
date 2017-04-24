package main;

import app.ControlUnit;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;
import ui.MyWindow;
import ui.NewWindow;

public class Main {

    public static void main(String[] args) {
        ControlUnit app = new ControlUnit();
        MyWindow w = new MyWindow(app);
        app.run();

    }
}
