/*
 * Main.java
 *
 * Created on 24 de septiembre de 2005, 11:25
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cl.chq.otview;

import cl.chq.otview.outlook.Contacto;
import cl.chq.otview.outlook.Correo;
import cl.chq.otview.outlook.Outlook;

/**
 *
 * @author Javier
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws Exception {
        Outlook VV= new Outlook();
        VV.envriarYRecibir();
        //probarCorreoLectura();
        leerOT("2017/0132");
    }
    
    private static void leerOT(String numeroOT){
        
        Correo A[]= Outlook.getCorreos();
        for (Correo A1 : A) {
            if (A1.getAsunto().contains(numeroOT)) {
                String t = A1.getAsunto();
                String a = A1.getDe();
                t = t.replace("RE: ", "");
                String e = t.substring(16);
                //System.out.println("--------------> EL Asunto es: " + t);
                System.out.println("--------------> El Estado es: " + e + " --> " + a);
            }
        }
    }
    
    private static void probarCorreoLectura() {
        try{
            //Outlook CO = new Outlook();
            Correo A[]= Outlook.getCorreos();
            for (int i =0; i< A.length;i++){
                String t = A[i].getAsunto();
                String M = A[i].getMensaje();
                System.out.println("--------------> EL Asunto es: " + t);
                //System.out.println(M);
            }
        } catch (Exception e){
            System.out.println("ERROR:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static void probarContacto() {
        try{
             Contacto A= new Contacto("Fabiana");
             String t = A.getTelefono_Particular();
             String FN = A.getApeNom();
             System.out.println("--------------> EL Numero de Telefono es" + t);
             System.out.println("EL Nombre es:  "+FN);
         } catch (Exception e){
             System.out.println("ERROR:");
             System.out.println(e.getMessage());
             e.printStackTrace();
         }
    }
    
    
    
    
    
    
}



