package vista.Utiles;

import controlador.AntenaController;
import controlador.TDA.grafos.Grafo;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.util.Utilidades;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JComboBox;
import modelo.Antena;

/**
 *
 * @author emilio
 */
public class UtilesAntenaVista {

    public static void cargarComboAntena(JComboBox cbx) throws Exception {
        cbx.removeAllItems();
        LinkedList<Antena> lista = new AntenaController().listAll(); //Objeto oculto

        for (int i = 0; i < lista.getSize(); i++) {
            cbx.addItem(lista.get(i));
        }
    }

    public static Antena getComboAntenas(JComboBox cbx) {
        return (Antena) cbx.getSelectedItem();
    }

    public static Double distanciaAntena(Antena o, Antena d) {

        return Utilidades.distanciaDosPuntos(o.getLatitud(), o.getLongitud(), d.getLatitud(), d.getLongitud());
    }

  

    public static void crearMapaAntena(Grafo g) throws IOException {
        if (g instanceof GrafoEtiquetadoDirigido || g instanceof GrafoEtiquetadoNoDirigido) {
            GrafoEtiquetadoDirigido ge = (GrafoEtiquetadoDirigido) g;

            String mapa = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                    + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                    + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                    + "\n"
                    + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                    + "\n"
                    + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                    + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                    + "            }).addTo(map);\n";

            for (int i = 1; i <= ge.nro_vertices(); i++) {
                Antena ec = (Antena) ge.obtenerEtiqueta(i);
                mapa += "            L.marker([" + ec.getLatitud() + ", " + ec.getLongitud() + "]).addTo(map)\n"
                        + "                    .bindPopup('" + ec.getNombre() + "')\n"
                        + "                    .openPopup();\n";
            }

            FileWriter file = new FileWriter("mapas/mapa.js");
            file.write(mapa);
            file.close();
        }
    }
}
