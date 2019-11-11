
package impresion;

import extra.CadenasValidas;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Determinista {
    //Old ones
    public static String[][] matriz;
    public static int totalEstados, estInicial, totalAlfabeto;
    public static String estFinales;
    
    //new ones
    public static boolean transicionE=false;
    public static ArrayList<String> coord = new ArrayList<>();
    public static ArrayList<String> lineas = new ArrayList<>();
    public static ArrayList<JLabel> labels = new ArrayList<>();
    
    public static ArrayList<Integer> indicesFinales;
    public static int s1,s2;
    //para las cadenas validas
    public static ArrayList<String> cadenas;
    //Clase que no modifica, solo asigna coordenadas a la matriz
    public static void asignarCoordenadas(String[][] matriz1, int totalEstados1, int totalAlfabeto1, JFrame frame, boolean transicion, String estF,ArrayList<Integer> indicesFinales2){
        matriz= matriz1;
        totalEstados = totalEstados1;
        totalAlfabeto=totalAlfabeto1;
        transicionE=transicion;
        estFinales = estF;
        indicesFinales = indicesFinales2;
        //Ejecucion comienza aqui
        calcularCoordenadas();
        calcularLineas();
        tulpa(frame);
        printLabels(frame);
        String stringFinal="";
        for(int i = 0 ; i< indicesFinales.size();i++){
            if(i!=indicesFinales.size()-1){
                stringFinal +=indicesFinales.get(i)+",";
            }else{
                stringFinal +=indicesFinales.get(i);
            } 
        }
        if(transicionE){
            cadenas = CadenasValidas.buscarCaminos(false,matriz, totalEstados, totalAlfabeto-1, estInicial, stringFinal, frame);
        }else{
           cadenas = CadenasValidas.buscarCaminos(false,matriz, totalEstados, totalAlfabeto, estInicial, stringFinal, frame); 
        }
        //aumentar tamaño del frame de acuerdo a los resultados
        int xFrame=600, yFrame=630;
        if(totalAlfabeto>1){
            int masX=totalAlfabeto-1;
            masX*=75;
            xFrame+=masX;
        }
        if(totalEstados>14){
            int masY=totalEstados-14;
            masY*=15;
            yFrame+=masY;
        }
        
        frame.setSize(xFrame,yFrame);
        
        
        printCadenas(frame);
        crearTabla(frame);
        OnScreen panel = new OnScreen(coord,lineas);
        panel.setBounds(170,290,250,300);//mitad inferior
        frame.add(panel);
        frame.repaint();
    }
    
    public static void printCadenas(JFrame frame){
        String stringCadenas ="<html>Cadenas válidas:<br/>";
        for(int i = 0; i< cadenas.size()&&i<10;i++){
            stringCadenas+=cadenas.get(i)+"<br/>";
        }
        stringCadenas+="</html>";
        Font font = new Font("Dialog", Font.PLAIN, 11);
        JLabel labelCadenas = new JLabel(stringCadenas);
        labelCadenas.setBounds(30, 430, 160, 170);
        labelCadenas.setFont(font);
        frame.add(labelCadenas);
        frame.repaint();
    }
    public static void tulpa(JFrame frame){
        //if(transicion)
        //totalAlfabeto --;
        String letrasS = "";
        String temp = "<html>A = {";
        for (int i = 0; i < totalAlfabeto - 1; i++) {// -1 es por la E
            //String s =String.valueOf(i+97);
            //temp  += String.valueOf(i+97);
            int n = i + 97;
            temp += Character.toString((char) n);
            letrasS += Character.toString((char) n) + "       ";//para la tabla
            if (i != totalAlfabeto - 2) {//antes era -1, por la E -2
                temp += ",";
            }
        }
        temp += "}";
        //label de estados
        temp += "<br/>Q = {";
        for (int i = 0; i < totalEstados; i++) {
            //String s =String.valueOf(i+97);
            if(totalEstados>11&&i%11==0){
                temp+="<br/>";
            }
            temp += Integer.toString(i);
            if (i != totalEstados - 1) {
                temp += ",";
            }
        }
        //nuevas etiquetas
        String stringFinal="";
        for(int i = 0 ; i< indicesFinales.size();i++){
            if(i!=indicesFinales.size()-1){
                stringFinal +=indicesFinales.get(i)+",";
            }else{
                stringFinal +=indicesFinales.get(i);
            }
            
        }
        temp += "}<br/>S = {" + Integer.toString(estInicial) + "}<br/>"
                + "F = {" + stringFinal + "}</html>";
        JLabel label = new JLabel(temp);
        label.setBounds(5, 300, 190, 120);// -------- dimension tulpa
        Font font = new Font("Dialog", Font.ROMAN_BASELINE, 13);
        label.setFont(font);
        frame.add(label);
        
    }
    public static void calcularCoordenadas(){
         s1 = totalEstados/2;
         s2 = totalEstados-s1;
        
        int r = (((s1+1)*22)/2)+1;
        int x = r*(-1);
        x+=17;

        for(int i =0; i<s1;i++){
            int y = calcularY(x,true,r);
            String s=i+","+x+","+y+",0";
            coord.add(s);
            x+=22;

        }
        x = r*(-1);

        for(int i =s1;i<totalEstados;i++){
            int y = calcularY(x,false,r);
            String s=i+","+x+","+y+",0";
            coord.add(s);
            x+=22;

        }
       
    }
    public static void calcularLineas(){
        System.out.println("totalAlfabeto: "+totalAlfabeto);
        int n =totalAlfabeto;
        if(transicionE){
            n--;
        }
        int x1, y1, x2, y2;
        for (int i = 0; i < totalEstados; i++) {
            for (int k = 0; k < n; k++) {
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
                        if(i<s1){
                            y1+=12;
                            
                        }else{
                            y1-=2;
                        }
                        x1+=8;
                        if(Integer.parseInt(s[j])<s1){
                            y2+=12;
                            
                        }else{
                            y2-=2;
                        }
                        x2+=8;
                        
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
     
    public static void printLabels(JFrame frame) {
        int x, y;
        for (int i = 0; i < coord.size(); i++) {
            String[] s = coord.get(i).split(",");
            labels.add(new JLabel(s[0]));
            x = (Integer.parseInt(s[1]))+325;

            y = (Integer.parseInt(s[2]))+386;//mas 300 porque estamos en el label de abajo
            labels.get(i).setBounds(x, y, 15, 20);
            frame.add(labels.get(i));
        }
    }
    
    public static void crearTabla(JFrame frame){
        int z =totalAlfabeto;
        if(transicionE){
            z--;
        }
        Object[] nombresColumnas = new Object[z+1];
       //Object[] nombresColumnas = {"Estados", "A", "B", "A", "B", "A", "B", "A", "B"};
        Object[][] data = new Object[matriz.length+1][z+1];
        data[0][0] = "Estados";
        nombresColumnas[0]="Estados";
        for(int i =1;i<z+1;i++){
            int n = i+96;
            //totalAlfabeto--;
            data[0][i] = Character.toString((char) n);
            nombresColumnas[i]=Character.toString((char) n);
        }
        //String[] r = {"2","3"};
        //nombresColumnas = {r[]};
        //para sabers si es finale hacemso un bucle
        boolean esFinal;
        for (int i = 1; i < matriz.length+1; i++) {
            esFinal=false;
            data[i][0]=i-1;
            for(int j=0; j<indicesFinales.size();j++){
                if((i-1)==indicesFinales.get(j)){
                    esFinal=true;
                }
            }
            if(esFinal){
                data[i][0]+="*";
            }
            //-------- asterisco magico de finales
            for(int j =1;j<z+1;j++){
                try{
                    String s = matriz[i-1][j-1];
                    data[i][j]=s;
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Clase Detrerminista, crearTabla");
                    System.out.println("matriz: "+matriz.length+" Alfabeto: "+z);
                    System.out.println("\ti: "+i+" j: "+j);
                }
                
            }
        }
        JTable table = new JTable(data, nombresColumnas);
        table.setBounds(470, 300, (z+1)*75, ((totalEstados+1)*16));
        frame.add(table);
        frame.repaint();
    }
}
