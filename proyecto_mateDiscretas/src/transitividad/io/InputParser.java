//clase encargada de manejar la entrada del usuario, ya sea a través de una matriz o de pares ordenados, y convertir esa entrada en una matriz de adyacencia que el programa pueda usar para verificar la transitividad.
package transitividad.io;
//importamos las clases necesarias para manejar la entrada del usuario y trabajar con expresiones regulares
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    //primera opcion: Leer desde matriz
    public static int[][] leerMatriz(Scanner scanner) {
        int n = Validaciones.leerEntero(scanner, "¿De qué tamaño es la matriz (N x N)?: ");
        int[][] matriz = new int[n][n];

        System.out.println("Ingresa los valores (0 o 1) fila por fila, separados por un espacio:");
        for (int i = 0; i < n; i++) {
            while (true) {
                System.out.print("Fila " + i + ": ");
                String[] valores = scanner.nextLine().trim().split("\\s+");// Separa por espacios, ignorando múltiples espacios seguidos
                
                if (valores.length == n) {// Validamos que el usuario haya ingresado exactamente n valores
                    try {
                        for (int j = 0; j < n; j++) {
                            int val = Integer.parseInt(valores[j]);// Intentamos convertir el valor a entero
                            if (val != 0 && val != 1) throw new NumberFormatException();// Validamos que el valor sea 0 o 1
                            matriz[i][j] = val;// Si el valor es correcto, lo asignamos a la matriz
                        }
                        break; // Si la fila es correcta, rompemos el while para pasar a la siguiente
                    } catch (NumberFormatException e) {// Si el usuario ingresó un valor no numérico o un número diferente de 0 o 1, mostramos un error y pedimos la fila nuevamente
                        System.out.println("error: La matriz solo puede contener números 0 o 1.");
                    }
                } else {
                    System.out.println("error: Debes ingresar exactamente " + n + " valores para esta fila.");
                }
            }
        }
        return matriz;
    }

    // segunda opcion: Leer desde pares ordenados
    public static int[][] leerPares(Scanner scanner) {
        int n = Validaciones.leerEntero(scanner, "¿Cuántos elementos tiene el conjunto (Ejemplo. para un conjuto s = {0,1,2} ingresa 3): ");
        int[][] matriz = new int[n][n]; // Se inicializa en 0 por defecto

        System.out.println("Ingresa los pares ordenados. Ejemplo: (0,1), (1,2), (0,2)");
        System.out.println("Nota: Los valores de los nodos deben ir desde 0 hasta " + (n - 1));
        System.out.print("Pares: ");
        String input = scanner.nextLine();

        //Matcher y Pattern son clases de Java que nos permiten trabajar con expresiones regulares, aprueba de espacios y formatos variados.
        //para extraer solo los números de los paréntesis
        Pattern pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
        Matcher matcher = pattern.matcher(input);

        // Por cada par (x, y) que encuentre en el texto del usuario:
        while (matcher.find()) {
            int origen = Integer.parseInt(matcher.group(1));
            int destino = Integer.parseInt(matcher.group(2));

            // Validamos que el nodo exista en nuestra matriz
            if (origen >= 0 && origen < n && destino >= 0 && destino < n) {
                matriz[origen][destino] = 1; // Trazamos el puente en la matriz
            } else {
                System.out.println("Advertencia: El par (" + origen + "," + destino + ") fue ignorado porque está fuera del límite.");
            }
        }
        return matriz;// De esta forma, el usuario puede ingresar los pares ordenados de manera flexible, y el programa se encargará de construir la matriz de adyacencia correspondiente.
    }
}