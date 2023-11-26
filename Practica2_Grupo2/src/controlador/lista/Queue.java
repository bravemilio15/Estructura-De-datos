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
public class Queue<E> {

    private QueueOperation<E> queueOperation;

    public Queue(Integer cant) {
        this.queueOperation = new QueueOperation(cant);
    }

    public Integer setSize() {
        return this.queueOperation.getSize();
    }

    public Integer setTop() {
        return this.queueOperation.getTop();
    }

    //Hacer PUSH
    public void print() {
        System.out.println("Queue");
        System.out.print(queueOperation.print());
        System.out.println("********");
    }

    public E dequeue() throws VacioException {
        return queueOperation.dequeue();
    }

    public void queue(E data) throws FullException {
        queueOperation.queue(data);
    }

    public void clear() {
        this.queueOperation.clear();
    }

    public static void main(String[] args) {
        Queue<Integer> s = new Queue(10);
        try {
            s.queue(12);
            s.queue(10);
            s.queue(11);
            s.print();

            System.out.println("Queue");
            s.dequeue();
            s.print();
        } catch (Exception e) {
            System.out.println("Wrong");
        }

    }

}
