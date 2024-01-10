/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.interfazDAO.DataAccessObject;
import controlador.lista.Exception.IndexListException;
import controlador.lista.Exception.NonExistentElementException;
import controlador.lista.Exception.VacioException;
import controlador.lista.LinkedList;
import javax.swing.SpringLayout;
import modelo.Censo;

/**
 *
 * @author Bravo
 */
public class CensoDAO extends DataAccessObject<Censo> {

    private Censo censo;

    public CensoDAO() {
        super(Censo.class);
    }

    public Censo getCenso() {
        if (censo == null) {
            censo = new Censo();
        }
        return censo;
    }

    public void setCenso(Censo censo) {
        this.censo = censo;
    }

    public Boolean save() {
        censo.setId(generated_id());
        return save(censo);
    }

    public Boolean update(Integer pos) {
        return update(censo, pos);
    }

    public int contarPorMotivo(String Estado) throws VacioException {
        int contador = 0;
        LinkedList<Censo> censos = listAll();

        for (int i = 0; i < censos.getSize(); i++) {
            Censo censo = censos.get(i);
            if (Estado.equals(censo.getEstado())) {
                contador++;
            }
        }

        return contador;
    }

    public int contarDivorciados() throws VacioException {
        return contarPorMotivo("Divorciado");
    }

    public int contarSeparados() throws VacioException {
        return contarPorMotivo("Separado");
    }

    public LinkedList<Censo> ordenarPorAtributo(String atributo, boolean ascendente, String tipo) throws VacioException, IllegalAccessException {
        LinkedList<Censo> censos = listAll();

        if (censos.isEmpty()) {
            System.out.println("La lista está vacía. No se puede ordenar.");
            return null;
        }

        if (tipo.equals("Quick")) {

            censos.quickSort(atributo, ascendente);

            return censos;

        } else {
            censos.mergeSort(atributo, ascendente);

            return censos;
        }
    }

    public Object buscarPorAtributo(String atributo, Object valor, String tipoBusqueda)
            throws VacioException, IllegalAccessException, NonExistentElementException, IndexListException {
        LinkedList<Censo> censos = listAll();

        if (censos.isEmpty()) {
            System.out.println("La lista está vacía. No se puede realizar la búsqueda.");
            return null;
        }

        censos.quickSort(atributo, true);

        switch (atributo) {
            case "nombre":
            case "estado":
            case "motivo":
            case "fecha":
            try {
                return "binario".equalsIgnoreCase(tipoBusqueda)
                        ? censos.binarySearch(atributo, valor)
                        : censos.linearBinarySearch(atributo, valor);
            } catch (NonExistentElementException e) {
                System.out.println("El elemento no fue encontrado con la búsqueda seleccionada.");
            }          
            break;
        }

        return null;
    }

}
