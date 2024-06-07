package DiffTool.view;

import DiffTool.controller.SetPathButtonListener;
import DiffTool.controller.SubmitButtonListener;

import javax.swing.*;
import java.awt.*;

public class SetupPanel extends JPanel {
    private JButton path1Button;
    private JButton path2Button;
    private JButton submitbutton;

    public SetupPanel() {

        path1Button = new JButton("Set Path 1");
        path1Button.setActionCommand("path1");
        path1Button.addActionListener(new SetPathButtonListener());

        path2Button = new JButton("Set Path 2");
        path2Button.setActionCommand("path2");
        path2Button.addActionListener(new SetPathButtonListener());

        submitbutton = new JButton("Submit");
        submitbutton.addActionListener(new SubmitButtonListener());
        submitbutton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel setPathsPanel = new JPanel();
        setPathsPanel.setLayout(new BoxLayout(setPathsPanel, BoxLayout.X_AXIS));
        setPathsPanel.add(path1Button);
        setPathsPanel.add(path2Button);

        JPanel verticalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.add(setPathsPanel);
        verticalPanel.add(submitbutton);

        add(verticalPanel);

    }
}