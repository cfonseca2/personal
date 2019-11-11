package impresion;

import extra.CadenasValidas;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class MatrizOriginal {

    //Old ones
    public static String[][] matriz;
    public static int totalEstados, estInicial, totalAlfabeto;
    public static ArrayList<Integer> indicesFinales;
    //new ones
    public static int s1, s2;
    public static boolean transicionE = false;
    public static ArrayList<String> coord = new ArrayList<>();
    public static ArrayList<String> lineas = new ArrayList<>();
    public static ArrayList<JLabel> labels = new ArrayList<>();
    //para las cadenas validas
    public static ArrayList<String> cadenas;

    //Clase que no modifica, solo asigna coordenadas a la matriz
    public static void asignarCoordenadas(boolean elTipo, String[][] matriz1, int totalEstados1, int totalAlfabeto1, JFrame frame, boolean transiconE1, ArrayList<Integer> indicesFinales2, String estFinal) {
        matriz = matriz1;
        totalEstados = totalEstados1;
        totalAlfabeto = totalAlfabeto1;
        transicionE = transiconE1;
        indicesFinales = indicesFinales2;
        //Ejecucion comienza aqui
        calcularCoordenadas();
        calcularLineas();
        printLabels(frame);
        //Adaptacion de pantalla
        if (elTipo) {//solo aplica para los deterministas
            int xFrame = 600, yFrame = 370;
            if (totalAlfabeto > 1) {
                int masX = totalAlfabeto - 1;
                masX *= 75;
                xFrame += masX;
            }
            if (totalEstados > 14) {
                int masY = totalEstados - 14;
                masY *= 15;
                yFrame += masY;
            }

            frame.setSize(xFrame, yFrame);
        }

        //buscar caminos
        cadenas = CadenasValidas.buscarCaminos(transicionE, matriz, totalEstados, totalAlfabeto, estInicial, estFinal, frame);
        printCadenas(frame);
        crearTabla(frame);
        OnScreen panel = new OnScreen(coord, lineas);
        panel.setBounds(150, 0, 250, 300);//mitad inferior
        frame.add(panel);
        frame.repaint();
    }

    public static void printCadenas(JFrame frame) {
        String stringCadenas = "<html>Cadenas válidas:<br/>";
        for (int i = 0; i < cadenas.size() && i < 10; i++) {
            stringCadenas += cadenas.get(i) + "<br/>";
        }
        stringCadenas += "</html>";
        Font font = new Font("Dialog", Font.PLAIN, 11);
        JLabel labelCadenas = new JLabel(stringCadenas);
        labelCadenas.setBounds(30, 130, 160, 170);
        labelCadenas.setFont(font);
        frame.add(labelCadenas);
        frame.repaint();
    }

    public static void calcularCoordenadas() {
        s1 = totalEstados / 2;
        s2 = totalEstados - s1;

        int r = (((s1 + 1) * 25) / 2) + 1;
        int x = r * (-1);
        x += 17;
        //r++;
        for (int i = 0; i < s1; i++) {
            int y = calcularY(x, true, r);
            String s = i + "," + x + "," + y + ",0";
            coord.add(s);
            x += 25;
            /*if(x==0){
                x++;
            }*/
            System.out.println("x:" + x + " y:" + y);
        }
        x = r * (-1);
        //x+=c;
        for (int i = s1; i < totalEstados; i++) {
            int y = calcularY(x, false, r);
            String s = i + "," + x + "," + y + ",0";
            coord.add(s);
            x += 25;
            /* if(x==0){
                x++;
            }*/
            System.out.println("x:" + x + "y:" + y);
        }

    }

    public static void printLabels(JFrame frame) {
        int x, y;
        for (int i = 0; i < coord.size(); i++) {
            String[] s = coord.get(i).split(",");
            labels.add(new JLabel(s[0]));
            x = (Integer.parseInt(s[1])) + 305;

            y = (Integer.parseInt(s[2])) + 96;
            labels.get(i).setBounds(x, y, 15, 20);
            frame.add(labels.get(i));
        }
    }

    public static void calcularLineas() {
        int x1, y1, x2, y2;
        for (int i = 0; i < totalEstados; i++) {
            for (int k = 0; k < totalAlfabeto; k++) {
                if (!"-".equals(matriz[i][k])) {
                    String[] s = matriz[i][k].split(",");
                    for (int j = 0; j < s.length; j++) {
                        String[] token = coord.get(i).split(",");
                        x1 = Integer.parseInt(token[1]);
                        y1 = Integer.parseInt(token[2]);
                        if (y1 < 0) {
                            y1 += 1;
                        }
                        token = coord.get(Integer.parseInt(s[j])).split(",");
                        x2 = Integer.parseInt(token[1]);
                        y2 = Integer.parseInt(token[2]);
                        if (y2 < 0) {
                            y2 += 1;
                        }
                        //Comndiciones para alinear un poco
                        if (i < s1) {
                            y1 += 12;

                        }
                        x1 += 8;
                        if (Integer.parseInt(s[j]) < s1) {
                            y2 += 12;

                        }
                        x2 += 8;
                        lineas.add(x1 + "," + y1 + "," + x2 + "," + y2);
                    }
                }
            }
        }
    }

    public static int calcularY(int x, boolean arriba, int r) {//s1 - totalEstados
        int result;
        double s = Math.sqrt((r * r) - (x * x));
        if (arriba) {
            s *= -1;
        }
        result = (int) s;
        double resta = s - result;
        if (result < 0 && resta < -.5) {
            result--;
        } else if (result > 0 && resta > .5) {
            result++;
        }
        return result;
    }

    public static void tupla(JLabel label, JFrame frame) {
        Font font = new Font("Dialog", Font.ROMAN_BASELINE, 15);
        label.setBounds(30, 30, 140, 85);
        label.setFont(font);
        frame.add(label);
        frame.repaint();

    }

    public static void crearTabla(JFrame frame) {
        Object[] nombresColumnas = new Object[totalAlfabeto + 1];
        Object[][] data = new Object[totalEstados + 1][totalAlfabeto + 1];
        data[0][0] = "Estados";
        nombresColumnas[0] = "Estados";
        int p;
        if (transicionE) {
            p = 2;
            data[0][1] = "E";
            nombresColumnas[1] = "E";
        } else {
            p = 1;
        }
        int num = 1;
        for (int i = p; i < totalAlfabeto + 1; i++) {
            int n = num + 96;
            num++;
            data[0][i] = Character.toString((char) n);
            nombresColumnas[i] = Character.toString((char) n);
        }
        boolean esFinal;
        for (int k = 1; k < matriz.length + 1; k++) {
            esFinal = false;
            data[k][0] = k - 1;
            for (int j = 0; j < indicesFinales.size(); j++) {
                if ((k - 1) == indicesFinales.get(j)) {
                    esFinal = true;
                }
            }
            if (esFinal) {
                data[k][0] += "*";
            }
            //data[k][0]=k-1+"*";//astersico loco
            for (int j = 1; j < totalAlfabeto + 1; j++) {
                data[k][j] = matriz[k - 1][j - 1];
            }
        }
        JTable table = new JTable(data, nombresColumnas);
        table.setBounds(450, 30, (totalAlfabeto + 1) * 75, ((totalEstados + 1) * 16));
        frame.add(table);
        frame.repaint();
    }
}
