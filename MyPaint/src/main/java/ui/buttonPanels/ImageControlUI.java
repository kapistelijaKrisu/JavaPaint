package ui.buttonPanels;

import ui.io.MouseGuy;
import app.ControlUnit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import tools.FileUtils;
import ui.NewWindow;

public class ImageControlUI extends JPanel implements Refreshable {

    private final ControlUnit cu;
    private final MouseGuy m;
    private final NewWindow w;
    private JSizePanel sizePanel;
    private JButton undo, redo;

    public ImageControlUI(NewWindow w, ControlUnit cu, MouseGuy m, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.w = w;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.green);
        setLayout(new GridLayout(8, 1, 1, 1));
        addbuttons();

        revalidate();

    }

    public void addbuttons() {
        add(getToDrawButton());
        add(getToBrushButton());
        BufferedImage img = cu.getImg().getImg();

        sizePanel = new JSizePanel(0, 0, img.getWidth(), img.getHeight());
        add(sizePanel);
        add(getSizeButton(sizePanel));
        add(getLoadButton());
        add(getSaveButton());
        undo = getUndoButton();
        add(undo);

        redo = getRedoButton();
        add(redo);
        refresh();
    }

    private JButton getUndoButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.undo();
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Undo");
        b.addActionListener(a);
        return b;
    }

    private JButton getRedoButton() {
        ActionListener a = (ActionEvent e) -> {
            cu.redo();
            w.refresh();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Redo");
        b.addActionListener(a);
        return b;
    }

    private JButton getSaveButton() {
        ActionListener a = (ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

            fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
            FileNameExtensionFilter png = new FileNameExtensionFilter("*png", "png");
            FileNameExtensionFilter jpg = new FileNameExtensionFilter("*jpg", "jpg");
            fileChooser.setFileFilter(png);
            fileChooser.setFileFilter(jpg);

            int userSelection = fileChooser.showSaveDialog(w);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                FileUtils.setFileLocation(fileToSave.getAbsolutePath());
                if (fileChooser.getFileFilter().equals(png)) {
                    FileUtils.setFormat("png");

                } else if (fileChooser.getFileFilter().equals(jpg)) {
                    FileUtils.setFormat("jpg");
                }
                FileUtils.saveFile(cu.getImg().getImg());
            }

            w.requestFocusInWindow();
            //  }
        };
        JButton b = new JButton("SAVE");
        b.addActionListener(a);
        return b;
    }

    private JButton getLoadButton() {
        ActionListener a = (ActionEvent e) -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogType(JFileChooser.OPEN_DIALOG);

            fc.setFileFilter(new FileNameExtensionFilter("Images", "png", "jpg", "jpeg", "bmp", "gif", "tif", "tiff"));
            //      if (e.getSource() == openButton) {
            int returnVal = fc.showOpenDialog(w);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {
                    Color old = cu.getImg().getColor();                   
                    cu.setImage(FileUtils.loadImageAsARGB(fc.getSelectedFile()));                 
                    cu.getImg().setColor(old);             
                    w.refresh();

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


    private JButton getToDrawButton() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showCMDPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Draw Settings");
        b.addActionListener(a);

        return b;
    }

    public JButton getToBrushButton() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showBrushPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Brush Settings");
        b.addActionListener(a);

        return b;
    }

    private JButton getSizeButton(JSizePanel sizePanel) {
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

                w.refresh();
                w.requestFocusInWindow();

            } catch (IllegalArgumentException ex) {
                sizePanel.startX.setText("" + 0);
                sizePanel.startY.setText("" + 0);
                sizePanel.width.setText("" + cu.getImg().getImg().getWidth());
                sizePanel.height.setText("" + cu.getImg().getImg().getHeight());
                ex.printStackTrace();
            }

        };
        JButton b = new JButton("CLIP/RESIZE");

        b.addActionListener(a);
        return b;
    }

    @Override
    public void refresh() {
        if (cu.getLog().getHistorySize() == 0) {
            undo.setEnabled(false);
        } else {
            undo.setEnabled(true);
        }

        if (cu.getLog().getRedoSize() == 0) {
            redo.setEnabled(false);
        } else {
            redo.setEnabled(true);
        }
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

            JLabel xLabel = new JLabel("startX:");
            xLabel.setLabelFor(startX);
            JLabel yLabel = new JLabel("startY:");
            yLabel.setLabelFor(startY);

            JLabel wLabel = new JLabel("width:");
            wLabel.setLabelFor(startX);
            JLabel hLabel = new JLabel("height:");
            hLabel.setLabelFor(startY);

            add(xLabel);
            add(startX);
            add(yLabel);
            add(startY);

            add(wLabel);
            add(width);
            add(hLabel);
            add(height);
        }

        public void set(int x, int y, int w, int h) {
            startX.setText("" + x);
            startY.setText("" + y);
            width.setText("" + w);
            height.setText("" + h);
        }
    }

}
