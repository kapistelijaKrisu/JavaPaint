package ui;

import app.ControlUnit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyWindow extends JPanel {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private ControlUnit control;
    private int xOffSet, yOffSet;
    private float scale;
    private BufferedImage bg;

    public MyWindow(ControlUnit cmd, int width, int height, float scale) {
        this.scale = scale;
        this.control = cmd;
        xOffSet = 0;
        yOffSet = 0;
        initFrame(width, height);
    }

    public MyWindow(ControlUnit cmd) {
        this.scale = 1;
        this.control = cmd;
        xOffSet = 0;
        yOffSet = 0;
        initFrame(1000, 800);
    }

    private void initFrame(int width, int height) {

        if (control == null) {
            System.out.println("error initframe");
            return;
        }
        int imgWidth = control.getImg().getImg().getWidth();
        int imgHeight = control.getImg().getImg().getHeight();
        bg = BackGroundCreator.create(imgWidth, imgHeight);
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);

        frame = new JFrame("asd");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        KeyInput kInput = new KeyInput(control);
        MouseInput mInput = new MouseInput(control, this);
        frame.add(this);

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);

        this.addMouseListener(mInput);
        this.addMouseMotionListener(mInput);
        frame.addKeyListener(kInput);

        frame.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.scale(scale / 1, scale / 1);
        g.drawImage(bg, xOffSet, yOffSet, null);
        g.drawImage(control.getImg().getImg(), xOffSet, yOffSet, null);
        
    }

    @Override
    public void resize(int width, int height) {

        frame.setVisible(false);
        frame.setResizable(true);

        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setMinimumSize(dim);
        setMaximumSize(dim);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }

    

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

}
