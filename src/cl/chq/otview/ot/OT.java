/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.chq.otview.ot;

import cl.chq.otview.enums.EstadoOT;
import cl.chq.otview.outlook.Correo;
import cl.chq.otview.outlook.Outlook;
import java.util.ArrayList;

/**
 *
 * @author pzunigap
 */
public class OT {
 
    private String numero;
    private ArrayList correos;
    private EstadoOT estado;

    public OT(String numeroOT) {
        Correo A[]= Outlook.getCorreos();
        for (Correo A1 : A) {
            if (A1.getAsunto().contains(numeroOT)) {
                String t = A1.getAsunto();
                t = t.replace("RE: ", "");
                String e = t.substring(16);
                numero = numeroOT;
                correos.add(A1);
                //Estado se va reemplazando por el último.
                estado.valueOf(e);
                
            }
        }
    }
    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ArrayList getCorreos() {
        return correos;
    }

    public void setCorreos(ArrayList correos) {
        this.correos = correos;
    }

    public EstadoOT getEstado() {
        return estado;
    }

    public void setEstado(EstadoOT estado) {
        this.estado = estado;
    }    
}
