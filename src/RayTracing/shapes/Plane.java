package RayTracing.shapes;

import RayTracing.mathematics.Vec3f;
import RayTracing.scene.Color;

/*
 * By Aymen OUADEN
 */
public class Plane extends Shape {

	public Plane(Color color, float reflection) {
		super(new Vec3f(0, -1f, 0), color, reflection);
	}

	@Override
	public Vec3f getIntersectionV2(Vec3f p, Vec3f v) {
		float l = -(p.getY() - position.getY()) / v.getY();
		return l > 0 ? p.add(v.scale(l)) : null;
	}

	@Override
	public double getIntersection(Vec3f p, Vec3f v) {
		float l = -(p.getY() - position.getY()) / v.getY();
		return l > 0 ? p.add(v.scale(l)).distance(v) : 0;
	}

	public Color getColor(Vec3f point) {
		return ((int) point.getX() % 2 == 0) ? Color.CHOCOLAT : Color.SANDYBROWN;
	}

}
