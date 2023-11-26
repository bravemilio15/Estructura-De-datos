/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.util;

import controlador.CensadorDAO;
import controlador.lista.Exception.VacioException;
import controlador.lista.LinkedList;
import controlador.lista.Nodo;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import modelo.Censador;

/**
 *
 * @author emilio
 */
public class UtilVista {

    public static void cargarCensador(JComboBox cbxCensador) throws VacioException {
        CensadorDAO csd = new CensadorDAO();
        cbxCensador.removeAllItems();

        for (int i = 0; i < csd.getCensadores().getSize(); i++) {
            Censador censador = csd.getCensadores().get(i);
            cbxCensador.addItem(censador);  // Agregar la instancia de Censador al combo box
            cbxCensador.updateUI();
        }
    }

    public static Censador getComboCensador(JComboBox cbx) {
        Object selectedObject = cbx.getSelectedItem();
        System.out.println("Tipo de objeto seleccionado: " + selectedObject.getClass().getName());
        return (Censador) selectedObject;
    }

    public static String formatDate(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            return formato.format(fecha);
        } else {
            return "";  // o algún otro valor predeterminado si la fecha es null
        }
    }

}
