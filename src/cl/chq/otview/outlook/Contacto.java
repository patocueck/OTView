/*
 * Contacto.java
 *
 * Created on 7 de octubre de 2005, 13:28
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package cl.chq.otview.outlook;
// includes basicos de jacob
import com.jacob.com.*;
import com.jacob.activeX.*;
// .FullName  
// .FirstName
// .LastName
// .HomeAddress
// .HomeTelephoneNumber
// .email1address
//TODO .HomeAddressStreet
//TODO .HomeAddressCity
//TODO .HomeAddressState
//TODO .HomeAddressCountry
//TODO .BusinessAddress
//TODO .PrimayTelephoneNumber
//TODO .BusinessTelephoneNumber

/**
 * Es un contacto posible
 * @author Javier
 */
public class Contacto {
    private Outlook cX;

    /**
     *  Es la representacion de FullName
     */
    private String ApeNom = null;
    /**
     *  Es la representacion de LastName
     */
    private String Apellido = null;
    /**
     *  es la representacion de FirstName
     */
    private String Nombre =null;
    /**
     * Email del contacto
     */
    private String EMail = null;
    /**
     *  Es la representacion de PrimayTelephoneNumber o HomeTelephoneNumber
     */
    private String Telefono_Particular = null;
    /**
     *  es la representacion de HomeAddress
     */
    private String Direccion_Particular =null;
    /**
     * Variable Dispatch donde esta el contacto
     */
    private Dispatch Cont;
    /**
     * Creates a new instance of Contacto
     * @param Nombre Nombre del contacto
     */
    public Contacto(String Nombre) throws Exception {
        cX = new Outlook();
        try {
            
        Cont =(Outlook.buscarContacto(Nombre));
        }catch (com.jacob.com.ComFailException e){
            System.out.println("ERROR: No ingreso el nombre Bien");
            throw new Exception("No ingreso bien el nombre a Buscar");
        }
        catch (Exception e){
            System.out.println("ERROR:");
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        CargarDatos();
    }
    
    private void  CargarDatos(){
        ApeNom      = Dispatch.get(Cont,"FullName").toString();
        Apellido    = Dispatch.get(Cont,"LastName").toString();
        Nombre      = Dispatch.get(Cont,"FirstName").toString();
        setEMail(Dispatch.get(Cont,"email1address").toString());
        Telefono_Particular =Dispatch.get(Cont,"HomeTelephoneNumber").toString();
        Direccion_Particular =Dispatch.get(Cont,"HomeAddress").toString();
        //TODO realizar el tema de recopilacion de datos
    }
        
    /**
     * Creates a new instance of Contacto
     * @param Contacto Variable Dispatch que genera el contacto
     * @see com.jacob.com
     */
    public Contacto(Dispatch Contacto) {
        Cont = Contacto;
        CargarDatos();
    }
    /** 
     *  Crea un contacto vacio para poder agregar
     */
    public Contacto(){
        
    }
    /**
     * Retorna el telefono
     * @return Retorna el primer telefono
     */
    public String getTelefono_Particular() {
        return Telefono_Particular;
    }

    /**
     * Asigna el telefono
     * @param Telefono_Particular Asigna el telefono
     */
    public void setTelefono_Particular(String Telefono_Particular) {
        this.Telefono_Particular = Telefono_Particular;
    }

    /**
     * Retorna la direccion particular
     * @return Retorna la direccion particular
     */
    public String getDireccion_particular() {
        return Direccion_Particular;
    }

    /**
     * Asigna la direccion
     * @param Direccion_particular Asigna la direccion
     */
    public void setDireccion_particular(String Direccion_particular) {
        this.Direccion_Particular = Direccion_particular;
    }

    /**
     * Retorna el dispatch
     * @return Retorna el dispatch
     */
    public Dispatch getCont() {
        return Cont;
    }
    

    /**
     * Retorna le full name o el nombre completo
     * @return Retorna el nombre completo 
     */
    public String getApeNom() {
        return this.ApeNom;
    }

    /**
     * Asigna el nombre completo que aparecera
     * @param ApeNom Asigna el nombre completo que aparecera
     */
    public void setApeNom(final String ApeNom) {
        this.ApeNom = ApeNom;
    }

    /**
     * Retorna el apellido solo
     * @return Retorna el apellido solo
     */
    public String getApellido() {
        return this.Apellido;
    }

    /**
     * Asiga el apellido
     * @param Apellido Asigna el apellido
     */
    public void setApellido(final String Apellido) {
        this.Apellido = Apellido;
    }

    /**
     * Retorna el nombre
     * @return Retorna el nombre
     */
    public String getNombre() {
        return this.Nombre;
    }

    /**
     * Asigna el nombre
     * @param Nombre Asigna el nombre
     */
    public void setNombre(final String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * Retorna el Email
     * @return Retorna el Email
     */
    public String getEMail() {
        return this.EMail;
    }
   /**
     * Asigna el Email
     * @param Nombre Asigna el Email
     */
    public void setEMail(final String EMail) {
        this.EMail = EMail;
    }
    /**
     * Guarda el contacto en outlook
     */
    public void Guardar(){
        if (Cont==null){
            //Set objContact = objOutlook.CreateItem(olContactItem)
            Cont = cX.call("CreateItem", "olContacItem").toDispatch();
        }
        GuardarDatos();
        Dispatch.call(Cont,"Save");
    }
    /**
     * Elimina el contacto 
     * 
     */
    public void Eliminar(){
        //TODO HAcerlo
    }
    /**
     * Guarda los Datos en sus respetivas variables
     */
    private void GuardarDatos() {
        Dispatch.put(Cont,"FullName", ApeNom);
        Dispatch.put(Cont,"LastName", Apellido);
        Dispatch.put(Cont,"FirstName",Nombre);
        Dispatch.put(Cont,"email1address",EMail);
        Dispatch.put(Cont,"HomeTelephoneNumber",Telefono_Particular);
        Dispatch.put(Cont,"HomeAddress",Direccion_Particular);
        //TODO realizar el tema de recopilacion de datos
 
    }
}
