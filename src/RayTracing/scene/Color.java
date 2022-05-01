package RayTracing.scene;

/*
 * By Aymen OUADEN
 */
public class Color {

	public static final Color BLACK = new Color(0F, 0F, 0F);
	public static final Color WHITE = new Color(1F, 1F, 1F);
	public static final Color RED = new Color(1F, 0F, 0F);
	public static final Color GREEN = new Color(0F, 1F, 0F);
	public static final Color GREENLIGHT = new Color(0F, 0.5F, 0F);
	public static final Color LIMEGREEN = new Color(0.2F, 1F, 0.2F);
	public static final Color BLUE = new Color(0F, 0F, 1F);
	public static final Color BLUECIEL = new Color(0.47F, 0.7F, 0.99F);
	public static final Color MAGENTA = new Color(1.0F, 0.0F, 1.0F);
	public static final Color GRAY = new Color(0.5F, 0.5F, 0.5F);
	public static final Color DARK_GRAY = new Color(0.2F, 0.2F, 0.2F);
	public static final Color CHOCOLAT = new Color(0.82F, 0.41F, 0.12f);
	public static final Color SANDYBROWN = new Color(0.95F, 0.56F, 0.37f);

	public static Color getRandomColor() {
		switch ((int) (Math.random() * 10)) {
		case 0:
			return BLACK;
		case 1:
			return GRAY;
		case 2:
			return RED;
		case 3:
			return BLUE;
		case 4:
			return CHOCOLAT;
		case 5:
			return MAGENTA;
		case 6:
			return LIMEGREEN;
		case 7:
			return DARK_GRAY;
		case 8:
			return GREEN;

		default:
			return WHITE;
		}
	}

	private float red;
	private float green;
	private float blue;

	public Color(float red, float green, float blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public float getRed() {
		return red;
	}

	public float getGreen() {
		return green;
	}

	public float getBlue() {
		return blue;
	}

	public Color multiply(float diffuse) {
		return new Color(red * Math.min(1, diffuse), green * Math.min(1, diffuse), blue * Math.min(1, diffuse));
	}

	public Color add(float specular) {
		return new Color(Math.min(1, red + specular), Math.min(1, green + specular), Math.min(1, blue + specular));
	}

	public static Color calculateColor(int argb) {
		int blue = (argb) & 0xFF;
		int green = (argb >> 8) & 0xFF;
		int red = (argb >> 16) & 0xFF;
		return new Color(blue / 255F, green / 255F, red / 255F);
	}

	private static float linearInterpolatedColor(float rgbColor, float pixelReflactionColor, float shapeReflection) {
		return rgbColor + shapeReflection * (pixelReflactionColor - rgbColor);
	}

	public static Color linearInterpolatedColor(Color intersectionColor, Color pixelReflectionColor,
			float shapeReflection) {
		return new Color(
				linearInterpolatedColor(intersectionColor.getRed(), pixelReflectionColor.getRed(), shapeReflection),
				linearInterpolatedColor(intersectionColor.getGreen(), pixelReflectionColor.getGreen(), shapeReflection),
				linearInterpolatedColor(intersectionColor.getBlue(), pixelReflectionColor.getBlue(), shapeReflection));
	}

}