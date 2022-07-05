/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author Abril
 */
public class Global {
    private static HashTable table;
    private static List listTitulo;
    private static List listSave;

    public static HashTable getTable(){
        return table;
    }

    public static void setTable(HashTable GlobalTable) {
        table = GlobalTable;
    }

    public static List getListTitulo() {
        return listTitulo;
    }

    public static void setListTitulo(List listTitulo) {
        Global.listTitulo = listTitulo;
    }

    public static List getListSave() {
        return listSave;
    }

    public static void setListSave(List listSave) {
        Global.listSave = listSave;
    }
    
    
}