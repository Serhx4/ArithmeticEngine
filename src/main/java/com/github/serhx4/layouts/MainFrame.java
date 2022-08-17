package com.github.serhx4.layouts;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Serhii on 8/15/2022.
 */
class MainFrame extends JFrame {
    MainFrame() {
        super();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 250, 500, 500);
        this.setTitle("Arithmetic Calculator");
        this.revalidate();
        this.setVisible(true);
    }
}
