package RayTracing.scene;

import java.util.ArrayList;
import java.util.List;

import RayTracing.mathematics.IntersectionRayShape;
import RayTracing.mathematics.Ray;
import RayTracing.mathematics.Vec3f;
import RayTracing.shapes.Shape;

/*
 *  By Aymen OUADEN
 */
public class Scene {

	public static final int WIDTH = 1024, HEIGHT = 768;
	private List<Shape> shapes;
	private Sky sky;
	private Vec3f ObservateurPos;
	private Light light;

	public Scene(Light light, Vec3f observateurPos) {
		this.shapes = new ArrayList<Shape>();
		this.sky = new Sky();
		this.ObservateurPos = observateurPos;
		this.light = light;
	}

	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}

	public Light getLight() {
		return light;
	}

	public Sky getSky() {
		return sky;
	}

	public Vec3f getObservateurPos() {
		return ObservateurPos;
	}

	public IntersectionRayShape findNearestIntersectionPoint(Ray ray) {
		IntersectionRayShape lambdaMin = null;
		for (Shape shape : shapes) {
			Vec3f intersectionPosition = shape.getIntersectionV2(ray.getOrigin(), ray.getDirection());
			if (intersectionPosition != null && (lambdaMin == null || lambdaMin.getIntersectionPosition()
					.distance(ray.getOrigin()) > intersectionPosition.distance(ray.getOrigin()))) {
				lambdaMin = new IntersectionRayShape(ray, shape, intersectionPosition);
			}
		}
		return lambdaMin;
	}

}
