package RayTracing.shapes;

import RayTracing.mathematics.Vec3f;
import RayTracing.scene.Color;

/*
 * By Aymen OUADEN
 */
public abstract class Shape {

	protected Vec3f position;
	protected Color color;
	protected float reflection;

	public Shape(Vec3f position, Color color, float reflection) {
		this.position = position;
		this.color = color;
		this.reflection = reflection;
	}

	public Vec3f getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public Color getColor(Vec3f point) {
		return color;
	}

	public float getReflection() {
		return reflection;
	}

	public abstract double getIntersection(Vec3f p, Vec3f v);

	public abstract Vec3f getIntersectionV2(Vec3f p, Vec3f v);

}