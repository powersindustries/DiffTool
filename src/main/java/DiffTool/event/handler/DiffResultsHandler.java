package DiffTool.event.handler;

import DiffTool.event.DiffResultsEvent;
import DiffTool.event.listener.DiffResultsListener;
import DiffTool.service.DiffService;

import javax.swing.*;

public class DiffResultsHandler implements DiffResultsListener {

    private JTextArea diffText = null;

    public void setDiffText(JTextArea diffText) {
        this.diffText = diffText;
    }

    @Override
    public void diffResultsCompleted(DiffResultsEvent diffResultsEvent) {
        diffText.setText(DiffService.getDiffResult());
    }

}
