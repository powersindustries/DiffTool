package DiffTool.event;

import DiffTool.service.DiffService;

import javax.swing.*;

public class UIPathsSetHandler implements PathsSetListener {

    private JButton submitButton = null;

    public void setSubmitButton(JButton submitButton) {
        this.submitButton = submitButton;
    }

    @Override
    public void pathsSetChanged(PathsSetEvent pathsSetEvent) {
        if (submitButton != null && DiffService.bothPathsSet()){
            submitButton.setEnabled(true);
        }
    }
}