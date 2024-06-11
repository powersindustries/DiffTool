package DiffTool.event.listener;

import DiffTool.event.PathsSetEvent;

import java.util.EventListener;

public interface PathsSetListener extends EventListener {
    void pathsSetChanged(PathsSetEvent pathsSetEvent);
}