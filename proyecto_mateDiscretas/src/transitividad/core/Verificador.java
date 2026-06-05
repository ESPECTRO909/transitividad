package transitividad.core;

import transitividad.model.Resultado;

public class Verificador {

    public static Resultado evaluarTransitividad(int[][] matriz) {
        int n = matriz.length;

        // 1. Manejo de Casos Especiales (Relación vacía o de un solo elemento)
        // Matemáticamente, estas relaciones son transitivas por verdad vacuana
        // Si la matriz tiene 0 o 1 nodo, no hay pares que puedan violar la transitividad
        if (n <= 1) {
            return new Resultado(true);//así que retornamos un resultado positivo sin necesidad de revisar nada más
        }

        // 2. Evaluación de Transitividad
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    
                    // Si existe el par (i, j) y el par (j, k)...
                    if (matriz[i][j] == 1 && matriz[j][k] == 1) {
                        
                        //obligatoriamente debe existir el par (i, k)
                        if (matriz[i][k] == 0) {
                            // Si es 0, la regla se rompe. enviamos el contraejemplo.
                            return new Resultado(false, i, j, k);
                        }
                    }
                }
            }
        }

        // 3. Si terminamos de revisar todas las combinaciones y no hubo fallas devolvemos un resultado positivo
        return new Resultado(true);
    }
}