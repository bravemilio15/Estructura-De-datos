/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package controlador.lista.Exception;

/**
 *
 * @author emilio
 */
public class FullException extends Exception {

    /**
     * Creates a new instance of <code>VacioException</code> without detail
     * message.
     */
    public FullException() {
    }

    /**
     * Constructs an instance of <code>VacioException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public FullException(String msg) {
        super(msg);
    }
}
