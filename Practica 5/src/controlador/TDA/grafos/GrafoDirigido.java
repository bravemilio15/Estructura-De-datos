/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.grafos.exeption.VerticeOfSizeExecption;
import controlador.TDA.listas.LinkedList;


/**
 *
 * @author sebastian
 */
public class GrafoDirigido extends Grafo {

    private Integer nro_vertices;
    private Integer nro_aristas;
    private LinkedList<Adycencia> listaAdycente[];
    private Double distancias[][];
    private Double[][] matrizAdyacencia;

    public GrafoDirigido(Integer nro_vertices) {
        this.nro_vertices = nro_vertices;
        nro_aristas = 0;
        listaAdycente = new LinkedList[nro_vertices + 1];
        for (int i = 1; i <= nro_vertices; i++) {
            listaAdycente[i] = new LinkedList<>();
        }
    }

    public LinkedList<Adycencia>[] getListaAdycente() {
        return listaAdycente;
    }

    public void setNro_aristas(Integer num) {
        this.nro_aristas = num;
    }

    @Override
    public Integer nro_vertices() {

        return this.nro_vertices;
    }

    @Override
    public Integer nro_aristas() {

        return this.nro_aristas;
    }

    @Override
    public Boolean existe_arista(Integer a, Integer b) throws Exception {

        Boolean band = false;
        if (a.intValue() <= nro_vertices.intValue() && b.intValue() <= nro_vertices.intValue()) {
            LinkedList<Adycencia> lista = listaAdycente[a];
            for (int i = 0; i < lista.getSize(); i++) {
                Adycencia aux = lista.get(i);
                if (aux.getD().intValue() == b.intValue()) {
                    band = true;
                    break;
                }
            }
        } else {
            throw new VerticeOfSizeExecption();
        }
        return band;
    }

    @Override
    public Double peso_arista(Integer a, Integer b) throws Exception {
        Double peso = Double.NaN;

        if (existe_arista(a, b)) {
            LinkedList<Adycencia> lista = listaAdycente[a];
            for (int i = 0; i < lista.getSize(); i++) {
                Adycencia aux = lista.get(i);
                if (aux.getD().intValue() == b.intValue()) {
                    peso = aux.getPeso();
                    break;
                }
            }
        }
        return peso;
    }

    @Override
    public void insertar(Integer a, Integer b) throws Exception {

        insertar(a, b, Double.NaN);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {

        if (a.intValue() <= nro_vertices.intValue() && b.intValue() <= nro_vertices.intValue()) {
            if (!existe_arista(a, b)) {
                nro_aristas++;
                Adycencia aux = new Adycencia();
                aux.setPeso(peso);
                aux.setD(b);
                listaAdycente[a].add(aux);
            }
        } else {
            throw new VerticeOfSizeExecption();
        }
    }

    @Override
    public LinkedList<Adycencia> adycentes(Integer a) {

        return listaAdycente[a];
    }

}
