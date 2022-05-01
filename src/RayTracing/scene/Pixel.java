package RayTracing.scene;

/*
 *  By Aymen OUADEN
 */
public class Pixel {
	private int row, col;
	private Color color;

	public Pixel(Color color) {
		this.color = color;
	}

	public Pixel(int row, int col, Color color) {
		this.row = row;
		this.col = col;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}