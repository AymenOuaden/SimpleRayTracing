
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import RayTracing.mathematics.Vec3f;
import RayTracing.scene.Color;
import RayTracing.scene.Light;
import RayTracing.scene.Pixel;
import RayTracing.scene.Renderer;
import RayTracing.scene.Scene;
import RayTracing.shapes.Plane;
import RayTracing.shapes.Sphere;

/*
 *  By Aymen OUADEN
 */

public class JavaTga {

	public static List<Scene> scenes;
	public static int NB_SCENES = 3;

	private static void writeShort(FileOutputStream fout, int n) throws IOException {
		fout.write(n & 255);
		fout.write((n >> 8) & 255);
	}

	private static void saveTGA(String filename, byte buffer[], int width, int height)
			throws IOException, UnsupportedEncodingException {
		FileOutputStream fout = new FileOutputStream(new File(filename));
		fout.write(0);
		fout.write(0);
		fout.write(2);
		writeShort(fout, 0);
		writeShort(fout, 0);
		fout.write(0);
		writeShort(fout, 0);
		writeShort(fout, 0);
		writeShort(fout, width);
		writeShort(fout, height);
		fout.write(24); //
		fout.write(0);
		fout.write(buffer);
		fout.close();
	}

	private static void initScenes() {
		System.out.println("Init scenes");
		scenes = new ArrayList<Scene>();

		// Scene 1
		Scene scene1 = new Scene(new Light(new Vec3f(20, 50, 20)), new Vec3f(0, 0, 10));
		scene1.addShape(new Sphere(new Vec3f(-2, -0.3f, 5), 0.6F, Color.BLACK, 0.9F));
		scene1.addShape(new Sphere(new Vec3f(-1, -0.7f, 4), 0.3F, Color.RED, 0.5F));
		scene1.addShape(new Sphere(new Vec3f(0, -0.7f, 4), 0.3F, Color.GREEN, 0.5F));
		scene1.addShape(new Sphere(new Vec3f(1, -0.7f, 4), 0.3F, Color.BLUE, 0.5F));
		scene1.addShape(new Sphere(new Vec3f(2, -0.3f, 5), 0.6F, Color.BLACK, 0.9F));
		scene1.addShape(new Plane(Color.BLACK, 0.3F));
		scenes.add(scene1);

		// Scene 2
		Scene scene2 = new Scene(new Light(new Vec3f(20, 50, 20)), new Vec3f(0, 0, 10));
		int z = 0;
		for (int i = 0; i < 20; i += 2) {
			scene2.addShape(new Sphere(new Vec3f(-i, -0.3f, z), 0.6F, Color.BLACK, 0.9F));
			scene2.addShape(new Sphere(new Vec3f(i, -0.3f, z), 0.6F, Color.BLACK, 0.9F));
			z = z - 2;
		}
		scene2.addShape(new Sphere(new Vec3f(0, 4.5f, -20), 3.5F, Color.BLACK, 0.9F));
		scene2.addShape(new Plane(Color.BLACK, 0.3F));
		scenes.add(scene2);

		// Scene 3 ----- Create a random scene
		Scene scene3 = new Scene(new Light(
				new Vec3f((float) Math.random() * 100, (float) Math.random() * 100, (float) Math.random() * 100)),
				new Vec3f(0, 0, 10));
		z = -20;
		for (int i = 0; i < (int) (Math.random() * 10 + 10); i++) {
			scene3.addShape(new Sphere(new Vec3f(-i, -0.3f, z), 0.6F, Color.getRandomColor(), (float) Math.random()));
			scene3.addShape(new Sphere(new Vec3f(i, -0.3f, z), 0.6F, Color.getRandomColor(), (float) Math.random()));
			z = z + 2;
		}
		scene3.addShape(new Plane(Color.BLACK, 0.3F));
		scenes.add(scene3);

	}

	public static void main(String[] args) {

		double start = System.currentTimeMillis();
		System.out.println("Start : ");
		initScenes();
		int co = 0;
		for (Scene scene : scenes) {
			byte buffer[] = new byte[3 * Scene.WIDTH * Scene.HEIGHT];
			for (int row = 0; row < Scene.HEIGHT; row++) { // for each row of the image
				for (int col = 0; col < Scene.WIDTH; col++) { // for each column of the image

					Vec3f rayDirection = Renderer.calculatePixelRayDirection(scene, row, col);
					Pixel pixel = Renderer.findColor(scene, rayDirection);

					int index = 3 * ((row * Scene.WIDTH) + col); // compute index of color for pixel (x,y) in the buffer
					buffer[index] = (byte) (pixel.getColor().getBlue() * 255); // Blue in the left
					buffer[index + 1] = (byte) (pixel.getColor().getGreen() * 255); // Green in the
					buffer[index + 2] = (byte) (pixel.getColor().getRed() * 255); // Red in the right
				}
			}
			String name = "scene_" + (co += 1) + ".tga";
			try {
				saveTGA(name, buffer, Scene.WIDTH, Scene.HEIGHT);
			} catch (Exception e) {
				System.err.println("TGA file not created :" + e);
			}
		}

		double end = System.currentTimeMillis();
		System.out.println("Done in " + ((end - start) * Math.pow(10, -3)) + " s");
	}

}
