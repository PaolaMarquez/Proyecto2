
package proyecto2;

/**
 *
 * @author Abril
 */

public class Nodo <T> {
    Nodo next;
    T data;
    
    public Nodo (T data){
        this.next = null;
        this.data = data;
        
    }

    public Nodo getNext() {
        return next;
    }

    public void setNext(Nodo next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}