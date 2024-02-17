/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.modeloTabla;

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
public class ModeloTablaAntena extends AbstractTableModel {

    private LinkedList<Antena> antenas = new LinkedList<>();

    public LinkedList<Antena> getAntenas() {
        return antenas;
    }

    public void setAntenas(LinkedList<Antena> antenas) {
        this.antenas = antenas;
    }
    @Override
    public int getRowCount() {
 
        return antenas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4; 
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Antena e = null;
        try {
            e = antenas.get(i);
        } catch (VacioException ex) {
            Logger.getLogger(ModeloTablaAntena.class.getName()).log(Level.SEVERE, null, ex);
        }

        switch (i1) { // i +1 
            case 0:
                return (e != null) ? e.getNombre() : "";
            case 1:
                return (e != null) ? e.getLatitud() : "";
            case 2:
                return (e != null) ? e.getLongitud() : "";
            case 3:
                return (e != null) ? e.getFoto() : "";

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Longitud";
            case 2:
                return "Latitud";
            case 3:
                return "Foto";
            default:
                return null;
        }
    }

}
