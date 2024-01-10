/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import controlador.interfazDAO.DataAccessObject;
import controlador.lista.LinkedList;

import modelo.Censador;

/**
 *
 * @author Bravo
 */
public class CensadorDAO extends DataAccessObject<Censador> {

    private Censador censador;
     private LinkedList<Censador> censadores = new LinkedList<>();

    public CensadorDAO() {
        super(Censador.class);
    }

    public Censador getCensador() {
        if (censador == null) {
            censador = new Censador();
        }
        return censador;
    }

  
    public void setCensador(Censador censador) {
        this.censador = censador;
    }

    public LinkedList<Censador> getCensadores() {
       if (censadores.isEmpty()) {
            censadores = this.listAll();
        }
        return censadores;
    }

    public void setCensadores(LinkedList<Censador> censadores) {
        this.censadores = censadores;
    }
    

    public Boolean save() {
        censador.setId(generated_id());
        return save(censador);
    }

    public Boolean update(Integer pos) {
        return update(censador, pos);
    }
    
    
    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer length = listAll().getSize() + 1;
        Integer pos = length.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");

        }
        code.append(length.toString());
        return code.toString();
    }
}
