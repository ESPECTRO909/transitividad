package transitividad.io;

import java.util.Scanner;

public class Validaciones {

    // Método a prueba de fallos para leer números enteros
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                // Leemos toda la línea y tratamos de convertirla a entero
                return Integer.parseInt(scanner.nextLine().trim());//si el usuario ingresa algo que no es un número entero, se lanzará una excepción NumberFormatException, que capturamos para mostrar un mensaje de error y pedir la entrada nuevamente
            } catch (NumberFormatException e) {
                System.out.println("error: Entrada inválida. Por favor, ingresa un número entero.");
            }
        }
    }
}