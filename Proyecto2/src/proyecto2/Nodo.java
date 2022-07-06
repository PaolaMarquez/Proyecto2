
package proyecto2;

/**
 *
 * @author Abril
 */

public class Nodo <T> {
    private Nodo next;
    private T data;
    private String key;
    private String keyTitle;
    
    public Nodo (T data){
        this.next = null;
        this.data = data;
        this.key = null;
        this.keyTitle = null;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeyTitle() {
        return keyTitle;
    }

    public void setKeyTitle(String keyTitle) {
        this.keyTitle = keyTitle;
    }
    
    
}