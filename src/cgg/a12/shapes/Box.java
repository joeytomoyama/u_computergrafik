package cgg.a12.shapes;

import cgg.a12.Hit;
import cgg.a12.materials.Material;
import cgtools.Direction;
import cgtools.Point;
import cgtools.Ray;

public class Box implements Shape {
    private Point center;
    private double width;
    private double height;
    private double depth;
    private Material material;

    // Temporary variables for min and max corners
    private Point minCorner;
    private Point maxCorner;

    public Box(Point minCorner, Point maxCorner, Material material) {
        this.minCorner = minCorner;
        this.maxCorner = maxCorner;
        this.material = material;
    }

	public Box(Point center, double width, double height, double depth, Material material) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.material = material;

        // Calculate min and max corners
        double halfWidth = width / 2;
        double halfHeight = height / 2;
        double halfDepth = depth / 2;
        minCorner = new Point(center.x() - halfWidth, center.y() - halfHeight, center.z() - halfDepth);
        maxCorner = new Point(center.x() + halfWidth, center.y() + halfHeight, center.z() + halfDepth);
    }

    @Override
    public Hit intersect(Ray ray) {
        double tMin = (minCorner.x() - ray.origin().x()) / ray.direction().x();
        double tMax = (maxCorner.x() - ray.origin().x()) / ray.direction().x();

        if (tMin > tMax) {
            double temp = tMin;
            tMin = tMax;
            tMax = temp;
        }

        double tyMin = (minCorner.y() - ray.origin().y()) / ray.direction().y();
        double tyMax = (maxCorner.y() - ray.origin().y()) / ray.direction().y();

        if (tyMin > tyMax) {
            double temp = tyMin;
            tyMin = tyMax;
            tyMax = temp;
        }

        if ((tMin > tyMax) || (tyMin > tMax)) {
            return null;
        }

        if (tyMin > tMin) {
            tMin = tyMin;
        }

        if (tyMax < tMax) {
            tMax = tyMax;
        }

        double tzMin = (minCorner.z() - ray.origin().z()) / ray.direction().z();
        double tzMax = (maxCorner.z() - ray.origin().z()) / ray.direction().z();

        if (tzMin > tzMax) {
            double temp = tzMin;
            tzMin = tzMax;
            tzMax = temp;
        }

        if ((tMin > tzMax) || (tzMin > tMax)) {
            return null;
        }

        if (tzMin > tMin) {
            tMin = tzMin;
        }

        if (tzMax < tMax) {
            tMax = tzMax;
        }

        double t = tMin;

        if (!ray.isValid(t)) {
            return null;
        }

        Point hitPoint = ray.pointAt(t);
        Direction normal = calculateNormal(hitPoint);

        // Calculate u and v for texture mapping
        double u = 0;
        double v = 0;

        // // Map hit point to texture coordinates based on the face
        // if (Math.abs(normal.x()) > 0) {
        //     // Hit on x-face (left or right)
        //     u = (hitPoint.y() - minCorner.y()) / (maxCorner.y() - minCorner.y());
        //     v = (hitPoint.z() - minCorner.z()) / (maxCorner.z() - minCorner.z());
        // } else if (Math.abs(normal.y()) > 0) {
        //     // Hit on y-face (top or bottom)
        //     u = (hitPoint.x() - minCorner.x()) / (maxCorner.x() - minCorner.x());
        //     v = (hitPoint.z() - minCorner.z()) / (maxCorner.z() - minCorner.z());
        // } else if (Math.abs(normal.z()) > 0) {
        //     // Hit on z-face (front or back)
        //     u = (hitPoint.x() - minCorner.x()) / (maxCorner.x() - minCorner.x());
        //     v = (hitPoint.y() - minCorner.y()) / (maxCorner.y() - minCorner.y());
        // }

		var textureRepeatX = 1;
		var textureRepeatY = 1;

		// Map hit point to repeating texture coordinates based on the face
        if (Math.abs(normal.x()) > 0) {
            // Hit on x-face (left or right)
            u = ((hitPoint.y() - minCorner.y()) % textureRepeatY) / textureRepeatY;
            v = ((hitPoint.z() - minCorner.z()) % textureRepeatX) / textureRepeatX;
        } else if (Math.abs(normal.y()) > 0) {
            // Hit on y-face (top or bottom)
            u = ((hitPoint.x() - minCorner.x()) % textureRepeatX) / textureRepeatX;
            v = ((hitPoint.z() - minCorner.z()) % textureRepeatY) / textureRepeatY;
        } else if (Math.abs(normal.z()) > 0) {
            // Hit on z-face (front or back)
            u = ((hitPoint.x() - minCorner.x()) % textureRepeatX) / textureRepeatX;
            v = ((hitPoint.y() - minCorner.y()) % textureRepeatY) / textureRepeatY;
        }

        return new Hit(t, hitPoint, normal, u, v, this.material);
    }

    private Direction calculateNormal(Point hitPoint) {
        // Implement logic to calculate the normal at the hit point
        // This is a simplified version; you should modify it based on your coordinate system
        Direction normal = new Direction(0, 0, 0);
        if (hitPoint.x() == minCorner.x() || hitPoint.x() == maxCorner.x()) normal = new Direction(1, 0, 0);
        else if (hitPoint.y() == minCorner.y() || hitPoint.y() == maxCorner.y()) normal = new Direction(0, 1, 0);
        else if (hitPoint.z() == minCorner.z() || hitPoint.z() == maxCorner.z()) normal = new Direction(0, 0, 1);
        return normal;
    }
}
