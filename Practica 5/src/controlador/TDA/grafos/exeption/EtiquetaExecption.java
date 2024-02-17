/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos.exeption;

/**
 *
 * @author sebastian
 */
public class EtiquetaExecption extends Exception {

    public EtiquetaExecption() {
        super("El grafo no esta etiquetado");
    }
    public EtiquetaExecption(String msg) {
        super(msg);
    }
}
