package controlador.TDA.grafos;

import controlador.TDA.listas.LinkedList;
import controlador.util.Utilidades;

import java.io.File;
import java.io.FileWriter;

public class DibujarGrafo {
    
    String URL = "d3/grafo.js";
    
    public void crearArchivo(Grafo g) {
        StringBuilder data = new StringBuilder();
        
        data.append("var nodes = new vis.DataSet([\n");
        for (int i = 1; i <= g.nro_vertices(); i++) {
            String etiqueta = obtenerEtiqueta(g, i);
            data.append(String.format("  { id: %d, label: \"%s\" },\n", i, etiqueta));
        }
        data.append("]);\n\n");
        
        data.append("var edges = new vis.DataSet([\n");
        for (int i = 1; i <= g.nro_vertices(); i++) {
            try {
                LinkedList<Adycencia> adyacentes = g.adycentes(i);
                for (int j = 0; j < adyacentes.getSize(); j++) {
                    Adycencia a = adyacentes.get(j);
                    String peso = obtenerPeso(g, i, a.getD());
                    data.append(String.format("  { from: %d, to: %d, label: \"%s\" },\n", i, a.getD(), Utilidades.redondear(Double.parseDouble(peso))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        data.append("]);\n\n");

        // Agrega el código final al String
        data.append("var container = document.getElementById(\"mynetwork\");\n"
                + "      var data = {\n"
                + "        nodes: nodes,\n"
                + "        edges: edges,\n"
                + "      };\n"
                + "      var options = {};\n"
                + "      var network = new vis.Network(container, data, options);\n"
                + "");
        
        try {
            FileWriter file = new FileWriter(URL);
            file.write(data.toString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private String obtenerPeso(Grafo g, int origen, int destino) {
        try {
            return g.peso_arista(origen, destino).toString();
        } catch (Exception e) {
            return "";
        }
    }
    
    private String obtenerEtiqueta(Grafo g, int i) {
        if (g instanceof GrafoEtiquetadoNoDirigido) {
            return ((GrafoEtiquetadoNoDirigido<?>) g).obtenerEtiqueta(i).toString();
        } else {
            return "Nodo " + i;
        }
    }
    
    public static void main(String[] args) {
        try {
            GrafoEtiquetadoNoDirigido<String> g = new GrafoEtiquetadoNoDirigido<>(5, String.class);
            
            g.etiquetarVertice(1, "Emilio");
            g.etiquetarVertice(2, "Andres");
            g.etiquetarVertice(3, "Bravo");
            g.etiquetarVertice(4, "Herrera");
            g.etiquetarVertice(5, "Unkown");

//            g.insertarAristaE("Emilio", "Andres", 1.0);
//            g.insertarAristaE("Andres", "Bravo", 2.0);
//            g.insertarAristaE("Bravo", "Herrera", 3.0);
//            g.insertarAristaE("Herrera", "Unkown", 4.0);
//            g.insertarAristaE("Unkown", "Emilio", 9.0);
            g.insertar(1, 2, 1.0);
            g.insertar(2, 3, 2.0);
            g.insertar(3, 4, 3.0);
            g.insertar(4, 5, 4.0);
            g.insertar(5, 1, 10.0);
            
            System.out.println(g);
            
            DibujarGrafo df = new DibujarGrafo();
            df.crearArchivo(g);
            
            String os = Utilidades.getOS();
            String dir = Utilidades.getDirProject();
            
            if (os.equalsIgnoreCase("Linux")) {
                Utilidades.abrirNavegadorPredeterminadoLinux(
                        dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");
            }
        } catch (Exception ex) {
            System.out.println("ex" + ex);
        }
    }
    
}
