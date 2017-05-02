package ui.buttonPanels;

import ui.NewWindow;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.PaintBrush;
import app.cmd.CommandMap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalSliderUI;
import ui.NewWindow;
import ui.PaintPanel;

public class BrushUi extends JPanel {

    ControlUnit cu;
    MouseGuy m;
    PaintPanel p;
    SwapPanel container;
    NewWindow w;
    
    private JAlphaPanel alphaPanel;

    public BrushUi(NewWindow w, ControlUnit cu, MouseGuy m, PaintPanel p, SwapPanel container, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.p = p;
        this.w = w;
        this.container = container;
        Dimension dim = new Dimension(width, height);
        setPreferredSize(dim);
        setBackground(Color.blue);
        setLayout(new GridLayout(7, 1, 1, 1));
        addbuttons();

        revalidate();

    }

    public void addbuttons() {
        add(getSwapperButton());
        add(getSwapper2Button());
        add(new JOverridePanel(cu));
        alphaPanel = new JAlphaPanel(cu);
        add(alphaPanel);
        add(new JBrushWidthPanel(cu));
        add(makeColorChooser());
        add(makeColorPicker());
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

    public void refresh() {
        alphaPanel.refresh();
    }

    public JButton getSwapper2Button() {
        ActionListener a = (ActionEvent e) -> {
            container.showImgControlPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Image Settings");
        b.addActionListener(a);

        return b;
    }

    private JButton makeColorChooser() {
        JButton b = new JButton("Choose color");
        ActionListener a = (ActionEvent e) -> {
            Color c = JColorChooser.showDialog(null, "Choose a Color", cu.getImg().getColor());
            if (c != null) {
                cu.getImg().setColor(c);
                refresh();
            }
            
        };
        b.addActionListener(a);
        return b;
    }

    private JButton makeColorPicker() {
        JButton b = new JButton("Pick color from image");
        ActionListener a = (ActionEvent e) -> {
            cu.setActiveCMD(CommandMap.PICKCOLOR);
            m.setRefreshMode(MouseGuy.UPDATE_ONRELEASE);
        };
        b.addActionListener(a);
        return b;
    }

    private class JOverridePanel extends JPanel {

        private JCheckBox hard;
        private JCheckBox soft;

        public JOverridePanel(ControlUnit cu) {
            ActionListener a = (ActionEvent e) -> {
                cu.getImg().setOverride(true);
            };
            hard = new JCheckBox("Paint on top");
            hard.addActionListener(a);
            hard.setSelected(true);
            ActionListener b = (ActionEvent e) -> {
                cu.getImg().setOverride(false);
            };
            soft = new JCheckBox("Fill pixels");
            soft.addActionListener(b);

            setLayout(new GridLayout(2, 2));
            ButtonGroup group = new ButtonGroup();
            group.add(hard);
            group.add(soft);

            add(soft);
            add(hard);
        }
    }

    private class JAlphaPanel extends JPanel {

        private JLabel alphaValue;
        private JSlider slider;

        public JAlphaPanel(ControlUnit cu) {
            alphaValue = new JLabel("Alpha:" + (int) (cu.getImg().getColor().getAlpha() / 2.55) + "%");

            slider = new JSlider();
            slider.setValue((int) (cu.getImg().getColor().getAlpha() / 2.55));
            slider.setMajorTickSpacing(25);
            slider.setMinorTickSpacing(5);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            slider.setUI(new MetalSliderUI() {
                protected void scrollDueToClickInTrack(int direction) {
                    // this is the default behaviour, let's comment that out
                    //scrollByBlock(direction);

                    int value = slider.getValue();

                    if (slider.getOrientation() == JSlider.HORIZONTAL) {
                        value = this.valueForXPosition(slider.getMousePosition().x);
                    } else if (slider.getOrientation() == JSlider.VERTICAL) {
                        value = this.valueForYPosition(slider.getMousePosition().y);
                    }
                    slider.setValue(value);
                }
            });

            slider.addChangeListener((ChangeEvent e) -> {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                alphaValue.setText("Alpha:" + value + "%");
                value *= 2.55;
                int c = cu.getImg().getColor().getRGB();
                c = c << 8;
                c = c >> 8;
                value = value << 24;

                c = c | value;
                cu.getImg().setColor(new Color(c, true));
                w.requestFocus();
            });
            alphaValue.setLabelFor(slider);
            add(alphaValue);
            add(slider);

        }
        
        public void refresh() {
            slider.setValue((int)(cu.getImg().getColor().getAlpha() / 2.55));
        }
    }

    private class JBrushWidthPanel extends JPanel {

        private JLabel brushWidth;
        private JSlider slider;

        public JBrushWidthPanel(ControlUnit cu) {
            brushWidth = new JLabel("Width:" + cu.getImg().getBrushWidth());

            slider = new JSlider();
            slider.setMaximum(PaintBrush.getMAX_WIDTH());
            slider.setValue(cu.getImg().getBrushWidth());
            slider.setMajorTickSpacing(5);
            slider.setMinorTickSpacing(1);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            slider.setUI(new MetalSliderUI() {
                protected void scrollDueToClickInTrack(int direction) {
                    // this is the default behaviour, let's comment that out
                    //scrollByBlock(direction);

                    int value = slider.getValue();

                    if (slider.getOrientation() == JSlider.HORIZONTAL) {
                        value = this.valueForXPosition(slider.getMousePosition().x);
                    } else if (slider.getOrientation() == JSlider.VERTICAL) {
                        value = this.valueForYPosition(slider.getMousePosition().y);
                    }
                    slider.setValue(value);
                }
            });

            slider.addChangeListener((ChangeEvent e) -> {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                brushWidth.setText("Width:" + value);

                cu.getImg().setWidth(value);
                w.requestFocus();
            });

            add(brushWidth);
            add(slider);

        }
    }
}
