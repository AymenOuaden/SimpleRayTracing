package RayTracing.scene;

import javax.imageio.ImageIO;

import RayTracing.mathematics.Vec3f;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 *  By Aymen OUADEN
 */

public class Sky {
	private BufferedImage image = null;
	private String fileName = "sky.jpg";

	 public Sky() {}

	/*public Sky() {
		image = new BufferedImage(Scene.WIDTH, Scene.HEIGHT, BufferedImage.TYPE_INT_RGB);
		try {
			System.out.println("Waiting for Sky image to load ...");
			image = ImageIO.read(getClass().getResourceAsStream("/res/" + fileName)); // image =
		} catch (IOException e) {
			System.err.println("No image selected....");
			System.out.println("The backGround is black.");
		}
	}*/

	public Color findColor(Vec3f point) {
		if (image != null) {
			float u = (float) (0.5 + Math.atan2(point.getZ(), point.getX()) / (2 * Math.PI));
			float v = (float) (0.5 - Math.asin(point.getY()) / Math.PI);
			try {
				return Color.calculateColor(
						image.getRGB((int) (u * (image.getWidth() - 1)), (int) (v * (image.getHeight() - 1))));
			} catch (Exception e) {
				return Color.BLUECIEL;
			}
		}
		return Color.BLUECIEL;

	}

}
