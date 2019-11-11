package extra;

import java.util.ArrayList;

public class Nodo {
    int nombre;
    boolean visitado = false;
    String representa;
    ArrayList<String> conexiones = new ArrayList<String>();
    
    public Nodo(int nombre, String representa){
        this.nombre = nombre;
        this.representa = representa;
    }
    
    public void changeConex(int a, String s){
       conexiones.set(a,s); 
    }
    public void addConex(String string){
        conexiones.add(string);
    }
    public void setNombre(int nombre){
        this.nombre=nombre;
    }
    public void setRepresenta(String representa){
        this.representa=representa;
    }
    public void setVisitado(boolean visitado){
        this.visitado=visitado;
    }
    
    public String getConx(int i){
        try{
           return conexiones.get(i); 
        }catch(IndexOutOfBoundsException e){
            return String.valueOf(nombre);
        }
        
    }
    public int getNombre(){
        return nombre;
    }
    public String getRepresenta(){
        return representa;
    }
    public boolean getVisitado(){
        return visitado;
    }
}
