/*
 * C_outlook.java
 *
 * Created on 6 de octubre de 2005, 21:37
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cl.chq.otview.outlook;
import cl.chq.otview.outlook.enums.Carpeta;
import com.jacob.com.*;
import com.jacob.activeX.*;

/**
 * Conector desde Java a OUTlook
 * @author Javier
 */
public class Outlook {
    private static Dispatch D;
    /**
     * Variable donde almacena el outlook
     */
    private static ActiveXComponent axOutlook;
    
    private static String Version;
    
    /**
     * Son las seciones a trabajar
     */
    protected static Dispatch Sesiones;
    
    private static Dispatch Carpetas;
    /** Creates a new instance of Outlook */
    public Outlook() throws Exception {
        try {
            
            axOutlook = new ActiveXComponent("Outlook.Application");
            Version= (axOutlook.getProperty("Version")).toString();
            Sesiones= axOutlook.getProperty("Session").toDispatch();
            //Buscamos como pasarlo a mapi
            Carpetas = Dispatch.call(axOutlook ,"GetNamespace","MAPI").toDispatch();
            
            // System.out.println("---------------------->Outlook Listo");
        } catch (Exception e){
            throw new Exception("Revise la Instalción de Outlook");
        }
    }
    
    /**
     * Responde el objt de outlook
     * @return Objeto deonde esta el outlook
     */
    public static ActiveXComponent getAxOutlook() {
        return axOutlook;
    }
    
    
    /**
     * Responde que version es
     * @return Version en texto
     */
    public static String getVersion() {
        return Version;
    }
    
    /**
     * DEvuelve el tipo de Sesiones que tiene
     * @return las sesiones
     */
    public static Dispatch getSesiones() {
        return Sesiones;
    }
    /**
     *   Los valores de las carpetas por defecto
     *    Nombre del Folder      Valor 	Nombre del Folder      Valor
     *  -----------------------------------	-----------------------------------
     *  Deleted Items               3	Calendar                9
     *  Outbox                      4   Contacts                10
     *  Sent Items                  5	Journal               	11
     *  Inbox                       6   Notes              	12
     * @param codigoCarpeta Valor numerico que dice que carpeta trabajar
     * @return Retorna la carpeta en dispatch
     */
    public static Dispatch buscarCarpeta(Carpeta codigoCarpeta){
        return Dispatch.call(Carpetas, "GetDefaultfolder", codigoCarpeta.getValue()).toDispatch();
    }
    /**
     * Busca un contacto en particular
     * @param Nombre Nombre del contacto a buscar
     * @return Retorna en dispach
     * @see Contacto
     */
    static Dispatch  buscarContacto(String Nombre) throws Exception {
        // La capteta 10 es de contactos
        Dispatch carpeta = buscarCarpeta(Carpeta.CONTACTS);
        return Dispatch.call(carpeta ,"Items" , new Variant(Nombre)).toDispatch();
        
    }
    
    static Correo correoNuevo(){
        Correo ret=null;
        Dispatch Inbox = buscarCarpeta(Carpeta.INBOX);
        Dispatch I = D.get(Inbox,"Items").toDispatch();
        int max = I.get(I,"count").toInt();
        int i=1;
        while (i<=max){
               ret = new Correo(I.call(Inbox,"Items",i).toDispatch());
               //System.out.println("Valor de I: "+i +" leido? "+ret.isNoLeido());
                if (ret.isNoLeido()) break;
                i++;
            
        }
        return (i>max)?null:ret;
    }
    
    static public Correo[] getCorreos(){
        Correo ret[];
        Dispatch Inbox = buscarCarpeta(Carpeta.INBOX);
        Dispatch I = D.get(Inbox,"Items").toDispatch();
        int max = I.get(I,"count").toInt();
        ret = new Correo[max];
        for (int i=1; i<=max;i++){
            ret[i-1]= new Correo(I.call(Inbox, "Items" ,i  ).toDispatch());
        }
        return ret;
    }
    
    static public Variant call(String Comando, Object a1){
        return Dispatch.call( axOutlook,Comando,a1);
    }

    public Variant envriarYRecibir() {
  //      return Dispatch.call( axOutlook,"SendAndReceive");
        return null;
        //TODO A REalizar
    }
    
}
