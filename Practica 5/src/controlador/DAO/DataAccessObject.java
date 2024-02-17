/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.thoughtworks.xstream.XStream;
import controlador.TDA.listas.LinkedList;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author sebastian
 */
public class DataAccessObject<T> implements TrasnferObject<T> {

    private XStream xstream;
    private Class<T> clazz;
    private String URL;

    public DataAccessObject(Class<T> clazz) {
        xstream = Connection.getXstream();
        this.clazz = clazz;
        URL = Connection.getURL() + this.clazz.getSimpleName() + ".json";
    }

    @Override
    public Boolean save(T data) {
        LinkedList<T> list = listAll();
        list.add(data);
        try {
            this.xstream.alias(list.getClass().getName(), LinkedList.class);
            this.xstream.toXML(list, new FileWriter(URL));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean update(T data, Integer index) {
        LinkedList<T> list = listAll();

        try {
            list.update(data, index);
            this.xstream.alias(list.getClass().getName(), LinkedList.class);
            this.xstream.toXML(list, new FileWriter(URL));
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public LinkedList<T> listAll() {
        //guardar una ListaEnlzada
        LinkedList<T> list = new LinkedList<>();
        try {
            list = (LinkedList<T>) xstream.fromXML(new FileReader(URL));
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public T find(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Integer generated_id() {
        return listAll().getSize() + 1;
    }

    public XStream getXstream() {
        return xstream;
    }

}
