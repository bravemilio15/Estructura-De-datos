/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos.exeption;

/**
 *
 * @author sebastian
 */
public class VerticeOfSizeExecption extends Exception {

    public VerticeOfSizeExecption() {
        super("Fuera de rango");
    }
    public VerticeOfSizeExecption(String msg) {
        super(msg);
    }
}
