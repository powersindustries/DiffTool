package DiffTool.event.listener;

import DiffTool.event.DiffResultsEvent;

import java.util.EventListener;

public interface DiffResultsListener extends EventListener {
    void diffResultsCompleted(DiffResultsEvent event);
}
