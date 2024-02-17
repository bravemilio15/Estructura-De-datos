/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.grafos.exeption.VerticeOfSizeExecption;
import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.MyQueue;
import controlador.TDA.listas.Queque;
import controlador.TDA.listas.Stack;
import controlador.TDA.listas.exception.VacioException;
import java.util.HashMap;
import java.util.Locale;

/**
 *
 * @author sebastian
 */
public abstract class Grafo {

    public abstract Integer nro_vertices();

    public abstract Integer nro_aristas();

    public abstract Boolean existe_arista(Integer a, Integer b) throws Exception;

    public abstract Double peso_arista(Integer a, Integer b) throws Exception;

    public abstract void insertar(Integer a, Integer b) throws Exception;

    public abstract void insertar(Integer a, Integer b, Double peso) throws Exception;

    public abstract LinkedList<Adycencia> adycentes(Integer a);
    //nroVertice = 20
    //20X20
    //1 ------ 2
    //|
    //------- 3

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS \n");
        try {
            for (int i = 1; i <= nro_vertices(); i++) {
                grafo.append("Vertice ").append(String.valueOf(i)).append("\n");
                if (!adycentes(i).isEmpty()) {
                    Adycencia[] lista = adycentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adycencia a = lista[j];
                        grafo.append("Adycente ").append(a.getD().toString()).append("\n");
                    }
                }

            }
        } catch (Exception e) {
        }
        return grafo.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    public Boolean esta_conectado() {
        Boolean band = true;
        try {
            for (int i = 1; i <= nro_vertices(); i++) {
                if (!adycentes(i).isEmpty()) {
                    band = false;
                    break;
                }

            }
        } catch (Exception e) {
        }
        return band;
    }

    public HashMap camino(Integer o, Integer d) throws Exception {
        HashMap sendero = new HashMap();
        if (esta_conectado()) {
            LinkedList<Integer> vertices = new LinkedList<>();
            LinkedList<Double> pesos = new LinkedList<>();
            Boolean finalizar = false;
            Integer inicial = o;
            vertices.add(inicial);
            while (!finalizar) {
                LinkedList<Adycencia> adycencias = adycentes(inicial);
                Double peso = Double.MAX_VALUE;
                Integer T = -1;
                for (int i = 0; i < adycencias.getSize(); i++) {
                    Adycencia ad = adycencias.get(i);
                    if (!estaEnCamino(vertices, ad.getD().intValue())) {
                        Double pesoArista = ad.getPeso();

                        if (d.intValue() == ad.getD().intValue()) {
                            T = ad.getD();
                            peso = pesoArista;
                            break;
                        } else if (pesoArista < peso) {
                            T = ad.getD();
                            peso = pesoArista;
                        }
                    }

                }
                vertices.add(T);
                pesos.add(peso);
                inicial = T;
                if (d.intValue() == inicial.intValue()) {
                    break;
                }

            }

            sendero.put("camino", vertices);
            sendero.put("peso", pesos);
        } else {

        }
        return sendero;

    }

    public Boolean estaEnCamino(LinkedList<Integer> lista, Integer vertices) throws Exception {
        Boolean band = false;
        for (int i = 0; i < lista.getSize(); i++) {
            if (lista.get(i).intValue() == vertices.intValue()) {
                band = true;
                break;
            }
        }
        return band;
    }

    public Boolean isConnected(boolean[] visited) {

        for (var vertex : visited) {
            if (!vertex) {
                return false;
            }
        }
        return true;
    }

    public boolean bfs(int v) {
        boolean[] visitado = new boolean[nro_vertices()];

        MyQueue<Integer> cola = new MyQueue<>();
        cola.offer(v);
        visitado[v - 1] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();

            LinkedList<Adycencia> ady = null;
            try {
                ady = adycentes(actual);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (ady != null) {
                for (int i = 0; i < ady.getSize(); i++) {
                    try {
                        int vecino = ady.get(i).getD();
                        if (!visitado[vecino - 1]) {
                            visitado[vecino - 1] = true;
                            cola.offer(vecino);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return isConnected(visitado);
    }

    public void dfs(int v) {
        boolean[] visitado = new boolean[nro_vertices()];
        dfsRecursive(v, visitado);
    }

    private void dfsRecursive(int v, boolean[] visitado) {
        visitado[v - 1] = true;

        LinkedList<Adycencia> ady = null;
        try {
            ady = adycentes(v);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ady != null) {
            for (int i = 0; i < ady.getSize(); i++) {
                try {
                    int vecino = ady.get(i).getD();
                    if (!visitado[vecino - 1]) {
                        dfsRecursive(vecino, visitado);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public double[][] obtenerMatrizAdyacencia() throws VacioException {
        double INF = Double.MAX_VALUE;
        double[][] matriz = new double[nro_vertices()][nro_vertices()];

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i == j) {
                    matriz[i][j] = 0;
                } else {
                    matriz[i][j] = INF;
                }
            }
        }

        for (int i = 1; i <= nro_vertices(); i++) {
            LinkedList<Adycencia> adyacencias = adycentes(i);

            if (adyacencias.getSize() == 0) {
                continue;
            }

            for (int j = 0; j < adyacencias.getSize(); j++) {
                Adycencia ad = adyacencias.get(j);
                int fila = i - 1; // Convertir el índice del vértice a índice de matriz (restar 1)
                int columna = ad.getD() - 1; // Convertir el índice del vértice destino a índice de matriz (restar 1)
                double peso = ad.getPeso(); // Obtener el peso de la arista

                // Asignar el peso de la arista a la posición correspondiente en la matriz
                matriz[fila][columna] = peso;
            }
        }

        return matriz;
    }

//    public double[][] matrix() {
//        double INF = Double.MAX_VALUE;
//        double[][] matriz = new double[nro_vertices()][nro_vertices()];
//
//        for (int i = 0; i < matriz.length; i++) {
//            for (int j = 0; j < matriz.length; j++) {
//                if (i == j) {
//                    matriz[i][j] = 0;
//                } else {
//                    matriz[i][j] = INF;
//                }
//            }
//        }
//
//        for (int i = 1; i <= nro_vertices(); i++) {
//            Adycencia[] adyacencias = adycentes(i).toArray();
//
//            if (adyacencias.length == 0) {
//                continue;
//            }
//
//            for (Adycencia ad : adyacencias) {
//                matriz[i - 1][ad.getD() - 1] = ad.getPeso();
//            }
//        }
//
//        return matriz;
//    }
}
