package DiffTool.event;

import java.util.EventObject;

public class PathsSetEvent extends EventObject {
    private final Boolean bBothPathsSet;

    public PathsSetEvent(Object source, Boolean bothPathsSet) {
        super(source);

        this.bBothPathsSet = bothPathsSet;
    }

    public Boolean getBothPathsSetString() {
        return bBothPathsSet;
    }
}
