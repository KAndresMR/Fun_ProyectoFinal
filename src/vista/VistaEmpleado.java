
package vista;

import controlador.cEjemplar;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class VistaEmpleado extends javax.swing.JPanel 
{
    // 
    private cEjemplar controladorEmpresa;
    List <Integer> listaHijo = new ArrayList();
    
    public void setControlador(cEjemplar controladorEmpresa){
     this.controladorEmpresa = controladorEmpresa;     
        
    }

    public VistaEmpleado() throws SQLException 
    {
        initComponents();
        lblNombreCliente.setVisible(false);
        lblApellidoCliente.setVisible(false);
        lblCorreoCliente.setVisible(false);
        lblTelefonoCliente.setVisible(false);
        lblCorreoCliente.setVisible(false);
        lblTipoCliente.setVisible(false);
        lblEstadoCliente.setVisible(false);
        
    }
    public void operaciones(ActionEvent ev) throws SQLException{
        /*
        if(ev.getSource().equals(btnAgregarEmpleado)){
            if(txtIdEmpleado.getText().isBlank() || txtNombreEmpleado.getText().isBlank() || txtApellidoEmpleado.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene todos los campos porfavor");
            }else{
                int id = Integer.parseInt(txtIdEmpleado.getText());
                String nombre = txtNombreEmpleado.getText();
                String apellido = txtApellidoEmpleado.getText();
                if(controladorEmpresa.agregarEmpleado(id, nombre, apellido)){
                    for(Integer i : listaHijo){
                    controladorEmpresa.updateHijo(id, i);     
                    controladorEmpresa.updateHijoApellido(apellido, i);
                    }
                    
                    controladorEmpresa.updateNumHijo(id);
                    JOptionPane.showMessageDialog(null, "Creacion Exitosa");
                    txtIdEmpleado.setText("");
                    txtNombreEmpleado.setText("");
                    txtApellidoEmpleado.setText("");
                    txtDireccion.setText("");
                    listaHijo.clear();
                    ResultSet listado =  controladorEmpresa.listarHijosDisponibles();
                    DefaultTableModel modeloTabla = (DefaultTableModel)tblHijoDis.getModel();
                    modeloTabla.setRowCount(0);
                    while(listado.next()){
                        modeloTabla.addRow(new Object[]{listado.getString("cedula"), listado.getString("nombre")});
                    }
                }else{
                    ResultSet empleado = controladorEmpresa.getEmpleado(id);
                    empleado.next();
                    int codigo = empleado.getInt("id_empleado");
                    String apellidoEmpleado = empleado.getString("apellido");
                    JOptionPane.showMessageDialog(null, "Este codigo ya existe pertenece a Codigo: " + codigo + " Apellido: " + apellidoEmpleado);
                }
                
                
            }
        }
        if(ev.getSource().equals(btnAgregarHijo)){           
            int idHijo = Integer.parseInt(txtDireccion.getText());
            ResultSet listado = controladorEmpresa.hijoUse(idHijo);
            ResultSet exist = controladorEmpresa.existeHijo(idHijo);
            listado.next();
            if(exist.next() == false){
                JOptionPane.showMessageDialog(null, "No hay codigo de este hijo");
            }else if(listado.getString("id_empleado") != null){
                JOptionPane.showMessageDialog(null, "Este hijo ya esta asignado a un padre");
            }else{
                listaHijo.add(idHijo);
                System.out.println(listaHijo);
            }
        }
        if(ev.getSource().equals(btnLimpiar)){
            ResultSet listado =  controladorEmpresa.listar();
            DefaultTableModel modeloTabla = (DefaultTableModel)tblListar.getModel();
            modeloTabla.setRowCount(0);
            while(listado.next()){
                controladorEmpresa.updateNumHijo(listado.getInt("id_empleado"));
                if(listado.getInt("numHijos")==0){
                    modeloTabla.addRow(new Object[]{listado.getString("id_empleado"), listado.getString("nombre"),listado.getString("apellido"), "no tiene hijos"});
                }else{
                    modeloTabla.addRow(new Object[]{listado.getString("id_empleado"), listado.getString("nombre"),listado.getString("apellido"), listado.getInt("numHijos")});
                }
            }
        }
        if(ev.getSource().equals(btnEliminarHijo)){
            if(txtCedulaEliminar.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Llene los campos de informacion");
            }else{
                int cedula = Integer.parseInt(txtCedulaEliminar.getText());
                ResultSet delete = controladorEmpresa.existeHijo(cedula);
                ResultSet listado = controladorEmpresa.hijoUse(cedula);
                listado.next();
                if(delete.next() == false){
                    JOptionPane.showMessageDialog(null, "No existe codigo del hijo");
                }else if(listado.getString("id_empleado") == null){
                    JOptionPane.showMessageDialog(null, "No se puede elimina el hijo");
                }else{
                    int empleado = listado.getInt("id_empleado");
                    controladorEmpresa.eliminarHijo(cedula);
                    controladorEmpresa.updateNumHijo(empleado);
                    JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
                    ResultSet listHijo =  controladorEmpresa.listarHijo();
                    DefaultTableModel modeloTabla = (DefaultTableModel)tblListarHijos.getModel();
                    modeloTabla.setRowCount(0);
                    while(listHijo.next()){
                        modeloTabla.addRow(new Object[]{listHijo.getString("cedula"), listHijo.getString("nombre"),listHijo.getString("apellidoHijo"), listHijo.getInt("id_empleado")});   
                    }
                    
                    
                }
            }
        }
        if(ev.getSource().equals(btnListarConsulta)){
            int cedula = Integer.parseInt(txtCedulaHijo.getText());
            ResultSet list = controladorEmpresa.existeHijo(cedula);   
              
            if(list.next()){      
                DefaultTableModel modeloTabla = (DefaultTableModel)tblListarConsulta.getModel();
                modeloTabla.setRowCount(0);    
                int idEmpleado = list.getInt("id_empleado");
                System.out.println("Id: "+idEmpleado);    
                boolean exist = false;
                ResultSet Consulta = controladorEmpresa.listarConsulta(idEmpleado, cedula);                
                while(Consulta.next()){
                    if(Consulta.getString("id_empleado") != null){
                        modeloTabla.addRow(new Object[]{Consulta.getString("id_empleado"), Consulta.getString("Empleado.nombre"), Consulta.getString("Empleado.apellido"),
                        Consulta.getString("Hijo.cedula"),Consulta.getString("Hijo.nombre")});   
                    } 
                    exist = true;
                }
                if(exist == false){
                    {
                        ResultSet empleado = controladorEmpresa.getEmpleado(idEmpleado);
                        empleado.next();
                        modeloTabla.addRow(new Object[]{empleado.getString("id_empleado"), empleado.getString("Empleado.nombre"), empleado.getString("Empleado.apellido"),
                        "No tiene hermanos", "No tiene hermanos"});   
                    }     
                }
                
            }else{
            JOptionPane.showMessageDialog(null, "No existe el hijo");
        }
            
            
            
        }
        */
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tbpFacturarClientes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        btnAgregarEmpleado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtApellidoEmpleado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        cmbTipoCliente = new javax.swing.JComboBox<>();
        cmbEstadoCliente = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListar = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnBuscarCliente = new javax.swing.JButton();
        txtCedulaCliente = new javax.swing.JTextField();
        lblActualizarCliente = new javax.swing.JLabel();
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
        lblTipoCliente = new javax.swing.JLabel();
        lblEstadoCliente = new javax.swing.JLabel();
        cmbTipoCliente1 = new javax.swing.JComboBox<>();
        cmbEstadoCliente1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        lblCedulaCLienteFac = new javax.swing.JLabel();
        txtCedulaEliminar = new javax.swing.JTextField();
        btnEliminarHijo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblCodigoFactura = new javax.swing.JLabel();
        lblNumeroFactura = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cmbProductos = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        spnNumeroProducto = new javax.swing.JSpinner();
        lblNombreProducto = new javax.swing.JLabel();
        lblPrecioProducto = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        btnAgregarProducto = new javax.swing.JButton();
        btnFinalizarFactura = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

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
        jLabel6.setText("Empleado");

        jLabel1.setText("Cedula");

        jLabel2.setText("Nombres:");

        btnAgregarEmpleado.setText("Agregar");
        btnAgregarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarEmpleadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Direccion:");

        jLabel7.setText("Apellidos:");

        jLabel8.setText("Telefono:");

        jLabel9.setText("Correo");

        jLabel10.setText("Tipo:");

        jLabel11.setText("Estado:");

        cmbTipoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoClienteActionPerformed(evt);
            }
        });

        cmbEstadoCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstadoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoClienteActionPerformed(evt);
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
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(txtNombreEmpleado)
                            .addComponent(txtApellidoEmpleado, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(138, 138, 138)
                        .addComponent(btnAgregarEmpleado)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarEmpleado))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellidoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cmbTipoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        tbpFacturarClientes.addTab("Registrar Cliente", jPanel1);

        tblListar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido", "Direccion", "Telefono", "Correo", "Tipo", "Estado"
            }
        ));
        jScrollPane2.setViewportView(tblListar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        lblActualizarCliente.setText("Buscar cliente por Codigo:");

        lblCorreoCliente.setText("Correo");

        lblDireccionCliente.setText("Direccion:");

        lblTelefonoCliente.setText("Telefono:");

        lblApellidoCliente.setText("Apellidos:");

        lblNombreCliente.setText("Nombres:");

        lblTipoCliente.setText("Tipo:");

        lblEstadoCliente.setText("Estado:");

        cmbTipoCliente1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbTipoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoCliente1ActionPerformed(evt);
            }
        });

        cmbEstadoCliente1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbEstadoCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoCliente1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(lblActualizarCliente)
                            .addGap(18, 18, 18)
                            .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnBuscarCliente)
                            .addGap(37, 37, 37)
                            .addComponent(btnLimpiar))
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
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblEstadoCliente)
                                        .addComponent(lblTipoCliente))
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmbTipoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbEstadoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(txtActualizarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtActualizarApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActualizarCliente)
                    .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarCliente)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreCliente)
                    .addComponent(txtActualizarNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoCliente)
                            .addComponent(cmbTipoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEstadoCliente)
                            .addComponent(cmbEstadoCliente1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        tbpFacturarClientes.addTab("Actualizar Cliente", jPanel2);

        lblCedulaCLienteFac.setText("Cedula del Cliente:");

        btnEliminarHijo.setText("Buscar");
        btnEliminarHijo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHijoActionPerformed(evt);
            }
        });

        jLabel4.setText("Codigo de factura");

        jLabel5.setText("Numero de factura");

        jLabel12.setText("Fecha");

        lblFecha.setText("\"\"");

        lblCodigoFactura.setText("\"\"");

        lblNumeroFactura.setText("\"\"");

        jLabel13.setText("Registre codigo de producto:");

        cmbProductos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Producto:");

        jLabel15.setText("Precio Unitario:");

        lblNombreProducto.setText("\"\"");

        lblPrecioProducto.setText("\"\"");

        jLabel16.setText("Cantidad producto:");

        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        btnFinalizarFactura.setText("Finalizar Factura");

        btnAnular.setText("Anular Factura");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFinalizarFactura)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(30, 30, 30)
                        .addComponent(lblCodigoFactura)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel12)
                        .addGap(39, 39, 39)
                        .addComponent(lblFecha))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(lblNumeroFactura))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(lblCedulaCLienteFac)
                                    .addGap(55, 55, 55)
                                    .addComponent(txtCedulaEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel14)
                                            .addGap(68, 68, 68)
                                            .addComponent(lblNombreProducto))
                                        .addComponent(jLabel13))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addGap(42, 42, 42)
                                    .addComponent(lblPrecioProducto)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(spnNumeroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarProducto)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnEliminarHijo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                                .addComponent(btnAnular)))))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCedulaEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEliminarHijo)
                        .addComponent(btnAnular))
                    .addComponent(lblCedulaCLienteFac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(lblCodigoFactura))
                    .addComponent(jLabel12)
                    .addComponent(lblFecha))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNumeroFactura))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblNombreProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(lblPrecioProducto))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(spnNumeroProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnFinalizarFactura)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tbpFacturarClientes.addTab("Facturar Clientes", jPanel3);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpFacturarClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(240, 240, 240)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnSalir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpFacturarClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(406, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEliminarHijoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHijoActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarHijoActionPerformed

    private void cmbEstadoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoCliente1ActionPerformed

    private void cmbTipoCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoCliente1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoCliente1ActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cmbEstadoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoClienteActionPerformed

    private void cmbTipoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoClienteActionPerformed

    private void btnAgregarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarEmpleadoActionPerformed
        try {
            operaciones(evt);
        } catch (SQLException ex) {
            Logger.getLogger(VistaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarEmpleadoActionPerformed

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarProductoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarEmpleado;
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnEliminarHijo;
    private javax.swing.JButton btnFinalizarFactura;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cmbEstadoCliente;
    private javax.swing.JComboBox<String> cmbEstadoCliente1;
    private javax.swing.JComboBox<String> cmbProductos;
    private javax.swing.JComboBox<String> cmbTipoCliente;
    private javax.swing.JComboBox<String> cmbTipoCliente1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblActualizarCliente;
    private javax.swing.JLabel lblApellidoCliente;
    private javax.swing.JLabel lblCedulaCLienteFac;
    private javax.swing.JLabel lblCodigoFactura;
    private javax.swing.JLabel lblCorreoCliente;
    private javax.swing.JLabel lblDireccionCliente;
    private javax.swing.JLabel lblEstadoCliente;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblNombreProducto;
    private javax.swing.JLabel lblNumeroFactura;
    private javax.swing.JLabel lblPrecioProducto;
    private javax.swing.JLabel lblTelefonoCliente;
    private javax.swing.JLabel lblTipoCliente;
    private javax.swing.JSpinner spnNumeroProducto;
    private javax.swing.JTable tblListar;
    private javax.swing.JTabbedPane tbpFacturarClientes;
    private javax.swing.JTextField txtActualizarApellido;
    private javax.swing.JTextField txtActualizarCorreo;
    private javax.swing.JTextField txtActualizarNombre;
    private javax.swing.JTextField txtActualizartelefono;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtCedulaEliminar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIActualizarDireccion;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
