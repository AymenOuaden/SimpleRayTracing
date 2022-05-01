package RayTracing.scene;

import RayTracing.mathematics.IntersectionRayShape;
import RayTracing.mathematics.Ray;
import RayTracing.mathematics.Vec3f;

/*
 * By Aymen OUADEN
 */
public class Light {
	private Vec3f position;
	private static final float DIFFUSE = 0.4F;
	private static final float SPECULAR = 0.7F;
	private static final float AMBIENT = 0.2F;

	public Light(Vec3f position) {
		this.position = position;
	}

	public Vec3f getPosition() {
		return position;
	}

	public float diffuse(Scene scene, IntersectionRayShape intersectionPoint) {

		IntersectionRayShape obstacle = scene.findNearestIntersectionPoint(
				new Ray(position, intersectionPoint.getIntersectionPosition().sub(position).normalize()));
		if (obstacle != null && obstacle.getShape() != intersectionPoint.getShape()) {
			return DIFFUSE;
		} else {
			return Math.max(DIFFUSE, Math.min(1, intersectionPoint.getNormal()
					.dotProduct(position.sub(intersectionPoint.getIntersectionPosition()))));

		}
	}

	public float specular(Scene scene, IntersectionRayShape intersectionPoint) {
		Vec3f observateur = scene.getObservateurPos().sub(intersectionPoint.getIntersectionPosition()).normalize();
		Vec3f lightDirection = intersectionPoint.getIntersectionPosition().sub(position).normalize();
		Vec3f lightReflection = lightDirection
				.sub(intersectionPoint.getNormal().scale(2 * lightDirection.dotProduct(intersectionPoint.getNormal())));
		float specular = Math.max(AMBIENT, Math.min(SPECULAR, lightReflection.dotProduct(observateur)));
		return (float) Math.pow(specular, 2) * intersectionPoint.getShape().getReflection();
	}
}
