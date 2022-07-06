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

import java.util.Set;
import javax.swing.JOptionPane;


public class HashTable {
    Nodo[] array;
    int tamaño;
    
    public HashTable(){
        this.tamaño = 1;
        this.array = new Nodo[tamaño];
    }

    public Nodo[] getArray() {
        return array;
    }

    public void setArray(Nodo[] array) {
        this.array = array;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    public boolean insertar(Nodo<Resumen> nodo, String titulo, String contenido) {
        int position = hash(titulo);
        boolean exists = false;
        if(position >= tamaño) {
            Nodo[] nuevo = new Nodo[position+1];
            for (int i = 0; i < tamaño; i++) {
                nuevo[i] = array[i];
            }
            nuevo[position] = nodo;
            array = nuevo;
            tamaño = position+1;
        }else{
        if (array[position] == null) {
            array[position] = nodo;
        } else {
            Nodo<Resumen> temp = array[position];
            if (temp.getData().getTitulo().equals(nodo.getData().getTitulo())) {
                JOptionPane.showMessageDialog(null, "Ese documento ya existe");
                exists = true;
            } else {
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().getTitulo().equals(nodo.getData().getTitulo())){
                        JOptionPane.showMessageDialog(null, "Ese documento ya existe");
                        exists = true;
                        break;

                }
                if (!exists) {
                temp.setNext(nodo);
                }
            }
        }

        }
        }
        return exists;
    }
    
    
    public int hash(String nombre) {
        int valor = 0;
        int position = 1;
        for (int i = 0; i < nombre.length() ; i++) {
                if (nombre.codePointAt (i) == 32) {
                    valor += 0;
                } else if (nombre.codePointAt (i) >= 48 && nombre.codePointAt (i) <= 57) {
                        valor += ((nombre.codePointAt (i) - 47) * position);
                } else if (nombre.codePointAt (i) >= 65 && nombre.codePointAt (i) <= 90) {
                        valor += ((nombre.codePointAt (i) - 54) * position);
                } else if (nombre.codePointAt (i) >= 97 && nombre.codePointAt (i) <= 122) {
                        valor += ((nombre.codePointAt (i) - 60) * position);
                }
                position++;
            }
            return (valor % tamaño);
    }
    
}

