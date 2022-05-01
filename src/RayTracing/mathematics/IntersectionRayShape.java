package RayTracing.mathematics;

import RayTracing.shapes.Plane;
import RayTracing.shapes.Shape;
import RayTracing.shapes.Sphere;

/*
 * By Aymen OUADEN
 */
public class IntersectionRayShape {
	private Ray ray;
	private Shape shape;
	private Vec3f intersectionPosition;
	private Vec3f normal;

	public IntersectionRayShape(Ray ray, Shape shape, Vec3f intersectionPosition) {
		this.ray = ray;
		this.shape = shape;
		this.intersectionPosition = intersectionPosition;
		if (shape instanceof Sphere) {
			this.normal = intersectionPosition.sub(shape.getPosition()).normalize();
		} else if (shape instanceof Plane)
			this.normal = new Vec3f(0, 1, 0);

	}

	public Ray getRay() {
		return ray;
	}

	public Shape getShape() {
		return shape;
	}

	public Vec3f getIntersectionPosition() {
		return intersectionPosition;
	}

	public Vec3f getNormal() {
		return normal;
	}
}