/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controlador.interfazDAO;

import controlador.lista.LinkedList;

/**
 *
 * @author emilio
 */
public interface TransferObject <T>{
    
     public Boolean save(T data);
    public Boolean update(T data, Integer index );
    public LinkedList<T> listAll();
    public T find (Integer id);
    
}
