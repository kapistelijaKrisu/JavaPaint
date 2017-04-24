/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import javafx.scene.control.ToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class NewWindow extends JFrame {
 
    public NewWindow() {
        super("ToolBar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon image1 = new ImageIcon("button1.gif");
        JButton button1 = new JButton(image1);
        ImageIcon image2 = new ImageIcon("button2.gif");
        JButton button2 = new JButton(image2);
        ImageIcon image3 = new ImageIcon("button3.gif");
        JButton button3 = new JButton(image3);
        JToolBar bar = new JToolBar();
        bar.add(button1);
        bar.add(button2);
        bar.add(button3);
        JTextArea edit = new JTextArea(8,40);
        JScrollPane scroll = new JScrollPane(edit);
        JPanel pane = new JPanel();
        BorderLayout bord = new BorderLayout();
        pane.setLayout(bord);
        pane.add("North", bar);
        pane.add("Center", scroll);
 
        setContentPane(pane);
    }
 
    public void addToolBar() {
        
    }
    
    public void addImagePanel() {
       
    }
    
    public void addSideBar() {
        
    }
    
    public void addLowerBar() {
        
    }
    
    /*
    JFrame frame = new JFrame("JToolBar Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JToolBar toolbar = new JToolBar();
    toolbar.setRollover(true);

    
    JButton button = new JButton("button");
    toolbar.add(button);
    toolbar.addSeparator();
    
    toolbar.add(new JButton("button 2"));
    
    toolbar.add(new JComboBox(new String[]{"A","B","C"}));
    
    Container contentPane = frame.getContentPane();
    contentPane.add(toolbar, BorderLayout.NORTH);
    JTextArea textArea = new JTextArea();
    JScrollPane pane = new JScrollPane(textArea);
    contentPane.add(pane, BorderLayout.CENTER);
    frame.setSize(350, 150);
    frame.setVisible(true);
    */
    
}