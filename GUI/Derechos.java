package GUI;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Derechos extends JFrame {
    JPanel Principal = new JPanel();
    JLabel Maylo = new JLabel();
    JLabel Joshua = new JLabel();
    JButton volver = new JButton("Regresar");
    public Derechos() {
        setTitle("Derechos de autor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        add(Principal);
        Detalles(); 
    }//Constructor

    public void Detalles() {
        Principal.setLayout(null);
        Principal.add(Maylo);
        Maylo.setBounds(60, 50, 300, 300);

        ImageIcon Imagen = new ImageIcon("Imagenes/Maylo.jpg");
        Image Original = Imagen.getImage();
        Image Modificado = Original.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon ImagenEscalado = new ImageIcon(Modificado);
        Maylo.setIcon(ImagenEscalado);

        JLabel Derechos = new JLabel("<html>FrondEnd:<br>Nombre: Maylo Daring Parra Aguirre<br>Carné: C35880<br>Carrera: Informática Empresarial</html>");
        Principal.add(Derechos);
        Derechos.setBounds(100, 300, 300, 300);

        Principal.add(Joshua);
        Joshua.setBounds(400, 50, 300, 300);

        ImageIcon Imagen1 = new ImageIcon("Imagenes/Joshua Obando.jpeg");
        Image Original1 = Imagen1.getImage();
        Image Modificado1 = Original1.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon ImagenEscalado1 = new ImageIcon(Modificado1);
        Joshua.setIcon(ImagenEscalado1);

        JLabel Derechos2 = new JLabel("<html>BackEnd:<br>Nombre: Joshua David Obando Gonzalez<br>Carné: C35652<br>Carrera: Informática Empresarial</html>");
        Principal.add(Derechos2);
        Derechos2.setBounds(400, 300, 300, 300);

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login v  = new Login();
                v.setVisible(true);
                v.setBounds(0, 0, 800, 600);
                v.setLocationRelativeTo(null);
                dispose();
            }
        });
        
        Principal.add(volver);
        volver.setBounds(600, 500, 150, 40);
        volver.setVisible(true);
    }//Detalles
}//fin de la clase