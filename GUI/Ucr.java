package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import POO.Pagos;

public class Ucr extends JPanel {

    private interfaz inter;
    JButton Efectivo, PagoFacil;
    Color nuevo = new Color(21, 85, 223);
    Color Boton = new Color(15, 60, 157);
    private Pagos pagos;
    private String nombreUsuario;

    public Ucr(String nombreUsuario, interfaz inter) {
        this.inter = inter;
        this.nombreUsuario = nombreUsuario;
        pagos = new Pagos(nombreUsuario);
        Efectivo = new JButton();
        PagoFacil = new JButton();

        setSize(1000, 700);
        setLayout(null);
        add(Efectivo);
        add(PagoFacil);
        setBackground(nuevo);

        detalles();
        eventos();
    }

    public void detalles() {
        Efectivo.setBounds(150, 300, 300, 50);
        Efectivo.setText("EFECTIVO");
        Efectivo.setFocusPainted(false);
        Efectivo.setBorderPainted(false);
        Efectivo.setBackground(Boton);
        Efectivo.setForeground(Color.WHITE);

        PagoFacil.setBounds(550, 300, 300, 50);
        PagoFacil.setText("PAGO FÁCIL");
        PagoFacil.setFocusPainted(false);
        PagoFacil.setBorderPainted(false);
        PagoFacil.setBackground(Boton);
        PagoFacil.setForeground(Color.WHITE);
    }

    public void NuevoPanel(JPanel panel) {
        panel.setSize(1000, 700);
        panel.setLocation(0, 0);
        removeAll();
        add(panel, BorderLayout.CENTER);
        repaint();
        revalidate();
    }

    public void eventos() {
        Efectivo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pagos.realizarPago(500, 0); // Pago en efectivo
                JOptionPane.showMessageDialog(null, "El pasaje es de 500 colones\nPor favor pagar al chofer");
                inter.NuevoPanel(new interfaz(inter.getNombreUsuario()).principal);
            }
        });

        PagoFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pagos.realizarPago(0, 500); // Pago con tarjeta
                JOptionPane.showMessageDialog(null, "El Pago se ha realizado correctamente");
                inter.NuevoPanel(new interfaz(inter.getNombreUsuario()).principal);
            }
        });
    }
}