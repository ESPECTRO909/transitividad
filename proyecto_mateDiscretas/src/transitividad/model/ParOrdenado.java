package transitividad.model;

public class ParOrdenado {

    private int origenNodo;
    private int destinoNodo;

    public ParOrdenado() {}

    public ParOrdenado(int origenNodo, int destinoNodo) {
        this.origenNodo = origenNodo;
        this.destinoNodo = destinoNodo;
    }

    public int getOrigenNodo() {
        return origenNodo;
    }

    public int getDestinoNodo() {
        return destinoNodo;
    }

    public void setOrigenNodo(int origenNodo) {
        this.origenNodo = origenNodo;
    }

    public void setDestinoNodo(int destinoNodo) {
        this.destinoNodo = destinoNodo;
    }
    
}
