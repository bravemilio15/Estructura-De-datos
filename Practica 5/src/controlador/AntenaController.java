/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;
import java.io.FileReader;
import java.io.FileWriter;
import modelo.Antena;

/**
 *
 * @author Bravo
 */
public class AntenaController extends DataAccessObject<Antena> {

    private Antena antena = new Antena();
    private LinkedList<Antena> antenas = new LinkedList<>();
    private GrafoEtiquetadoNoDirigido<Antena> grafoAntena;

    public GrafoEtiquetadoNoDirigido<Antena> getGrafoAntena() throws VacioException {
        if (grafoAntena == null) {
            LinkedList<Antena> lista = getAntenas();
            Integer size = lista.getSize();
            if (size > 0) {
                grafoAntena = new GrafoEtiquetadoNoDirigido(size, Antena.class);
                for (int i = 0; i < size; i++) {
                    grafoAntena.etiquetarVertice((i + 1), lista.get(i));

                }

            }
        }
        return grafoAntena;
    }

    public void setGrafoAntena(GrafoEtiquetadoNoDirigido<Antena> grafoAntena) {
        this.grafoAntena = grafoAntena;
    }

    public AntenaController() {
        super(Antena.class);
    }

    public LinkedList<Antena> getAntenas() {
        if (antenas.isEmpty()) {
            antenas = listAll();
        }
        return antenas;
    }

    public void setAntenas(LinkedList<Antena> antenas) {
        this.antenas = antenas;
    }

    public Antena getAntena() {
        if (antena == null) {
            antena = new Antena();
        }
        return antena;
    }

    public void setAntena(Antena antena) {
        this.antena = antena;
    }

    public Boolean save() {
        antena.setId(generated_id());
        return save(antena);
    }

    public Boolean update() {
        //antena.setId(generated_id());
        return update(antena, BuscarIndex(antena.getId()));
    }

    private Integer BuscarIndex(Integer id) {
        Integer index = -1;

        if (!listAll().isEmpty()) {
            Antena[] antenas = listAll().toArray();
            for (int i = 0; i < antenas.length; i++) {
                if (id.intValue() == antenas[i].getId().intValue()) {
                    index = 1;
                    break;
                }
            }
        }
        return index;

    }

    public void guardarGrafo() throws Exception {
        if (grafoAntena != null) {
            getXstream().alias(grafoAntena.getClass().getName(), GrafoEtiquetadoNoDirigido.class);
            getXstream().toXML(grafoAntena, new FileWriter("data/grafo.json"));
        } else {
            new NullPointerException("Grafo Vacio");
        }

    }

    public void cargarGrafo() throws Exception {
        grafoAntena = (GrafoEtiquetadoNoDirigido<Antena>) getXstream().fromXML(new FileReader("data/grafo.json"));
        antenas.clear();
        for (int i = 1; i < grafoAntena.nro_vertices(); i++) {
            antenas.add(grafoAntena.obtenerEtiqueta(i));
        }
        System.out.println("Grafo: " + antenas);

    }

   

    public static void main(String[] args) throws Exception {
        AntenaController ec = new AntenaController();

    }
}
