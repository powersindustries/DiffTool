package DiffTool.model;

import DiffTool.event.PathsSetEvent;
import DiffTool.event.PathsSetListener;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PathsModel {

    private List<PathsSetListener> pathsSetListeners = new ArrayList<>();
    private Path path = Path.of("");

    public Path getPath() {
        return path;
    }

    public void setPath(String path1) {
        this.path = Path.of(path1);

        fireDataChangeEvent(new PathsSetEvent(this, true));
    }

    public void addDataChangeListener(PathsSetListener listener) {
        pathsSetListeners.add(listener);
    }

    public void removeDataChangeListener(PathsSetListener listener) {
        pathsSetListeners.remove(listener);
    }

    private void fireDataChangeEvent(PathsSetEvent event) {
        for (PathsSetListener listener : pathsSetListeners) {
            listener.pathsSetChanged(event);
        }
    }
}
