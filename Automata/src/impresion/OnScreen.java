package impresion;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class OnScreen extends JPanel {

    ArrayList<String> coord = new ArrayList<>();
    ArrayList<String> lineas = new ArrayList<>();

    public OnScreen(ArrayList<String> coord, ArrayList<String> lineas) {
        this.coord = coord;
        this.lineas = lineas;
    }

    public void printNodos(Graphics g, ArrayList<String> coord) {
        // System.out.println("length: "+coord.size());
        if (coord.size() < 8) {
            for (int i = 0; i < coord.size(); i++) {
                String[] s = coord.get(i).split(",");
                int x, y;
                // if (Integer.parseInt(s[1]) > 0) {
                x = Integer.parseInt(s[1]) + 150;
                // } else {
                //     x = Integer.parseInt(s[1]) + 100;
                // }
                y = Integer.parseInt(s[2]) + 100;
                g.drawOval(x, y, 15, 15);
                //System.out.println("x:" + x + "y:" + y);
            }
        }

    }

    public void printLineas(Graphics g) {
        for (int i = 0; i < lineas.size(); i++) {
            String[] s = lineas.get(i).split(",");
            int x1, y1, x2, y2;
            x1 = (Integer.parseInt(s[0])) + 150;//+100
            y1 = (Integer.parseInt(s[1])) + 100;//+80
            /*if (x1 < 0) {
                x1 += 105;
            } else {
                x1 += 92;//positivas
            }*/
            x2 = (Integer.parseInt(s[2])) + 150;
            y2 = (Integer.parseInt(s[3])) + 100;
            /*if (x2 < 0) {
                x2 += 105;
            } else {
                x2 += 92;
            }*/
            g.drawLine(x1, y1, x2, y2);
            //System.out.println("x:" + x1 + "y:" + y2);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        printNodos(g, coord);
        printLineas(g);
    }
}
