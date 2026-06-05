package transitividad.model;


public class Resultado {

    private boolean esTransitiva;// Atributo para indicar si la relación es transitiva o no, lo uso para poder mostrar el resultado de la transitividad en la consola de manera clara y sencilla
    private Integer nodoA;//uso Integer en vez de int para poder dejarlo null en caso de que no sea transitiva y no haya nodos a mostrar
    private Integer nodoB;
    private Integer nodoC;

    public Resultado() {}// Constructor vacío lo dejo por buena pratica

    public Resultado(boolean esTransitiva) {// Constructor para el caso de que sea transitiva y no haya nodos a mostrar
        this.esTransitiva = esTransitiva;
    }

    public Resultado(boolean esTransitiva, Integer nodoA, Integer nodoB, Integer nodoC) {// Constructor para el caso de que no sea transitiva y haya nodos a mostrar
        this.esTransitiva = esTransitiva;
        this.nodoA = nodoA;
        this.nodoB = nodoB;
        this.nodoC = nodoC;
    }
    
    // Getters y Setters para mejor seguridad, lo uso por conveccion y para evitar problemas de acceso a los atributos desde otras clases

    public boolean isEsTransitiva() {
        return esTransitiva;
    }

    public Integer getNodoA() {
        return nodoA;
    }

    public Integer getNodoB() {
        return nodoB;
    }

    public Integer getNodoC() {
        return nodoC;
    }

    public void setEsTransitiva(boolean esTransitiva) {
        this.esTransitiva = esTransitiva;
    }

    public void setNodoA(Integer nodoA) {
        this.nodoA = nodoA;
    }

    public void setNodoB(Integer nodoB) {
        this.nodoB = nodoB;
    }

    public void setNodoC(Integer nodoC) {
        this.nodoC = nodoC;
    }

}
