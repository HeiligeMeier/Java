package bezier;

import java.awt.Graphics;
import java.util.List;

class Bezier {
	private List<Point> points;
	private double h;
	int n;

	/**
	 * Berechnet Beziér-Kurven. Der Grad der Beziér-Kurve ist über die Zahl der
	 * Kontrollpunkte festgelegt.
	 * 
	 * @param points Kontrollpunkte.
	 * @param h      Schrittweite beim Zeichnen der Beziér-Kurve
	 */
	Bezier(List<Point> points, double h) {
		this.points = points;
		this.h = h;
		this.n = points.size() - 1;
	}

	/**
	 * Berechne ein Punkt-Objekt, das die zweidimensionale Koordinate der
	 * Bézier-Kurve für einen gegebenen Parameterwert errechnet.
	 * 
	 * @param t Kurvenparameter
	 * @return Koordinate der Bézier-Kurve
	 */
	Point casteljau(double t) {
		Point[][] p = new Point[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			p[0][i] = points.get(i);
		}
		for (int k = 1; k <= n; k++) {
			for (int i = k; i <= n; i++) {
				p[k][i] = new Point(1 - t, p[k-1][i-1] , t, p[k-1][i]);
			}
		}
		return p[n][n];
	}

	/**
	 * Zeichne eine Bezier-Kurve mit Stütz- und Kontrollpunkten aus points.
	 * @param graphics Grafikobjekt
	 */
	void render(Graphics graphics) {
		// Stützpunkte
//		Point p = points.get(0);
//		graphics.drawRect((int) (0.5 + p.x-1), (int) (0.5 + p.y-1), 3, 3);
		double jmp = 0.1;
		double curve = jmp;
		Point beg = casteljau(0);

		// Bezierkurve
		while (curve <= 1) {
			Point end = casteljau(curve);
			graphics.drawLine((int) beg.x, (int) beg.y, (int) end.x, (int) end.y);
			beg = end;
			curve += jmp;
		}

	}
}
