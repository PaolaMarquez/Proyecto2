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

    public static HashTable getTable(){
        return table;
    }

    public void setTable(HashTable GlobalTable) {
        this.table = GlobalTable;
    }
    
    
}