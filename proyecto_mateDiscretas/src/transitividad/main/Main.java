package transitividad.main;

import transitividad.core.Verificador;
import transitividad.io.InputParser;
import transitividad.io.NetworkParser;
import transitividad.io.Validaciones;
import transitividad.model.Resultado;
import transitividad.model.Topologia;

import java.util.Scanner;

// Clase principal que maneja la interacción con el usuario y coordina las diferentes partes del programa
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("   VALIDADOR DE TRANSITIVIDAD DE RELACIONES");
        System.out.println("==================================================");

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nSelecciona el formato de entrada:");
            System.out.println("1. Matriz de Adyacencia");
            System.out.println("2. Pares Ordenados (Numéricos)");
            System.out.println("3. Extra: Validar Topología de Red (Texto)");
            System.out.println("4. Salir");

            int opcion = Validaciones.leerEntero(scanner, "Opción: ");
            int[][] matriz = null;
            Topologia topologia = null; // Guardará el diccionario si elegimos la opción de red

            switch (opcion) {
                case 1:
                    matriz = InputParser.leerMatriz(scanner);
                    break;
                case 2:
                    matriz = InputParser.leerPares(scanner);
                    break;
                case 3:
                    topologia = NetworkParser.leerRed(scanner);
                    matriz = topologia.getMatriz(); // Extraemos la matriz numérica para el algoritmo central
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    continuar = false;
                    continue; // Salta al final del ciclo y termina
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
                    continue; // Vuelve a mostrar el menú
            }

            // Si la matriz se creó correctamente, hacemos la evaluación matemática
            if (matriz != null) {
                Resultado resultado = Verificador.evaluarTransitividad(matriz);// Evaluamos la transitividad y obtenemos el resultado detallado

                System.out.println("\n----------------- RESULTADO -----------------");
                
                if (resultado.isEsTransitiva()) {
                    // Caso de éxito
                    if (topologia != null) {
                        System.out.println("La relación ES transitiva (La red está completamente enrutada / Full Mesh).");
                    } else {
                        System.out.println("La relación ES transitiva.");
                    }
                } else {
                    // Caso de fallo
                    System.out.println("La relación NO es transitiva.");
                    
                    // Si viene de la Opción 3, traducimos los ids de vuelta a nombres de texto
                    if (topologia != null) {
                        String nodoA = topologia.getNombreNodo(resultado.getNodoA());
                        String nodoB = topologia.getNombreNodo(resultado.getNodoB());
                        String nodoC = topologia.getNombreNodo(resultado.getNodoC());
                        
                        System.out.println("   -> Falta enlace directo: Existen rutas (" + nodoA + " -> " + nodoB + ") " +
                                "y (" + nodoB + " -> " + nodoC + "), pero falta (" + nodoA + " -> " + nodoC + ").");
                    } else {
                        // Si viene de la Opción 1 o 2, imprimimos los números directamente
                        System.out.println("   -> Contraejemplo: Existen los pares (" + resultado.getNodoA() + ", " + resultado.getNodoB() + ") " +
                                "y (" + resultado.getNodoB() + ", " + resultado.getNodoC() + "), " +
                                "pero falta el par (" + resultado.getNodoA() + ", " + resultado.getNodoC() + ").");
                    }
                }
                System.out.println("---------------------------------------------");
            }
        }
        
        scanner.close();// Cerramos el scanner al finalizar el programa
    }
}