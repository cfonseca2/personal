package resuelvenMatrices;

import extra.Nodo;
import impresion.Determinista;
import java.util.ArrayList;
import javax.swing.JFrame;

public class NoDeterminista {

    public static ArrayList<Nodo> nuevos = new ArrayList<Nodo>();//Donde se guardaran todos los estados que salgan
    public static String[][] matriz2;
    public static String estF;
    public static ArrayList<Integer> indicesFinales;

    public static void resolver(String[][] matriz, int estInicial, String estFinal, int totalAlfabeto, int totalEstados, JFrame frame) {
        estF = estFinal;
        //Para los estados finales
        //Para los estados finales
        String[] stringFinales = estFinal.split(",");
        indicesFinales = new ArrayList();
        boolean banderaFinal = false;
        //Resto de procedimiento
        int contEstados = 1;
        int noVisitados = 1;
        boolean hayEstadoVacio = false;
        //agregamos el estado inicial a la lista
        nuevos.add(new Nodo(0, String.valueOf(estInicial)));
        //Comenzamos a recorrer a partir del estado inical
        while (noVisitados > 0) {
            int cont = -1; //para que comience a evaluar en 0
            do {
                cont++;
            } while (nuevos.get(cont).getVisitado());
            nuevos.get(cont).setVisitado(true);
            noVisitados--;//porque ya encontramos uno en el do while
            String evaluando = nuevos.get(cont).getRepresenta() + ",";

            String[] elem;//separar elementos del estado que representa el no visitado
            if (nuevos.get(cont).getRepresenta().length() > 2) {//el 2 es por las comas
                elem = nuevos.get(cont).getRepresenta().split(",");
            } else {
                elem = new String[]{nuevos.get(cont).getRepresenta()};
            }

            for (int k = 0; k < totalAlfabeto; k++) {//recorrer por cada letra
                ArrayList<String> sobre = new ArrayList<String>();
                //sobre es para que conforme vayamos recorriendo varios estados no repitamos una conexion
                for (int j = 0; j < elem.length; j++) {
                    //System.out.println(elem[j] + "**");
                   // try {
                        int n = Integer.parseInt(elem[j]);
                        try {
                            if (!matriz[n][k].equals("-")) {
                                String[] s = matriz[n][k].split(",");
                                for (int r = 0; r < s.length; r++) {//loco for
                                    boolean nuevo = true;
                                    for (int i = 0; i < sobre.size(); i++) {
                                        if (s[r].equals(sobre.get(i))) {
                                            nuevo = false;
                                        }
                                    }
                                    if (nuevo) {
                                        sobre.add(s[r]);
                                    }
                                }
                            }

                    } catch (NumberFormatException e) {
                        //System.out.println("No se imprime: " + elem[j]);
                    }
                }
                //ahora sobre lo acomodamos en un String con el formato por comas
                evaluando = "";
                banderaFinal = false;
                for (int h = 0; h < sobre.size(); h++) {
                    //for para saber si va a ser finale
                    for (int i = 0; i < stringFinales.length; i++) {
                        if (sobre.get(h).equals(stringFinales[i])) {
                            banderaFinal = true;
                        }
                    }
                    if (h != sobre.size() - 1) {
                        evaluando += sobre.get(h) + ",";
                    } else {
                        evaluando += sobre.get(h);
                    }
                }
                //Buscar si evaluando no se repite en la lista de nuevos
                boolean seRepite = false;
                if (evaluando.length() > 0) {
                    String[] eval = evaluando.split(",");
                    for (int i = 0; i < nuevos.size(); i++) {
                        int match = 0;//para saber cuantos elementos han coincidido
                        if (nuevos.get(i).getRepresenta().length() == evaluando.length()) {
                            String[] s = nuevos.get(i).getRepresenta().split(",");
                            for (int j = 0; j < eval.length; j++) {
                                for (int r = 0; r < s.length; r++) {
                                    if (eval[j].equals(s[r])) {
                                        match++;
                                    }
                                }
                            }
                        }
                        if (match == eval.length) {
                            seRepite = true;
                            i = nuevos.size();
                        }
                    }
                    if (!seRepite) {//si no se repite agregamos a nuevos como nuevo estado
                        nuevos.add(new Nodo(contEstados, evaluando));
                        if (banderaFinal) {
                            indicesFinales.add(contEstados);
                        }
                        //System.out.println("\tN: " + contEstados + "  " + evaluando);
                        contEstados++;
                        noVisitados++;
                    }
                    //Finalmente, se agregue o no a nuevos, debemos guardar su conexion con la letra
                    nuevos.get(cont).addConex(evaluando);
                    //System.out.println("\t\tC: " + (contEstados - 1) + "  " + evaluando);
                } else {
                    nuevos.get(cont).addConex("-");
                    hayEstadoVacio = true;
                }
            }//Cierre por letras
        }//del while mayor
        //Agregamos estado vacio
        if (hayEstadoVacio) {
            nuevos.add(new Nodo(contEstados, "-"));
            for (int i = 0; i < totalAlfabeto; i++) {
                nuevos.get(contEstados).addConex("-");
            }
            //System.out.println("\tN: " + contEstados + "  " + evaluando);
            contEstados++;
        }

        armarMatriz(nuevos, contEstados, totalAlfabeto, frame);
    }

    //el siguiente metodo acomodara todo en la nueva matriz
    public static void armarMatriz(ArrayList<Nodo> nuevos, int contEstados, int totalAlfabeto, JFrame frame) {
        matriz2 = new String[contEstados][totalAlfabeto];
        for (int i = 0; i < nuevos.size(); i++) {//------ estados
            int match;
            for (int j = 0; j < totalAlfabeto; j++) {//------- letras
                // if (!("-").equals(nuevos.get(i).getConx(j))) {
                for (int k = 0; k < nuevos.size(); k++) {//----
                    if (nuevos.get(i).getConx(j).length() == nuevos.get(k).getRepresenta().length()) {
                        match = 0;
                        String[] s = nuevos.get(k).getRepresenta().split(",");
                        String[] z = nuevos.get(i).getConx(j).split(",");
                        for (int p = 0; p < z.length; p++) {//---
                            for (int r = 0; r < s.length; r++) {
                                if (z[p].equals(s[r])) {
                                    match++;
                                }
                            }
                        }
                        if (match == z.length) {
                            matriz2[i][j] = String.valueOf(nuevos.get(k).getNombre());
                            k = nuevos.size();
                        }
                    }
                }
                // } else {
                /* matriz2[i][j] = "-";
                 }*/
            }
        }
       // System.out.println("Estados finales: " + indicesFinales.size());
        Determinista.asignarCoordenadas(matriz2, contEstados, totalAlfabeto, frame, false, estF, indicesFinales);
        //impresino de matriz
        /*for (int i = 0; i < contEstados; i++) {
         System.out.print(i + ":   ");
         for (int j = 0; j < totalAlfabeto; j++) {
         System.out.print(matriz2[i][j] + "  ");
         }
         System.out.println();
         }*/

    }
}
