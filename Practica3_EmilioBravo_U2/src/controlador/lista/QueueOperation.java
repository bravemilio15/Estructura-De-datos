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
public class QueueOperation<E> extends LinkedList<E> { // Hacerla Infinita para la prueba

    private Integer top;

    public QueueOperation(Integer top) {
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

    public void queue(E data) throws FullException {
        if (verify()) {
            add(data);
        } else {
            throw new FullException("Queue Full");
        }
    }

    public E dequeue() throws VacioException {
        if (isEmpty()) {
            throw new VacioException(" Que Empty");
        } else {
            return deleteFirst();
        }
    }

}
