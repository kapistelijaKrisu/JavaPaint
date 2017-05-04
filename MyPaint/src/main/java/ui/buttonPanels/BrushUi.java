package ui.buttonPanels;

import ui.tools.Refreshable;
import ui.io.MouseGuy;
import app.ControlUnit;
import app.PaintBrush;
import app.cmd.CommandMap;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.metal.MetalSliderUI;
import ui.TheWindow;
import ui.PaintPanel;

public class BrushUi extends JPanel implements Refreshable {

    private final ControlUnit cu;
    private final MouseGuy m;
    private final TheWindow w;

    private JAlphaPanel alphaPanel;
    private JBrushWidthPanel brushWidthPanel;
    private JOverridePanel overridePanel;

    public BrushUi(TheWindow w, ControlUnit cu, MouseGuy m, int width, int height) {
        this.cu = cu;
        this.m = m;
        this.w = w;

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.blue);
        setLayout(new GridLayout(7, 1, 1, 1));
        addbuttons();
    }

    private void addbuttons() {
        add(getDrawSwap());
        add(getImageSwap());
        overridePanel = new JOverridePanel(cu);
        add(overridePanel);
        alphaPanel = new JAlphaPanel(cu);
        add(alphaPanel);
        brushWidthPanel = new JBrushWidthPanel(cu);
        add(brushWidthPanel);
        add(makeColorChooser());
        add(makeColorPicker());
    }

    @Override
    public void refresh() {
        alphaPanel.refresh();
        brushWidthPanel.refresh();
        overridePanel.refresh();
    }

    private JButton getDrawSwap() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showCMDPanel();
            w.requestFocusInWindow();
        };
        JButton b = new JButton("Draw Settings");
        b.addActionListener(a);

        return b;
    }

    private JButton getImageSwap() {
        ActionListener a = (ActionEvent e) -> {
            w.getOptionPanel().showImgControlPanel();
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
                w.refresh();
                w.requestFocusInWindow();
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
            w.getPaintPanel().setToolBarMode(PaintPanel.RECT);
            w.getInfo().getCmdInfo().setText("Pick Color From Image");
            w.refresh();
            w.requestFocusInWindow();
        };
        b.addActionListener(a);
        return b;
    }

    private class JOverridePanel extends JPanel implements Refreshable {

        private JCheckBox hard;
        private JCheckBox soft;

        public JOverridePanel(ControlUnit cu) {
            ActionListener a = (ActionEvent e) -> {
                cu.getImg().setOverride(true);
                w.requestFocusInWindow();
            };
            hard = new JCheckBox("Paint on top");
            hard.addActionListener(a);
            hard.setSelected(true);
            ActionListener b = (ActionEvent e) -> {
                cu.getImg().setOverride(false);
                w.requestFocusInWindow();
            };
            soft = new JCheckBox("Fill what's left");
            soft.addActionListener(b);

            setLayout(new GridLayout(2, 2));
            ButtonGroup group = new ButtonGroup();
            group.add(hard);
            group.add(soft);

            add(soft);
            add(hard);
        }

        @Override
        public void refresh() {
            if (cu.getImg().getBrushComposite() == AlphaComposite.SRC) {
                soft.setSelected(false);
                hard.setSelected(true);
            } else {
                hard.setSelected(false);
                soft.setSelected(true);
            }
        }
    }

    private class JAlphaPanel extends JPanel implements Refreshable {

        private final JLabel alphaValue;
        private final JSlider slidez;

        public JAlphaPanel(ControlUnit cu) {
            alphaValue = new JLabel("Alpha:" + (int) (cu.getImg().getColor().getAlpha()) + "  (0-255)");
            slidez = new JSlider();
            slidez.setMaximum(255);
            slidez.setMinimum(0);
            slidez.setMajorTickSpacing(51);
            slidez.setMinorTickSpacing(5);
            slidez.setPaintTicks(true);
            slidez.setPaintLabels(true);
            slidez.setValue((int) (cu.getImg().getColor().getAlpha()));

            slidez.setPaintLabels(true);

            slidez.setUI(new MetalSliderUI() {
                @Override
                protected void scrollDueToClickInTrack(int direction) {

                    int value = slidez.getValue();
                    value = this.valueForXPosition(slidez.getMousePosition().x);
                    slidez.setValue(value);
                }
            });

            slidez.addChangeListener((ChangeEvent e) -> {

                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();

                Color c = cu.getImg().getColor();
                alphaValue.setText("Alpha:" + value);

                cu.getImg().setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), value));
                w.refresh();
                w.requestFocus();

            });
            alphaValue.setLabelFor(slidez);
            add(alphaValue);
            add(slidez);

        }

        @Override
        public void refresh() {

            int value = cu.getImg().getColor().getAlpha();
            slidez.setValue(value);
            alphaValue.setText("Alpha:" + value + "  (0-255)");
        }
    }

    private class JBrushWidthPanel extends JPanel implements Refreshable {

        private final JLabel brushWidth;
        private final JSlider slider;

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

                    int value = slider.getValue();
                    value = this.valueForXPosition(slider.getMousePosition().x);
                    slider.setValue(value);
                }
            });

            slider.addChangeListener((ChangeEvent e) -> {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                brushWidth.setText("Width:" + value);

                cu.getImg().setBrushWidth(value);
                w.refresh();
                w.requestFocus();
            });

            add(brushWidth);
            brushWidth.setLabelFor(slider);
            add(slider);

        }

        @Override
        public void refresh() {
            int value = cu.getImg().getBrushWidth();
            slider.setValue(value);
            brushWidth.setText("Width:" + value);
        }
    }
}
