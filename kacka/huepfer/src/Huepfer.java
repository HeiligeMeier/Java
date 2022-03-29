import java.awt.Color;
import java.awt.Graphics;
import java.math.*;

class Huepfer {

	/** Zum Zeichnen in Panel */
	Graphics graphics;

	/** Breite und Höhe Zeichen-Panel */
	int width, height;

	/** Minimal-/Maximalkoordinaten des logischen Koordinatensystems (LKOS) */
	double xMin, xMax, yMin, yMax;

	/** Hüpfer-Parameter */
	double a, b, c;

	/** Anzahl Punkte */
	int num;

	public Huepfer(Graphics graphics,
			int width, int height,
			double xMin, double xMax, double yMin, double yMax,
			double a, double b, double c, 
			int num) {
		super();
		this.graphics = graphics;
		this.width = width;
		this.height = height;
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
		this.a = a;
		this.b = b;
		this.c = c;
		this.num = num;
	}

	/**
	 * Methode zum Zeichnen eines Pixels.
	 * 
	 * HACK: Zeichne Pixel als Linie der Länge 0. Es gibt in Java2D keine Methode
	 * zum Zeichnen eines einzelnen Pixels!
	 *
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 */
	void setPixel(int x, int y) {
		graphics.drawLine(x, y, x, y);
	}

	int transformX(double x) {
		return (int) ((width / (xMax - xMin) * (x - xMin)));
	}
	int transformY(double y) {
		return (int) ((height/ (yMin - yMax) * (y - yMax)));
	}

	public void render() {
		double x = 0;
		double y = 0;
		int farbe1 = 121;
		int farbe2 = 36;
		int farbe3 = 198;
		for (int i = 0; i < this.num; i++) {
			setPixel(transformX(x), transformY(y));
			if(num % 100 == 0) {
				farbe1 = (int) (Math.random() * 255);
				farbe2 = (int) (Math.random() * 255);
				farbe3 = (int) (Math.random() * 255);
				graphics.setColor(new Color(farbe1, farbe2, farbe3));
			}
			double xx = y - Math.signum(x) * Math.sqrt(Math.abs(this.b * x - this.c));
			double yy = this.a - x;
			x = xx;
			y = yy;
		}
	}
}
