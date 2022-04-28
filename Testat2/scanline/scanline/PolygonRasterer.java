package scanline;

import java.awt.Graphics;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Rastert Polygone mit Scanline-Verfahren.
 * 
 * Zur Vereinfachung nehmen wir an, dass sich Polygone immer komplett im
 * Viewport befinden, den Rand also nicht schneiden.
 */
public class PolygonRasterer {
	/** Zum Zeichnen */
	private Graphics graphics;
	/** Höhe des Zeichenfensters */
	private int height;
	/** Die scanline.Edge Table */
	private LinkedList<Edge> edgeTable = new LinkedList<>();
	/** Die Active scanline.Edge Table */
	private LinkedList<Edge> activeEdgeTable = new LinkedList<>();
	private int r;

	//selber
	private LinkedList<Edge> remove = new LinkedList<>();

	public PolygonRasterer(int height) {
		this.height = height;
	}

	/**
	 * Umsetzung des Scan-Line-Verfahrens
	 */
	public void rasterize() {
		for (int y = 0; y < height; y++) {
			int even = 0;
			int odd = 1;
			for (Edge edge: edgeTable) {
				int yMin = edge.getYMin();
				// Kanten mit yMin = y werden in AET aufgenommen
				if (yMin == y) {
					activeEdgeTable.add(new Edge(edge));
				}
			}

			// AET xSchnitt
			activeEdgeTable.sort(Comparator.comparing(Edge::getxIntersect));

			// Fülle Pixel zwischen Paaren von x-Koordinaten
				for(; odd < activeEdgeTable.size(); odd += 2) {
					int xMin = (int) activeEdgeTable.get(even).getxIntersect();
					int xMax = (int) activeEdgeTable.get(odd).getxIntersect();
					graphics.drawLine(xMin, y, xMax, y);
					even += 2;
				}

			// Kanten aus AET entfernen wenn yMax == y
			for (Edge edge: activeEdgeTable) {
				int yMax = edge.getyMax();
				if(yMax == y) {
					remove.add(edge);
				}
			}
			activeEdgeTable.removeAll(remove);

				// Aktualisiere für alle Kanten der AET die xSchnitt-Werte:
				// xSchnitt = xSchnitt + 1/m;
			for (int i = 0; i < activeEdgeTable.size(); i++) {
				double m = activeEdgeTable.get(i).getxIntersect() + activeEdgeTable.get(i).getmReci();
				activeEdgeTable.get(i).setxIntersect(m);
			}
		}
	}

	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	public void addEdges(LinkedList<Edge> edges) {
		edgeTable.addAll(edges);
	}
}
