package controlador.TDA.GrafoBusquedas;

import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;

public class Dijkstra {

    private static final int INFINITO = Integer.MAX_VALUE;

    public String encontrarRutaMasCorta(double grafo[][], int nodoOrigen, int nodoDestino) throws VacioException {
        int cantidadNodos = grafo.length;
        int[] distancias = new int[cantidadNodos];
        int[] nodosPrevios = new int[cantidadNodos];
        boolean[] conjuntoCorto = new boolean[cantidadNodos];

        for (int i = 0; i < cantidadNodos; i++) {
            distancias[i] = INFINITO;
            nodosPrevios[i] = -1;
            conjuntoCorto[i] = false;
        }

        distancias[nodoOrigen] = 0;

        for (int contador = 0; contador < cantidadNodos - 1; contador++) {
            int nodoActual = encontrarNodoConDistanciaMinima(distancias, conjuntoCorto, cantidadNodos);
            conjuntoCorto[nodoActual] = true;

            for (int vecino = 0; vecino < cantidadNodos; vecino++) {
                if (!conjuntoCorto[vecino] && grafo[nodoActual][vecino] != 0 && distancias[nodoActual] != INFINITO &&
                        distancias[nodoActual] + grafo[nodoActual][vecino] < distancias[vecino]) {
                    distancias[vecino] = (int) (distancias[nodoActual] + grafo[nodoActual][vecino]);
                    nodosPrevios[vecino] = nodoActual;
                }
            }
        }

        return imprimirRutaMasCorta(distancias, nodoOrigen, nodoDestino, nodosPrevios);
    }

    private int encontrarNodoConDistanciaMinima(int distancias[], boolean conjuntoCorto[], int cantidadNodos) {
        int minimaDistancia = INFINITO, nodoConMinimaDistancia = -1;

        for (int nodo = 0; nodo < cantidadNodos; nodo++) {
            if (!conjuntoCorto[nodo] && distancias[nodo] <= minimaDistancia) {
                minimaDistancia = distancias[nodo];
                nodoConMinimaDistancia = nodo;
            }
        }

        return nodoConMinimaDistancia;
    }

    private LinkedList<Integer> reconstruirCamino(int nodoOrigen, int nodoDestino, int[] nodosPrevios) {
        LinkedList<Integer> camino = new LinkedList<>();
        int nodoActual = nodoDestino;
        while (nodoActual != -1) {
            camino.addFirst(nodoActual);
            nodoActual = nodosPrevios[nodoActual];
        }
        return camino;
    }

    private String imprimirRutaMasCorta(int distancias[], int nodoOrigen, int nodoDestino, int[] nodosPrevios) throws VacioException {
        if (distancias[nodoDestino] == INFINITO) {
            return "No hay ruta de " + nodoOrigen + " a " + nodoDestino;
        }

        LinkedList<Integer> rutaResultado = reconstruirCamino(nodoOrigen, nodoDestino, nodosPrevios);
        StringBuilder sb = new StringBuilder();
        sb.append("Camino más corta de ").append(nodoOrigen).append(" a ").append(nodoDestino).append(": ");
        for (int i = 0; i < rutaResultado.getSize(); i++) {
            sb.append(rutaResultado.get(i)).append(" -> ");
        }
        sb.delete(sb.length() - 4, sb.length());
        return sb.toString();
    }

}
