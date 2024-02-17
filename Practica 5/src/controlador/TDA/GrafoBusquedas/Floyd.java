package controlador.TDA.GrafoBusquedas;

import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;

public class Floyd {

    private static final double INFINITO = Double.MAX_VALUE;

    public static String calcularCaminoMasCorto(double[][] grafo, int inicio, int fin) throws VacioException {
        int tamano = grafo.length;

        double[][] distancias = new double[tamano][tamano];
        int[][] siguienteNodo = new int[tamano][tamano];

        // Inicializar las matrices
        inicializarMatrices(grafo, distancias, siguienteNodo);

        // Ejecutar el algoritmo de Floyd
        ejecutarAlgoritmoFloyd(grafo, distancias, siguienteNodo);

        // Obtener y devolver el camino más corto
        LinkedList<Integer> caminoResultado = obtenerCamino(inicio, fin, siguienteNodo);
        if (caminoResultado.isEmpty()) {
            return "No hay camino de " + inicio + " a " + fin;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Ruta más corta de ").append(inicio).append(" a ").append(fin).append(": ");
            for (int i = 0; i < caminoResultado.getSize(); i++) {
                sb.append(caminoResultado.get(i)).append(" -> ");
            }
            sb.delete(sb.length() - 4, sb.length());
            return sb.toString();
        }
    }

    private static void inicializarMatrices(double[][] grafo, double[][] distancias, int[][] siguienteNodo) {
        int tamano = grafo.length;
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                distancias[i][j] = grafo[i][j];
                if (i != j && grafo[i][j] != INFINITO) {
                    siguienteNodo[i][j] = j;
                } else {
                    siguienteNodo[i][j] = -1;
                }
            }
        }
    }

    private static void ejecutarAlgoritmoFloyd(double[][] grafo, double[][] distancias, int[][] siguienteNodo) {
        int tamano = grafo.length;
        for (int k = 0; k < tamano; k++) {
            for (int i = 0; i < tamano; i++) {
                for (int j = 0; j < tamano; j++) {
                    if (distancias[i][k] != INFINITO && distancias[k][j] != INFINITO
                            && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        siguienteNodo[i][j] = siguienteNodo[i][k];
                    }
                }
            }
        }
    }

    private static LinkedList<Integer> obtenerCamino(int inicio, int fin, int[][] siguienteNodo) {
        LinkedList<Integer> camino = new LinkedList<>();
        if (siguienteNodo[inicio][fin] == -1) {
            return camino;
        }
        camino.add(inicio);
        while (inicio != fin) {
            inicio = siguienteNodo[inicio][fin];
            camino.add(inicio);
        }
        return camino;
    }
}
