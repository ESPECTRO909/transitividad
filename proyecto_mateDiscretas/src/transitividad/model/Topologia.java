package transitividad.model;

public class Topologia {
    private int[][] matriz;
    private String[] diccionarioNodos;

    public Topologia(int[][] matriz, String[] diccionarioNodos) {
        this.matriz = matriz;
        this.diccionarioNodos = diccionarioNodos;
    }

    public int[][] getMatriz() { return matriz; }
    
    // Este método es para devolver el nombre del nodo dado su id 
    public String getNombreNodo(int id) {
        return diccionarioNodos[id];
    }
}