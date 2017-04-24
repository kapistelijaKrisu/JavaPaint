package ui;

import app.ControlUnit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import tools.Area;

public class MyWindow extends JPanel {

    private static final long serialVersionUID = 1L;
    public  boolean drawToolTip;
    public Area toolTip;

    private JFrame frame;
    private ControlUnit control;
    private int xOffSet, yOffSet;
    private float scale;
    private BufferedImage bg;
    
    

    public MyWindow(ControlUnit cmd, int width, int height, float scale) {
        this.scale = scale;
        this.control = cmd;
        initFrame(width, height);
    }

    public MyWindow(ControlUnit cmd) {
        this.scale = 2f;
        this.control = cmd;       
        initFrame(1000, 800);
    }

    private void initFrame(int width, int height) {

        if (control == null) {
            System.out.println("error initframe");
            return;
        }
        xOffSet = 0;
        yOffSet = 0;
        drawToolTip = false;
        
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
        KeyInput kInput = new KeyInput(control, this);      
        MouseInput mInput = new MouseInput(control, this);
        toolTip = mInput.getArea();
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
        
        g.drawImage(bg, xOffSet, yOffSet, null);
        g2.scale(scale / 1, scale / 1);
        
        g.drawImage(bg, xOffSet, yOffSet, null);
        g.drawImage(control.getImg().getImg(), xOffSet, yOffSet, null);
        
        if (drawToolTip) {
            Area r = toolTip.getRectangle();
        g.drawRect(r.getStartX(), r.getStartY(), r.getCurX(), r.getCurY());
        }
        
        
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

    public void moveOffSet(int xAmount, int yAmount) {
        this.xOffSet += xAmount;
        this.yOffSet += yAmount;
        
    }

    public void setScale(float scale) {
        if (scale >= 8) {
            this.scale = 8;
        } else if (scale <= 0.125) {
            this.scale = scale;
                 this.scale = 0.125f;
        } else {
            this.scale = scale;
       
        }
    }

    public float getScale() {
        return scale;
    }

    public int getxOffSet() {
        return xOffSet;
    }

    public int getyOffSet() {
        return yOffSet;
    }
    
    
    
}
