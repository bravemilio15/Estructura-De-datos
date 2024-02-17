/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTabla;

import controlador.TDA.grafos.Adycencia;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Antena;


/**
 *
 * @author sebastian
 */
public class ModeloTablaAdycencia extends AbstractTableModel {

    private GrafoEtiquetadoNoDirigido<Antena> grafo;

    public GrafoEtiquetadoNoDirigido<Antena> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoNoDirigido<Antena> grafo) {
        this.grafo = grafo;
    }

    @Override
    public int getRowCount() {
       
        return grafo.nro_vertices();
    }

    @Override
    public int getColumnCount() {
        return grafo.nro_vertices() + 1;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        if (i1 == 0) {
            return grafo.obtenerEtiqueta(i + 1).toString();
        } else {
            String valor = "-*-*-";
            try {
                if (i == i1) {
                    return valor;
                } else {
                    Antena o = grafo.obtenerEtiqueta(i + 1);
                    Antena d = grafo.obtenerEtiqueta(i1 + 1);
                    if (grafo.existeAristaE(o, d)) {
                        valor = grafo.peso_arista((i + 1), i1).toString();
                    }
                    return valor;

                }

            } catch (Exception e) {
                return valor;
            }
        }
    }

    @Override
    public String getColumnName(int column) {

        if (column == 0) {
            return "Antenas";
        } else {
            return grafo.obtenerEtiqueta(column).toString();
        }

    }

}
