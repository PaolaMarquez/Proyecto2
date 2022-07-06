
package proyecto2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import javax.swing.JOptionPane;


public class txt {
    
    public static void start(){
        String str = "test\\resumenes.txt";
        String contenido = leer_archivo(str);
        String[] contenido_array = contenido.split("archivos::\n");
        for (int i = 1; i < contenido_array.length; i++) {
            crear_nodo(contenido_array[i]);
        }
        
    }
    
    public static boolean crear_nodo(String contenido) {
        boolean exists = false;
        String titulo = contenido.split("Autores")[0];
        String[] autores = contenido.split("Resumen\n")[0].split("Autores\n")[1].split("\n");
        String resumen = contenido.split("Resumen\n")[1].split("Palabras claves:")[0];
        String[] palabras_claves = contenido.split("Palabras claves: ")[1].split(".\n")[0].split(", ");

        boolean ok = ValidarArchivo(titulo, resumen, autores, palabras_claves);
        if(ok){
            Resumen nodo = new Resumen(titulo, resumen, autores, palabras_claves);
            Nodo<Resumen> nodo2 = new Nodo(nodo);
            HashTable table = Global.getTable();
            if (table == null){
                table = new HashTable();
                Global.setTable(table);
            }
            exists = table.insertar(nodo2, titulo, contenido);
            if (Global.getListSave() == null){
                List<String> list1 = new List();
                Global.setListSave(list1);
            }
            if (Global.getListTitulo() == null){
                List<String> list2 = new List();
                Global.setListTitulo(list2);
            }
            if (!exists){
                Global.getListTitulo().insertOrdered(titulo);
                
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Ese documento no es válido");
            exists = true;
        }
        return exists;
    }
    
    
    
    public static boolean ValidarArchivo(String titulo, String resumen, String[] autores, String[] palabras_clave) {
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
    
    public static String leer_archivo(String path) {
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
//                JOptionPane.showMessageDialog(null, "Lectura exitosa");
              
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo");
        }
        return contenido_txt;
    }
    
    public static void append(String contenido) {
        
    }
}
