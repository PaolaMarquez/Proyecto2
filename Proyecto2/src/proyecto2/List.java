/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author paola
 */
public class List<T> {
    private Nodo cabeza, end;
    private int len;
    
    public List(){
        this.cabeza = null;
        this.len = 0;
    }
    
    public List(Nodo cabeza){
        this.cabeza = cabeza;
        this.len = 1;
    }
    

    
    //Metodos de la lista
    
    public boolean vacia(){
        return (getCabeza() == null);
    }
        
    public Nodo getCabeza(){
        return this.cabeza;
    }
    
    public void setCabeza(Nodo cabeza){
        this.cabeza = cabeza;
    }
    
    public int getLen(){
        return this.len;
    }
    
    public void setLen(int len){
        this.len = len;
    }
    
    
    public void insertarInicio(T elemento){
        Nodo<T> nodo = new Nodo(elemento);
        if(vacia()){
            setCabeza(nodo);
            setEnd(nodo);
        }else{
            nodo.setNext(getCabeza());
            setCabeza(nodo);
        }
        len++;
    }

    public Nodo getEnd() {
        return end;
    }

    public void setEnd(Nodo end) {
        this.end = end;
    }
    
    public void insertarFinal(T elemento){
        Nodo<T> nodo = new Nodo(elemento);
        if(vacia()){
            setCabeza(nodo);
            setEnd(nodo);
        }else{
            Nodo pointer = getEnd();
            pointer.setNext(nodo);
            setEnd(nodo);
        }
        len++;
    }
    
    public void insertOrdered(T data){
        if (vacia()){
            insertarInicio(data);
        } else if(String.valueOf(data).compareToIgnoreCase(String.valueOf(getCabeza().getData())) <= 0){                 //que la palabra que vamos a insertar sea menor que todas las demas
            //aqui esta comparando la data con la primera data del arreglo
                this.insertarInicio(data);
                 
            }else if(String.valueOf(data).compareToIgnoreCase(String.valueOf(getEnd().getData())) >= 0){
                this.insertarFinal(data);
                 
            }else{//en el medio
                
                Nodo anterior = this.cabeza;
                Nodo current = anterior.getNext(); //El segundo indice
                while((String.valueOf(data).compareToIgnoreCase(String.valueOf(current.getData()))) > 0){
                    //el while se rompe cuando el data es menorx
                    //ese es el momento en el que el dato es mayor que el acutal
                    anterior = current;
                    current = current.getNext();
                }
                Nodo nodo = new Nodo(data);
                anterior.setNext(nodo);
                nodo.setNext(current);
                len++;
            }
            
        }
   
        
        
    public void insertarPosicion(T elemento, int posicion){
        
        if (posicion == 0){
            insertarInicio(elemento);
        }else if(posicion == (len +1)){
            insertarFinal(elemento);
        }else{
            Nodo nodo = new Nodo(elemento);
            if(vacia()){
            setCabeza(nodo);
            }else{

                int count = 0;
                Nodo pointer = getCabeza();
                while(count < (posicion - 1)){
                    pointer = pointer.getNext();
                    count++;
                }
                nodo.setNext(pointer.getNext());
                pointer.setNext(nodo);
            }
            len++;
            }
    }
    
    public void eliminarUltimo(){
        if (vacia()){
            System.out.println("No hay elementos en la lista");
        }else{
            Nodo pointer = getCabeza();
            while(pointer.getNext().getNext() != null){
                pointer = pointer.getNext();
            }
            pointer.setNext(null);
           
            len -= 1;         
        }
        
    }
    
    public void eliminarInicio(){
        if (vacia()){
            System.out.println("No hay elementos en la lista");
        }else{
            Nodo pointer = getCabeza();
            setCabeza(pointer.getNext());
            pointer.setNext(null);
            len -=1;
           }  
    }
    
    public void eliminarPosicion(int posicion){
        if(posicion == 0){
            eliminarInicio();
        }else if(posicion == len){
            eliminarUltimo();
        }else{
            Nodo pointer = getCabeza();
            int count = 0;
            while(count < (posicion - 1)){
                pointer = pointer.getNext();
                count++;
            }
            Nodo temp = pointer.getNext();
            pointer.setNext(temp.getNext());
            temp.setNext(null); 
            len -=1;
        }
    }
    
    public Nodo<T> buscarNodo(int index){
        int count = 0;
        Nodo pointer = getCabeza();
        while(count != index){
            pointer = pointer.getNext();
            count++;
        }
        
        return pointer;
        
    }
    
    public boolean deleteRepetition(String word){
        boolean repetido = false;
        for(int i = 0; i < getLen(); i++){
            if(word.equals(this.buscarNodo(i).getData())){
                repetido = true;
            }
        }
        return repetido;
    }
}        
