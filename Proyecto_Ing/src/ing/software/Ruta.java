package ing.software;

import ing.excep.MiExcepcion;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ruta {
    public static String abrir() throws MiExcepcion {
        String ruta = null;
        JFileChooser escogerAb = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo texto", "txt", "TXT");
        escogerAb.setFileFilter(filtro);
        int valor = escogerAb.showOpenDialog(null);
        if (valor == JFileChooser.APPROVE_OPTION){
            ruta = escogerAb.getSelectedFile().getPath();
        }else{
            throw new MiExcepcion("Se necesita un archivo");
        }
        return ruta;
    }
    
    public static String guardar() throws MiExcepcion{
        String ruta = null;
        JFileChooser guardaSelec = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Solo texto", "txt", "TXT");
        guardaSelec.setFileFilter(filtro);
        int valor = guardaSelec.showOpenDialog(null);
        if ( valor == JFileChooser.APPROVE_OPTION){
            ruta = guardaSelec.getSelectedFile().getPath();
        }else{
            throw new MiExcepcion ("Se necesita un archivo");
        }
        return ruta;
    }
}
