package DiffTool.event;

import java.util.EventListener;

public interface DiffResultsListener extends EventListener {
    void diffResultsCompleted(DiffResultsEvent event);
}
