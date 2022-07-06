
package proyecto2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;


public class txt {
    
//    public static void writeTxt(Grafo grafo){
//       List<Storage> info = grafo.getVertices();
//       int[][] matriz = grafo.getMatriz();
//       String storageTxt = "Almacenes;\n";
//        if(!info.isEmpty()){
//            for (int i = 0; i < info.getLength(); i++){
//                storageTxt += "Almacen " +info.getElement(i).getName() + ":\n";
//                for(int j = 0; j < info.getElement(i).getProducts().getLength(); j++){
//                    if(j == (info.getElement(i).getProducts().getLength() -1)){
//                         storageTxt += info.getElement(i).getProducts().getElement(j).getName() + "," + info.getElement(i).getProducts().getElement(j).getStock() + ";\n";
//                    }else{
//                        storageTxt += info.getElement(i).getProducts().getElement(j).getName() + "," + info.getElement(i).getProducts().getElement(j).getStock() + "\n";
//                    }
//                }
//            }
//            String routes = "Rutas;\n";
//            for (int i = 0; i < info.getLength(); i++){
//                for(int j = 0; j < info.getLength(); j++){
//                    if(matriz[i][j] != 0){
//                        String a = grafo.getNameStorage(i);
//                        String b = grafo.getNameStorage(j);
//                        if(!"".equals(a)&& !"".equals(b) ){
//                            routes += a + "," + b + "," + matriz[i][j] + "\n";
//                        }
//                    }
//                }
//            }
//            
//            storageTxt += routes;
//        }
//        
//        try{
//            PrintWriter pw = new PrintWriter("test\\txt.txt");
//            pw.print(storageTxt); // escribe
//            pw.close();
//             JOptionPane.showMessageDialog(null,"Guardador realizado Exitosamente");
//        }catch(Exception err){
//            JOptionPane.showMessageDialog(null,"Error");
//        }
//        
//    } 
    
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
        String[] palabras_claves = contenido.split("Palabras claves:")[1].split(".")[0].split(", ");
        boolean ok = ValidarArchivo(titulo, resumen, autores, palabras_claves);
        if(ok){
            Resumen nodo = new Resumen(titulo, resumen, autores, palabras_claves);
            Nodo<Resumen> nodoHash = new Nodo(nodo);
            HashTable table = Global.getTable();
            if (table == null){
                table = new HashTable();
                Global.setTable(table);
            }
            List<String> list = new List();
            exists = table.insertar(nodoHash, titulo, autores, palabras_claves);
            if (Global.getListSave() == null){
                Global.setListSave(list);
            }
            if (Global.getListTitulo() == null){
                Global.setListTitulo(list);
            }
            if (Global.getListAuthor() == null){
                Global.setListAuthor(list);
            }
            
            if (!exists){
                Global.getListTitulo().insertOrdered(titulo);
                for(int i = 0; i < autores.length; i++){
                    if(!Global.getListAuthor().deleteRepetition(autores[i])){
                        Global.getListAuthor().insertOrdered(autores[i]);
                    }
                }
            }
        }else {
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
