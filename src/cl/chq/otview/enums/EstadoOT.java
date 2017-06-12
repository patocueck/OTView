/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chq.otview.enums;

/**
 *
 * @author pzunigap
 */
public enum EstadoOT {
    ATESTING(0,"La OT esta lista para ir a TESTING"), 
    OK_ATESTING(1,"La OT esta aprobada por TESTING"), 
    NOK_ATESTING(2,"La OT esta reprobada por TESTING"),
    TESTING_FUNCIONAL(3,"La OT esta lista para ir a TESTING FUNCIONAL"),
    APROBACION_USUARIO(4,"La OT esta lista para ser aprobada por el usuario"),
    //Falta OK usuario y  NOK usuario
    APRODUCCION(5,"La OT esta lista para ir a PRODUCCIÓN"),
    OK_APRODUCCION(6,"La OT esta aprobada por PRODUCCIÓN"),
    NOK_APRODUCCION(7,"La OT esta reprobada por PRODUCCIÓN");

    private final int id;
    private final String descripcion;
    
    EstadoOT(int id, String descripcion) { 
        this.id = id; 
        this.descripcion = descripcion;
    }
    
    public int getValue() { 
        return id; 
    }
    
    public String getDescripcion() { 
        return descripcion;
    }
}
