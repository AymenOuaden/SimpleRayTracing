package RayTracing.scene;

import RayTracing.mathematics.IntersectionRayShape;
import RayTracing.mathematics.Ray;
import RayTracing.mathematics.Vec3f;

/*
 * By Aymen OUADEN
 */
public class Renderer {

	public static Vec3f calculatePixelRayDirection(Scene scene, int row, int col) {
		float D = 1; // zoom factor
		float x = ((float) (col - (Scene.WIDTH / 2)) / Scene.HEIGHT);
		float y = ((float) (row - (Scene.HEIGHT / 2)) / Scene.HEIGHT);
		float z = -D;
		return new Vec3f(-x, y, z);
	}

	public static Pixel findColor(Scene scene, Vec3f rayDirection) {
		IntersectionRayShape lambdaMin = scene
				.findNearestIntersectionPoint(new Ray(scene.getObservateurPos(), rayDirection));
		if (lambdaMin != null) {
			return calculateColorRecursively(scene, lambdaMin, 5);
		} else {
			Color pixelColor = scene.getSky().findColor(rayDirection);
			return new Pixel(pixelColor);
		}
	}

	private static Pixel calculateColorRecursively(Scene scene, IntersectionRayShape lambdaMin, int co) {

		Pixel pixelReflection;
		Vec3f reflectionDirection = lambdaMin.getRay().getDirection().sub(
				lambdaMin.getNormal().scale(2 * lambdaMin.getRay().getDirection().dotProduct(lambdaMin.getNormal())));
		Vec3f reflectionRayPosition = lambdaMin.getIntersectionPosition().add(reflectionDirection.scale(0.001F));

		IntersectionRayShape reflectionIntersection = null;
		if (co > 0)
			reflectionIntersection = scene
					.findNearestIntersectionPoint(new Ray(reflectionRayPosition, reflectionDirection));

		if (reflectionIntersection != null)
			pixelReflection = calculateColorRecursively(scene, reflectionIntersection, co - 1);
		else
			pixelReflection = new Pixel(scene.getSky().findColor(reflectionDirection));

		Color intersectionColor = lambdaMin.getShape()
				.getColor(lambdaMin.getIntersectionPosition().sub(lambdaMin.getShape().getPosition()));
		Color pixelColor = Color
				.linearInterpolatedColor(intersectionColor, pixelReflection.getColor(),
						lambdaMin.getShape().getReflection())
				.multiply(scene.getLight().diffuse(scene, lambdaMin)).add(scene.getLight().specular(scene, lambdaMin));
		return new Pixel(pixelColor);
	}

}
