package recepcionDatos;

import impresion.MatrizOriginal;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import resuelvenMatrices.NoDeterminista;
import resuelvenMatrices.TransicionE;
// Al aire, no por recorrido. Analisis de base secuencial

//Compañero programador, cuando hice este programa solo dios y yo sabiamos como funcionaba
//Ahora solo dios lo sabe...

public class Inicio {

    public static int totalEstados, totalAlfabeto, estInicial = 0;
    public static String estFinal;
    public static JLabel temp2;
    public static int tipo = 0;
    public static ArrayList<Integer> indicesFinales;
    public static boolean errorDatos=false;

    public static void main(String args[]) {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setTitle("DISIA");
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        //primerFrame(frame);

        //primerFrame pide datos de tupla 
        primerFrame(frame);
        frame.repaint();
    }

    public static void presentacion(JFrame frame){
        
    }
    public static void primerFrame(JFrame frame) {
        errorDatos = false;
        
        //Texto inicial
        Font font = new Font("Dialog", Font.PLAIN, 18);
        JLabel label1 = new JLabel("Ingresa los datos que se solicitan");
        label1.setFont(font);
        label1.setBounds(10, 10, 290, 35);

        // Especificaciones
        Font font2 = new Font("Dialog", Font.BOLD, 13);
        String s = "<html>- Maximo 5 elementos del alfabeto."
                + "<br/><br/>- Minimo 3 estados, maximo 8. </html>";
        JLabel indications = new JLabel(s);
        indications.setBounds(226, 60, 290, 50);
        indications.setForeground(Color.GRAY);
        //Estados (etiqueta y esapacio en blanco)

        JLabel labelNodos = new JLabel("<html>Elementos del alfabeto: <br/><br/>"
                + "Total de nodos<br/><br/>Estado inicial<br/><br/>"
                + "Estados finales</html>");
        labelNodos.setBounds(40, 60, 150, 120);
        labelNodos.setFont(font2);
        //Alfabeto
        JTextField textAlfabeto = new JTextField();
        textAlfabeto.setBounds(193, 60, 30, 20);

        JTextField textNodos = new JTextField();
        textNodos.setBounds(193, 95, 30, 20);

        JTextField textInicial = new JTextField();
        textInicial.setBounds(193, 130, 30, 20);

        JTextField textFinales = new JTextField();
        textFinales.setBounds(193, 165, 30, 20);
        //Boton para capturar los datos de los espacios en blanco
        JButton listo = new JButton("Listo");
        listo.setBounds(465, 360, 70, 20);

        //Seleccionar el tipo de grafo
        JLabel labelTipo = new JLabel("Selecciona una opcion");
        labelTipo.setBounds(40, 250, 150, 20);
        labelTipo.setFont(font2);
        JCheckBox box1 = new JCheckBox("Determinista", false);
        box1.setForeground(Color.GRAY);
        //box1.setEnabled(true);
        box1.setBounds(60, 280, 110, 20);
        JCheckBox box2 = new JCheckBox("No Determinista", false);
        box2.setForeground(Color.GRAY);
        //box1.setEnabled(true);
        box2.setBounds(60, 310, 125, 20);
        JCheckBox box3 = new JCheckBox("E Transicion", false);
        box3.setForeground(Color.GRAY);
        //box1.setEnabled(true);
        box3.setBounds(60, 340, 110, 20);

        //listener de los boxes
        box1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == box1) {
                    //System.out.println("tipo=1");
                    tipo = 1;
                    box2.setSelected(false);
                    box3.setSelected(false);
                }
            }
        });
        box2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == box2) {

                    tipo = 2;
                   // System.out.println("tipo: " + tipo);
                    box3.setSelected(false);
                    box1.setSelected(false);
                }
            }
        });
        box3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == box3) {
                    tipo = 3;
                    box2.setSelected(false);
                    box1.setSelected(false);
                }
            }
        });

        //listener de boton listo
        listo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean sinError = true;
                if (ae.getSource() == listo) {
                    try {
                        totalEstados = Integer.parseInt(textNodos.getText());
                        totalAlfabeto = Integer.parseInt(textAlfabeto.getText());
                        //if(tipo==3){
                        
                        // }
                        estInicial = Integer.parseInt(textInicial.getText());
                        estFinal = textFinales.getText();
                    } catch (NumberFormatException e) {
                        //System.out.println("Catch, clase inicio, excepcion de boton Listo");
                        String s = "<html>Los datos ingresados no son correctos,<br/>"
                                + "favor de intentarlo de nuevo.</html>";
                        //Desde aqui mandamos a llamar al frame error
                        mensajeError(frame, s);
                        sinError = false;
                    }
                    //checar que los datos esten dentro de los parametros
                    if (totalEstados > 8 || totalAlfabeto > 5 || estInicial > totalEstados
                            || estInicial < 0 || totalAlfabeto < 1) {
                        sinError = false;
                    }
                    //Si no hay errores podemos pedir la tabla de transiciones
                    if (sinError) {
                        totalAlfabeto++;//por los E transiciones  
                        frame.getContentPane().removeAll();
                        pedirTabla(frame);
                       // System.out.println("Entramos a pedir tabla");
                    }
                }
            }
        });

        frame.add(box3);
        frame.add(box2);
        frame.add(box1);
        frame.add(labelTipo);
        frame.add(textInicial);
        frame.add(textFinales);
        frame.add(listo);
        frame.add(labelNodos);
        frame.add(textNodos);
        frame.add(textAlfabeto);
        frame.add(label1);
        frame.add(indications);
        frame.repaint();
    }

    public static void pedirTabla(JFrame frame) {
        //Para el la lista de estados finales que se mandan a imprimir en la tabla deMatrizOriginal 
       indicesFinales = new ArrayList();
        String[] stringFinal = estFinal.split(",");
       for(int p=0; p<stringFinal.length;p++){
           indicesFinales.add(Integer.parseInt(stringFinal[p]));
       }
        //crear string de letras del alfabeto
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
            temp += Integer.toString(i);
            if (i != totalEstados - 1) {
                temp += ",";
            }
        }
        //nuevas etiquetas
        temp += "}<br/>S = {" + Integer.toString(estInicial) + "}<br/>"
                + "F = {" + estFinal + "}</html>";
        Font font = new Font("Dialog", Font.ROMAN_BASELINE, 15);
        JLabel labelTulpa = new JLabel(temp);
        labelTulpa.setFont(font);
        temp2= new JLabel(temp);//pasar tupla a la clase que imprime tabla
        labelTulpa.setBounds(30, 30, 140, 75);
        //Indicaciones
        Font font3 = new Font("Dialog", Font.PLAIN, 18);
        JLabel label1 = new JLabel("Completa la tabla de transiciones");
        label1.setFont(font3);
        label1.setBounds(150, 120, 290, 35);
        //especificaciones
        /*JTextArea especificaciones = new JTextArea();
        especificaciones.setBackground(Color.LIGHT_GRAY);
        especificaciones.setBounds(100, 160, 400, 80);
        String laChina ="<html>Cada espacio en blanco recibe las conexiones de un solo estado. <br/> "
                + "Por cada estado escribe sus transiciones en un solo renglón. Las transiciones del mismo elemento del alfabeto"
                + "separados por comas y para indicar que vas a cambiar de símbolo del alfabeto separa con dos espacios seguidos"
                + ". Es importante no agregar ningún elemento extra a los que se aceptan en esta nomenclatura</html>";
        especificaciones.setText(laChina);
        especificaciones.setEditable(false);*/
        Font font5 = new Font("Dialog", Font.PLAIN, 12);
        String laChina ="<html>Cada espacio en blanco recibe todas las transiciones de un solo estado.<br/> "
                + "Las transiciones con el mismo elemento del alfabeto van separadas por comas. Para indicar que vas a pasar al siguiente símbolo del alfabeto separa con dos espacios seguidos. Si no hay transición en alguna letra coloca un guion"
                + ". Es importante no agregar ningún elemento extra a los que se aceptan en esta nomenclatura.</html>";
        JLabel indications = new JLabel(laChina);
        indications.setFont(font5);
        indications.setBounds(60,160,440,100);
        //Ejemplo
        Font font6 = new Font("Dialog", Font.BOLD, 13);
        String stringEjemplo = "Ejemplo:";
        JLabel ejemplo = new JLabel(stringEjemplo);
        ejemplo.setForeground(Color.GRAY);
        ejemplo.setFont(font6);
        ejemplo.setBounds(380,300,90,20);
        JTextField fieldEjemplo = new JTextField("1,2  -  3,0");
        fieldEjemplo.setEditable(false);
        fieldEjemplo.setBounds(390,325,110,20);
        
        //Letras del abecedario para la tabla
        Font font4 = new Font("Dialog", Font.PLAIN, 15);
        String e;
        
        if (tipo == 3) {
            e = "E       " + letrasS;
        } else {
            e = letrasS;
            totalAlfabeto--;
        }
        JLabel letras = new JLabel(e);
        letras.setFont(font4);
        letras.setBounds(85, 270, totalAlfabeto * 40, 20);
        //Imprimir nombre de los nodos
        
        temp = "<html>";
        JLabel nombreNodos = new JLabel();
        for (int i = 0; i < totalEstados; i++) {
            temp += String.valueOf(i) + "<br/>";
        }
        temp += "</html>";
        int longY = totalEstados * 20;//longitud en eje y del label
        nombreNodos.setText(temp);
        nombreNodos.setFont(font4);
        nombreNodos.setBounds(40, 300, 25, longY);
        //Crear espacios para escribir los estados
        //Como van a ser muchos y no sabemos cuantos, crearemos un arraylist
        ArrayList<JTextField> listaEspacios = new ArrayList();
        for (int i = 0; i < totalEstados; i++) {//tantos elementos como numeros del alfabeto
            listaEspacios.add(new JTextField());
            int n = 20 * i;
            listaEspacios.get(i).setBounds(70, 300 + n, totalAlfabeto * 40, 20);
            frame.add(listaEspacios.get(i));
        }
        //boton para capturar datos y pasar al siguiente frame
        JButton listo = new JButton("Listo");
        listo.setBounds(450, 450, 70, 20);

        listo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (ae.getSource() == listo) {
                    crearMatriz(listaEspacios, frame);
                }
            }
        });
        
        frame.add(fieldEjemplo);
        frame.add(ejemplo);
        frame.add(listo);
        frame.add(letras);
        //frame.add(especificaciones);
        frame.add(indications);
        frame.add(label1);
        frame.add(nombreNodos);
        frame.add(labelTulpa);
        frame.repaint();
    }

    //de la lista de espacios para llenar la tabla de transiciones generaremos una matriz
    //En este paso detectaremos de que tipo de autómata se trata
    public static void crearMatriz(ArrayList<JTextField> listaEspacios, JFrame frame) {
        // int tipo = 0;// 0 D, 1 ND, 2 E
        int contE = 0;//contador de las E que van saliendo
        int contTrans = 0;
        boolean error = false;
        String[][] matriz = new String[totalEstados][totalAlfabeto];

        for (int i = 0; i < listaEspacios.size(); i++) {//estados
            contTrans = 0;
            //primero se separa la cadena por espacios
            String[] espacio;
            try {
                espacio = listaEspacios.get(i).getText().split("  ");//----- doble espacio    
                if (espacio.length == totalAlfabeto) {
                    //System.out.println("length: "+espacio.length+" totalAlf: "+totalAlfabeto);
                    for (int j = 0; j < espacio.length; j++) {//letras
                        //ahora separamos cada elemento por comas
                        if (!"-".equals(espacio[j])) {
                            String[] comas = espacio[j].split(",");
                            if (comas.length <= 3) {//porque no se pueden mas de 3 de la misma letra
                                if (j == 0) {
                                    contE += comas.length;
                                }
                                contTrans += comas.length;
                                matriz[i][j] = espacio[j];
                                if (comas.length > 1) {
                                    //tipo = 1;
                                }
                            } else {//se rompio una condicion
                                String s = "No puede haber mas de 3 transiciones por valor";
                                mensajeError(frame, s);
                                j = espacio.length;
                                i = listaEspacios.size();
                                error = true;
                            }
                        } else {
                            matriz[i][j] = espacio[j];
                        }
                    }
                } else {
                    System.out.println("Error en else");
                    String s = "<html>El numero de posibles transiciones no coincide con el total de elementos del alfabeto</html>";
                    mensajeError(frame, s);
                    i = listaEspacios.size();
                    errorDatos=true;
                }
                if (contTrans > 5) {
                    String s = "No puede haber más de 5 transiciones por nodo";
                    mensajeError(frame, s);
                    i = listaEspacios.size();
                    errorDatos=true;
                }

            } catch (NumberFormatException e) {
                String s = "<html>Los espacios no se llenaron correctamente<br/>"
                        + "Intenta de nuevo</html>";
                mensajeError(frame, s);
                i = listaEspacios.size();
                errorDatos=true;
            }
        }//for de estados
        if (contE > 5&&tipo==3) {
            String s = "No puede haber más de 5 E transiciones";
            mensajeError(frame, s);
        } else if(!errorDatos){
            frame.getContentPane().removeAll();
            if (tipo == 3) {//e transicion
                TransicionE.resolver(matriz, estInicial, estFinal, totalAlfabeto, totalEstados, frame);
                MatrizOriginal.asignarCoordenadas(false,matriz, totalEstados, totalAlfabeto, frame, true,indicesFinales,estFinal);
                MatrizOriginal.tupla(temp2, frame);
            } else if (tipo == 2) {//no determinista
                NoDeterminista.resolver(matriz, estInicial, estFinal, totalAlfabeto, totalEstados, frame);
                MatrizOriginal.asignarCoordenadas(false,matriz, totalEstados, totalAlfabeto, frame, false,indicesFinales,estFinal);
                MatrizOriginal.tupla(temp2, frame);
            } else if (tipo == 1) {//determinista
                //transicionE.resolver(matriz, estInicial, estFinal, totalAlfabeto, totalEstados, frame);
                //System.out.println("Tipo es 1");
                MatrizOriginal.asignarCoordenadas(true, matriz, totalEstados, totalAlfabeto, frame, false,indicesFinales,estFinal);
                MatrizOriginal.tupla(temp2, frame);
            } else if (tipo == 0) {
                String mensaje ="No se ha seleccionado el tipo";
                mensajeError(frame,mensaje);
            }
        }

        //Impresion de matriz
        /* for (int i = 0; i < matriz.length; i++) {
            System.out.print(i + ":  ");
            for (int j = 0; j < totalAlfabeto; j++) {
                System.out.print(matriz[i][j] + " | ");
            }
            System.out.println(" ");
        }*/
        //ya podemos mandar a llamar a las clases que resuelven matrices
    }

    public static void mensajeError(JFrame frame, String string) {
        //borrar todo lo que teniamos en el frame y mostrar mensaje de error
        frame.getContentPane().removeAll();
        Font font = new Font("Dialog", Font.PLAIN, 20);
        JLabel label = new JLabel(string);
        label.setFont(font);
        label.setBounds(100, 50, 400, 100);
        //boton para reinciar frame
        JButton entendido = new JButton("Entendido");
        entendido.setBounds(270, 250, 90, 25);
        entendido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.getContentPane().removeAll();
                primerFrame(frame);
            }
        });
        frame.add(entendido);
        frame.add(label);
        frame.repaint();
    }
}
