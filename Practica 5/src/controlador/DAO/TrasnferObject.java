/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.TDA.listas.LinkedList;

/**
 *
 * @author sebastian
 */
public interface TrasnferObject <T> {
    public Boolean save(T data);
    public Boolean update(T data, Integer index);
    public LinkedList<T> listAll();
    public T find(Integer id);
    
}
