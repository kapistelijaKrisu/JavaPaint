package app.cmd;

import app.ControlUnit;
import tools.Area;

public abstract class CMD {

    public ControlUnit controller;

    public CMD(ControlUnit controller) {
        this.controller = controller;
    }

    public abstract void execute(Area area);

    protected boolean areaOrControllerIsNull(Area area) {
        return controller == null || area == null;
    }

    public Object getControlUnit() {
        return controller;
    }
}
