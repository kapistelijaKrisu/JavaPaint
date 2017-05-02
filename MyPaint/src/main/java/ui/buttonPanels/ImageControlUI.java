package ui.buttonPanels;

import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.cmd.CommandMap;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;
import ui.NewWindow;
import ui.PaintPanel;

public class ImageControlUI extends JPanel {

    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    SwapPanel container;
    NewWindow w;
    JSizePanel sp;

    public ImageControlUI(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, SwapPanel container, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        this.container = container;
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.green);
        setLayout(new GridLayout(10, 1, 1, 1));
        addbuttons();

        revalidate();

    }

    public void addbuttons() {
        add(getSwapperButton());
        add(getSwapper2Button());
        BufferedImage img = cu.getImg().getImg();

        sp = new JSizePanel(0, 0, img.getWidth(), img.getHeight());
        add(sp);
        add(setSizePanel(sp));
        add(getLoadButton());
        add(getSaveButton());

    }

    public void refresh() {
        sp.refresh(0, 0, cu.getImg().getImg().getWidth(), cu.getImg().getImg().getHeight());
        w.refresh();
        p.revalidate();
        w.repaint();
    }

    public JButton getSaveButton() {
        ActionListener a = (ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
  fileChooser.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "bmp", "gif", "tif", "tiff"));
      
int userSelection = fileChooser.showSaveDialog(w);
 
if (userSelection == JFileChooser.APPROVE_OPTION) {
    File fileToSave = fileChooser.getSelectedFile();
  //  fileChooser.get
    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
    System.out.println("cur " + fileChooser.getFileFilter());
}
          
            w.requestFocusInWindow();
            //  }
        };
        JButton b = new JButton("SAVE");
        b.addActionListener(a);
        return b;
    }

    public JButton getLoadButton() {
        ActionListener a = (ActionEvent e) -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.OPEN_DIALOG);
            

            fc.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "bmp", "gif", "tif", "tiff"));
            //      if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(w);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                try {
                    cu.setImage(ImageIO.read(file));
                    refresh();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            w.requestFocusInWindow();
            //  }
        };
        JButton b = new JButton("OPEN");
        b.addActionListener(a);
        return b;
    }

    public JButton getThickenBrushButton() {
        ActionListener a = (ActionEvent e) -> {

            w.requestFocusInWindow();
        };
        JButton b = new JButton("asd");
        b.addActionListener(a);
        return b;
    }

    public JButton getSwapperButton() {
        ActionListener a = (ActionEvent e) -> {
            container.showCMDPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Draw Settings");
        b.addActionListener(a);

        return b;
    }

    public JButton getSwapper2Button() {
        ActionListener a = (ActionEvent e) -> {
            container.showBrushPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Brush Settings");
        b.addActionListener(a);

        return b;
    }

    public JButton setSizePanel(JSizePanel sizePanel) {
        ActionListener a = (ActionEvent e) -> {
            try {
                int x = Integer.parseInt(sizePanel.startX.getText());
                int y = Integer.parseInt(sizePanel.startY.getText());
                int width = Integer.parseInt(sizePanel.width.getText());
                int height = Integer.parseInt(sizePanel.height.getText());

                if (x < 0 || y < 0 || width <= 0 || height <= 0) {
                    throw new IllegalArgumentException();
                }
                BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);

                BufferedImage img = cu.getImg().getImg();
                img = img.getSubimage(x, y, img.getWidth() - x - 1, img.getHeight() - y - 1);

                resized.setData(img.getRaster());
                cu.setImage(resized);

                p.revalidate();
                p.repaint();

            } catch (IllegalArgumentException ex) {
                sizePanel.startX.setText("" + 0);
                sizePanel.startY.setText("" + 0);
                sizePanel.width.setText("" + cu.getImg().getImg().getWidth());
                sizePanel.height.setText("" + cu.getImg().getImg().getHeight());
                ex.printStackTrace();
            }

        };
        JButton b = new JButton("APPLY");

        b.addActionListener(a);
        return b;
    }

   

    private class JSizePanel extends JPanel {

        JTextField startX;
        JTextField startY;
        JTextField width;
        JTextField height;

        public JSizePanel(int x, int y, int w, int h) {
            setLayout(new GridLayout(4, 2));

            startX = new JTextField("" + x);
            startY = new JTextField("" + y);
            width = new JTextField("" + w);
            height = new JTextField("" + h);

            JLabel xl = new JLabel("startX:");
            xl.setLabelFor(startX);
            JLabel xll = new JLabel("startY:");
            xll.setLabelFor(startY);

            JLabel xlll = new JLabel("width:");
            xlll.setLabelFor(startX);
            JLabel xllll = new JLabel("height:");
            xllll.setLabelFor(startY);

            add(xl);
            add(startX);
            add(xll);
            add(startY);

            add(xlll);
            add(width);
            add(xllll);
            add(height);
        }

        public void refresh(int x, int y, int w, int h) {
            startX.setText("" + x);
            startY.setText("" + y);
            width.setText("" + w);
            height.setText("" + h);
        }
    }

}
