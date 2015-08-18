package net.sf.openrocket.gui.rocketfigure;

import net.sf.openrocket.util.Coordinate;
import net.sf.openrocket.util.Transformation;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by chris on 8/16/15.
 */
public class ControlElementSetShapes extends RocketComponentShapes {
    public static Shape[] getShapesSide(net.sf.openrocket.rocketcomponent.RocketComponent component, Transformation transformation) {
        net.sf.openrocket.rocketcomponent.ControlElementSet set = (net.sf.openrocket.rocketcomponent.ControlElementSet) component;
        double length = set.getLength();
        double radius = set.getBodyRadius();
        Coordinate[] start = transformation.transform(set.toAbsolute(new Coordinate(0,0,0)));
        Shape[] s = new Shape[start.length];
        for (int i = 0; i < start.length; i++) {
            s[i] = s[i] = new Rectangle2D.Double(start[i].x*S,(start[i].y-radius)*S, length*S,2*radius*S);
        }
        return s;
    }
    public static Shape[] getShapesBack(net.sf.openrocket.rocketcomponent.RocketComponent component, Transformation transformation) {
        net.sf.openrocket.rocketcomponent.ControlElementSet set = (net.sf.openrocket.rocketcomponent.ControlElementSet) component;
        double length = set.getLength();
        double radius = set.getBodyRadius();
        Coordinate[] start = transformation.transform(set.toAbsolute(new Coordinate(0,0,0)));
        Shape[] s = new Shape[start.length];
        for (int i = 0; i < start.length; i++) {
            s[i] = new Ellipse2D.Double((start[i].z-radius)*S,(start[i].y-radius)*S,2*radius*S,2*radius*S);
        }
        return s;
    }
}
