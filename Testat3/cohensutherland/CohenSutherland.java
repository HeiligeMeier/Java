import java.awt.*;

/**
 * Clipping nach Cohen-Sutherland.
 */
public class CohenSutherland {
	/** Zum Zeichnen */
	private Graphics graphics;

	/** Dimension des Clipping-Rechtecks */
	private int xmin;
	private int xmax;
	private int ymin;
	private int ymax;

	/**
	 * Ctor.
	 *
	 * @param graphics Zum Zeichnen
	 * @param xmin     minimale x-Koordinate
	 * @param ymin     minimale y-Koordinate
	 * @param xmax     maximale x-Koordinate
	 * @param ymax     maximale y-Koordinate
	 */
	public CohenSutherland(Graphics graphics, int xmin, int ymin, int xmax, int ymax) {
		super();
		this.graphics = graphics;
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
	}

	/**
	 * Berechne den Cohen-Sutherland-Outputcode für einen Punkt.
	 *
	 * @formatter:off
	 * viertletztes Bit = 1 <=> y > ymax
	 * drittletztes Bit = 1 <=> y < ymin
	 * vorletztes Bit = 1 <=> x > xmax
	 * letztes Bit = 1 <=> x < xmin
	 * @formatter:on
	 *
	 * Die 4 Bits werden sehr verschwenderisch in einem int untergebracht.
	 *
	 * Warum kein byte? Die bitweisen Operationen sind für Datentyp byte nicht
	 * definiert! Genauer werden z.B. die Bytes bei byte1 | byte2 zu ints gecastet
	 * und das Ergebnis ist ein int.
	 * Mehr Details: <a href="https://stackoverflow.com/questions/27582233/why-byte-and-short-values-are-promoted-to-int-when-an-expression-is-evaluated">Stack Overflow</a>
	 *
	 * @param x x-Koordinate Punkt
	 * @param y y-Koordinate Punkt
	 * @return Outputcode
	 */
	int outputCode(int x, int y) {
		int b1 = 0;
		int b2 = 0;
		int b3 = 0;
		int b4 = 0;
		if (y > ymax) {
			b1 = 1;
		}
		if (y < ymin) {
			b2 = 1;
		}
		if (x > xmax) {
			b3 = 1;
		}
		if (x < xmin) {
			b4 = 1;
		}
		return b1 * Area.GTYMAX + b2 * Area.LTYMIN + b3 * Area.GTXMAX + b4 * Area.LTXMIN;
	}

	/**
	 * Clipping nach Cohen-Sutherland. Die Linie von (xA,yA) nach (xE,yE) wird an
	 * dem durch die Attribute (xmin,ymin) und (xmax,ymax) definierten Rechteck
	 * geclippt und der sichtbare Teil der Linie gezeichnet.
	 *
	 * @param xA x-Koordinate Anfangspunkt Linie
	 * @param yA y-Koordinate Anfangspunkt Linie
	 * @param xE x-Koordinate Endpunkt Linie
	 * @param yE y-Koordinate Endpunkt Linie
	 */
	void clipLine(int xA, int yA, int xE, int yE) {
		int ocA = outputCode(xA, yA);
		int ocE = outputCode(xE, yE);
		double xSchnitt = xE - xA;
		double ySchnitt = yE - yA;
		double yDiv = xSchnitt / ySchnitt;
		double xDiv = ySchnitt / xSchnitt;
		int count = 0;

		// Linien die außerhalb des Rechtecks liegen
		if ((ocA | ocE) != 0b0000) {
//			graphics.setColor(Color.GRAY);
//			graphics.drawLine(xA, yA, xE, yE);
			if ((ocA & 0b1000) != 0 & (ocE & 0b1000) != 0 ||
					(ocA & 0b0100) != 0 & (ocE & 0b0100) != 0 ||
					(ocA & 0b0010) != 0 & (ocE & 0b0010) != 0 ||
					(ocA & 0b0001) != 0 & (ocE & 0b0001) != 0) {
//				System.out.println("Linie außerhalb");
				// Schnittpunkt obere Linie
			} else if ((ocA & 0b1000) != 0) {
//				System.out.println("oben yA");
				double y = ymax;
				if (ySchnitt != 0) {
					double x = yDiv * (y - yE) + xE;
					clipLine((int) x, (int) y, xE, yE);
				}
			} else if ((ocE & 0b1000) != 0) {
//				System.out.println("oben yE");
				double y = ymax;
				if (ySchnitt != 0) {
					double x = yDiv * (y - yE) + xE;
					clipLine(xA, yA, (int) x, (int) y);
				}

				// Schnittpunkt untere Linie
			} else if ((ocA & 0b0100) != 0) {
//				System.out.println("unten");
				double y = ymin;
				if (ySchnitt != 0) {
					double x = yDiv * (y - yE) + xE;
					clipLine((int) x, (int) y, xE, yE);
				}
			} else if ((ocE & 0b0100) != 0) {
//				System.out.println("unten");
				double y = ymin;
				if (ySchnitt != 0) {
					double x = yDiv * (y - yE) + xE;
					clipLine(xA, yA, (int) x, (int) y);
				}

				// Schnittpunkt rechte Linie
			} else if ((ocA & 0b0010) != 0) {
//				System.out.println("rechts");
				double x = xmax;
				if (xSchnitt != 0) {
					double y = xDiv * (x - xE) + yE;
					clipLine((int) x, (int) y, xE, yE);
				}
			} else if ((ocE & 0b0010) != 0) {
//				System.out.println("rechts");
				double x = xmax;
				if (xSchnitt != 0) {
					double y = xDiv * (x - xE) + yE;
					clipLine(xA, yA, (int) x, (int) y);
				}

				// Schnittpunkt linke Linie
			} else if ((ocA & 0b0001) != 0) {
//				System.out.println("links");
				double x = xmin;
				if (xSchnitt != 0) {
					double y = xDiv * (x - xE) + yE;
					clipLine((int) x, (int) y, xE, yE);
				}
			} else if ((ocE & 0b0001) != 0) {
//				System.out.println("links");
				double x = xmin;
				if (xSchnitt != 0) {
					double y = xDiv * (x - xE) + yE;
					clipLine(xA, yA, (int) x, (int) y);
				}
			}
		} else {
			graphics.setColor(Color.GREEN);
			graphics.drawLine(xA, yA, xE, yE);
		}
	}
}