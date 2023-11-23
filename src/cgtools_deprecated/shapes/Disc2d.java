package cgtools_deprecated.shapes;

import cgtools_deprecated.Color;
import cgtools_deprecated.Point;
import cgtools_deprecated.Util;

public record Disc2d(Point position, double radius, Color color) {
    
    public boolean isPointInDisc(Point point) {
        return position.distance2d(point) < radius;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Disc2d)) return false;
        if (o == this) return true;
        
        Disc2d v = (Disc2d) o;
        return Util.isZero(radius - v.radius)
                && position.equals(v.position)
                && color.equals(v.color);
    }
    
}
