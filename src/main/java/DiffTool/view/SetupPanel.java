package DiffTool.view;

import DiffTool.controller.SetPathButtonListener;
import DiffTool.controller.SubmitButtonListener;
import DiffTool.event.UIPathsSetHandler;
import DiffTool.service.DiffService;

import javax.swing.*;
import java.awt.*;

public class SetupPanel extends JPanel {

    public SetupPanel() {

        // UI implementation.
        JButton path1Button = new JButton("Set Path 1");
        path1Button.setActionCommand("path1");
        path1Button.addActionListener(new SetPathButtonListener());

        JButton path2Button = new JButton("Set Path 2");
        path2Button.setActionCommand("path2");
        path2Button.addActionListener(new SetPathButtonListener());

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setEnabled(false);

        JTextArea path1Text = new JTextArea();
        path1Text.setEditable(false);
        path1Text.setText("Path 1: *");

        JTextArea path2Text = new JTextArea();
        path2Text.setEditable(false);
        path2Text.setText("Path 2: *");

        JPanel setPathsPanel = new JPanel();
        setPathsPanel.setLayout(new BoxLayout(setPathsPanel, BoxLayout.X_AXIS));
        setPathsPanel.add(path1Button);
        setPathsPanel.add(path2Button);

        JPanel verticalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.add(setPathsPanel);
        verticalPanel.add(path1Text);
        verticalPanel.add(path2Text);
        verticalPanel.add(submitButton);

        add(verticalPanel);


        // Event handlers.
        UIPathsSetHandler uiPathsSetHandler = new UIPathsSetHandler();
        uiPathsSetHandler.setUIPrimitives(submitButton, path1Text, path2Text);

        DiffService.addPathsChangedListeners(uiPathsSetHandler);
    }
}