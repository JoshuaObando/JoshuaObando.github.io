package POO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Pagos {

    private String login;

    public Pagos(String login) {
        this.login = login;
    }

    public void realizarPago(double montoEfectivo, double montoTarjeta) {
        insertarOActualizarTotales(montoEfectivo, montoTarjeta);
    }

    private void insertarOActualizarTotales(double montoEfectivo, double montoTarjeta) {
        Conexion objetoconexion = new Conexion();
        Connection con = null;
        PreparedStatement psSelect = null;
        PreparedStatement psInsert = null;
        PreparedStatement psUpdate = null;
        ResultSet rs = null;

        String consultaSelect = "SELECT * FROM totales WHERE UsuarioChofer = ?";
        String consultaInsert = "INSERT INTO totales (UsuarioChofer, monto_efectivo, monto_tarjeta) VALUES (?, ?, ?)";
        String consultaUpdate = "UPDATE totales SET monto_efectivo = monto_efectivo + ?, monto_tarjeta = monto_tarjeta + ? WHERE UsuarioChofer = ?";

        try {
            con = objetoconexion.EstablecerConexion();
            psSelect = con.prepareStatement(consultaSelect);
            psSelect.setString(1, login);
            rs = psSelect.executeQuery();

            if (rs.next()) {
                // El registro existe, actualiza
                psUpdate = con.prepareStatement(consultaUpdate);
                psUpdate.setBigDecimal(1, BigDecimal.valueOf(montoEfectivo));
                psUpdate.setBigDecimal(2, BigDecimal.valueOf(montoTarjeta));
                psUpdate.setString(3, login);
                psUpdate.executeUpdate();
            } else {
                // El registro no existe, inserta
                psInsert = con.prepareStatement(consultaInsert);
                psInsert.setString(1, login);
                psInsert.setBigDecimal(2, BigDecimal.valueOf(montoEfectivo));
                psInsert.setBigDecimal(3, BigDecimal.valueOf(montoTarjeta));
                psInsert.executeUpdate();
            }

            System.out.println("Totales actualizados correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar los totales: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cerrarRecursos(psSelect, psInsert, psUpdate, rs, con);
        }
    }

    private void cerrarRecursos(PreparedStatement psSelect, PreparedStatement psInsert, PreparedStatement psUpdate, ResultSet rs, Connection con) {
        try {
            if (rs != null) rs.close();
            if (psSelect != null) psSelect.close();
            if (psInsert != null) psInsert.close();
            if (psUpdate != null) psUpdate.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}//