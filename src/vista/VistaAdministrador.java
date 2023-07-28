
package vista;

import controlador.cEjemplar;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


public class VistaAdministrador extends javax.swing.JPanel 
{
    // 
    private cEjemplar controladorFarmacia;
    
    
    public void setControlador(cEjemplar controladorFarm) throws SQLException{
    this.controladorFarmacia = controladorFarm; 
    
    }

    public VistaAdministrador() throws SQLException 
    {
        initComponents();
        this.cmbEstadoCliAct.addItem("Activo");
        this.cmbEstadoCliAct.addItem("Deshabilitado");
        this.cmbTipoEmpleado.addItem("Administrador");
        this.cmbTipoEmpleado.addItem("Empleado");
        
        
    }
    public void operaciones(ActionEvent ev) throws SQLException{
    
        //---------------------------LISTAR----------------------------------
        
        if(ev.getSource().equals(btnListarProductos)){
            DefaultTableModel modeloTabla = (DefaultTableModel)tblProductos.getModel();
            modeloTabla.setRowCount(0);    
            ResultSet Consulta = controladorFarmacia.listarProductos();                
            while(Consulta.next()){
                if(Consulta.getString("pro_id") != null){
                    modeloTabla.addRow(new Object[]{Consulta.getString("pro_id"), Consulta.getString("pro_nombre"),Consulta.getString("pro_precio")
                    ,Consulta.getString("pro_stock"), Consulta.getString("pro_iva"), Consulta.getString("far_categorias_cat_id"), Consulta.getString("pro_codigo_barra")});   
                } 
            }
            
        }
        if(ev.getSource().equals(btnListarPersonas)){
            
            DefaultTableModel modeloTabla = (DefaultTableModel)tblListarPersonas.getModel();
            modeloTabla.setRowCount(0);    
            ResultSet Consulta = controladorFarmacia.listarPersona();                
            while(Consulta.next()){
                if(Consulta.getString("per_id") != null){
                    modeloTabla.addRow(new Object[]{Consulta.getString("per_id"), Consulta.getString("per_cedula"),Consulta.getString("nombre")
                    ,Consulta.getString("per_direccion"), Consulta.getString("per_telefono"), Consulta.getString("per_correo"), Consulta.getString("per_tipo")
                    , Consulta.getString("per_estado")});   
                } 
            }
            
        }
        if(ev.getSource().equals(btnListarCategorias)){
            DefaultTableModel modeloTabla = (DefaultTableModel)tblCategorias.getModel();
            modeloTabla.setRowCount(0);    
            ResultSet Consulta = controladorFarmacia.listarCategorias();                
            while(Consulta.next()){
                if(Consulta.getString("cat_id") != null){
                    modeloTabla.addRow(new Object[]{Consulta.getString("cat_id"),
                        Consulta.getString("cat_nombre"),
                        Consulta.getString("cat_descripcion")
                    });   
                } 
            } 
        }
        if(ev.getSource().equals(btnListarProveedores)){
            DefaultTableModel modeloTabla = (DefaultTableModel)tblProveedores.getModel();
            modeloTabla.setRowCount(0);    
            ResultSet Consulta = controladorFarmacia.listarProveedores();                
            while(Consulta.next()){
                if(Consulta.getString("prov_id") != null){
                    modeloTabla.addRow(new Object[]{Consulta.getString("prov_id"), 
                        Consulta.getString("prov_nombre"),Consulta.getString("prov_ruc")
                    });   
                } 
            }
            
        }
        if(ev.getSource().equals(btnListarUsuarios)){
            DefaultTableModel modeloTabla = (DefaultTableModel)tblUsuarios.getModel();
            modeloTabla.setRowCount(0);    
            ResultSet Consulta = controladorFarmacia.listarUsuarios();                
            while(Consulta.next()){
                if(Consulta.getString("usu_id") != null){
                    modeloTabla.addRow(new Object[]{Consulta.getString("usu_id"),
                        Consulta.getString("usu_username"),
                        Consulta.getString("usu_contrasena"),
                        Consulta.getString("usu_tipo"),
                        Consulta.getString("per_cedula"),
                        Consulta.getString("nombre")
                    });   
                } 
            } 
        }
        //---------------------------CREACION DE OBJETOS------------------------
        if(ev.getSource().equals(btnAnadirProducto)){
            if(txtNombreProd.getText().isBlank() || txtPrecioProd.getText().isBlank() 
                    || txtStockProd.getText().isBlank() || txtIvaProd.getText().isBlank() ||
                    txtCodigoBarras.getText().isBlank() || cmbCategoriaProdu.getSelectedItem().toString().equals("Elige")){
                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear el Producto");
            }else{
                String nombre = txtNombreProd.getText();
                int precio = Integer.parseInt(txtPrecioProd.getText());
                int stock = Integer.parseInt(txtStockProd.getText());
                int iva = Integer.parseInt(txtIvaProd.getText());
                int categoria = Integer.parseInt(cmbCategoriaProdu.getSelectedItem().toString());
                int codigoBarras = Integer.parseInt(txtCodigoBarras.getText());
                boolean crearPro = controladorFarmacia.CrearProducto(nombre, precio, stock, iva, categoria, codigoBarras);
                if(crearPro == true){
                    System.out.println("Creado exitosamente");
                    cargarListas();
                }else{
                    System.out.println("Error al agregar");
                }
            }
        }
        if(ev.getSource().equals(btnCrearCategoria)){
            if(txtNombreCate.getText().isBlank() || txtDescripcion.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear la categoria");
            }else{
                String nombre = txtNombreCate.getText();
                String descripcion = txtDescripcion.getText();
                boolean crearCate = controladorFarmacia.CrearCategoria(nombre, descripcion);
                if(crearCate == true){
                    System.out.println("Creado exitosamente");
                    cargarListas();
                }else{
                    System.out.println("Error al agregar");
                }
            }
        }
        if(ev.getSource().equals(btnAgregarPersona)){
            if(txtCedulaPersona.getText().isBlank() || txtNombrePersona.getText().isBlank()
            || txtApellidoPersona.getText().isBlank() || txtDireccionPersona.getText().isBlank()
            || txtTelefonoPersona.getText().isBlank()
            || txtCorreoPersona.getText().isBlank()){

                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear la categoria");
            }else{
                String cedula = txtCedulaPersona.getText();
                String nombre = txtNombrePersona.getText();
                String apellido = txtApellidoPersona.getText();
                String direccion = txtDireccionPersona.getText();
                String telefono = txtTelefonoPersona.getText();
                String correo = txtCorreoPersona.getText();
                String tipoPersona, estadoPersona;
                tipoPersona = "C";
                estadoPersona = "1";
               
                boolean crearPersona = controladorFarmacia.CrearPersona(cedula, nombre, apellido, direccion, telefono, correo, tipoPersona, estadoPersona);
                if(crearPersona == true){
                    System.out.println("Creado Persona exitosamente");
                    cargarListas();
                }else{
                    System.out.println("Error al agregar Persona");
                }
            }
        }
        if(ev.getSource().equals(btnAnadirProveedor)){
            if(txtNombreProveedor.getText().isBlank() || txtRucProveedor.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear el Proveedor");
            }else{
                String nombreProv = txtNombreProveedor.getText();
                String rucProv = txtRucProveedor.getText();
                ResultSet existe = controladorFarmacia.getRucProv(rucProv);
                if(existe.next()){
                    JOptionPane.showMessageDialog(null, "Ya existe un proveedor con ese numero de RUC");
            
                }else{
                    boolean estado = controladorFarmacia.CrearProveedor(nombreProv, rucProv);
                    if(estado = true){
                        System.out.println("Proveedor anadido");
                    }else{
                        System.out.println("No anadido");
                    }
                }
                
                
            }
            
        }
        if(ev.getSource().equals(btnCrearUsuario)){
                if(txtContrasena.getText().isBlank() || txtUsername.getText().isBlank() || cmbCedulaUsu.getSelectedItem().toString().equals("Elige")){
                    JOptionPane.showMessageDialog(null, "Llene todos los campos para crear el Usuario");
                }else{
                    String username = txtUsername.getText();
                    String contrasena = txtContrasena.getText();
                    String tipo = cmbTipoEmpleado.getSelectedItem().toString();
                    String tipoUsu;
                    if(tipo.equals("Administrador")){
                        tipoUsu= "A";
                    }else{
                        tipoUsu= "E";
                    }
                    String cedula = cmbCedulaUsu.getSelectedItem().toString();
                    ResultSet person = controladorFarmacia.getPersona(cedula);
                    person.next();
                    String id = person.getString("per_id");
                    boolean estado = controladorFarmacia.CrearUsuario(username,contrasena , tipoUsu, id);
                    if(estado = true){
                        controladorFarmacia.commit();
                        cargarListas();
                        System.out.println("Creado correctamente");
                    }else{
                        System.out.println("ERROR");
                    }
                
                }
            }
        
        
        //----------------------LIMPIAR------------------------------
        if(ev.getSource().equals(btnLimpiarPersona)){
            txtCedulaPersona.setText("");
                txtNombrePersona.setText("");
                txtApellidoPersona.setText("");
                txtDireccionPersona.setText("");
                txtTelefonoPersona.setText("");
                txtCorreoPersona.setText("");
        }
        if(ev.getSource().equals(btnLimpiarAct)){
            cmbCedulaPerAct.setSelectedIndex(0);
            txtActualizarNombre.setText("");
            txtActualizarApellido.setText("");
            txtIActualizarDireccion.setText("");
            txtActualizarCorreo.setText("");
            txtActualizartelefono.setText("");
        }
        if(ev.getSource().equals(btnLimpiarFactura)){
            lblFechaFac.setText("");
            lblFechaFac.setText("Fecha:");
            lblCodeFac.setText("Codigo de factura:");
            lblFacCedula.setText("Cedula Cliente: ");
            lblFacNombre.setText("Nombre Cliente:");
            lblFacApellido.setText("Apellido Cliente: Som");
            
        }
        if(ev.getSource().equals(btnLimpiarCategoria)){
            txtDescripcionAct.setText("");
            txtNombreCateAct.setText("");
            txtDescripcion.setText("");
            txtNombreCate.setText("");
            cmbCodigoCate.setSelectedIndex(0);
        }
        if (ev.getSource().equals(btnLimpiarProductos)) {
            txtStockProd.setText("");
            txtNombreProd.setText("");
            txtStockProd.setText("");
            txtIvaProd.setText("");
            txtCodigoBarras.setText("");
            txtPrecioProd.setText("");
        }
        if (ev.getSource().equals(btnLimpiarUsuario)){
            txtContrasena.setText("");
            txtUsername.setText("");         
        }
        if(ev.getSource().equals(btnLimpiarProv)){
            txtNombreProveedor.setText("");
            txtRucProveedor.setText("");
        }
    }
   public void cargarListas() throws SQLException{
        cbmCedulaPersonaFac.removeAllItems();
        cmbCedulaUsu.removeAllItems();
        cmbCategoriaProdu.removeAllItems();
        cmbCodigoCate.removeAllItems();
        cmbProveedores.removeAllItems();
        cmbIdProveedor.removeAllItems();
        cmbCodigoProducto.removeAllItems();
        cmbCedulaPerAct.removeAllItems();
        cmbBorrarCate.removeAllItems();
        cmbBorrarNota.removeAllItems();
        cmbBorrarPersona.removeAllItems();
        cmbBorrarProducto.removeAllItems();
        cmbBorrarUsuario.removeAllItems();
        cmbBorrarProv.removeAllItems();
        
        cbmCedulaPersonaFac.addItem("Elige");
        cmbCedulaUsu.addItem("Elige");
        cmbCategoriaProdu.addItem("Elige");
        cmbCodigoCate.addItem("Elige");
        cmbProveedores.addItem("Elige");
        cmbIdProveedor.addItem("Elige");
        cmbCodigoProducto.addItem("Elige");
        cmbCedulaPerAct.addItem("Elige");
        cmbBorrarCate.addItem("Elige");
        cmbBorrarNota.addItem("Elige");
        cmbBorrarPersona.addItem("Elige");
        cmbBorrarProducto.addItem("Elige");
        cmbBorrarUsuario.addItem("Elige");
        cmbBorrarProv.addItem("Elige");
        ResultSet cedulaPersonaFac = controladorFarmacia.listarPersona();   
        ResultSet cedulaPersona = controladorFarmacia.listarPersona(); 
        ResultSet categoria = controladorFarmacia.listarCategorias(); 
        ResultSet proveedor = controladorFarmacia.listarProveedores();
        ResultSet producto = controladorFarmacia.listarProductos();
        ResultSet listPerson = controladorFarmacia.listarPersona();  
        ResultSet usuario = controladorFarmacia.listarUsuarios();
        while(cedulaPersonaFac.next()){
            this.cbmCedulaPersonaFac.addItem(cedulaPersonaFac.getString("per_cedula"));
        }
        while(cedulaPersona.next()){
            this.cmbCedulaUsu.addItem(cedulaPersona.getString("per_cedula"));
        }
        while(categoria.next()){
            this.cmbCategoriaProdu.addItem(categoria.getString("cat_nombre"));
            this.cmbCodigoCate.addItem(categoria.getString("cat_id"));
            this.cmbBorrarCate.addItem(categoria.getString("cat_nombre"));
        }    
        while(proveedor.next()){
                this.cmbProveedores.addItem(proveedor.getString("prov_nombre"));
                this.cmbIdProveedor.addItem(proveedor.getString("prov_nombre"));
                this.cmbBorrarProv.addItem(proveedor.getString("prov_nombre"));
        } 
        while(producto.next()){
                this.cmbCodigoProducto.addItem(producto.getString("pro_codigo_barra"));
                this.cmbBorrarProducto.addItem(producto.getString("pro_codigo_barra"));
        } 
        while(listPerson.next()){
                this.cmbCedulaPerAct.addItem(listPerson.getString("per_cedula"));
                this.cmbBorrarPersona.addItem(listPerson.getString("per_cedula"));
        }   
        while(usuario.next()){
            this.cmbBorrarUsuario.addItem(usuario.getString("usu_username"));
        }
   }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tbpAdministardor = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCedulaPersona = new javax.swing.JTextField();
        txtNombrePersona = new javax.swing.JTextField();
        btnAgregarPersona = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtDireccionPersona = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtApellidoPersona = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTelefonoPersona = new javax.swing.JTextField();
        txtCorreoPersona = new javax.swing.JTextField();
        btnLimpiarPersona = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListarPersonas = new javax.swing.JTable();
        btnLimpiarAct = new javax.swing.JButton();
        btnBuscarPerAct = new javax.swing.JButton();
        lblActualizarPersona = new javax.swing.JLabel();
        lblCorreoCliente = new javax.swing.JLabel();
        txtActualizartelefono = new javax.swing.JTextField();
        txtActualizarNombre = new javax.swing.JTextField();
        txtActualizarCorreo = new javax.swing.JTextField();
        lblDireccionCliente = new javax.swing.JLabel();
        txtIActualizarDireccion = new javax.swing.JTextField();
        lblTelefonoCliente = new javax.swing.JLabel();
        lblApellidoCliente = new javax.swing.JLabel();
        txtActualizarApellido = new javax.swing.JTextField();
        lblNombreCliente = new javax.swing.JLabel();
        lblEstadoCliente = new javax.swing.JLabel();
        cmbEstadoCliAct = new javax.swing.JComboBox<>();
        btnListarPersonas = new javax.swing.JButton();
        btnActualizarPersona = new javax.swing.JButton();
        cmbCedulaPerAct = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblCedulaCLienteFac = new javax.swing.JLabel();
        btnBuscarPersonaCedula = new javax.swing.JButton();
        lblCodeFac = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblFechaFac = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnImprimirFac = new javax.swing.JButton();
        btnLimpiarFactura = new javax.swing.JButton();
        lblFacCedula = new javax.swing.JLabel();
        lblFacNombre = new javax.swing.JLabel();
        lblFacApellido = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblProductosFac = new javax.swing.JTable();
        cbmCedulaPersonaFac = new javax.swing.JComboBox<>();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblProductosFac1 = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblProductosFac2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCategorias = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        btnListarCategorias = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtNombreCate = new javax.swing.JTextField();
        lblDescripcion = new javax.swing.JLabel();
        btnActualizarCategoria = new javax.swing.JButton();
        btnCrearCategoria = new javax.swing.JButton();
        btnLimpiarCategoria = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtNombreCateAct = new javax.swing.JTextField();
        lblDescripcion1 = new javax.swing.JLabel();
        cmbCodigoCate = new javax.swing.JComboBox<>();
        btnCateEnter = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtDescripcionAct = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        lblTipoUsuario = new javax.swing.JLabel();
        btnLimpiarUsuario = new javax.swing.JButton();
        lblApellidoProv1 = new javax.swing.JLabel();
        btnActualizarUsuario = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        cmbTipoEmpleado = new javax.swing.JComboBox<>();
        btnListarUsuarios = new javax.swing.JButton();
        lblIdUsuAct = new javax.swing.JLabel();
        cmbCedulaUsu = new javax.swing.JComboBox<>();
        btnCrearUsuario = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        btnBuscarProducto = new javax.swing.JButton();
        btnListarProductos = new javax.swing.JButton();
        txtNombreProd = new javax.swing.JTextField();
        lblApellidoProv2 = new javax.swing.JLabel();
        btnActualizarProducto = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnAnadirProducto = new javax.swing.JButton();
        lblTipoUsuario1 = new javax.swing.JLabel();
        txtPrecioProd = new javax.swing.JTextField();
        btnLimpiarProductos = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtStockProd = new javax.swing.JTextField();
        lblTipoUsuario2 = new javax.swing.JLabel();
        txtIvaProd = new javax.swing.JTextField();
        lblTipoUsuario3 = new javax.swing.JLabel();
        cmbCategoriaProdu = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        txtCodigoBarras = new javax.swing.JTextField();
        cmbProductoAct = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnActualizarProveedor = new javax.swing.JButton();
        btnAnadirProveedor = new javax.swing.JButton();
        btnListarProveedores = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        txtRucProveedor = new javax.swing.JTextField();
        lblRucProveedor = new javax.swing.JLabel();
        btnLimpiarProv = new javax.swing.JButton();
        cmbProveedores = new javax.swing.JComboBox<>();
        btnBuscarProveedor = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        lblRucProv = new javax.swing.JLabel();
        btnLimpiarNota = new javax.swing.JButton();
        lblNombreProveedor = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblCategorias4 = new javax.swing.JTable();
        btnAnadirNota = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        btnListarNotas = new javax.swing.JButton();
        cmbIdProveedor = new javax.swing.JComboBox<>();
        lblFechaNota = new javax.swing.JLabel();
        lblValorTotalNota = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cmbCodigoProducto = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        btnGenerarNota = new javax.swing.JButton();
        lblNombreProductoNota = new javax.swing.JLabel();
        btnBuscarProd = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnBorrarNota = new javax.swing.JButton();
        btnBorrarProveedor = new javax.swing.JButton();
        btnBorrarUsuario = new javax.swing.JButton();
        btnBorrarCategoria = new javax.swing.JButton();
        btnBorrarPer = new javax.swing.JButton();
        cmbBorrarPersona = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbBorrarNota = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbBorrarCate = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        cmbBorrarUsuario = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cmbBorrarProv = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        lblPersonaDelete = new javax.swing.JLabel();
        lblNotaDelete = new javax.swing.JLabel();
        lblProdDelete = new javax.swing.JLabel();
        cmbBorrarProducto = new javax.swing.JComboBox<>();
        btnBorrarProd = new javax.swing.JButton();
        btnBorrarProd1 = new javax.swing.JButton();
        btnLogoutAdmin = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Administrador");

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombres:");

        btnAgregarPersona.setText("Agregar Persona");
        btnAgregarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPersonaActionPerformed(evt);
            }
        });

        jLabel3.setText("Direccion:");

        jLabel7.setText("Apellidos:");

        jLabel8.setText("Telefono:");

        jLabel9.setText("Correo");

        btnLimpiarPersona.setText("Limpiar");
        btnLimpiarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarPersonaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedulaPersona, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addComponent(txtNombrePersona)
                            .addComponent(txtApellidoPersona, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(82, 82, 82)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarPersona)
                            .addComponent(btnLimpiarPersona))
                        .addGap(68, 68, 68))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDireccionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtCedulaPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPersona))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombrePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiarPersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellidoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccionPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefonoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCorreoPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tbpAdministardor.addTab("Registrar Persona", jPanel1);

        tblListarPersonas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Cedula", "Nombre", "Direccion", "Telefono", "Correo", "Tipo", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tblListarPersonas);

        btnLimpiarAct.setText("Limpiar");
        btnLimpiarAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActActionPerformed(evt);
            }
        });

        btnBuscarPerAct.setText("Buscar");
        btnBuscarPerAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPerActActionPerformed(evt);
            }
        });

        lblActualizarPersona.setText("Buscar persona por Cedula:");

        lblCorreoCliente.setText("Correo");

        lblDireccionCliente.setText("Direccion:");

        lblTelefonoCliente.setText("Telefono:");

        lblApellidoCliente.setText("Apellidos:");

        lblNombreCliente.setText("Nombres:");

        lblEstadoCliente.setText("Estado:");

        cmbEstadoCliAct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoCliActActionPerformed(evt);
            }
        });

        btnListarPersonas.setText("Listar Personas");
        btnListarPersonas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarPersonasActionPerformed(evt);
            }
        });

        btnActualizarPersona.setText("Actualizar");
        btnActualizarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarPersonaActionPerformed(evt);
            }
        });

        cmbCedulaPerAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnListarPersonas)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblNombreCliente)
                                .addComponent(lblDireccionCliente)
                                .addComponent(lblApellidoCliente)
                                .addComponent(lblTelefonoCliente)
                                .addComponent(lblCorreoCliente))
                            .addGap(46, 46, 46)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtIActualizarDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtActualizartelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtActualizarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(89, 89, 89)
                                    .addComponent(lblEstadoCliente)
                                    .addGap(30, 30, 30)
                                    .addComponent(cmbEstadoCliAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtActualizarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtActualizarApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblActualizarPersona)
                            .addGap(18, 18, 18)
                            .addComponent(cmbCedulaPerAct, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnBuscarPerAct)
                            .addGap(37, 37, 37)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnActualizarPersona)
                                .addComponent(btnLimpiarAct)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActualizarPersona)
                    .addComponent(btnBuscarPerAct)
                    .addComponent(btnLimpiarAct)
                    .addComponent(cmbCedulaPerAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombreCliente)
                            .addComponent(txtActualizarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnActualizarPersona)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApellidoCliente)
                    .addComponent(txtActualizarApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccionCliente)
                    .addComponent(txtIActualizarDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTelefonoCliente)
                            .addComponent(txtActualizartelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCorreoCliente)
                            .addComponent(txtActualizarCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblEstadoCliente)
                        .addComponent(cmbEstadoCliAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(btnListarPersonas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tbpAdministardor.addTab("Actualizar Persona", jPanel2);

        lblCedulaCLienteFac.setText("Cedula del Cliente:");

        btnBuscarPersonaCedula.setText("Buscar");
        btnBuscarPersonaCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonaCedulaActionPerformed(evt);
            }
        });

        lblCodeFac.setText("Codigo de factura:");

        jLabel5.setText("Numero de factura: ");

        lblFechaFac.setText("Fecha:");

        jLabel13.setText("-----------------------------------------------------------Productos-----------------------------------------------------------");

        btnImprimirFac.setText("Imprimir Factura");

        btnLimpiarFactura.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 14)); // NOI18N
        btnLimpiarFactura.setText("Limpiar");
        btnLimpiarFactura.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnLimpiarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarFacturaActionPerformed(evt);
            }
        });

        lblFacCedula.setText("Cedula Cliente: ");

        lblFacNombre.setText("Nombre Cliente:");

        lblFacApellido.setText("Apellido Cliente:");

        tblProductosFac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Precio", "IVA", "Stock"
            }
        ));
        jScrollPane8.setViewportView(tblProductosFac);

        cbmCedulaPersonaFac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));
        cbmCedulaPersonaFac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbmCedulaPersonaFacActionPerformed(evt);
            }
        });

        tblProductosFac1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Cantidad"
            }
        ));
        jScrollPane9.setViewportView(tblProductosFac1);

        tblProductosFac2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Codigo Barra"
            }
        ));
        jScrollPane10.setViewportView(tblProductosFac2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnImprimirFac)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCodeFac)
                                    .addComponent(lblFacCedula))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(113, 113, 113)
                                        .addComponent(lblFechaFac))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(lblFacNombre)))
                                .addGap(53, 53, 53)
                                .addComponent(lblFacApellido))
                            .addComponent(jLabel13)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblCedulaCLienteFac)
                                .addGap(53, 53, 53)
                                .addComponent(cbmCedulaPersonaFac, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(btnBuscarPersonaCedula)
                                .addGap(24, 24, 24)
                                .addComponent(btnLimpiarFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscarPersonaCedula)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCedulaCLienteFac)
                        .addComponent(cbmCedulaPersonaFac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCodeFac)
                            .addComponent(lblFechaFac))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5))
                    .addComponent(btnLimpiarFactura))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFacCedula)
                    .addComponent(lblFacNombre)
                    .addComponent(lblFacApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addComponent(btnImprimirFac))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 42, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tbpAdministardor.addTab("Facturar Clientes", jPanel3);

        tblCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Descripcion"
            }
        ));
        jScrollPane3.setViewportView(tblCategorias);
        if (tblCategorias.getColumnModel().getColumnCount() > 0) {
            tblCategorias.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel17.setText("Codigo de categoria:");

        btnListarCategorias.setText("Listar Categorias");
        btnListarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarCategoriasActionPerformed(evt);
            }
        });

        jLabel18.setText("Nombre:");

        lblDescripcion.setText("Descripcion");

        btnActualizarCategoria.setText("Actualizar Categoria");
        btnActualizarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarCategoriaActionPerformed(evt);
            }
        });

        btnCrearCategoria.setText("Crear Categoria");
        btnCrearCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearCategoriaActionPerformed(evt);
            }
        });

        btnLimpiarCategoria.setText("Limpiar");
        btnLimpiarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarCategoriaActionPerformed(evt);
            }
        });

        jLabel21.setText("Nombre:");

        lblDescripcion1.setText("Descripcion");

        cmbCodigoCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        btnCateEnter.setText("<---");
        btnCateEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCateEnterActionPerformed(evt);
            }
        });

        txtDescripcionAct.setColumns(20);
        txtDescripcionAct.setRows(5);
        jScrollPane11.setViewportView(txtDescripcionAct);

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane12.setViewportView(txtDescripcion);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDescripcion)
                            .addComponent(jLabel18))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreCate, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescripcion1)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombreCateAct, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addGap(18, 18, 18)
                                        .addComponent(cmbCodigoCate, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(btnCateEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizarCategoria, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnLimpiarCategoria)
                                .addGap(21, 21, 21)))
                        .addGap(138, 138, 138))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnCrearCategoria)
                                .addGap(18, 18, 18)
                                .addComponent(btnListarCategorias))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtNombreCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(btnActualizarCategoria)
                    .addComponent(cmbCodigoCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCateEnter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDescripcion)
                            .addComponent(jLabel21)
                            .addComponent(txtNombreCateAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(btnLimpiarCategoria)
                                .addGap(23, 23, 23))
                            .addComponent(lblDescripcion1)))
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearCategoria)
                    .addComponent(btnListarCategorias))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        tbpAdministardor.addTab("Categorias", jPanel4);

        lblTipoUsuario.setText("Tipo de empleado");

        btnLimpiarUsuario.setText("Limpiar");
        btnLimpiarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarUsuarioActionPerformed(evt);
            }
        });

        lblApellidoProv1.setText("Contraseña");

        btnActualizarUsuario.setText("Actualizar Usuario");

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Username", "Contraseña", "Tipo", "Cedula", "Nombre"
            }
        ));
        jScrollPane5.setViewportView(tblUsuarios);

        jLabel22.setText("Username:");

        btnListarUsuarios.setText("Listar Usuarios");
        btnListarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarUsuariosActionPerformed(evt);
            }
        });

        lblIdUsuAct.setText("Cedula de la persona");

        cmbCedulaUsu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        btnCrearUsuario.setText("Crear Usuario");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiarUsuario)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(lblApellidoProv1)
                                            .addComponent(lblTipoUsuario))
                                        .addGap(28, 28, 28))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addComponent(lblIdUsuAct)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                    .addComponent(txtContrasena)
                                    .addComponent(cmbTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbCedulaUsu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(98, 98, 98)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnActualizarUsuario)
                                    .addComponent(btnListarUsuarios)
                                    .addComponent(btnCrearUsuario)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIdUsuAct)
                            .addComponent(cmbCedulaUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblApellidoProv1)
                            .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoUsuario)
                            .addComponent(cmbTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnListarUsuarios)
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearUsuario)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarUsuario)))
                .addGap(18, 18, 18)
                .addComponent(btnLimpiarUsuario)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpAdministardor.addTab("Usuarios", jPanel6);

        btnBuscarProducto.setText("Buscar Producto");

        btnListarProductos.setText("Listar Productos");
        btnListarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarProductosActionPerformed(evt);
            }
        });

        lblApellidoProv2.setText("Precio Unitario");

        btnActualizarProducto.setText("Actualizar Producto");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Precio Unitario", "Stock", "IVA", "Categoria", "Codigo Barras"
            }
        ));
        jScrollPane6.setViewportView(tblProductos);

        btnAnadirProducto.setText("Añadir Producto");
        btnAnadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirProductoActionPerformed(evt);
            }
        });

        lblTipoUsuario1.setText("Stock");

        btnLimpiarProductos.setText("Limpiar");
        btnLimpiarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProductosActionPerformed(evt);
            }
        });

        jLabel24.setText("Nombre Producto");

        lblTipoUsuario2.setText("IVA");

        lblTipoUsuario3.setText("Categoria");

        cmbCategoriaProdu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        jLabel25.setText("Codigo Barra");

        cmbProductoAct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(lblApellidoProv2)
                            .addComponent(lblTipoUsuario1)
                            .addComponent(lblTipoUsuario2)
                            .addComponent(lblTipoUsuario3)
                            .addComponent(jLabel25))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIvaProd, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(txtNombreProd)
                            .addComponent(txtPrecioProd)
                            .addComponent(txtStockProd, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(txtCodigoBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(cmbCategoriaProdu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(btnAnadirProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                .addComponent(cmbProductoAct, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarProducto)
                                .addGap(19, 19, 19))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnLimpiarProductos)
                                    .addComponent(btnActualizarProducto))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnListarProductos)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoUsuario3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblTipoUsuario1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTipoUsuario2))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(txtNombreProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAnadirProducto)
                                    .addComponent(btnBuscarProducto)
                                    .addComponent(cmbProductoAct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblApellidoProv2)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtPrecioProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnLimpiarProductos)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtStockProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnActualizarProducto))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIvaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbCategoriaProdu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(txtCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(btnListarProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbpAdministardor.addTab("Productos", jPanel7);

        btnActualizarProveedor.setText("Actualizar Proveedor");
        btnActualizarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarProveedorActionPerformed(evt);
            }
        });

        btnAnadirProveedor.setText("Añadir Proveedor");
        btnAnadirProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirProveedorActionPerformed(evt);
            }
        });

        btnListarProveedores.setText("Listar Proveedores");
        btnListarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarProveedoresActionPerformed(evt);
            }
        });

        jLabel20.setText("Nombre:");

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "RUC"
            }
        ));
        jScrollPane4.setViewportView(tblProveedores);

        lblRucProveedor.setText("RUC:");

        btnLimpiarProv.setText("Limpiar");
        btnLimpiarProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarProvActionPerformed(evt);
            }
        });

        btnBuscarProveedor.setText("Buscar");
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnLimpiarProv)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAnadirProveedor)
                            .addComponent(btnListarProveedores)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(lblRucProveedor))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtRucProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(txtNombreProveedor))
                                .addGap(71, 71, 71)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbProveedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                        .addGap(48, 48, 48)
                        .addComponent(btnActualizarProveedor)))
                .addGap(141, 141, 141))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtNombreProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnActualizarProveedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRucProveedor)
                    .addComponent(txtRucProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarProveedor))
                .addGap(18, 18, 18)
                .addComponent(btnAnadirProveedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiarProv)
                    .addComponent(btnListarProveedores))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        tbpAdministardor.addTab("Proveedores", jPanel5);

        lblRucProv.setText("RUC:");

        btnLimpiarNota.setText("Limpiar");
        btnLimpiarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarNotaActionPerformed(evt);
            }
        });

        lblNombreProveedor.setText("Nombre:");

        tblCategorias4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Nota", "Fecha", "Codigo Producto", "Producto", "Cantidad", "Valor Total"
            }
        ));
        jScrollPane7.setViewportView(tblCategorias4);

        btnAnadirNota.setText("Añadir Notas");
        btnAnadirNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirNotaActionPerformed(evt);
            }
        });

        jLabel27.setText("Proveedor:");

        btnListarNotas.setText("Listar Notas");
        btnListarNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarNotasActionPerformed(evt);
            }
        });

        cmbIdProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));
        cmbIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIdProveedorActionPerformed(evt);
            }
        });

        lblFechaNota.setText("Fecha:");

        lblValorTotalNota.setText("Valor Total");

        jLabel28.setText("Cantidad:");

        jLabel29.setText("Codigo de Producto");

        cmbCodigoProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Productos" }));
        cmbCodigoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCodigoProductoActionPerformed(evt);
            }
        });

        btnGenerarNota.setText("Generar");
        btnGenerarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarNotaActionPerformed(evt);
            }
        });

        lblNombreProductoNota.setText("Nombre Producto:");

        btnBuscarProd.setText("Buscar");
        btnBuscarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel27)
                                            .addComponent(lblNombreProveedor)
                                            .addComponent(lblRucProv)
                                            .addComponent(jLabel28))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cmbIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(59, 59, 59))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                                        .addComponent(cmbCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)))
                                                .addComponent(btnBuscarProd))))
                                    .addComponent(lblFechaNota, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGenerarNota)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addComponent(btnListarNotas)
                                        .addGap(8, 8, 8))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(84, 84, 84)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnLimpiarNota)
                                            .addComponent(btnAnadirNota)))))
                            .addComponent(lblNombreProductoNota)
                            .addComponent(jLabel29)
                            .addComponent(lblValorTotalNota))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(cmbIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblNombreProveedor)
                        .addGap(11, 11, 11)
                        .addComponent(lblRucProv)
                        .addGap(9, 9, 9)
                        .addComponent(lblFechaNota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(cmbCodigoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscarProd))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblNombreProductoNota)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblValorTotalNota))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerarNota)
                            .addComponent(btnAnadirNota))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarNota)
                        .addGap(154, 154, 154)
                        .addComponent(btnListarNotas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        tbpAdministardor.addTab("Nota Ventas", jPanel8);

        btnBorrarNota.setText("Anular Nota");
        btnBorrarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarNotaActionPerformed(evt);
            }
        });

        btnBorrarProveedor.setText("Borrar Proveedor");
        btnBorrarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarProveedorActionPerformed(evt);
            }
        });

        btnBorrarUsuario.setText("Borrar Usuario");
        btnBorrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarUsuarioActionPerformed(evt);
            }
        });

        btnBorrarCategoria.setText("Borrar Categoria");
        btnBorrarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarCategoriaActionPerformed(evt);
            }
        });

        btnBorrarPer.setText("Borrar Persona");
        btnBorrarPer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarPerActionPerformed(evt);
            }
        });

        cmbBorrarPersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));
        cmbBorrarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBorrarPersonaActionPerformed(evt);
            }
        });

        jLabel4.setText("Cedula");

        cmbBorrarNota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        jLabel10.setText("Num. Factura");

        jLabel11.setText("Categoria");

        cmbBorrarCate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        jLabel12.setText("Usuario");

        cmbBorrarUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        jLabel14.setText("Proveedor");

        cmbBorrarProv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        jLabel15.setText("Codigo Barras");

        lblPersonaDelete.setText("Persona");

        lblNotaDelete.setText("Nota: ");

        lblProdDelete.setText("Producto");

        cmbBorrarProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige" }));

        btnBorrarProd.setText("Borrar Producto");
        btnBorrarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarProdActionPerformed(evt);
            }
        });

        btnBorrarProd1.setText("Buscar Producto");
        btnBorrarProd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarProd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(lblProdDelete)
                        .addContainerGap())
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(lblPersonaDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(35, 35, 35)
                                        .addComponent(cmbBorrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel4))
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(cmbBorrarCate, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(21, 21, 21))
                                            .addGroup(jPanel9Layout.createSequentialGroup()
                                                .addComponent(cmbBorrarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrarUsuario)
                                    .addComponent(btnBorrarPer)
                                    .addComponent(btnBorrarCategoria)
                                    .addComponent(btnBorrarProd1))
                                .addGap(33, 33, 33)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNotaDelete)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbBorrarNota, 0, 108, Short.MAX_VALUE)
                                    .addComponent(cmbBorrarProv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrarNota)
                                    .addComponent(btnBorrarProveedor)))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBorrarProd)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbBorrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarNota)
                    .addComponent(btnBorrarPer)
                    .addComponent(cmbBorrarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbBorrarNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(10, 10, 10)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonaDelete)
                    .addComponent(lblNotaDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrarCategoria)
                    .addComponent(jLabel11)
                    .addComponent(cmbBorrarCate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cmbBorrarProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarProveedor))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbBorrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarUsuario))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cmbBorrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarProd1))
                .addGap(18, 18, 18)
                .addComponent(lblProdDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrarProd)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        tbpAdministardor.addTab("Borrar", jPanel9);

        btnLogoutAdmin.setText("LogOut");
        btnLogoutAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutAdminActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar informacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tbpAdministardor, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(159, 159, 159)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(btnLogoutAdmin)
                        .addGap(176, 176, 176))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnLogoutAdmin)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpAdministardor, javax.swing.GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutAdminActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnLogoutAdminActionPerformed

    private void btnBuscarPersonaCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonaCedulaActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarPersonaCedulaActionPerformed

    private void btnBuscarPerActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPerActActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarPerActActionPerformed

    private void btnLimpiarActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarActActionPerformed

    private void btnListarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarCategoriasActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarCategoriasActionPerformed

    private void btnListarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarProveedoresActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarProveedoresActionPerformed

    private void btnAnadirProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirProveedorActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnadirProveedorActionPerformed

    private void btnListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarProductosActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarProductosActionPerformed

    private void btnAnadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirProductoActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAnadirProductoActionPerformed

    private void btnAnadirNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAnadirNotaActionPerformed

    private void btnListarNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarNotasActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarNotasActionPerformed

    private void cmbCodigoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCodigoProductoActionPerformed
        
    }//GEN-LAST:event_cmbCodigoProductoActionPerformed

    private void btnListarPersonasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarPersonasActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarPersonasActionPerformed

    private void btnListarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarUsuariosActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnListarUsuariosActionPerformed

    private void btnCrearCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearCategoriaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearCategoriaActionPerformed

    private void cmbEstadoCliActActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoCliActActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoCliActActionPerformed

    private void btnLimpiarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarPersonaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarPersonaActionPerformed

    private void btnAgregarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPersonaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarPersonaActionPerformed

    private void btnLimpiarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarFacturaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarFacturaActionPerformed

    private void cbmCedulaPersonaFacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbmCedulaPersonaFacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbmCedulaPersonaFacActionPerformed

    private void btnLimpiarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarCategoriaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarCategoriaActionPerformed

    private void btnBuscarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProdActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarProdActionPerformed

    private void cmbIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIdProveedorActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbIdProveedorActionPerformed

    private void btnLimpiarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProductosActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarProductosActionPerformed

    private void btnLimpiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarUsuarioActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarUsuarioActionPerformed

    private void btnLimpiarProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarProvActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarProvActionPerformed

    private void btnLimpiarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarNotaActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarNotaActionPerformed

    private void btnCateEnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCateEnterActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCateEnterActionPerformed

    private void btnGenerarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarNotaActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGenerarNotaActionPerformed

    private void btnActualizarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarPersonaActionPerformed
        try {
            actualizar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnActualizarPersonaActionPerformed

    private void btnActualizarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarCategoriaActionPerformed
        try {
            actualizar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarCategoriaActionPerformed

    private void btnBorrarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarProveedorActionPerformed
        try {
            Borrar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarProveedorActionPerformed

    private void btnBorrarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarNotaActionPerformed

    private void btnBorrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarUsuarioActionPerformed
        try {
            Borrar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarUsuarioActionPerformed

    private void btnBorrarPerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarPerActionPerformed
        try {
            Borrar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarPerActionPerformed

    private void cmbBorrarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBorrarPersonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBorrarPersonaActionPerformed

    private void btnBorrarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarProdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarProdActionPerformed

    private void btnBorrarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarCategoriaActionPerformed
        try {
            Borrar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBorrarCategoriaActionPerformed

    private void btnBorrarProd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarProd1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBorrarProd1ActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnActualizarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarProveedorActionPerformed
        try {
            actualizar(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarProveedorActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
        try {
            operacionesGet(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaAdministrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarProveedorActionPerformed
    public void operacionesGet(ActionEvent ev) throws SQLException{
        

        
        if(ev.getSource().equals(btnBuscarProd)){
            String item = cmbCodigoProducto.getSelectedItem().toString();
            if(item.equals("Productos")){
            lblNombreProductoNota.setText("Nombre Producto:");
            }else{  
            ResultSet producto = controladorFarmacia.getProducto(item);
            if (producto.next()) {
                lblNombreProductoNota.setText("Nombre producto: " + producto.getString("pro_nombre"));
            } else {
                lblNombreProductoNota.setText("Nombre producto: Producto no encontrado");
            }         
        }
        }
        if(ev.getSource().equals(btnBuscarPerAct)){
           String item = cmbCedulaPerAct.getSelectedItem().toString();
            if(item.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Elija una Cedula, porfavor");
            }else{  
            ResultSet personaAct = controladorFarmacia.getPersona(item);
                personaAct.next();
                    String nombre = personaAct.getString("per_nombre");
                    String apellido = personaAct.getString("per_apellido");
                    String direccion = personaAct.getString("per_direccion");
                    String correo = personaAct.getString("per_correo");
                    String telefono = personaAct.getString("per_telefono");
                    System.out.println(nombre);
                    txtActualizarNombre.setText(nombre);
                    txtActualizarApellido.setText(apellido);
                    txtIActualizarDireccion.setText(direccion);
                    txtActualizarCorreo.setText(correo);
                    txtActualizartelefono.setText(telefono);
                    cmbEstadoCliAct.setSelectedIndex(0);
            }
        }
        if(ev.getSource().equals(btnBuscarPersonaCedula)){
           String item = cbmCedulaPersonaFac.getSelectedItem().toString();
            if(item.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Elija una Cedula, porfavor");
            }else{  
            ResultSet personaFac = controladorFarmacia.getPersona(item);
                personaFac.next();
                    String nombre = personaFac.getString("per_nombre");
                    String apellido = personaFac.getString("per_apellido");
                    String cedula = personaFac.getString("per_cedula");
                    
                    lblFacNombre.setText("Nombre Cliente:" + nombre);
                    lblFacApellido.setText("Apellido Cliente:" + apellido);
                    lblFacCedula.setText("Cedula Cliente: " + cedula);

            }
        }
        if(ev.getSource().equals(btnCateEnter)){
            String item = cmbCodigoCate.getSelectedItem().toString();
            if(item.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Elija una Cedula, porfavor");
            }else{  
                ResultSet categoria = controladorFarmacia.getCategoria(item);
                categoria.next();
                String nombre = categoria.getString("cat_nombre");
                String desc = categoria.getString("cat_descripcion");
                txtNombreCateAct.setText(nombre);
                txtDescripcionAct.setText(desc);
                
            }
        }
        if(ev.getSource().equals(btnGenerarNota)){
            String item = cmbIdProveedor.getSelectedItem().toString();
            if(item.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Elija un proveedor");
            }else{
                ResultSet prov = controladorFarmacia.getProveedor(item);
                prov.next();
                String name = prov.getString("prov_nombre");
                String Ruc = prov.getString("prov_ruc");
                String fecha = prov.getString("fecha");
                lblFechaNota.setText("Fecha: " + fecha);
                lblRucProv.setText("RUC: " + Ruc);
                lblNombreProveedor.setText("Nombre: " + name);
            } 
        }
    }
    public void actualizar(ActionEvent ev) throws SQLException{
        if(ev.getSource().equals(btnActualizarProveedor)){
            if (txtNombreProveedor.getText().isBlank() || 
                txtRucProveedor.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear actualizar el Proveedor");
            }else{
                String nombre = txtNombreProveedor.getText();
                int ruc = Integer.parseInt(txtRucProveedor.getText());
                String codigo = cmbProveedores.getSelectedItem().toString();
                
                boolean actualizado = controladorFarmacia.actualizarProveedor(codigo, nombre, ruc);
                
                if(actualizado == true){
                    JOptionPane.showMessageDialog(null, "Actualizacion Completa");
                    controladorFarmacia.commit();
                    cargarListas();
                }else{
                    JOptionPane.showMessageDialog(null, "Error al Actualizar");
                }
            }
        }
        
        if(ev.getSource().equals(btnActualizarPersona)){
            if (txtActualizarNombre.getText().isBlank() || 
                txtActualizarApellido.getText().isBlank() ||
                txtIActualizarDireccion.getText().isBlank() ||
                txtActualizarCorreo.getText().isBlank() ||
                txtActualizartelefono.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos para crear actualizar la Persona");
            }else{
                String nombreAct = txtActualizarNombre.getText();
                String apellidoAct = txtActualizarApellido.getText();
                String direAct = txtIActualizarDireccion.getText();
                String correoAct = txtActualizarCorreo.getText();
                String telefonoAct = txtActualizartelefono.getText();
                String estadoAct = cmbEstadoCliAct.getSelectedItem().toString();
                String cedula = cmbCedulaPerAct.getSelectedItem().toString();
                String estado;
                if(estadoAct.equals("Activo")){
                    estado = "1";
                }else{
                    estado = "0";
                }
                boolean actualizado = controladorFarmacia.actualizarPersonas(cedula, nombreAct, apellidoAct, direAct, telefonoAct, correoAct, estado);
                
                if(actualizado == true){
                    System.out.println("Actualizado completo");
                    controladorFarmacia.commit();
                    cargarListas();
                }else{
                    System.out.println("Error al actualizar");
                }
            }
        }
        if(ev.getSource().equals(btnActualizarCategoria)){
            if(txtDescripcionAct.getText().isEmpty() || txtNombreCateAct.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos actualizar la categoria");
            
            }else{
                String descAct = txtDescripcionAct.getText();
                String nombreAct = txtNombreCateAct.getText();
                String codigo = cmbCodigoCate.getSelectedItem().toString();
                boolean estado = controladorFarmacia.actualizarCate(codigo, nombreAct, descAct);
                if(estado = true){
                    System.out.println("Actualizado cate GOOD");
                    controladorFarmacia.commit();
                    cargarListas();
                }else{
                    System.out.println("Fail to actualizar");
                }
            
            }
        }
    }
    public void Borrar(ActionEvent ev) throws SQLException{
        if(ev.getSource().equals(btnBorrarPer)){
           String personas = cmbBorrarPersona.getSelectedItem().toString();
            if(personas.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Escoja la Persona a borrar");       
            }else{
                boolean estado = controladorFarmacia.borrarPersona(Integer.parseInt(personas));
                if(estado == true){
                    JOptionPane.showMessageDialog(null, "Borrado Exitosamente");
                    cargarListas();
                    controladorFarmacia.commit();
                }else{
                    System.out.println("ERROR");
                }
            }
        }
        if(ev.getSource().equals(btnBorrarProveedor)){
            String prov = cmbBorrarProv.getSelectedItem().toString();
            if(prov.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Escoja el Proveedor a borrar");       
            }else{
                boolean estado = controladorFarmacia.borrarProv(prov);
                if(estado == true){
                    JOptionPane.showMessageDialog(null, "Borrado Exitosamente");
                    cargarListas();
                    controladorFarmacia.commit();
                }else{
                    System.out.println("ERROR");
                }
            }
        }
        if(ev.getSource().equals(btnBorrarCategoria)){
            String cate = cmbBorrarCate.getSelectedItem().toString();
            if(cate.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Escoja la Categoria a borrar"); 
            }else{
                boolean estado = controladorFarmacia.borrarCate(cate);
                if(estado == true){
                    JOptionPane.showMessageDialog(null, "Borrado Exitosamente");
                    cargarListas();
                    controladorFarmacia.commit();
                }else{
                    System.out.println("ERROR");
                }
            }
        }
        if(ev.getSource().equals(btnBorrarUsuario)){
            String usuario = cmbBorrarUsuario.getSelectedItem().toString();
            if(usuario.equals("Elige")){
                JOptionPane.showMessageDialog(null, "Escoja un Usuario a borrar"); 
            }else{
                boolean estado = controladorFarmacia.borrarUsuario(usuario);
                if(estado == true){
                    JOptionPane.showMessageDialog(null, "Borrado Exitosamente");
                    controladorFarmacia.commit();
                    cargarListas();
                }else{
                    System.out.println("ERROR");
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarCategoria;
    private javax.swing.JButton btnActualizarPersona;
    private javax.swing.JButton btnActualizarProducto;
    private javax.swing.JButton btnActualizarProveedor;
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnAgregarPersona;
    private javax.swing.JButton btnAnadirNota;
    private javax.swing.JButton btnAnadirProducto;
    private javax.swing.JButton btnAnadirProveedor;
    private javax.swing.JButton btnBorrarCategoria;
    private javax.swing.JButton btnBorrarNota;
    private javax.swing.JButton btnBorrarPer;
    private javax.swing.JButton btnBorrarProd;
    private javax.swing.JButton btnBorrarProd1;
    private javax.swing.JButton btnBorrarProveedor;
    private javax.swing.JButton btnBorrarUsuario;
    private javax.swing.JButton btnBuscarPerAct;
    private javax.swing.JButton btnBuscarPersonaCedula;
    private javax.swing.JButton btnBuscarProd;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnCateEnter;
    private javax.swing.JButton btnCrearCategoria;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnGenerarNota;
    private javax.swing.JButton btnImprimirFac;
    private javax.swing.JButton btnLimpiarAct;
    private javax.swing.JButton btnLimpiarCategoria;
    private javax.swing.JButton btnLimpiarFactura;
    private javax.swing.JButton btnLimpiarNota;
    private javax.swing.JButton btnLimpiarPersona;
    private javax.swing.JButton btnLimpiarProductos;
    private javax.swing.JButton btnLimpiarProv;
    private javax.swing.JButton btnLimpiarUsuario;
    private javax.swing.JButton btnListarCategorias;
    private javax.swing.JButton btnListarNotas;
    private javax.swing.JButton btnListarPersonas;
    private javax.swing.JButton btnListarProductos;
    private javax.swing.JButton btnListarProveedores;
    private javax.swing.JButton btnListarUsuarios;
    private javax.swing.JButton btnLogoutAdmin;
    private javax.swing.JComboBox<String> cbmCedulaPersonaFac;
    private javax.swing.JComboBox<String> cmbBorrarCate;
    private javax.swing.JComboBox<String> cmbBorrarNota;
    private javax.swing.JComboBox<String> cmbBorrarPersona;
    private javax.swing.JComboBox<String> cmbBorrarProducto;
    private javax.swing.JComboBox<String> cmbBorrarProv;
    private javax.swing.JComboBox<String> cmbBorrarUsuario;
    private javax.swing.JComboBox<String> cmbCategoriaProdu;
    private javax.swing.JComboBox<String> cmbCedulaPerAct;
    private javax.swing.JComboBox<String> cmbCedulaUsu;
    private javax.swing.JComboBox<String> cmbCodigoCate;
    private javax.swing.JComboBox<String> cmbCodigoProducto;
    private javax.swing.JComboBox<String> cmbEstadoCliAct;
    private javax.swing.JComboBox<String> cmbIdProveedor;
    private javax.swing.JComboBox<String> cmbProductoAct;
    private javax.swing.JComboBox<String> cmbProveedores;
    private javax.swing.JComboBox<String> cmbTipoEmpleado;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblActualizarPersona;
    private javax.swing.JLabel lblApellidoCliente;
    private javax.swing.JLabel lblApellidoProv1;
    private javax.swing.JLabel lblApellidoProv2;
    private javax.swing.JLabel lblCedulaCLienteFac;
    private javax.swing.JLabel lblCodeFac;
    private javax.swing.JLabel lblCorreoCliente;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblDescripcion1;
    private javax.swing.JLabel lblDireccionCliente;
    private javax.swing.JLabel lblEstadoCliente;
    private javax.swing.JLabel lblFacApellido;
    private javax.swing.JLabel lblFacCedula;
    private javax.swing.JLabel lblFacNombre;
    private javax.swing.JLabel lblFechaFac;
    private javax.swing.JLabel lblFechaNota;
    private javax.swing.JLabel lblIdUsuAct;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreProductoNota;
    private javax.swing.JLabel lblNombreProveedor;
    private javax.swing.JLabel lblNotaDelete;
    private javax.swing.JLabel lblPersonaDelete;
    private javax.swing.JLabel lblProdDelete;
    private javax.swing.JLabel lblRucProv;
    private javax.swing.JLabel lblRucProveedor;
    private javax.swing.JLabel lblTelefonoCliente;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JLabel lblTipoUsuario1;
    private javax.swing.JLabel lblTipoUsuario2;
    private javax.swing.JLabel lblTipoUsuario3;
    private javax.swing.JLabel lblValorTotalNota;
    private javax.swing.JTable tblCategorias;
    private javax.swing.JTable tblCategorias4;
    private javax.swing.JTable tblListarPersonas;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTable tblProductosFac;
    private javax.swing.JTable tblProductosFac1;
    private javax.swing.JTable tblProductosFac2;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTabbedPane tbpAdministardor;
    private javax.swing.JTextField txtActualizarApellido;
    private javax.swing.JTextField txtActualizarCorreo;
    private javax.swing.JTextField txtActualizarNombre;
    private javax.swing.JTextField txtActualizartelefono;
    private javax.swing.JTextField txtApellidoPersona;
    private javax.swing.JTextField txtCedulaPersona;
    private javax.swing.JTextField txtCodigoBarras;
    private javax.swing.JTextField txtContrasena;
    private javax.swing.JTextField txtCorreoPersona;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextArea txtDescripcionAct;
    private javax.swing.JTextField txtDireccionPersona;
    private javax.swing.JTextField txtIActualizarDireccion;
    private javax.swing.JTextField txtIvaProd;
    private javax.swing.JTextField txtNombreCate;
    private javax.swing.JTextField txtNombreCateAct;
    private javax.swing.JTextField txtNombrePersona;
    private javax.swing.JTextField txtNombreProd;
    private javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtPrecioProd;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtStockProd;
    private javax.swing.JTextField txtTelefonoPersona;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
