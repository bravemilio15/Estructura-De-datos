/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTabla;

import controlador.lista.Exception.VacioException;
import controlador.lista.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import modelo.Censador;
import modelo.Censo;
import vista.util.UtilVista;

/**
 *
 * @author Bravo
 */
public class ModeloTablaCenso extends AbstractTableModel {

    private LinkedList<Censo> censo = new LinkedList<>();
    private LinkedList<Censador> censador = new LinkedList<>();

    public LinkedList<Censo> getCensos() {
        return censo;
    }

    public void setCensos(LinkedList<Censo> censo) {
        this.censo = censo;
    }

    public LinkedList<Censador> getCensador() {
        return censador;
    }

    public void setCensador(LinkedList<Censador> censador) {
        this.censador = censador;
    }

    @Override
    public int getRowCount() {
        return censo.getSize();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Censo c = null;

        try {
            c = censo.get(row);
        } catch (Exception e) {
        }

        switch (col) {
            case 0:
                if (c != null && c.getCensador() != null) {
                    return c.getCensador().getNombre();
                }
                return " ";

            case 1:
                return (c != null) ? c.getNombre() : " ";
            case 2:
                return (c != null) ? c.getMotivo() : " ";
            case 3:
                return (c != null) ? c.getEstadoEmocional() : " ";
            case 4:
                return (c != null) ? UtilVista.formatDate(c.getFecha()) : " ";
            case 5:
                return (c != null) ? c.getEstado() : " ";
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Censador";
            case 1:
                return "Censado";
            case 2:
                return "Motivo";
            case 3:
                return "Estado Emocional";
            case 4:
                return "Fecha";
            case 5:
                return "Estado";
            default:
                return null;
        }
    }
}
