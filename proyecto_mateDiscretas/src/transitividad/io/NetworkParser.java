package transitividad.io;

import transitividad.model.Topologia;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//clase encargada de manejar la entrada del usuario, ya sea a través de una matriz o de pares ordenados, y convertir esa entrada en una matriz de adyacencia que el programa pueda usar para verificar la transitividad.
public class NetworkParser {

    public static Topologia leerRed(Scanner scanner) {
        Map<String, Integer> mapaIds = new HashMap<>();// Este mapa asignará un ID numérico a cada nodo (RouterA -> 0, RouterB -> 1, etc.)
        
        System.out.println("Ingresa los enlaces de red. Ejemplo: (RouterA, RouterB), (RouterB, Switch1)");
        System.out.print("Enlaces: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\(\\s*([\\w.-]+)\\s*,\\s*([\\w.-]+)\\s*\\)");// Esta expresión regular permite nombres de nodos con letras, números, guiones y puntos, y es flexible con los espacios.
        
        // 1. Contar nodos únicos y asignarles un ID
        int contadorNodos = 0;
        Matcher matcherConteo = pattern.matcher(input);// Reutilizamos el matcher para contar los nodos únicos
        while (matcherConteo.find()) {// Por cada par (x, y) que encuentre en el texto del usuario:
            if (!mapaIds.containsKey(matcherConteo.group(1))) mapaIds.put(matcherConteo.group(1), contadorNodos++);// Si el nodo origen no tiene ID, se le asigna uno nuevo
            if (!mapaIds.containsKey(matcherConteo.group(2))) mapaIds.put(matcherConteo.group(2), contadorNodos++); 
        }

        // 2. Crear la matriz y el diccionario inverso (arreglo de Strings)
        int[][] matriz = new int[contadorNodos][contadorNodos];
        String[] diccionarioNodos = new String[contadorNodos];
        
        // Llenar el diccionario inverso
        for (Map.Entry<String, Integer> entry : mapaIds.entrySet()) {
            diccionarioNodos[entry.getValue()] = entry.getKey();
        }

        // 3. Llenar la matriz con los puentes (unos)
        Matcher matcherRelleno = pattern.matcher(input);
        while (matcherRelleno.find()) {
            int origen = mapaIds.get(matcherRelleno.group(1));
            int destino = mapaIds.get(matcherRelleno.group(2));
            matriz[origen][destino] = 1;// Trazamos el puente en la matriz
        }

        return new Topologia(matriz, diccionarioNodos);// De esta forma, el usuario puede ingresar los enlaces de red de manera flexible, y el programa se encargará de construir la matriz de adyacencia y el diccionario de nodos correspondiente.
    }
}