package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class AdminPanel extends JFrame {

    // Definición de colores y columnas de la tabla
    Color colorPanel = new Color(234, 235, 237);
    String[] nombresColumnas = {"Cedula", "nombre1", "Nombre2", "apellido1", "apellido2", "login", "contraseña"};
    DefaultTableModel modelo = new DefaultTableModel(nombresColumnas, 0); // Modelo de la tabla
    JTable tabla = new JTable(modelo); // Tabla que muestra los usuarios

    // Botones de la interfaz
    JButton Agregar = new JButton("Agregar");
    JButton Actualizar = new JButton("Actualizar");
    JButton Eliminar = new JButton("Eliminar");
    JButton Regresar = new JButton("Regresar");

    // Etiquetas y campos de texto
    JLabel Login = new JLabel("Usuario: ");
    JLabel Contraseña = new JLabel("Contraseña: ");
    JLabel Nombre1 = new JLabel("Primer nombre: ");
    JLabel Nombre2 = new JLabel("Segundo nombre: ");
    JLabel Apellido1 = new JLabel("Primer apellido: ");
    JLabel Apellido2 = new JLabel("Segundo apellido: ");
    JLabel Cedula = new JLabel("Cédula: ");
    JTextField TUsuario = new JTextField();
    JTextField TContraseña = new JTextField();
    JTextField TNombre1 = new JTextField();
    JTextField TNombre2 = new JTextField();
    JTextField TApellido1 = new JTextField();
    JTextField TApellido2 = new JTextField();
    JTextField TCedula = new JTextField();

    // Paneles para organizar la interfaz
    JPanel Datos = new JPanel();
    JPanel Tabla = new JPanel();
    JPanel Principal = new PanelConFondo("/Imagenes/Fondo3.jpg"); // Mantiene la imagen de fondo

    // Constructor de la clase
    public AdminPanel() {
        setSize(1000, 700); // Tamaño de la ventana
        setLocationRelativeTo(null); // Centrar la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamiento al cerrar
        setResizable(false); // No permitir redimensionar la ventana

        add(Principal); // Agregar el panel principal a la ventana
        Detalles(); // Configurar detalles de la interfaz
        eventos(); // Configurar eventos de los componentes
        cargarDatosDesdeBaseDeDatos(); // Cargar datos de la base de datos
    } // Fin del constructor

    // Método para configurar los detalles de la interfaz
    public void Detalles() {
        Datos.setLayout(null);
        Tabla.setLayout(null);
        Principal.setLayout(null);
        Principal.setBackground(Color.red);
        Principal.add(Datos);
        Principal.add(Tabla);

        Datos.setBounds(15, 0, 960, 330);
        Datos.setBackground(colorPanel);

        // Agregar componentes al panel de datos
        Datos.add(Cedula);
        Datos.add(TCedula);
        Datos.add(Nombre1);
        Datos.add(TNombre1);
        Datos.add(Nombre2);
        Datos.add(TNombre2);
        Datos.add(Apellido1);
        Datos.add(TApellido1);
        Datos.add(Apellido2);
        Datos.add(TApellido2);
        Datos.add(Login);
        Datos.add(TUsuario);
        Datos.add(Contraseña);
        Datos.add(TContraseña);
        Datos.add(Agregar);
        Datos.add(Actualizar);
        Datos.add(Eliminar);
        Datos.add(Regresar);

        // Configuración del panel de la tabla
        JScrollPane scrollPane = new JScrollPane(tabla);
        Tabla.add(scrollPane);
        Tabla.setBounds(15, 360, 960, 300);
        Tabla.setBackground(colorPanel);
        scrollPane.setBounds(0, 0, 960, 250);

        // Posicionamiento y configuración de los campos y etiquetas
        Cedula.setBounds(10, 10, 100, 30);
        TCedula.setBounds(140, 10, 200, 30);
        TCedula.setBorder(new Borde(15));
        TCedula.setOpaque(false);
        Nombre1.setBounds(10, 50, 100, 30);
        TNombre1.setBounds(140, 50, 200, 30);
        TNombre1.setBorder(new Borde(15));
        TNombre1.setOpaque(false);
        Nombre2.setBounds(10, 90, 120, 30);
        TNombre2.setBounds(140, 90, 200, 30);
        TNombre2.setBorder(new Borde(15));
        TNombre2.setOpaque(false);
        Apellido1.setBounds(10, 130, 100, 30);
        TApellido1.setBounds(140, 130, 200, 30);
        TApellido1.setBorder(new Borde(15));
        TApellido1.setOpaque(false);
        Apellido2.setBounds(10, 170, 120, 30);
        TApellido2.setBounds(140, 170, 200, 30);
        TApellido2.setBorder(new Borde(15));
        TApellido2.setOpaque(false);
        Login.setBounds(10, 210, 100, 30);
        TUsuario.setBounds(140, 210, 200, 30);
        TUsuario.setBorder(new Borde(15));
        TUsuario.setOpaque(false);
        Contraseña.setBounds(10, 250, 100, 30);
        TContraseña.setBounds(140, 250, 200, 30);
        TContraseña.setBorder(new Borde(15));
        TContraseña.setOpaque(false);

        // Configuración de los botones
        Agregar.setBounds(400, 290, 100, 30);
        Agregar.setContentAreaFilled(false);
        Agregar.setBackground(Color.white);
        Actualizar.setBounds(515, 290, 100, 30);
        Actualizar.setContentAreaFilled(false);
        Actualizar.setBackground(Color.white);
        Eliminar.setBounds(630, 290, 100, 30);
        Eliminar.setContentAreaFilled(false);
        Eliminar.setBackground(Color.white);
        Regresar.setBounds(745, 290, 100, 30);
        Regresar.setContentAreaFilled(false);
        Regresar.setBackground(Color.white);
    } // Fin del método Detalles

    // Método para cargar datos desde la base de datos
    public void cargarDatosDesdeBaseDeDatos() {
        try {
            // Establecer conexión
            POO.Conexion objetoconexion = new POO.Conexion();
            Connection con = (Connection) objetoconexion.EstablecerConexion();

            // Ejecutar consulta SQL
            String consulta = "call SeleccionarUsuario();";
            PreparedStatement ps = (PreparedStatement) con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            // Llenar el DefaultTableModel
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0); // Limpiar el modelo para evitar duplicados

            while (rs.next()) {
                Object[] fila = new Object[7]; // Ajusta el tamaño del arreglo según el número de columnas
                fila[0] = rs.getString("Cedula");
                fila[1] = rs.getString("nombre1");
                fila[2] = rs.getString("nombre2");
                fila[3] = rs.getString("apellido1");
                fila[4] = rs.getString("apellido2");
                fila[5] = rs.getString("Login");
                fila[6] = rs.getString("clave");
                modelo.addRow(fila);
            } // Fin del while

            // Asignar el modelo a la JTable
            tabla.setModel(modelo);

        } catch (Exception e) {
            e.printStackTrace();
        } // Fin del catch
    } // Fin del método cargarDatosDesdeBaseDeDatos

    // Método para configurar los eventos de los componentes
    public void eventos() {
        // Evento para agregar usuario
        Agregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AgregarUsuario(TCedula, TNombre1, TNombre2, TApellido1, TApellido2, TUsuario, TContraseña);

                String[] datos = {
                    TCedula.getText(),
                    TNombre1.getText(),
                    TNombre2.getText(),
                    TApellido1.getText(),
                    TApellido2.getText(),
                    TUsuario.getText(),
                    TContraseña.getText()
                };
                modelo.addRow(datos);
                clearTextFields();
            } // Fin del actionPerformed
        });

        // Evento para actualizar usuario
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabla.getSelectedRow();

                if (selectedRow >= 0) {
                    ActualizarUsuario(TCedula, TNombre1, TNombre2, TApellido1, TApellido2, TUsuario, TContraseña);
                    modelo.setValueAt(TCedula.getText(), selectedRow, 0);
                    modelo.setValueAt(TNombre1.getText(), selectedRow, 1);
                    modelo.setValueAt(TNombre2.getText(), selectedRow, 2);
                    modelo.setValueAt(TApellido1.getText(), selectedRow, 3);
                    modelo.setValueAt(TApellido2.getText(), selectedRow, 4);
                    modelo.setValueAt(TUsuario.getText(), selectedRow, 5);
                    modelo.setValueAt(TContraseña.getText(), selectedRow, 6);
                    clearTextFields();
                } // Fin del if
            } // Fin del actionPerformed
        });

        // Evento para eliminar usuario
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabla.getSelectedRow();

                if (selectedRow >= 0) {
                    EliminarUsuario(TCedula);
                    modelo.removeRow(selectedRow);
                    clearTextFields();
                } // Fin del if
            }
        });

        // Evento para regresar al login
        Regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login ventana = new Login();
                ventana.setBounds(0, 0, 800, 600);
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);
            } // Fin del actionPerformed
        });

        // Evento para seleccionar una fila de la tabla
        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tabla.getSelectedRow();
                if (selectedRow >= 0) {
                    TCedula.setText(modelo.getValueAt(selectedRow, 0).toString());
                    TNombre1.setText(modelo.getValueAt(selectedRow, 1).toString());
                    TNombre2.setText(modelo.getValueAt(selectedRow, 2).toString());
                    TApellido1.setText(modelo.getValueAt(selectedRow, 3).toString());
                    TApellido2.setText(modelo.getValueAt(selectedRow, 4).toString());
                    TUsuario.setText(modelo.getValueAt(selectedRow, 5).toString());
                    TContraseña.setText(modelo.getValueAt(selectedRow, 6).toString());
                } // Fin del if
            }
        });
    } // Fin del método eventos

    // Método para limpiar los campos de texto
    private void clearTextFields() {
        TUsuario.setText("");
        TContraseña.setText("");
        TNombre1.setText("");
        TNombre2.setText("");
        TApellido1.setText("");
        TApellido2.setText("");
        TCedula.setText("");
    } // Fin del método clearTextFields

    // Método para agregar un usuario
    public void AgregarUsuario(JTextField TCedula, JTextField TNombre1, JTextField TNombre2, JTextField TApellido1, JTextField TApellido2, JTextField TUsuario, JTextField TContraseña) {
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "call Ingresarusuario(?,?,?,?,?,?,?);";

        try {
            // Llamada al procedimiento almacenado
            CallableStatement call = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta);// Llamada al procedimiento almacenado
            call.setString(1, TCedula.getText());
            call.setString(2, TNombre1.getText());
            call.setString(3, TNombre2.getText());
            call.setString(4, TApellido1.getText());
            call.setString(5, TApellido2.getText());
            call.setString(6, TUsuario.getText());
            call.setString(7, TContraseña.getText());
            call.execute();// Ejecución del procedimiento almacenado

            JOptionPane.showMessageDialog(null, "Usuario agregado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar usuario: " + e.toString());
        }
    } // Fin del método AgregarUsuario

    // Método para actualizar un usuario
    public void ActualizarUsuario(JTextField TCedula, JTextField TNombre1, JTextField TNombre2, JTextField TApellido1, JTextField TApellido2, JTextField TUsuario, JTextField TContraseña) {
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "CALL ActualizarUsuario(?, ?, ?, ?, ?, ?, ?);";

        try {
            // Llamada al procedimiento almacenado
            CallableStatement call = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta);
            call.setString(1, TCedula.getText());
            call.setString(2, TNombre1.getText());
            call.setString(3, TNombre2.getText());
            call.setString(4, TApellido1.getText());
            call.setString(5, TApellido2.getText());
            call.setString(6, TUsuario.getText());
            call.setString(7, TContraseña.getText());
            call.execute();

            JOptionPane.showMessageDialog(null, "Usuario actualizado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar usuario: " + e.toString());
        }
    } // Fin del método ActualizarUsuario

    // Método para eliminar un usuario
    public void EliminarUsuario(JTextField TCedula) {
        POO.Conexion objetoconexion = new POO.Conexion();
        String consulta = "call EliminarUsuario(?);";

        try {
            CallableStatement cs = (CallableStatement) objetoconexion.EstablecerConexion().prepareCall(consulta);
            cs.setString(1, TCedula.getText());
            cs.execute();
            JOptionPane.showMessageDialog(null, "Usuario eliminado");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar usuario: " + e.toString());
        }
    } // Fin del método EliminarUsuario

} // Fin de la clase AdminPanel