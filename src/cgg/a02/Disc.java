package cgg.a02;

import cgtools.Color;
import cgtools.Point;
import cgtools.Util;

public record Disc(Point position, double radius, Color color) {
    
    boolean isPointInDisc(Point point) {
        return position.distance2d(point) < radius;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Disc)) return false;
        if (o == this) return true;
        
        Disc v = (Disc) o;
        return Util.isZero(radius - v.radius)
                && position.equals(v.position)
                && color.equals(v.color);
    }
    
}
