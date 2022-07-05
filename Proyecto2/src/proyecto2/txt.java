
package proyecto2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.JOptionPane;


public class txt {
    public void start(){
        String str = "Resumenes.txt";
        String contenido = this.leer_archivo(str);
        String[] contenido_array = contenido.split("archivos::\n");
        for (int i = 1; i < contenido_array.length; i++) {
            this.crear_nodo(contenido_array[i]);
        }
        
    }
    
    public boolean crear_nodo(String contenido) {
        boolean exists = false;
        String nombre = contenido.split("Autores")[0];
        String[] autores = contenido.split("Resumen\n")[0].split("Autores\n")[1].split("\n");
        String resumen = contenido.split("Resumen\n")[1].split("Palabras Claves:")[0];
        String[] palabras_claves = contenido.split("Palabras Claves: ")[1].split(".")[0].split(", ");
        boolean ok = ValidarArchivo(nombre, resumen, autores, palabras_claves);
        if(ok){
            Resumen nodo = new Resumen(nombre, resumen, autores, palabras_claves);
            Nodo<Resumen> nodo2 = new Nodo(nodo);
            exists = Global.getTable().insertar(nodo2, nombre, contenido);}
        else {
            JOptionPane.showMessageDialog(null, "Ese documento no es valido");
            exists = true;
        }
        return exists;
        
    }
    
    
    
    public boolean ValidarArchivo(String titulo, String resumen, String[] autores, String[] palabras_clave) {
        boolean ok = true;
        if("".equals(titulo)){
            ok = false;
        }
        if("".equals(resumen)){
            ok = false;
        }
        if(autores.length == 0){
            ok = false;
        }
        if(palabras_clave.length == 0){
            ok = false;
        }
        return ok;
    }
    
    public String leer_archivo(String path) {
        String contenido_txt = "";
        String line;
        File file = new File(path);
        try {
            if (!file.exists()){
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    if (!line.isEmpty()) {
                        contenido_txt += line + "\n";
                    }
                }
                br.close();
                JOptionPane.showMessageDialog(null, "Lectura exitosa");
              
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
        return contenido_txt;
    }
    
    public static void append(String contenido) {
        
    }
}
