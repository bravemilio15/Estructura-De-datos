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
public class Stack<E> {

    private StackOperation<E> stackOperation;

    public Stack(Integer cant) {
        this.stackOperation = new StackOperation(cant);
    }

    public void push(E data) throws FullException {
        stackOperation.push(data);
    }

    public Integer getSize() {
        return this.stackOperation.getSize();
    }
    
    

    public void clear() {
        this.stackOperation.clear();
    }

    public Integer setTop() {
        return this.stackOperation.getTop();
    }

    //Hacer PUSH
    public void print() {
        System.out.println("Stack");
        System.out.print(stackOperation.print());
        System.out.println("********");
    }

    public E pop() throws VacioException {
        return stackOperation.pop();
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack(10);
        try {
            s.push(12);
            s.push(10);
            s.push(11);
            s.print();
            
            System.out.println("POP");
            s.pop();
            s.print();
        } catch (Exception e) {
            System.out.println("Wrong");
        }

    }

}
