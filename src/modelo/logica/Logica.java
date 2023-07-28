
package modelo.logica;

import java.sql.ResultSet;
import modelo.dao.EjemplarDaoImpl;
import vista.VistaAdministrador;


public class Logica 
{
       EjemplarDaoImpl ejemplarDao = new EjemplarDaoImpl();
       VistaAdministrador panel;
        
    public ResultSet listarPersonas(){
        return ejemplarDao.listarPersona();
    } 
    public ResultSet listarProveedores(){
        return ejemplarDao.listarProveedores();
    }
    public ResultSet listarProductos(){
        return ejemplarDao.listarProductos();
    }
    public ResultSet listarCategorias(){
        return ejemplarDao.listarCategorias();
    }
    public ResultSet listarUsuarios(){
        return ejemplarDao.listarUsuarios();
    }
    //------------------------------------CREAR---------------------------------
    public boolean CrearProducto(String nombre,int precio,int stock,int iva,int categoria,int codigoBarras){
        return ejemplarDao.CrearProducto(nombre, precio, stock, iva, categoria, codigoBarras);
    }
    public boolean CrearCategoria(String nombre, String des){
        return ejemplarDao.CrearCategoria(nombre, des);
    }
    public boolean CrearPersona(String cedula, String nombre,String apellido,String direccion,String telefono, String correo, String tipoPersona,String estadoPersona){
        return ejemplarDao.CrearPersona(cedula, nombre, apellido, direccion, telefono, correo, tipoPersona, estadoPersona);
    }
    public boolean CrearProveedor(String nombre, String Ruc){
        return ejemplarDao.CrearProveedor(nombre, Ruc);
    }
    
    public ResultSet getProducto(String id){
        return ejemplarDao.getProducto(id);
    }
    public ResultSet getPersona(String cedula){
        return ejemplarDao.getPersona(cedula);
    }
    public ResultSet getUsuario(String username, String password){
        return ejemplarDao.getUsuario(username, password);
    }
    public ResultSet getCategoria(String code){
        return ejemplarDao.getCategoria(code);
    }
    public ResultSet getProveedor(String nombreProv){
        return ejemplarDao.getProveedor(nombreProv);
    }
    public ResultSet getRucProv(String ruc){
        return ejemplarDao.getRucProv(ruc);
    }
    //------------------------------------ACTULIZAR---------------------------------
    public boolean actualizarProveedor(String codigo,String nombre,int ruc){
        return ejemplarDao.actualizarProveedor(codigo, nombre, ruc);
    }
    public boolean actualizarPersonas(String cedula, String nombre,String apellido,String direccion,String telefono,String correo,String estadoPersona){
        return ejemplarDao.actualizarPersonas(cedula, nombre, apellido, direccion, telefono, correo, estadoPersona);
    }
    public boolean actualizarCate(String codigo, String nombre, String desc) {
        return ejemplarDao.actualizarCate(codigo, nombre, desc);
    }
    public void commit(){
        ejemplarDao.commit();
    }
    
//-------------------------------BORRAR-----------------------------------------
    public boolean borrarPersona(int cedula){
        return ejemplarDao.borrarPersona(cedula);
    }
    public boolean borrarProv(String nombre){
        return ejemplarDao.borrarProv(nombre);
    }
    public boolean borrarCate(String nombre){
        return ejemplarDao.borrarCate(nombre);
    }
    public boolean borrarUsuario(String username){
        return ejemplarDao.borrarUsuario(username);
    }
    public boolean CrearUsuario(String nombre, String contra, String tipo, String cedula){
        return ejemplarDao.CrearUsuario(nombre, contra, tipo, cedula);
    }
}
