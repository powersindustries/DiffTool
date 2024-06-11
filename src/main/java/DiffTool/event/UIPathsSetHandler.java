package DiffTool.event;

import DiffTool.service.DiffService;

import javax.swing.*;

public class UIPathsSetHandler implements PathsSetListener {

    private JButton submitButton = null;
    private JTextArea path1Text = null;
    private JTextArea path2Text = null;

    public void setUIPrimitives(JButton submitButton, JTextArea path1Text, JTextArea path2Text) {
        this.submitButton = submitButton;
        this.path1Text = path1Text;
        this.path2Text = path2Text;
    }

    @Override
    public void pathsSetChanged(PathsSetEvent pathsSetEvent) {

        String path1 = DiffService.getPath1().toString();
        String path2 = DiffService.getPath2().toString();

        if (!path1.isEmpty()){
            path1Text.setText("Path 1: " + path1);
        }

        if (!path2.isEmpty()){
            path2Text.setText("Path 2: " + path2);
        }

        if (submitButton != null && !path1.isEmpty() && !path2.isEmpty()) {
            submitButton.setEnabled(true);
        }
    }
}