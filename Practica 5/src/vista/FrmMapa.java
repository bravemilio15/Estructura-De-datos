/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.TDA.GrafoBusquedas.Floyd;
import controlador.AntenaController;
import controlador.TDA.GrafoBusquedas.Dijkstra;
import controlador.TDA.grafos.DibujarGrafo;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.TDA.listas.LinkedList;
import controlador.TDA.listas.exception.VacioException;
import controlador.util.Utilidades;
import java.io.File;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Antena;
import vista.Utiles.UtilesAntenaVista;
import vista.modeloTabla.ModeloTablaAdycencia;

/**
 *
 * @author emilio
 */
public class FrmMapa extends javax.swing.JDialog {

    private AntenaController ec = new AntenaController();
    private ModeloTablaAdycencia mta = new ModeloTablaAdycencia();

    /**
     * Creates new form FrmMapa
     */
    public FrmMapa(java.awt.Frame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        limpiar();

    }

    private void limpiar() {
        try {
            UtilesAntenaVista.cargarComboAntena(cbxOrigen);
            UtilesAntenaVista.cargarComboAntena(cbxDestino);
            cargarTabla();
        } catch (Exception ex) {
            Logger.getLogger(FrmMapa.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void cargarTabla() {
        try {
            mta.setGrafo(ec.getGrafoAntena());
            mta.fireTableDataChanged();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void guardarGrafo() {
        int i = JOptionPane.showConfirmDialog(
                null, "Esta deacuerdo con guardar?",
                "Pregunta",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ec.guardarGrafo();
                JOptionPane.showMessageDialog(null,
                        "Grafo Guardado",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void cargarGrafo() {
        int i = JOptionPane.showConfirmDialog(
                null, "Esta deacuerdo con cargar el grafo?",
                "Pregunta",
                JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            try {
                ec.cargarGrafo();
                JOptionPane.showMessageDialog(null,
                        "Grafo Cargado",
                        "OK",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void mostrarGrafo() {
        try {

            DibujarGrafo dg = new DibujarGrafo();
            dg.crearArchivo(ec.getGrafoAntena());

            System.out.println(ec.getGrafoAntena());

            String os = Utilidades.getOS();
            String dir = Utilidades.getDirProject();

            if (os.equalsIgnoreCase("Windows 10")) {
                Utilidades.abrirNavegadorPredeterminadoWindows(dir + File.separatorChar + "d3" + File.separatorChar + "grafo.html");;

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void CrearAycencia() {
        Integer posOrigen = cbxOrigen.getSelectedIndex();
        Integer posDestino = cbxDestino.getSelectedIndex();

        if (posOrigen != posDestino) {

            Double peso = UtilesAntenaVista.distanciaAntena(UtilesAntenaVista.getComboAntenas(cbxOrigen),
                    UtilesAntenaVista.getComboAntenas(cbxDestino));

            System.out.println(peso);

            try {
                ec.getGrafoAntena().insertarAristaE(ec.getAntenas().get(posOrigen),
                        ec.getAntenas().get(posDestino),
                        peso);
            } catch (VacioException ex) {
                Logger.getLogger(FrmMapa.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FrmMapa.class.getName()).log(Level.SEVERE, null, ex);
            }

            JOptionPane.showMessageDialog(null,
                    "Se Realizo bien la Adyacencia",
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE);

            limpiar();
        } else {
            JOptionPane.showMessageDialog(null,
                    "No se puede agregar la misma Adyacencia",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void camino() throws Exception {
        if (ec.getGrafoAntena() != null) {
            Integer posOrigen = cbxOrigen.getSelectedIndex();
            Integer posDestino = cbxDestino.getSelectedIndex();

            HashMap<String, LinkedList> mapa = ec.getGrafoAntena().camino(posOrigen, posDestino);
            if (mapa.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No Existe Camino",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                LinkedList<Integer> caminos = mapa.get("camino");
                for (int i = 0; i < caminos.getSize(); i++) {
                    Integer v = caminos.get(i);
                    System.out.println(ec.getGrafoAntena().obtenerEtiqueta(v));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Mapa vacio",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verificarConectividad() throws VacioException {
        if (ec.getGrafoAntena() != null) {
            boolean conectadoBFS = ec.getGrafoAntena().bfs(1); // Comienza desde el vértice 1
            boolean conectadoDFS = false;
            try {
                ec.getGrafoAntena().dfs(1); // Comienza desde el vértice 1
                conectadoDFS = ec.getGrafoAntena().isConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (conectadoBFS && conectadoDFS) {
                JOptionPane.showMessageDialog(null, "El grafo está completamente conectado");
            } else {
                JOptionPane.showMessageDialog(null, "El grafo no está completamente conectado");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay grafo cargado");
        }
    }

    private void conectarNodosAleatoriamenteEnGrafo() {
        try {
            GrafoEtiquetadoDirigido<Antena> grafo = ec.getGrafoAntena();
            conectarNodosAleatoriamenteConPeso(grafo);
            JOptionPane.showMessageDialog(this, "Nodos conectados aleatoriamente con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al conectar nodos aleatoriamente: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void conectarNodosAleatoriamenteConPeso(GrafoEtiquetadoDirigido<Antena> grafo) throws Exception {
        Random rand = new Random();

        // Itera sobre todos los nodos del grafo
        for (int nodoActual = 1; nodoActual <= grafo.nro_vertices(); nodoActual++) {
            // Conecta el nodo actual al menos dos veces con otros nodos
            for (int conexion = 0; conexion < 2; conexion++) {
                // Elige aleatoriamente otro nodo al que conectar
                int nodoDestino = rand.nextInt(grafo.nro_vertices()) + 1;

                // Asegúrate de que el nodo destino no sea el mismo que el nodo actual
                while (nodoDestino == nodoActual) {
                    nodoDestino = rand.nextInt(grafo.nro_vertices()) + 1;
                }

                // Obtiene las antenas correspondientes a los nodos actual y destino
                Antena antenaOrigen = grafo.obtenerEtiqueta(nodoActual);
                Antena antenaDestino = grafo.obtenerEtiqueta(nodoDestino);

                // Calcula el peso utilizando la distancia entre las antenas
                Double peso = UtilesAntenaVista.distanciaAntena(antenaOrigen, antenaDestino);

                // Conecta el nodo actual con el nodo destino con el peso calculado
                grafo.insertarAristaE(antenaOrigen, antenaDestino, peso);
            }
        }
    }

    private void floyd() {
        try {
            // Verificar si el grafo de antenas en FrmMapa no es nulo
            if (ec != null && ec.getGrafoAntena() != null) {
                int posOrigen = cbxOrigen.getSelectedIndex();
                int posDestino = cbxDestino.getSelectedIndex();

                // Obtener la matriz de adyacencia del grafo de antenas desde FrmMapa
                double[][] matrizAdyacencia = ec.getGrafoAntena().obtenerMatrizAdyacencia();

                // Calcular la ruta más corta utilizando el algoritmo de Floyd
                long startTime = System.currentTimeMillis(); // Iniciar medición de tiempo
                String rutaMasCorta = Floyd.calcularCaminoMasCorto(matrizAdyacencia, posOrigen, posDestino);
                long endTime = System.currentTimeMillis(); // Finalizar medición de tiempo
                long totalTime = endTime - startTime; // Calcular tiempo total en milisegundos

                // Mostrar la ruta más corta en el JTextArea
                txtCamino.setText(rutaMasCorta);
                txtCamino.append("\nTiempo de ejecución: " + totalTime + " ms");
            } else {
                // Mostrar un mensaje de error si el grafo es nulo
                JOptionPane.showMessageDialog(null, "Mapa vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // Capturar y mostrar cualquier excepción ocurrida durante el cálculo de las rutas
            JOptionPane.showMessageDialog(null, "Error al calcular el camino: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void dijkstra() {
        try {
            // Verificar si el grafo de antenas en FrmMapa no es nulo
            if (ec != null && ec.getGrafoAntena() != null) {
                int posOrigen = cbxOrigen.getSelectedIndex();
                int posDestino = cbxDestino.getSelectedIndex();

                double[][] matrizAdyacencia = ec.getGrafoAntena().obtenerMatrizAdyacencia();

                // Calcular la ruta más corta utilizando el algoritmo de Dijkstra
                Dijkstra dijkstra = new Dijkstra();
                long startTime = System.currentTimeMillis(); // Iniciar medición de tiempo
                String rutaMasCorta = dijkstra.encontrarRutaMasCorta(matrizAdyacencia, posOrigen, posDestino);
                long endTime = System.currentTimeMillis(); // Finalizar medición de tiempo
                long totalTime = endTime - startTime; // Calcular tiempo total en milisegundos

                // Mostrar la ruta más corta en el JTextArea
                txtCamino.setText(rutaMasCorta);
                txtCamino.setText(rutaMasCorta);
                txtCamino.append("\nTiempo de ejecución: " + totalTime + " ms");
            } else {
                // Mostrar un mensaje de error si el grafo es nulo
                JOptionPane.showMessageDialog(null, "Mapa vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // Capturar y mostrar cualquier excepción ocurrida durante el cálculo de las rutas
            JOptionPane.showMessageDialog(null, "Error al calcular el camino: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxOrigen = new javax.swing.JComboBox<>();
        cbxDestino = new javax.swing.JComboBox<>();
        btnAdycencia = new javax.swing.JButton();
        brnFloyd = new javax.swing.JButton();
        btnDijkstra = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCamino = new javax.swing.JTextArea();
        btnConectividad = new javax.swing.JButton();
        btnConectar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();
        btnGrafo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mapas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Antenas"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ubicacion Antenas"));

        jLabel2.setText("Origen");

        jLabel3.setText("Destino");

        cbxOrigen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxOrigenActionPerformed(evt);
            }
        });

        cbxDestino.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAdycencia.setText("Adyacencia");
        btnAdycencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdycenciaActionPerformed(evt);
            }
        });

        brnFloyd.setText("Floyd");
        brnFloyd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnFloydActionPerformed(evt);
            }
        });

        btnDijkstra.setText("Dijkstra");
        btnDijkstra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDijkstraActionPerformed(evt);
            }
        });

        txtCamino.setColumns(20);
        txtCamino.setRows(5);
        jScrollPane3.setViewportView(txtCamino);

        btnConectividad.setText("Conectividad");
        btnConectividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectividadActionPerformed(evt);
            }
        });

        btnConectar.setText("Conectar");
        btnConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConectarActionPerformed(evt);
            }
        });

        btnGuardar.setText("guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCargar.setText("cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnGrafo.setText("Ver Grafo");
        btnGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafoActionPerformed(evt);
            }
        });

        jLabel1.setText("UBICACIONES DE LAS ANTENAS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(btnAdycencia)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnConectar)))
                                .addGap(18, 18, 18)
                                .addComponent(btnConectividad))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnGrafo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargar, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154)
                        .addComponent(btnDijkstra)
                        .addGap(18, 18, 18)
                        .addComponent(brnFloyd))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(brnFloyd)
                            .addComponent(btnDijkstra)
                            .addComponent(btnGuardar)
                            .addComponent(btnCargar)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbxOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdycencia)
                            .addComponent(btnConectar)
                            .addComponent(btnConectividad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGrafo)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafoActionPerformed
        mostrarGrafo();
    }//GEN-LAST:event_btnGrafoActionPerformed

    private void cbxOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxOrigenActionPerformed

    private void btnAdycenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdycenciaActionPerformed
        CrearAycencia();
    }//GEN-LAST:event_btnAdycenciaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        guardarGrafo();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        cargarGrafo();
    }//GEN-LAST:event_btnCargarActionPerformed

    private void brnFloydActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnFloydActionPerformed
        floyd();
    }//GEN-LAST:event_brnFloydActionPerformed

    private void btnConectividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectividadActionPerformed
        try {
            verificarConectividad();
        } catch (VacioException ex) {
            Logger.getLogger(FrmMapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConectividadActionPerformed

    private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConectarActionPerformed
        conectarNodosAleatoriamenteEnGrafo();
    }//GEN-LAST:event_btnConectarActionPerformed

    private void btnDijkstraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDijkstraActionPerformed
        dijkstra();
    }//GEN-LAST:event_btnDijkstraActionPerformed

    /**
     * v
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMapa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMapa dialog = new FrmMapa(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnFloyd;
    private javax.swing.JButton btnAdycencia;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnConectar;
    private javax.swing.JButton btnConectividad;
    private javax.swing.JButton btnDijkstra;
    private javax.swing.JButton btnGrafo;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbxDestino;
    private javax.swing.JComboBox<String> cbxOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea txtCamino;
    // End of variables declaration//GEN-END:variables
}
