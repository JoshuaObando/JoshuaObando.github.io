package GUI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import POO.Conexion;

public class Reporte extends JPanel {
    // Definición de colores y componentes de la interfaz
    Color nuevo = new Color(21, 85, 223); // Color de fondo del panel
    Color Boton = new Color(15, 60, 157); // Color de fondo de los botones
    JLabel Titulo; // Etiqueta para el título del reporte
    JTextArea Dato; // Área de texto para mostrar los datos del reporte
    JButton Salir; // Botón para salir del sistema
    private String nombreUsuario; // Nombre del usuario actual

    // Constructor de la clase
    public Reporte(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario; // Asignar el nombre de usuario
        Titulo = new JLabel(); // Inicializar la etiqueta del título
        Dato = new JTextArea(); // Inicializar el área de texto para los datos
        Salir = new JButton(); // Inicializar el botón de salir

        setSize(1000, 700); // Tamaño del panel
        setLayout(null); // No usar layout manager para posicionar componentes manualmente
        setBackground(nuevo); // Establecer el color de fondo del panel
        add(Titulo); // Agregar la etiqueta del título al panel
        add(Dato); // Agregar el área de texto al panel
        add(Salir); // Agregar el botón de salir al panel

        detalles(); // Configurar detalles de los componentes
        eventos(); // Configurar eventos de los componentes
        cargarDatosDesdeBaseDeDatos(); // Cargar los datos del reporte desde la base de datos
    } // Fin del constructor

    // Método para configurar los detalles de los componentes
    public void detalles() {
        Titulo.setBounds(380, 10, 600, 50); // Posición y tamaño del título
        Titulo.setText("Reporte Final del día"); // Texto del título
        Titulo.setFont(new Font("Arial", Font.PLAIN, 25)); // Fuente y tamaño del texto del título
        Titulo.setForeground(Color.WHITE); // Color del texto del título

        Dato.setBounds(10, 100, 970, 200); // Posición y tamaño del área de texto
        Dato.setEditable(false); // Hacer que el área de texto no sea editable
        Dato.setBackground(Boton); // Establecer el color de fondo del área de texto
        Dato.setForeground(Color.WHITE); // Establecer el color del texto del área de texto

        Salir.setBounds(400, 400, 200, 70); // Posición y tamaño del botón de salir
        Salir.setText("Salir del sistema"); // Texto del botón de salir
        Salir.setFocusPainted(false); // Quitar el enfoque pintado del botón
        Salir.setBorderPainted(false); // Quitar el borde pintado del botón
        Salir.setBackground(Boton); // Establecer el color de fondo del botón
        Salir.setForeground(Color.WHITE); // Establecer el color del texto del botón
    } // Fin del método detalles

    // Método para configurar los eventos de los componentes
    public void eventos() {
        // Evento del botón Salir
        Salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Cerrar la aplicación
            }
        });
    } // Fin del método eventos

    // Método para cargar los datos del reporte desde la base de datos
    public void cargarDatosDesdeBaseDeDatos() {
        Connection con = null; // Conexión a la base de datos
        PreparedStatement ps = null; // PreparedStatement para las consultas SQL
        ResultSet rs = null; // ResultSet para almacenar los resultados de la consulta
        StringBuilder reporte = new StringBuilder(); // StringBuilder para construir el reporte

        try {
            Conexion objetoconexion = new Conexion(); // Crear una instancia de la clase Conexion
            con = objetoconexion.EstablecerConexion(); // Establecer la conexión con la base de datos

            // Consulta SQL para obtener los datos del reporte
            String consulta = "SELECT UsuarioChofer, monto_efectivo, monto_tarjeta, total FROM totales WHERE UsuarioChofer = ?";
            ps = con.prepareStatement(consulta);
            ps.setString(1, nombreUsuario); // Establecer el parámetro de la consulta
            rs = ps.executeQuery(); // Ejecutar la consulta

            // Construir el reporte a partir de los resultados de la consulta
            if (rs.next()) {
                String chofer = rs.getString("UsuarioChofer");
                double totalEfectivo = rs.getDouble("monto_efectivo");
                double totalTarjeta = rs.getDouble("monto_tarjeta");
                double totalDia = rs.getDouble("total");

                reporte.append("Chofer: ").append(chofer)
                        .append("\nTotal en efectivo: ").append(totalEfectivo)
                        .append("\nTotal en tarjeta: ").append(totalTarjeta)
                        .append("\nTotal del día: ").append(totalDia)
                        .append("\n\n");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar datos de la base de datos: " + e.toString());
        } finally {
            // Cerrar los recursos en el bloque finally
            try {
                if (rs != null) rs.close(); // Cerrar ResultSet
                if (ps != null) ps.close(); // Cerrar PreparedStatement
                if (con != null) con.close(); // Cerrar Connection
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        }

        Dato.setText(reporte.toString()); // Establecer el texto del área de texto con el reporte
    } // Fin del método cargarDatosDesdeBaseDeDatos
} // Fin de la clase Reporte