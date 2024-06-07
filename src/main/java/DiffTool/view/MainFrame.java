package DiffTool.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setTitle("Diff Tool");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new SetupPanel());

        setVisible(true);
    }
}