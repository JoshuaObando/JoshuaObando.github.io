package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import POO.Pagos;

public class Aero extends JPanel {

    // Atributos de la clase
    private interfaz inter; // Referencia a la interfaz principal para la navegación
    JButton Efectivo, PagoFacil; // Botones para los diferentes métodos de pago
    Color nuevo = new Color(21, 85, 223); // Color de fondo del panel
    Color Boton = new Color(15, 60, 157); // Color de fondo de los botones
    private Pagos pagos; // Instancia de la clase Pagos para manejar las transacciones
    private String nombreUsuario; // Nombre del usuario actual

    // Constructor de la clase
    public Aero(String nombreUsuario, interfaz inter) {
        this.inter = inter;
        this.nombreUsuario = nombreUsuario;
        pagos = new Pagos(nombreUsuario);
        Efectivo = new JButton();
        PagoFacil = new JButton();

        setSize(1000, 700); // Tamaño del panel
        setLayout(null); // No usar layout manager para posicionar componentes manualmente
        add(Efectivo); // Agregar botón de pago en efectivo al panel
        add(PagoFacil); // Agregar botón de pago con tarjeta al panel
        setBackground(nuevo); // Establecer el color de fondo del panel

        detalles(); // Configurar detalles de los componentes
        eventos(); // Configurar eventos de los componentes
    } // Fin del constructor

    // Método para configurar los detalles de los componentes
    public void detalles() {
        // Configuración del botón de pago en efectivo
        Efectivo.setBounds(150, 300, 300, 50); // Posición y tamaño
        Efectivo.setText("EFECTIVO"); // Texto del botón
        Efectivo.setFocusPainted(false); // Quitar el enfoque pintado
        Efectivo.setBorderPainted(false); // Quitar el borde pintado
        Efectivo.setBackground(Boton); // Establecer color de fondo
        Efectivo.setForeground(Color.WHITE); // Establecer color de texto

        // Configuración del botón de pago con tarjeta
        PagoFacil.setBounds(550, 300, 300, 50); // Posición y tamaño
        PagoFacil.setText("PAGO FÁCIL"); // Texto del botón
        PagoFacil.setFocusPainted(false); // Quitar el enfoque pintado
        PagoFacil.setBorderPainted(false); // Quitar el borde pintado
        PagoFacil.setBackground(Boton); // Establecer color de fondo
        PagoFacil.setForeground(Color.WHITE); // Establecer color de texto
    } // Fin del método detalles

    // Método para cambiar el panel actual a otro panel
    public void NuevoPanel(JPanel panel) {
        panel.setSize(1000, 700); // Tamaño del nuevo panel
        panel.setLocation(0, 0); // Posición del nuevo panel
        removeAll(); // Quitar todos los componentes del panel actual
        add(panel, BorderLayout.CENTER); // Agregar el nuevo panel
        repaint(); // Repintar el panel
        revalidate(); // Revalidar el panel
    } // Fin del método NuevoPanel

    // Método para configurar los eventos de los componentes
    public void eventos() {
        // Evento del botón de pago en efectivo
        Efectivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pagos.realizarPago(500, 0); // Realizar pago en efectivo
                JOptionPane.showMessageDialog(null, "El pasaje es de 500 colones\nPor favor pagar al chofer"); // Mostrar mensaje de confirmación
                inter.NuevoPanel(new interfaz(inter.getNombreUsuario()).principal); // Cambiar al panel principal
            }
        });

        // Evento del botón de pago con tarjeta
        PagoFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pagos.realizarPago(0, 500); // Realizar pago con tarjeta
                JOptionPane.showMessageDialog(null, "El Pago se ha realizado correctamente"); // Mostrar mensaje de confirmación
                inter.NuevoPanel(new interfaz(inter.getNombreUsuario()).principal); // Cambiar al panel principal
            }
        });
    } // Fin del método eventos
} // Fin de la clase Aero