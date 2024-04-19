package projekt;

import javax.swing.*;
import java.awt.*;

public class CoinCombinationFrame extends JFrame {
    public static final int WIDTH = 400, HEIGHT = 400;

    public CoinCombinationFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension size = kit.getScreenSize();
        int width = size.width;
        int height = size.height;
        setLocation((width - WIDTH) / 2, (height - HEIGHT) / 2);

        setTitle("Kombinacje monet w woreczku");
        setSize(WIDTH,HEIGHT);

        setLayout(new BorderLayout());
        CoinCombinationPanel panel = new CoinCombinationPanel();
        panel.setBackground(Color.PINK);
        add(panel, BorderLayout.CENTER);
    }
}
