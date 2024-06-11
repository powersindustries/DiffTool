package DiffTool.model;

import DiffTool.event.*;

import java.util.ArrayList;
import java.util.List;

public class DiffResultsModel {

    private List<DiffResultsListener> diffResultsListeners = new ArrayList<>();
    private String diffResult = "";

    public String getDiffResult() {
        return diffResult;
    }

    public void setDiffResult(String diffResult) {
        this.diffResult = diffResult;

        fireDataChangeEvent(new DiffResultsEvent(this));
    }

    public void addDataChangeListener(DiffResultsListener listener) {
        diffResultsListeners.add(listener);
    }

    public void removeDataChangeListener(DiffResultsListener listener) {
        diffResultsListeners.remove(listener);
    }

    private void fireDataChangeEvent(DiffResultsEvent event) {
        for (DiffResultsListener listener : diffResultsListeners) {
            listener.diffResultsCompleted(event);
        }
    }
}
