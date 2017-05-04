package ui;

import app.ControlUnit;
import ui.tools.Refreshable;
import app.MyImage;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import ui.io.MouseGuy;

public final class InfoPanel extends JPanel implements Refreshable {

    private final JLabel mouseText;
    private final JLabel colorInfo;
    private final JPanel colorPanel;
    private JLabel scaleInfo;
    private JLabel cmdInfo;
    private final JSlider slider;
    private final PaintPanel pan;
    private final MouseGuy mouse;
    private final ControlUnit cu;

    public InfoPanel(ControlUnit cu, PaintPanel pan, MouseGuy mouse) {
        this.pan = pan;
        this.cu = cu;
        this.mouse = mouse;
        setPreferredSize(new Dimension(pan.getWidth(), 60));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        setLayout(flowLayout);

        mouseText = new JLabel();
        mouseText.setText("width:" + cu.getImg().getImg().getWidth() + " height: " + cu.getImg().getImg().getHeight());
        add(mouseText);

        colorInfo = new JLabel();
        colorInfo.setText("           Alpha:" + cu.getImg().getColor().getAlpha() + "    Current color: ");
        add(colorInfo);

        colorPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(cu.getImg().getColor());
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
        
        cmdInfo = new JLabel("Brush");
        add (new JLabel("Current CMD: "));
        add(cmdInfo);
    }

    @Override
    public void refresh() {
        slider.setValue((int) pan.getScale());
        mouseText.setText(
                "width:" + cu.getImg().getImg().getWidth()
                + " height: " + cu.getImg().getImg().getHeight()
                + "           mouse x:"
                + Math.min(mouse.getToolTip().getCurX(), cu.getImg().getImg().getWidth())
                + " y:" + Math.min(mouse.getToolTip().getCurY(), cu.getImg().getImg().getHeight()));
        colorInfo.setText("           Alpha:" + cu.getImg().getColor().getAlpha() + "    Current color: ");
        colorPanel.repaint();
    }

    public JLabel getCmdInfo() {
        return cmdInfo;
    }
}
