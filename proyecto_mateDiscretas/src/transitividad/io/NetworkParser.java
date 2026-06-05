package transitividad.io;

import transitividad.model.Topologia;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetworkParser {

    public static Topologia leerRed(Scanner scanner) {
        Map<String, Integer> mapaIds = new HashMap<>();
        
        System.out.println("Ingresa los enlaces de red. Ejemplo: (RouterA, RouterB), (RouterB, Switch1)");
        System.out.print("Enlaces: ");
        String input = scanner.nextLine();

        Pattern pattern = Pattern.compile("\\(\\s*([\\w.-]+)\\s*,\\s*([\\w.-]+)\\s*\\)");
        
        // 1. Contar nodos únicos y asignarles un ID
        int contadorNodos = 0;
        Matcher matcherConteo = pattern.matcher(input);
        while (matcherConteo.find()) {
            if (!mapaIds.containsKey(matcherConteo.group(1))) mapaIds.put(matcherConteo.group(1), contadorNodos++);
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
            matriz[origen][destino] = 1;
        }

        return new Topologia(matriz, diccionarioNodos);
    }
}