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

import javax.swing.JOptionPane;


public class HashTable {
    Nodo<Resumen>[] array;
    Nodo<Integer>[] authors;
    Nodo<Integer>[] words;
    int tamaño;
    
    public HashTable(){
        this.tamaño = 27;
        this.array= new Nodo[tamaño];
        this.authors = new Nodo[tamaño];
        this.words = new Nodo[tamaño];
    }

    public Nodo<Resumen>[] getArray() {
        return array;
    }

    public void setArray(Nodo<Resumen>[] array) {
        this.array = array;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public Nodo<Integer>[] getAuthors() {
        return authors;
    }

    public void setAuthors(Nodo<Integer>[] authors) {
        this.authors = authors;
    }

    public Nodo<Integer>[] getWords() {
        return words;
    }

    public void setWords(Nodo<Integer>[] words) {
        this.words = words;
    }
    
    public boolean insertar(Nodo<Resumen> nodo, String titulo, String[] author, String[] Keywords) {
        int position = hash(titulo);
        boolean exists = false;
        if(position > tamaño) {
            Nodo<Resumen>[] nuevo = new Nodo[position+1];
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
                }else {
                    while (temp.getNext() != null) {
                        temp = temp.getNext();
                        if (temp.getData().getTitulo().equals(nodo.getData().getTitulo())){
                            JOptionPane.showMessageDialog(null, "Ese documento ya existe");
                            exists = true;
                            break;
                        }
                    }
                    if (!exists) {
                    temp.setNext(nodo);
                    }
                }
            }
        }

        if(!exists){
            String[][] group = {author, Keywords};
            int numClave = 0;
            for(int m = 0; m < group.length; m++){
                String[] tabla = group[m];
                for (int i = 0; i < tabla.length; i++){
                    Nodo<Integer> nodito = new Nodo(position);
                    nodito.setKeyTitle(titulo);
                    numClave = hash(tabla[i]);
                    nodito.setKey(tabla[i]);
                    if (m == 0){
                        if(numClave > authors.length) {
                            Nodo<Integer>[] nuevoArray = new Nodo[numClave+1];
                            for (int j = 0; j < authors.length; j++) {
                                nuevoArray[j] = authors[j];
                            }
                            nuevoArray[numClave] = nodito;
                            authors = nuevoArray;
                        }else{
                            if (authors[numClave] == null) {
                                authors[numClave] = nodito;
                            }else{
                                Nodo<Integer> temp = authors[numClave];
                                while(temp.getNext() != null){
                                   temp = temp.getNext(); 
                                }
                                temp.setNext(nodito);
                            }
                        }
                    }else{ 
                        if(numClave > words.length) {
                            Nodo<Integer>[] nuevoArray = new Nodo[numClave+1];
                            for (int j = 0; j < words.length; j++) {
                                nuevoArray[j] = words[j];
                            }
                            nuevoArray[numClave] = nodito;
                            words = nuevoArray;
                        }else{
                            if (words[numClave] == null) {
                                words[numClave] = nodito;
                            }else{
                                Nodo<Integer> temp = words[numClave];
                                while(temp.getNext() != null){
                                   temp = temp.getNext(); 
                                }
                                temp.setNext(nodito);
                            }
                        }
                    }
                }
            }
        }
        return exists;
    }
        
    public int hash(String keyword) {
        int valor = 0;
        int position = 1;
        for (int i = 0; i < keyword.length() ; i++) {
                if (keyword.codePointAt (i) == 32) {
                    valor += 0;
                } else if (keyword.codePointAt (i) >= 48 && keyword.codePointAt (i) <= 57) {
                        valor += ((keyword.codePointAt (i) - 47) * position);
                } else if (keyword.codePointAt (i) >= 65 && keyword.codePointAt (i) <= 90) {
                        valor += ((keyword.codePointAt (i) - 54) * position);
                } else if (keyword.codePointAt (i) >= 97 && keyword.codePointAt (i) <= 122) {
                        valor += ((keyword.codePointAt (i) - 60) * position);
                }
                position++;
            }
            int prueba = (valor % this.tamaño);
            return (prueba);
    }
    
//        public int hashWord(String keyword, int size) {
//        int valor = 0;
//        int position = 1;
//        for (int i = 0; i < keyword.length() ; i++) {
//                if (keyword.codePointAt (i) == 32) {
//                    valor += 0;
//                } else if (keyword.codePointAt (i) >= 48 && keyword.codePointAt (i) <= 57) {
//                        valor += ((keyword.codePointAt (i) - 47) * position);
//                } else if (keyword.codePointAt (i) >= 65 && keyword.codePointAt (i) <= 90) {
//                        valor += ((keyword.codePointAt (i) - 54) * position);
//                } else if (keyword.codePointAt (i) >= 97 && keyword.codePointAt (i) <= 122) {
//                        valor += ((keyword.codePointAt (i) - 60) * position);
//                }
//                position++;
//            }
//            return (valor % size);
//    }
//    
    
    public List search(String clave, Nodo[] tabla){
        List<Resumen> list = new List();
        int position = hash(clave);
        if(position < tabla.length){
            Nodo<Integer> temp = tabla[position];
            if(temp != null){
                if (temp.getNext() == null){
                    if(temp.getKey().equals(clave)){
                        int posTitle = (int) temp.getData();
                        Nodo<Resumen> aux = this.array[posTitle];
                        if(aux.getNext() == null){
                            list.insertOrdered(this.array[posTitle].getData());
                        }else{
                            while(aux.getNext() != null){
                            if(temp.getKeyTitle().equals(aux.getData().getTitulo())){
                                list.insertOrdered(aux.getData());
                            }
                            aux = aux.getNext();
                            }
                        }
                    }
                }else{
                    while(temp.getNext() != null){
                        if(clave.equals(temp.getKey())){
                            int posTitle = (int) temp.getData();
                            Nodo<Resumen> aux = this.array[posTitle];
                            if(aux.getNext() == null){
                                list.insertOrdered(this.array[posTitle].getData());
                            }else{
                                while(aux.getNext() != null){
                                if(temp.getKeyTitle().equals(aux.getData().getTitulo())){
                                    list.insertOrdered(aux.getData());
                                }
                                aux = aux.getNext();
                                }
                            }
                        }
                        temp = temp.getNext();
                    }    
                }
            }
        }
        return list;   
    }
    
    public Nodo<Resumen> searchResumen(String clave){
        int position = hash(clave);
        if(position < array.length){
            Nodo<Resumen> temp = this.array[position];
            if(temp != null){
                if (temp.getNext() == null) {
                    if(temp.getData().getTitulo().equals(clave)){
                        return temp;
                    }
                }else{
                    while(temp.getNext() != null){
                        if(clave.equals(temp.getData().getTitulo())){
                            return temp;
                        }
                        temp = temp.getNext();
                    }
                }
            }
        }
        return null;
    }

}

