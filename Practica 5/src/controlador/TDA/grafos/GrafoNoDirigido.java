/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.TDA.grafos;

import controlador.TDA.grafos.exeption.VerticeOfSizeExecption;

/**
 *
 * @author sebastian
 */
public class GrafoNoDirigido extends GrafoDirigido {
    
    public GrafoNoDirigido(Integer nro_vertices) {
        super(nro_vertices);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        //super.insertar(a, b, peso); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if(a.intValue() <= nro_vertices().intValue() &&  b.intValue() <= nro_vertices().intValue()) {
             if(!existe_arista(a, b)) {
                 setNro_aristas(nro_aristas()+1);
                 
                 Adycencia auxO = new Adycencia();
                 auxO.setPeso(peso);
                 auxO.setD(b);
                 
                 Adycencia auxD = new Adycencia();
                 auxD.setPeso(peso);
                 auxD.setD(a);
                 getListaAdycente()[a].add(auxO);
                 getListaAdycente()[b].add(auxD);
                 //listaAdycente[a].add(aux);
             }
         } else 
             throw new VerticeOfSizeExecption();
    }
    
    
    
}
