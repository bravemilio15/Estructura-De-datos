/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.TDA.listas.exception;

/**
 *
 * @author walter
 */
public class TopeException extends Exception {

    public TopeException() {
        super("La pila esta llena");
    }
    
    public TopeException(String msg) {
        super(msg);
    }
}
