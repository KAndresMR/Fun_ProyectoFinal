
package controlador;

import java.sql.ResultSet;
import modelo.dao.EjemplarDaoImpl;
import modelo.logica.Logica;
import vista.VentanaMenu;


public class cEjemplar 
{
    private Logica miLogica;
    private VentanaMenu ventanaMenu;
    private EjemplarDaoImpl control;
    

    public Logica getMilogica() {
        return miLogica;
    }

    public void setMilogica(Logica miLogica) {
        this.miLogica = miLogica;
    }

    public VentanaMenu getVentanaMenu() {
        return ventanaMenu;
    }

    public void setVentanaMenu(VentanaMenu ventanaMenu) {
        this.ventanaMenu = ventanaMenu;
    }
    public ResultSet listarPersona(){
        return miLogica.listarPersonas();
    }
    public ResultSet listarProveedores(){
        return miLogica.listarProveedores();
    }
    public ResultSet listarProductos(){
        return miLogica.listarProductos();
    }
    public ResultSet listarCategorias(){
        return miLogica.listarCategorias();
    }
    public ResultSet listarUsuarios(){
        return miLogica.listarUsuarios();
    }
    
    //------------------------------------CREAR---------------------------------
    
    public boolean CrearProducto(String nombre,int precio,int stock,int iva,int categoria,int codigoBarras){
        return miLogica.CrearProducto(nombre, precio, stock, iva, categoria, codigoBarras);
    }
    public boolean CrearCategoria(String nombre, String des){
        return miLogica.CrearCategoria(nombre, des);
    }
    public boolean CrearPersona(String cedula, String nombre,String apellido,String direccion,String telefono,String correo,String tipoPersona,String estadoPersona){
        return miLogica.CrearPersona(cedula, nombre, apellido, direccion, telefono, correo, tipoPersona, estadoPersona);
    }
    public boolean CrearProveedor(String nombre, String Ruc){
        return miLogica.CrearProveedor(nombre, Ruc);
    }
    
    public ResultSet getProducto(String id){
        return miLogica.getProducto(id);
    }
    public ResultSet getPersona(String cedula){
        return miLogica.getPersona(cedula);
    }
    public ResultSet getUsuario(String username, String password){
        return miLogica.getUsuario(username, password);
    }
    public ResultSet getCategoria(String code){
        return miLogica.getCategoria(code);
    }
    public ResultSet getProveedor(String nombreProv){
        return miLogica.getProveedor(nombreProv);
    }
    public ResultSet getRucProv(String ruc){
        return miLogica.getRucProv(ruc);
    }
    
    //------------------------------------ACTUALIZAR----------------------------
    public boolean actualizarProveedor(String codigo,String nombre,int ruc){
        return miLogica.actualizarProveedor(codigo, nombre, ruc);
    }
    public boolean actualizarCate(String codigo, String nombre, String desc) {
        return miLogica.actualizarCate(codigo, nombre, desc);
    }
    public boolean actualizarPersonas(String cedula, String nombre,String apellido,String direccion,String telefono,String correo,String estadoPersona){
        return miLogica.actualizarPersonas(cedula, nombre, apellido, direccion, telefono, correo, estadoPersona);
    }
    public void commit(){
        miLogica.commit();
    } 
    
//------------------------------------BORRAR------------------------------------
    public boolean borrarPersona(int cedula){
        return miLogica.borrarPersona(cedula);
    }
    public boolean borrarProv(String nombre){
        return miLogica.borrarProv(nombre);
    }
    public boolean borrarCate(String nombre){
        return miLogica.borrarCate(nombre);
    }
    public boolean borrarUsuario(String username){
        return miLogica.borrarUsuario(username);
    }      
    public boolean CrearUsuario(String nombre, String contra, String tipo, String cedula){
        return miLogica.CrearUsuario(nombre, contra, tipo, cedula);
    }
}
