package DiffTool.event;

import java.util.EventListener;

public interface PathsSetListener extends EventListener {
    void pathsSetChanged(PathsSetEvent pathsSetEvent);
}