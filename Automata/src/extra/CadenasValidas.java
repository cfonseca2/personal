package extra;

import java.util.ArrayList;
import javax.swing.JFrame;

public class CadenasValidas {

    public static int totalEstados, totalAlfabeto, estInicial;
    public static String[][] matriz;
    public static String[] estFinales;
    //Lista donde se van a guardar las cadenas validas
    public static ArrayList<String> cadenas;
    public static int contTotal = 0;//para que no haga infinitas recursividades

    public static ArrayList<String> buscarCaminos(boolean transE, String[][] matriz2, int totalEstados2, int totalAlfabeto2, int estInicial2, String estFinal2, JFrame frame) {
        contTotal = 0;
        cadenas = new ArrayList();
        totalEstados = totalEstados2;
        totalAlfabeto = totalAlfabeto2;
        matriz = matriz2;
        estInicial = estInicial2;
        estFinales = estFinal2.split(",");
        String t = "";
        /*for(int i =0; i<totalAlfabeto;i++){
            if(!"-".equals(matriz[estInicial][i])){
                recursivo(1,i,t);
            }
            
        }*/
        if (transE) {
            recursivoE(1, estInicial, t);
        } else {
            recursivo(1, estInicial, t);
        }

        /* for (int i = 0; i < cadenas.size(); i++) {
            System.out.println(" " + cadenas.get(i));
        }*/
        return cadenas;
    }

    public static void recursivoE(int cont, int est, String cadena) {
        boolean found = false;
        for (int i = 0; i < estFinales.length; i++) {
            if (est == Integer.parseInt(estFinales[i])) {
                //System.out.println(est+" "+estFinales[i]);
                found = true;
                cadenas.add(cadena);
                contTotal++;
            }
        }
        //aqui entra la recursividad
        if (cont < totalEstados && contTotal < 11) {
            for (int i = 0; i < totalAlfabeto; i++) {
                if (!"-".equals(matriz[est][i])) {
                    String[] s = matriz[est][i].split(",");
                    for (int j = 0; j < s.length; j++) {

                        String temp;
                        if (i == 0) {
                            temp = cadena + "E";
                        } else {
                            int n = i + 96;
                            temp = cadena + Character.toString((char) n);
                        }
                        //System.out.println(est+" "+i+" s[j]:"+s[j]+" temp:"+Character.toString((char) n));
                        recursivoE(cont + 1, Integer.parseInt(s[j]), temp);
                        //recursivo(cont+1,Integer.parseInt(s[j]),temp);
                    }
                }
            }
        }
    }

    public static void recursivo(int cont, int est, String cadena) {
        boolean found = false;
        try {
            for (int i = 0; i < estFinales.length; i++) {
                if (est == Integer.parseInt(estFinales[i])) {
                    //System.out.println(est+" "+estFinales[i]);
                    found = true;
                    cadenas.add(cadena);
                    contTotal++;
                }
            }
            //aqui entra la recursividad
            if (cont < totalEstados && contTotal < 11) {
                for (int i = 0; i < totalAlfabeto; i++) {
                    if (!"-".equals(matriz[est][i])) {
                        String[] s = matriz[est][i].split(",");
                        for (int j = 0; j < s.length; j++) {
                            int n = i + 97;
                            String temp = cadena + Character.toString((char) n);
                            //System.out.println(est+" "+i+" s[j]:"+s[j]+" temp:"+Character.toString((char) n));
                            recursivo(cont + 1, Integer.parseInt(s[j]), temp);
                            //recursivo(cont+1,Integer.parseInt(s[j]),temp);
                        }

                    }
                }
            }
        }catch(NumberFormatException e){
            
        }

    }
}
