package main;

import app.ControlUnit;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.*;
import ui.*;
import ui.research.NewWindow;


public class Main {

    public static void main(String[] args) {
        ControlUnit app = new ControlUnit(777,777);
        NewWindow window = new NewWindow(app);
       // MyWindow window = new MyWindow(app);
        app.run();
       
       

       
   
        //        frame.getContentPane().add(new JScrollPane(new TilePainter()));
        
       
       
       
    }
}
