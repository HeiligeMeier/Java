import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Triangle extends JPanel {

    /** Dimension des Zeichen-Panels */
    private int width = 500;
    private int height = 500;

    /**
     * Einfache Klasse, die einen 2D-Punkt spezifiziert.
     */
    class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /** Eine Liste mit Point2D */
    List<Point> points = new LinkedList<Point>();

    /** wird nach dem dritten Punkt auf false gesetzt */
    boolean setTriangle = true;
    /** wird nach dem vierten Punkt auf false gesetzt */
    boolean setPoints = true;

    /** Konstruktor des Panels */
    public Triangle() {
        setPreferredSize(new Dimension(width, height));

        /** Koordinaten eines Mausklicks abfragen */
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                if (setPoints) {
                    int x = evt.getX();
                    int y = evt.getY();
                    points.add(new Point(x, y));
                }
                // Nach drei Punkten ist das Dreieck fertig
                if (points.size() == 3)
                    setTriangle = false;
                // Nach vier Punkten werden keine Mausklickkoordinaten mehr
                // ermittelt.
                if (points.size() == 4)
                    setPoints = false;
            }
        });
    }


    /**
     * Inhalt des Zeichen-Panels.
     */
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.BLACK);

        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.WHITE);

        // Eigener Code
        for(Point p: points) {
            graphics.drawRect(p.x, p.y, 5, 5);
        }
        if(!setTriangle)  {
            Point p1 = points.get(0);
            Point p2 = points.get(1);
            Point p3 = points.get(2);
            drawTriangle(graphics, 3);
            double flaeche = flaeche(p1, p2, p3);
            graphics.drawString("Die Fläche ist " + Math.abs((int) flaeche) + " Pixel groß", 20, 480);
            if(!setPoints) {
                Point p4 = points.get(3);
                if(istInerrhalb()) {
                    graphics.drawString("Innerhalb", 20, 460);
                } else {
                    graphics.drawString("Außerhalb", 20, 460);
                }
            }
        }
    }

    public Boolean istInerrhalb() {
        double abc = flaeche(points.get(0), points.get(1), points.get(2));
        double abp = flaeche(points.get(0), points.get(1), points.get(3));
        double bcp = flaeche(points.get(1), points.get(2), points.get(3));
        double cap = flaeche(points.get(2), points.get(0), points.get(3));
        if(abp / abc > 0 && abp / abc < 1 && bcp / abc > 0 && bcp / abc < 1 && cap / abc > 0 && cap / abc < 1) {
            return true;
        } else {
            return false;
        }
    }

    public double flaeche(Point p1, Point p2, Point p3) {
        double v1x = p1.x - p2.x;
        double v1y = p1.y - p2.y;
        double v2x = p3.x - p1.x;
        double v2y = p3.y - p1.y;
        double a = v1x * v2y;
        double b = v1y * v2x;
        return 0.5 * (a - b);
    }

    public void drawTriangle(Graphics graphics, int size) {
        for(int i = 0; i < size; i++) {
            Point p = points.get(i);
            int a = i + 1;
            if (a >= size) {
                a = a - size;
            }
            Point pp = points.get(a);
            graphics.drawLine(p.x, p.y, pp.x, pp.y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dreiecksoperationen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Triangle triang = new Triangle();
        frame.add(triang);
        frame.pack();
        frame.setVisible(true);
        while (true) {
            // Neuzeichnen anstoßen
            frame.repaint();
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}