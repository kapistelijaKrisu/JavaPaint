package UI;

import app.ControlUnit;
import java.awt.Color;
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
    private BufferedImage background;

    public MyWindow(ControlUnit cmd, int width, int height, float scale) {
        this.scale = scale;
        this.control = cmd;
        xOffSet = 0;
        yOffSet = 0;
        initFrame(width, height);
        background = initBackGround();
    }

    public MyWindow(ControlUnit cmd) {
        this.scale = 1;
        this.control = cmd;
        xOffSet = 0;
        yOffSet = 0;
        initFrame(1000, 800);
        background = initBackGround();
    }

    private void initFrame(int width, int height) {

        if (control == null) {
            System.out.println("error initframe");
            return;
        }

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
        g.drawImage(background, 0, 0, null);
        g.drawImage(control.getImg().getImg(), 0, 0, null);
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

    private BufferedImage initBackGround() {
        BufferedImage bg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        System.out.println(getWidth() + "?");
        int area = 50;
        Color a = new Color(123, 123, 123);
        Color b = new Color(55, 55, 55);
        Color active = a;
        for (int j = 0; j < bg.getHeight(); j += area) {
            if (bg.getWidth() / area % 2 == 1) {
                if (active == a) {
                    active = b;
                } else {
                    active = a;
                }
            }
            for (int k = 0; k < bg.getWidth(); k += area) {
                if (active == a) {
                    active = b;
                } else {
                    active = a;
                }
                for (int l = 0; l < area && j + l < bg.getHeight(); l++) {
                    for (int m = 0; m < area && k + m < bg.getWidth(); m++) {
                        bg.setRGB(k + m, j + l, active.getRGB());
                    }
                }
            }
        }
        return bg;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

}
