/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.lista;

import controlador.lista.Exception.FullException;
import controlador.lista.Exception.VacioException;

/**
 *
 * @author emilio
 */
public class StackOperation<E> extends LinkedList<E> { // Hacerla Infinita para la prueba

    private Integer top;

    public StackOperation(Integer top) {
        this.top = top;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }

    public void push(E data) throws FullException {
        if (verify()) {
            addFirst(data);
        } else {
            throw new FullException("Stack Full");
        }
    }

    public E pop() throws VacioException {
        if (isEmpty()) {
            throw new VacioException(" Stack Empty");
        } else {
            return deleteFirst();
        }
    }

}
