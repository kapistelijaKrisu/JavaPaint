package ui;

import app.MyImage;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import ui.buttonPanels.Refreshable;
import ui.io.MouseGuy;

public final class InfoPanel extends JPanel implements Refreshable {

    private final JLabel mouseText;
    private final JLabel colorInfo;
    private final JPanel colorPanel;
    private JLabel scaleInfo;
    private final JSlider slider;
    private final PaintPanel pan;
    private final MyImage img;
    private final MouseGuy mouse;

    public InfoPanel(MyImage img, PaintPanel pan, MouseGuy mouse) {
        this.pan = pan;
        this.img = img;
        this.mouse = mouse;

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        setLayout(flowLayout);

        mouseText = new JLabel();
        mouseText.setText("width:" + img.getImg().getWidth() + " height: " + img.getImg().getHeight());
        add(mouseText);

        colorInfo = new JLabel();
        colorInfo.setText("           Alpha:" + img.getColor().getAlpha() + "    Current color: ");
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

    @Override
    public void refresh() {
        slider.setValue((int) pan.getScale());
        mouseText.setText(
                "width:" + img.getImg().getWidth()
                + " height: " + img.getImg().getHeight()
                + "           mouse x:"
                + Math.min(mouse.getToolTip().getCurX(), img.getImg().getWidth())
                + " y:" + Math.min(mouse.getToolTip().getCurY(), img.getImg().getHeight()));
        colorInfo.setText("           Alpha:" + img.getColor().getAlpha() + "    Current color: ");
        colorPanel.repaint();
    }
}
