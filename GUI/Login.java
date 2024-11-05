package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    // Atributos de la clase
    private JTextField tfUsuario; // Campo de texto para el nombre de usuario
    private JPasswordField pfContraseña; // Campo de texto para la contraseña
    private JLabel Lbusuario, LbContraseña; // Etiquetas para los campos de texto
    private JButton btnIniciar, btnCancelar, Creditos; // Botones de la interfaz
    JPanel panel = new PanelConFondo("/Imagenes/Fondo3.jpg"); // Panel con fondo personalizado

    // Constructor de la clase
    public Login() {
        setTitle("Ventana de inicio de sesión"); // Título de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Comportamiento al cerrar la ventana

        add(panel); // Agregar el panel principal a la ventana
        panel.setLayout(null); // No usar layout manager para posicionar componentes manualmente
        eventos(); // Configurar eventos de los componentes
    } // Fin del constructor

    // Método para configurar los eventos y agregar los componentes al panel
    public void eventos() {
        // Configuración de la etiqueta y campo de texto para el nombre de usuario
        Lbusuario = new JLabel("Ingrese el nombre usuario:");
        Lbusuario.setBounds(240, 130, 200, 35);
        Lbusuario.setForeground(Color.WHITE); // Color de texto
        panel.add(Lbusuario);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(240, 160, 300, 35);
        panel.add(tfUsuario);

        // Configuración de la etiqueta y campo de texto para la contraseña
        LbContraseña = new JLabel("Ingrese la contraseña:");
        LbContraseña.setBounds(240, 210, 200, 35);
        LbContraseña.setForeground(Color.WHITE); // Color de texto
        panel.add(LbContraseña);

        pfContraseña = new JPasswordField();
        pfContraseña.setBounds(240, 240, 300, 35);
        panel.add(pfContraseña);

        // Configuración del botón para iniciar sesión
        btnIniciar = new JButton("Iniciar sesión");
        btnIniciar.setBounds(240, 300, 150, 35);
        panel.add(btnIniciar);

        // Configuración del botón para cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(390, 300, 150, 35);
        panel.add(btnCancelar);

        // Configuración del botón para mostrar los créditos
        Creditos = new JButton("Derechos de autor");
        Creditos.setBounds(610, 500, 150, 35);
        panel.add(Creditos);

        // Evento del botón Iniciar sesión
        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                POO.CLogin objetLogin = new POO.CLogin();
                objetLogin.Validacion(tfUsuario, pfContraseña); // Llamada a la validación del login
                dispose(); // Cerrar la ventana de login
            }
        });

        // Evento del botón Derechos de autor
        Creditos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Derechos ventana = new Derechos(); // Crear una nueva ventana de créditos
                ventana.setVisible(true); // Mostrar la ventana de créditos
                dispose(); // Cerrar la ventana de login
            }// Fin del actionPerformed
        });
    } // Fin del método eventos

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        Login ventana = new Login(); // Crear una instancia de la ventana de login
        ventana.setBounds(0, 0, 800, 600); // Establecer tamaño de la ventana
        ventana.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        ventana.setVisible(true); // Hacer visible la ventana
    } // Fin del main
} // Fin de la clase