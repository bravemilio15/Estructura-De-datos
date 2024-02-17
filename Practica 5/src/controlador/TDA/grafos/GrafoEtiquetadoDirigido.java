/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.grafos.exeption.EtiquetaExecption;
import controlador.TDA.listas.LinkedList;
import java.util.HashMap;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebastian
 */
public class GrafoEtiquetadoDirigido<E> extends GrafoDirigido {

    protected E etiqueta[];//etqiuetas del grafo ---> Integer o, Integer d
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;

    public GrafoEtiquetadoDirigido(Integer nro_vertices, Class<E> clazz) {
        super(nro_vertices);
        this.clazz = clazz;
        etiqueta = (E[]) Array.newInstance(clazz, nro_vertices + 1);
        dicVertices = new HashMap<>(nro_vertices);
    }

    public Boolean existeAristaE(E o, E d) throws Exception {
        if (estaEtiquetado()) {
            return existe_arista(obtenerCodigoE(o), obtenerCodigoE(d));
        } else {
            throw new EtiquetaExecption();
        }
    }

    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        if (estaEtiquetado()) {
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), peso);
        } else {
            throw new EtiquetaExecption();
        }
    }

    public void insertarAristaE(E o, E d) throws Exception {

        if (estaEtiquetado()) {
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), Double.NaN);
        } else {
            throw new EtiquetaExecption();
        }
    }

    public HashMap<E, Integer> getDicVertices() {
        return dicVertices;
    }

    public void setDicVertices(HashMap<E, Integer> dicVertices) {
        this.dicVertices = dicVertices;
    }

    public LinkedList<Adycencia> adycentesE(E o) throws Exception {
        if (estaEtiquetado()) {
            return adycentes(obtenerCodigoE(o));
        } else {
            throw new EtiquetaExecption();
        }
    }

    public void etiquetarVertice(Integer vertice, E dato) {
        etiqueta[vertice] = dato;
        dicVertices.put(dato, vertice);
    }

    public Boolean estaEtiquetado() {
        Boolean band = true;
        for (int i = 1; i < etiqueta.length; i++) {
            E dato = etiqueta[i];
            if (dato == null) {
                band = false;
                break;
            }
        }
        return band;
    }

    public Integer obtenerCodigoE(E etiqueta) {
        return dicVertices.get(etiqueta);
    }

    public E obtenerEtiqueta(Integer i) {
        return etiqueta[i];
    }

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS ETIQUETADOS \n");
        try {
            for (int i = 1; i <= nro_vertices(); i++) {
                grafo.append("Vertice ").append(obtenerEtiqueta(i)).append("\n");
                if (!adycentes(i).isEmpty()) {
                    Adycencia[] lista = adycentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adycencia a = lista[j];
                        grafo.append("Adycente ").append(obtenerEtiqueta(a.getD())).
                                append(" -Peso- ").append(a.getPeso()).append("\n");
                    }
                }

            }
        } catch (Exception e) {
        }
        return grafo.toString();
    }

    public void dfs(int v, boolean[] visitado) throws Exception {
        visitado[v - 1] = true;

        LinkedList<Adycencia> ady = adycentes(v);
        if (ady != null) {
            for (int i = 0; i < ady.getSize(); i++) {
                int vecino = ady.get(i).getD();
                if (!visitado[vecino - 1]) {
                    dfs(vecino, visitado);
                }
            }
        }
    }

    public boolean isConnected() {
        try {
            if (estaEtiquetado()) {
                boolean[] visited = new boolean[nro_vertices()];
                dfs(1, visited); // Realiza un recorrido en profundidad desde el vértice 1
                for (boolean vertexVisited : visited) {
                    if (!vertexVisited) {
                        return false;
                    }
                }
                return true;
            } else {
                throw new EtiquetaExecption("El grafo no está etiquetado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Manejo de excepciones
        }
    }

    public int indiceVertice(Integer etiqueta) {
        for (int i = 0; i < dicVertices.size(); i++) {
            if (dicVertices.get(i).equals(etiqueta)) {
                return i;
            }
        }
        return -1; // Retorna -1 si no se encuentra la etiqueta en el grafo
    }

}
