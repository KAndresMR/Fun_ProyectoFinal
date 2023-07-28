
package vista;

import controlador.cEjemplar;
import java.io.IOException;
import java.sql.SQLException;
import modelo.dao.EjemplarDaoImpl;
import modelo.logica.Logica;

public class Inicio {

   
    public static void main(String[] args) throws SQLException, IOException 
    {
        VentanaMenu v=new VentanaMenu();
       
        cEjemplar controladorFarm = new cEjemplar();
        EjemplarDaoImpl con = new EjemplarDaoImpl();
        con.conectar();
        Logica miLogica = new Logica(); 
        
        v.pnlLogin.setControlador(controladorFarm);
        v.pnlLogin.setAdmin(v);
        v.admin.setControlador(controladorFarm);
        controladorFarm.setMilogica(miLogica);
        controladorFarm.setVentanaMenu(v);
        v.admin.cargarListas();
        
        v.setVisible(true);
    }
    
}
