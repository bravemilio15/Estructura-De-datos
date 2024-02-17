/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.listas;

import controlador.TDA.listas.exception.LlenoException;
import controlador.TDA.listas.exception.VacioException;

/**
 *
 * @author sebastian
 */
public class Stack<E> {
    private StackOperation<E> stackOperation;
    public Stack(Integer cant) {
        this.stackOperation = new StackOperation<>(cant);
    }
    public void push(E dato) throws LlenoException {
        stackOperation.push(dato);
    }
    
    public Integer getSize() {
        return this.stackOperation.getSize();
    }
    
    public void clear() {
        this.stackOperation.clear();
    }
    
    public Integer getTop() {
        return this.stackOperation.getTop();
    }
    
     public boolean isEmpty() {
        return stackOperation.isEmpty(); // 
    }
    
    public void print() {
        System.out.println("PILA");
        System.out.println(stackOperation.print());
        System.out.println("******");
    }
    //TODO.. falta push
    public E pop() throws VacioException {
        return stackOperation.pop();
    }
}










