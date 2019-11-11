package resuelvenMatrices;

import extra.Nodo;
import impresion.Determinista;
import java.util.ArrayList;
import javax.swing.JFrame;
//Fue en este momento que Erick conocio el verdadero terror

public class TransicionE {

    public static ArrayList<Nodo> nuevos = new ArrayList<Nodo>();//Donde se guardaran todos los estados que salgan
    public static String[][] matriz2;
    public static String estF;
    public static ArrayList<Integer> indicesFinales;
    //String que separe estados finales y un arrray list que guarde los indices de los que seran estados finales
    

    public static void resolver(String[][] matriz, int estInicial, String estFinal, int totalAlfabeto, int totalEstados, JFrame frame) {
        estF= estFinal;
        //Para los estados finales
        String[] stringFinales = estFinal.split(",");
        indicesFinales = new ArrayList();
        boolean banderaFinal=false;
        //Resto del procedimiento
        int contEstados = 1;
        int noVisitados = 1;
        boolean hayEstadoVacio = false;
        //agregamos nodo inicial a la lista
        nuevos.add(new Nodo(0, String.valueOf(estInicial)));
        //comenzamos a recorrer a partir del estado inicial
        while (noVisitados > 0) {
            //buscamos en la lista quien es el siguiente no visitado
            int cont = -1;
            do {
                cont++;
            } while (nuevos.get(cont).getVisitado());// Este es X1
            nuevos.get(cont).setVisitado(true);
            noVisitados--;//porque ya encontramos uno en el do while
            String evaluando = nuevos.get(cont).getRepresenta() + ",";
            //System.out.print("E - " + nuevos.get(cont).getRepresenta() + "  ");//----------------
            ArrayList<String> sobre2 = new ArrayList<String>();
            String[] elem;//separar elementos del estado que representa el no visitado
            if (nuevos.get(cont).getRepresenta().length() > 2) {//el 2 es por las comas
                elem = nuevos.get(cont).getRepresenta().split(",");
                for (int i = 0; i < elem.length; i++) {
                    sobre2.add(elem[i]);
                }
            } else {
                elem = new String[]{nuevos.get(cont).getRepresenta()};
                sobre2.add(elem[0]);
            }
            //Recorrer por E, los que salgan de aqui son los que agregamos a nuevos
            for (int j = 0; j < elem.length; j++) {
                int n = Integer.parseInt(elem[j]);
                if (!matriz[n][0].equals("-")) {
                    String[] s = matriz[n][0].split(",");
                    for (int r = 0; r < s.length; r++) {//loco for
                        boolean nuevo = true;
                        for (int i = 0; i < sobre2.size(); i++) {
                            if (s[r].equals(sobre2.get(i))) {
                                nuevo = false;
                            }
                        }
                        if (nuevo) {
                            sobre2.add(s[r]);
                        }
                    }
                }
            }
            evaluando = "";
            for (int h = 0; h < sobre2.size(); h++) {
                if (h != sobre2.size() - 1) {
                    evaluando += sobre2.get(h) + ",";
                } else {
                    evaluando += sobre2.get(h);
                }
            }
            // System.out.println(" -->" + evaluando);//-------------------------------
            //------------------------------------------------------------
            //System.out.println("elem:-" + evaluando+"-");
            elem = evaluando.split(",");// Este es ya X2
            //evaluando es lo que evaluaremos con el alfabeto  
            //el contador k comienza en 1 porque ya no buscamos conexiones con E que es el indice 0
            for (int k = 1; k < totalAlfabeto; k++) {//recorrer por cada letra
                ArrayList<String> sobre = new ArrayList<String>();
                //sobre es para que conforme vayamos recorriendo varios estados no repitamos una conexion
                for (int j = 0; j < elem.length; j++) {
                    //System.out.println(elem[j] + "**");
                    try {
                        int n = Integer.parseInt(elem[j]);
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
                        System.out.println("No se imprime: " + elem[j]);
                    }
                }
                //ahora sobre lo acomodamos en un String con el formato por comas
                evaluando = "";
                banderaFinal=false;
                for (int h = 0; h < sobre.size(); h++) {
                    //for para saber si va a ser finale
                    for(int i = 0; i< stringFinales.length;i++){
                        if(sobre.get(h).equals(stringFinales[i])){
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
                        //Agregamos si es final o no
                        if(banderaFinal){
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
                    hayEstadoVacio = true;
                    nuevos.get(cont).addConex("-");
                    
                }
            }//Cierre por letras
        }//del while mayore
        //Agregamos estado vacio
        if (hayEstadoVacio) {
            nuevos.add(new Nodo(contEstados, "-"));
            for (int i = 0; i < totalAlfabeto; i++) {
                nuevos.get(contEstados).addConex("-");
            }
            contEstados++;
        }

        armarMatriz(nuevos, contEstados, totalAlfabeto,frame);
    }

    //el siguiente metodo acomodara todo en la nueva matriz
    public static void armarMatriz(ArrayList<Nodo> nuevos, int contEstados, int totalAlfabeto,JFrame frame) {
        matriz2 = new String[contEstados][totalAlfabeto - 1];
        for (int i = 0; i < nuevos.size(); i++) {//------ estados
            int match;
            for (int j = 0; j < totalAlfabeto - 1; j++) {//------- letras
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
                //} else {
                //    matriz2[i][j] = "-";
                // }
            }
        }
        Determinista.asignarCoordenadas(matriz2, contEstados, totalAlfabeto, frame,true,estF,indicesFinales);
        //impresino de matriz
       /* for (int i = 0; i < contEstados; i++) {
            System.out.print(i + ":   ");
            for (int j = 0; j < totalAlfabeto - 1; j++) {
                System.out.print(matriz2[i][j] + "  ");
            }
            System.out.println();
        }*/
    }
}
