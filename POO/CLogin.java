package POO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import GUI.AdminPanel;
import GUI.interfaz;

public class CLogin {

    private interfaz inter; // Referencia a la interfaz principal
    private static String choferCedula; // Variable estática para almacenar la cédula del chofer

    // Método para obtener la cédula del chofer
    public static String getChoferCedula() {
        return choferCedula;
    }

    // Método para validar el inicio de sesión
    public void Validacion(JTextField Usuario, JPasswordField contraseña) {
        ResultSet rs = null; // ResultSet para almacenar los resultados de la consulta
        PreparedStatement ps = null; // PreparedStatement para las consultas SQL
        Connection con = null; // Conexión a la base de datos

        try {
            Conexion objetoconexion = new Conexion(); // Crear una instancia de la clase Conexion
            con = objetoconexion.EstablecerConexion(); // Establecer la conexión con la base de datos

            if (con != null) {
                // Consulta para verificar si el usuario es AdminJefe
                String consultaAdmin = "call VerificacionAdmin(?, ?);";
                ps = con.prepareStatement(consultaAdmin);
                
                String user = Usuario.getText(); // Obtener el nombre de usuario del campo de texto
                String contra = new String(contraseña.getPassword()); // Obtener la contraseña del campo de texto

                ps.setString(1, user); // Establecer el primer parámetro de la consulta
                ps.setString(2, contra); // Establecer el segundo parámetro de la consulta

                rs = ps.executeQuery(); // Ejecutar la consulta

                if (rs.next()) {
                    if (user.equals("AdminJefe") && contra.equals("admin123")) {
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema Admin Jefe");
                        AdminPanel ventana = new AdminPanel(); // Crear una nueva ventana de AdminPanel
                        ventana.setVisible(true); // Hacer visible la ventana de AdminPanel
                        ventana.setLocationRelativeTo(null); // Centrar la ventana de AdminPanel
                        return; // Salir del método
                    }
                }

                // Consulta para verificar si el usuario es un chofer
                String consultaUsuarios = "call VerificarUsuario(?,?);";
                ps = con.prepareStatement(consultaUsuarios);

                ps.setString(1, user); // Establecer el primer parámetro de la consulta
                ps.setString(2, contra); // Establecer el segundo parámetro de la consulta

                rs = ps.executeQuery(); // Ejecutar la consulta

                if (rs.next()) {
                    String nombreUsuario = rs.getString("login"); // Obtener el nombre de usuario desde el ResultSet
                    JOptionPane.showMessageDialog(null, "Bienvenido al sistema conductor");
                    interfaz ventana = new interfaz(nombreUsuario); // Crear una nueva ventana de interfaz
                    ventana.setVisible(true); // Hacer visible la ventana de interfaz
                    ventana.setLocationRelativeTo(null); // Centrar la ventana de interfaz
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                }

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo establecer la conexión con la base de datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        } finally {
            // Cerrar los recursos en el bloque finally
            try {
                if (rs != null) rs.close(); // Cerrar ResultSet
                if (ps != null) ps.close(); // Cerrar PreparedStatement
                if (con != null) con.close(); // Cerrar Connection
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.getMessage());
            }
        }
    } // Fin del método Validacion
} // Fin de la clase CLogin