package app.cmd;


import app.MyImage;
import tools.Area;

public interface CMD {


    public abstract void execute(MyImage img, Area area);


}
