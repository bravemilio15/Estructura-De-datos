/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTabla;

import controlador.lista.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Censador;

/**
 *
 * @author Bravo
 */
public class ModeloTablaCensador extends AbstractTableModel {
    private LinkedList<Censador> censador = new LinkedList<>();

    public LinkedList<Censador> getCensadors() {
        return censador;
    }
    public void setCensadors(LinkedList<Censador> censador) {
        this.censador= censador;
    }

    @Override
    public int getRowCount() {
        return censador.getSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int col) {

        Censador c = null;

        try {
            c = censador.get(row);
        } catch (Exception e) {
        }

        switch (col) {
            case 0:
                return (c != null) ? c.getId() : " ";
            case 1:
                return (c != null) ? c.getNombre() : " ";
            case 2:
                return (c != null) ? c.getCodigo(): " ";

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Id";
            case 1:
                return "Nombre";
            case 2:
                return "Codigo";
            default:
                return null;
        }
    }
}
