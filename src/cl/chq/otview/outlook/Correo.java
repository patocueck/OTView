/*
 * Correo.java
 *
 * Created on 8 de octubre de 2005, 13:48
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cl.chq.otview.outlook;
import com.jacob.com.*;
import com.jacob.activeX.*;

/**
 * Como trabajar un correo
 * @author Javier
 */
public class Correo {
    Outlook cX ;
    private Dispatch EMail;
    private String De;
    private String Para;
    private String Asunto;
    private String Mensaje;
    private String Archivos[];
    private boolean NoLeido;
    /** Creates a new instance of Correo */
    
    public Correo() {
    }
    public Correo(Dispatch I){
        EMail=I;
        CargarDatos();
    }
    private void CargarDatos(){
        Para = EMail.get(EMail, "To").toString();
        Asunto = EMail.get(EMail, "Subject").toString();
        Mensaje = EMail.get(EMail, "Body").toString();
        NoLeido = EMail.get(EMail, "UnRead").toBoolean();
        
    }
    
    public String[] getArchivos() {
        return this.Archivos;
    }
    
    public void setArchivos(final String[] Archivos) {
        this.Archivos = Archivos;
    }
    
    public String getAsunto() {
        return this.Asunto;
    }
    
    public void setAsunto(final String Asunto) {
        this.Asunto = Asunto;
    }
    
    public String getDe() {
        return this.De;
    }
    
    public void setDe(final String De) {
        this.De = De;
    }
    
    public String getMensaje() {
        return this.Mensaje;
    }
    
    public void setMensaje(final String Mensaje) {
        this.Mensaje = Mensaje;
    }
    
    public String getPara() {
        return this.Para;
    }
    
    public void setPara(final String Para) {
        this.Para = Para;
    }

    public boolean isNoLeido() {
        return this.NoLeido;
    }

    public void setNoLeido(final boolean NoLeido) {
        this.NoLeido = NoLeido;
    }
}
