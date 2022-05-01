package RayTracing.shapes;

import RayTracing.mathematics.Vec3f;
import RayTracing.scene.Color;

/*
 * By Aymen OUADEN
 */
public class Sphere extends Shape {

	private float radius;

	public Sphere(Vec3f position, float radius, Color color, float reflection) {
		super(position, color, reflection);
		this.radius = radius;
	}

	@Override
	public double getIntersection(Vec3f p, Vec3f v) {
		float dotProduct = position.sub(p).dotProduct(v);
		Vec3f point = p.add(v.scale(dotProduct));
		float lenght = position.sub(point).length();
		if (lenght < radius) {
			float tmp = dotProduct - (float) Math.sqrt(radius * radius - lenght * lenght);
			return tmp > 0 ? p.add(v.scale(tmp)).distance(v) : 0;
		}
		return 0;
	}

	@Override
	public Vec3f getIntersectionV2(Vec3f p, Vec3f v) {
		float dotProduct = position.sub(p).dotProduct(v);
		Vec3f point = p.add(v.scale(dotProduct));
		float lenght = position.sub(point).length();
		if (lenght < radius) {
			float tmp = dotProduct - (float) Math.sqrt(radius * radius - lenght * lenght);
			return tmp > 0 ? p.add(v.scale(tmp)) : null;
		}
		return null;
	}

}
