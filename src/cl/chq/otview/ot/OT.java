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
    private String estado;

    public OT(String numeroOT) throws Exception {
        Outlook outlook = new Outlook();
        correos = new ArrayList();
        Correo A[]= outlook.getCorreos();
        int j = 0;
        for (int i=0; i < A.length; i++) {
            if (A[i].getAsunto().contains(numeroOT)) {
                String t = A[i].getAsunto();
                if ( j == 0) this.estado = obtenerEstado(t);
                numero = numeroOT;
                correos.add(A[i]);
                j++;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    

    private String obtenerEstado(String t) {
        String sEstado = "";
        //t: Asunto del correo. "PE CHQ 2017/0157 - A PRODUCCIÓN -  MS Interfaz Cargos"
        t = t.replace("RE: ", "");
        //t = t.replaceAll(" ", "");
        String e = t.substring(17);
        String segmento[] = e.split("-");
        //Si segmento = 3 entonces el estado trae guion.
        if ( segmento.length == 3 ){
            sEstado = segmento[1];
        } else{
            sEstado = segmento[1] + segmento[2];
        }
        
        if (sEstado.startsWith(" ")) sEstado = sEstado.substring(1);
        if (sEstado.endsWith(" ")) sEstado = sEstado.substring(0, sEstado.length()-1);
                
        return sEstado;
    }
}
