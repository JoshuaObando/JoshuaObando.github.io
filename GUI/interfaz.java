package GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class interfaz extends JFrame {
    JPanel principal;
    JButton SanRoque, UCR, ElPulmon, AeroPuerto, Reporte;
    Color nuevo = new Color(21, 85, 223);
    Color Boton = new Color(15, 60, 157);
    JLabel Titulo;
    JButton Regresar = new JButton("Regresar");
   
    private String nombreUsuario;

    // Creación de atributos y objetos
    public interfaz(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        principal = new JPanel();
        SanRoque = new JButton();
        UCR = new JButton();
        ElPulmon = new JButton();
        AeroPuerto = new JButton();
        Titulo = new JLabel();
        Reporte = new JButton();

        // Inicialización de botones y etc
        add(principal);
        principal.add(SanRoque);
        principal.add(UCR);
        principal.add(ElPulmon);
        principal.add(AeroPuerto);
        principal.add(Titulo);
        principal.add(Reporte);
        principal.add(Regresar);
        principal.setLayout(null);
        // esta sección de arriba sirve para agregar botones o paneles

        Jframe();
        Detalles();
        Eventos();
        // Llamado de métodos
    }// Constructor

    public void Jframe() {
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }// Este método sirve para darle detalles al JFrame

    public void Detalles() {
        principal.setBackground(nuevo);
        SanRoque.setBounds(50, 100, 400, 200);
        SanRoque.setText("San Roque");
        SanRoque.setFocusPainted(false);
        SanRoque.setBorderPainted(false);
        SanRoque.setBackground(Boton);
        SanRoque.setForeground(Color.WHITE);

        Reporte.setBounds(800, 10, 150, 50);
        Reporte.setText("Reporte del día");
        Reporte.setBackground(Boton);
        Reporte.setForeground(Color.WHITE);
        Reporte.setFocusPainted(false);
        Reporte.setBorderPainted(false);

        UCR.setBounds(550, 100, 400, 200);
        UCR.setText("UCR");
        UCR.setFocusPainted(false);
        UCR.setBorderPainted(false);
        UCR.setBackground(Boton);
        UCR.setForeground(Color.WHITE);

        ElPulmon.setBounds(50, 400, 400, 200);
        ElPulmon.setText("El Pulmón");
        ElPulmon.setFocusPainted(false);
        ElPulmon.setBorderPainted(false);
        ElPulmon.setBackground(Boton);
        ElPulmon.setForeground(Color.WHITE);

        AeroPuerto.setBounds(550, 400, 400, 200);
        AeroPuerto.setText("Aeropuerto");
        AeroPuerto.setFocusPainted(false);
        AeroPuerto.setBorderPainted(false);
        AeroPuerto.setBackground(Boton);
        AeroPuerto.setForeground(Color.WHITE);

        Titulo.setBounds(300, 10, 600, 50);
        Titulo.setText("Bienvenido al Sistema de pago rápido");
        Titulo.setFont(new Font("Arial", Font.PLAIN, 25));// este método sirve para poner fuente y tamaño de letra
        Titulo.setForeground(Color.WHITE);

        Regresar.setBounds(400, 400, 200, 70);
        Regresar.setFocusPainted(false);
        Regresar.setBorderPainted(false);
        Regresar.setBackground(Boton);
        Regresar.setForeground(Color.WHITE);
    }// este método sirve para dar tamaño y cual detalle a los atributos del GUI

    public void NuevoPanel(JPanel panel) {
        panel.setSize(1000, 700);
        panel.setLocation(0, 0);
        principal.removeAll();
        principal.add(panel, BorderLayout.CENTER);
        principal.repaint();
        principal.revalidate();
    }// método para intercambiar paneles

    public void Eventos() {
        SanRoque.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevoPanel(new Sanroque(nombreUsuario, interfaz.this));
            }
        });// Esta acción es del botón San Roque

        UCR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevoPanel(new Ucr(nombreUsuario, interfaz.this));
            }
        });// Esta acción es del botón UCR

        ElPulmon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevoPanel(new Pulmon(nombreUsuario, interfaz.this));
            }
        });// Esta acción es del botón ElPulmon

        AeroPuerto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevoPanel(new Aero(nombreUsuario, interfaz.this));
            }
        });// Esta acción es del botón AeroPuerto

        Reporte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NuevoPanel(new Reporte(nombreUsuario));
            }
        });// Esta acción es del botón Reporte para saber el reporte del conductor durante el día

        Regresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login v = new Login();
                v.setVisible(true);
                v.setBounds(0, 0, 800, 600);
                v.setLocationRelativeTo(null);
                dispose();
            }
        });
    }// Este método sirve para agregar eventos a los botones

    public void InsertarSanRoque() {
    }

    public void InsertarPulmon() {
    }

    public void InsertarAero() {
    }

    // Getter para nombreUsuario
    public String getNombreUsuario() {
        return nombreUsuario;
    }
}// Clase interfaz