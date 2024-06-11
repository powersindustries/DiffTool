package DiffTool.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Diff Tool");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new SetupPanel());

        pack();
        setVisible(true);
    }
}