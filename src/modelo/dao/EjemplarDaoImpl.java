
package modelo.dao;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EjemplarDaoImpl
{
   
    //Conectar Base de DAtos
    private static String user = "root";
    private static String password = "BDdres9r";
    private static String url = "jdbc:mysql://localhost:3306/Fundamentos";//jdbc:oracle:thin:@localhost:1521:xe
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    Connection con;
    Statement consulta;
    
    public Connection conectar(){
        
        try {
            //Class.forName(driver);
            this.con = DriverManager.getConnection(url, user, password);
            this.consulta = con.createStatement();
            System.out.println("Conectado");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("No Conectado");
            System.exit(0);
        }
        return this.con;     
    }
    public void desconectar() {
        try {
            this.con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public ResultSet getProducto(String codigoProducto){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("SELECT * FROM far_productos WHERE pro_codigo_barra = ?");
            sentencia.setString(1, codigoProducto);
            
            ResultSet producto = sentencia.executeQuery();
            return producto;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet getPersona(String cedula){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("SELECT * FROM far_personas WHERE per_cedula = ?");
            sentencia.setString(1, cedula);
            
            ResultSet persona = sentencia.executeQuery();
            return persona;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet getUsuario(String username, String password){
        conectar();
        try {
            String sqlQuery = "SELECT * FROM far_usuarios WHERE usu_nombre = ? AND usu_contrasena = ?";
            PreparedStatement sentencia = con.prepareStatement(sqlQuery);
            sentencia.setString(1, username);
            sentencia.setString(2, password);
            
            ResultSet persona = sentencia.executeQuery();
            return persona;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet getCategoria(String code){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("SELECT * FROM far_categorias WHERE cat_id = ?");
            sentencia.setString(1, code);
            
            ResultSet producto = sentencia.executeQuery();
            return producto;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet getProveedor(String nombreProv){
    conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("SELECT prov_nombre, prov_ruc, TO_CHAR(TRUNC(SYSDATE), 'YYYY-MM-DD') as fecha "
                    + "FROM far_proveedores WHERE prov_nombre = ?");
            sentencia.setString(1, nombreProv);
            
            ResultSet producto = sentencia.executeQuery();
            return producto;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ResultSet getRucProv(String ruc){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("SELECT prov_ruc FROM far_proveedores WHERE prov_ruc = ?");
            sentencia.setString(1, ruc);
            
            ResultSet producto = sentencia.executeQuery();
            return producto;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    //-----------------------LISTAR---------------------------------------
    public ResultSet listarPersona(){
        conectar();
        try {
            ResultSet listado = consulta.executeQuery("SELECT per_id, per_cedula, per_nombre || ' ' || per_apellido AS nombre ,"
                + "per_direccion, per_telefono, per_correo, per_tipo, per_estado " +
                "FROM far_personas");
            return listado;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public ResultSet listarProductos(){
        conectar();
        try {
            
            ResultSet listado = consulta.executeQuery("SELECT * FROM far_productos");
            return listado;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public ResultSet listarProveedores(){
        conectar();
        try {
            ResultSet listado = consulta.executeQuery("SELECT * FROM far_proveedores");
            return listado;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public ResultSet listarCategorias(){
        conectar();
        try {
            ResultSet listado = consulta.executeQuery("SELECT cat_id, cat_nombre, cat_descripcion from far_categorias");
            return listado;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    public ResultSet listarUsuarios(){
       conectar();
        try {
            ResultSet listado = consulta.executeQuery("SELECT usu_id, usu_nombre AS usu_username, "
            + "usu_contrasena, usu_tipo, per_cedula, per_nombre || ' ' || per_apellido AS nombre "
            + "FROM far_usuarios JOIN far_personas ON "
            + "far_usuarios.far_personas_per_id = far_personas.per_id ORDER BY usu_id ASC");
            return listado;
            
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
        
    }

//------------------------CREACION DE OBJETOS-----------------------------------
    
    public boolean CrearProducto(String nombre,int precio,int stock,int iva,int categoria,int codigoBarras){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO far_productos "
                    + "(pro_nombre, pro_precio, pro_stock,pro_IVA,pro_categoria,pro_codigo_barra) "
                    + "VALUES (seq_pro_id.NEXTVAL, ?, ?)");
            sentencia.setString(1, nombre);
            sentencia.setInt(2, precio);
            sentencia.setInt(3, stock);
            sentencia.setInt(4, iva);
            sentencia.setInt(5, categoria);
            sentencia.setInt(6, codigoBarras);
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    public boolean CrearCategoria(String nombre, String descripcion){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO far_categorias (cat_id, cat_descripcion, cat_nombre) " +
                "VALUES (seq_cat_id.NEXTVAL, ?, ?)");
                sentencia.setString(1, descripcion);
                sentencia.setString(2, nombre);
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }
    public boolean CrearPersona(String cedula, String nombre,String apellido,String direccion,String telefono,String correo,String tipoPersona,String estadoPersona){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO far_personas "
                    + "(per_cedula, per_nombre, per_apellido, per_direccion, per_telefono, "
                    + "per_correo, per_tipo, per_estado) \n" +
                    "VALUES (?,?,?,?,?,?,?,?)");
            sentencia.setString(1, cedula);
            sentencia.setString(2, nombre);
            sentencia.setString(3, apellido);
            sentencia.setString(4, direccion);
            sentencia.setString(5, telefono);
            sentencia.setString(6, correo);
            sentencia.setString(7, tipoPersona);
            sentencia.setString(8, estadoPersona);
            
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }
    public boolean CrearProveedor(String nombre, String Ruc){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO far_proveedores (prov_id, prov_nombre, prov_ruc) "
                    + "VALUES (seq_prov_id.NEXTVAL, ?, ?)");
            sentencia.setString(1, nombre);
            sentencia.setString(2, Ruc);
            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    public boolean CrearUsuario(String nombre, String contra, String tipo, String cedula){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("INSERT INTO far_usuarios (usu_id, usu_nombre, usu_contrasena, usu_tipo, far_personas_per_id)"
                        + " VALUES (seq_usu_id.NEXTVAL, ?, ?, ?, ?)");
            sentencia.setString(1, nombre);
            sentencia.setString(2, contra);
            sentencia.setString(3, tipo);
            sentencia.setString(4, cedula);

            sentencia.executeUpdate();
            sentencia.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }
    
    
//------------------------ACTUALIZAR OBJETOS------------------------------------
    public boolean actualizarProveedor(String codigo,String nombre,int ruc){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("UPDATE far_proveedores " +
            "SET prov_nombre = ?, " +
            "prov_ruc= ? " +
            "WHERE prov_id = ?");
            
            sentencia.setString(1, nombre);
            sentencia.setInt(2, ruc);
            sentencia.setString(3, codigo);
            
            
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean actualizarPersonas(String cedula, String nombre,String apellido,String direccion,String telefono,String correo,String estadoPersona){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("UPDATE far_personas " +
                "SET per_nombre = ?, " +
                "per_apellido = ?, " +
                "per_direccion = ?, " +
                "per_telefono = ?, " +
                "per_correo = ?, " +
                "per_estado = ? " +
                "WHERE per_cedula = ?");
            
            sentencia.setString(1, nombre);
            sentencia.setString(2, apellido);
            sentencia.setString(3, direccion);
            sentencia.setString(4, telefono);
            sentencia.setString(5, correo);
            sentencia.setString(6, estadoPersona);
            sentencia.setString(7, cedula);
            
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean actualizarCate(String codigo,String nombre,String desc){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("UPDATE far_categorias " +
            "SET cat_nombre = ?, " +
            "cat_descripcion = ? " +
            "WHERE cat_id = ?");
            
            sentencia.setString(1, nombre);
            sentencia.setString(2, desc);
            sentencia.setString(3, codigo);
            
            
            sentencia.executeUpdate();
            sentencia.close();

            return true;
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//-----------------------BORRAR OBJETOS-----------------------------------------
public boolean borrarPersona(int cedula){
    conectar();
    try {
        PreparedStatement sentencia = con.prepareStatement("DELETE FROM far_personas WHERE per_cedula = ?");
        sentencia.setInt(1, cedula);
        sentencia.executeUpdate();
        sentencia.close();

        return true;
    } catch (SQLException ex) {        
        if (ex.getErrorCode() == 2292) {
            System.out.println("ERROR AL ELIMINAR");
        } else {
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return false;
}    
public boolean borrarProv(String nombre){
    conectar();
    try {
        PreparedStatement sentencia = con.prepareStatement("DELETE FROM far_proveedores WHERE prov_nombre = ?");
        sentencia.setString(1, nombre);
        sentencia.executeUpdate();
        sentencia.close();

        return true;
    } catch (SQLException ex) {        
        if (ex.getErrorCode() == 2292) {
            System.out.println("ERROR AL ELIMINAR, porque tiene notas de venta que referencian a esta.");
        } else {
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return false;
}    
public boolean borrarCate(String nombre){
    conectar();
    try {
        PreparedStatement sentencia = con.prepareStatement("DELETE FROM far_categorias WHERE cat_nombre = ?");
        sentencia.setString(1, nombre);
        sentencia.executeUpdate();
        sentencia.close();

        return true;
    } catch (SQLException ex) {  
        if (ex.getErrorCode() == 2292) {
            System.out.println("ERROR AL ELIMINAR, porque tiene productos que referencian a esta.");
        } else {
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    return false;
}    
public boolean borrarUsuario(String username){
    conectar();
    try {
        PreparedStatement sentencia = con.prepareStatement("DELETE FROM far_usuarios WHERE usu_nombre = ?");
        sentencia.setString(1, username);
        sentencia.executeUpdate();
        sentencia.close();

        return true;
    } catch (SQLException ex) {  
        if (ex.getErrorCode() == 2292) {
            System.out.println("ERROR AL ELIMINAR usuario");
        } else {
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    return false;
}    
    
    
    
    
    public void commit(){
        conectar();
        try {
            PreparedStatement sentencia = con.prepareStatement("COMMIT");
            sentencia.executeUpdate();
            sentencia.close();
        } catch (SQLException ex) {
            
            Logger.getLogger(EjemplarDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        
}
