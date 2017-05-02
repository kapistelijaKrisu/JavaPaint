package ui;

import app.MyImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JLabel;
;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.metal.MetalSliderUI;
import ui.io.MouseGuy;



public class InfoPanel extends JPanel {

    private final JLabel mouseText;
    private JLabel colorInfo;
    private JPanel colorPanel;
    private JLabel scaleInfo;
    private JSlider slider;
    private PaintPanel pan;
    private MyImage img;

    public InfoPanel(MyImage img, PaintPanel pan) {
        this.pan = pan;
        this.img = img;

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        setLayout(flowLayout);

        mouseText = new JLabel();
        // mouseText.getDocument().putProperty("filterNewlines", Boolean.TRUE);
        mouseText.setText("width:" + img.getImg().getWidth() + " height: " + img.getImg().getHeight());

        add(mouseText);

        colorInfo = new JLabel();
        colorInfo.setText("           Alpha:" + (int) (img.getColor().getAlpha() / 2.55) + "    Current color: ");
        add(colorInfo);

        colorPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(img.getColor());
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        add(colorPanel);

        slider = new JSlider(1, 16);
        scaleInfo = new JLabel("     Scale:" + pan.getScale() + "x  ");

        slider.setMinorTickSpacing(1);
        slider.setValue((int) pan.getScale());
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener((ChangeEvent e) -> {
            JSlider source = (JSlider) e.getSource();
            int value = source.getValue();
            scaleInfo.setText("     Scale:" + value + "x  ");
            pan.setScale(value);
            pan.revalidate();
            pan.repaint();

        });
        scaleInfo.setLabelFor(slider);

        add(scaleInfo);

        add(slider);
    }

    public void refresh() {
        slider.setValue((int) pan.getScale());
        mouseText.setText("width:" + img.getImg().getWidth() + " height: " + img.getImg().getHeight());
    }

    public JLabel getMouseText() {
        return mouseText;
    }

    public JLabel getColorInfo() {
        return colorInfo;
    }

    public JPanel getColorPanel() {
        return colorPanel;
    }

}
