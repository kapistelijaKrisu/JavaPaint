package app.cmd;


import app.MyImage;
import tools.TwoPoint;

/**
 * 
 * <p>Interface which is designed for manipulating MyImage instances using information from Area</p>
 * 
 */
public interface CMD {


    public abstract void execute(MyImage img, TwoPoint area);


}
