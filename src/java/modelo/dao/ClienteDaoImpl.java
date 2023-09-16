package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDaoImpl implements ClienteDAO {

    private final String INSERT = "INSERT INTO cliente (nombrecliente, apellidocliente, direccioncliente) VALUES (?, ?, ?);";
    private final String UPDATE = "UPDATE cliente SET nombrecliente = ?, apellidocliente = ?, direccioncliente = ? WHERE idcliente = ?";
    private final String DELETE = "DELETE FROM cliente WHERE idcliente = ?";
    private final String GETALL = "SELECT * FROM cliente;";
    private final String GETONE = "SELECT * FROM cliente WHERE idcliente = ?;";
    private Connection conn = null;

    public ClienteDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertar(Cliente t) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, t.getNombreCliente());
            stat.setString(2, t.getApellidoCliente());
            stat.setString(3, t.getDireccionCliente());
            stat.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modificar(Cliente t) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, t.getNombreCliente());
            stat.setString(2, t.getApellidoCliente());
            stat.setString(3, t.getDireccionCliente());
            stat.setInt(4, t.getIdCliente());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void eliminar(int id) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> listaClientes = new ArrayList<>();
        PreparedStatement stat = null;
        ResultSet resultSet = null;
        try {
            stat = conn.prepareStatement(GETALL);
            resultSet = stat.executeQuery();
            while (resultSet.next()) {
                int idCliente = resultSet.getInt("idcliente");
                String nombreCliente = resultSet.getString("nombrecliente");
                String apellidoCliente = resultSet.getString("apellidocliente");
                String direccionCliente = resultSet.getString("direccioncliente");

                Cliente cliente = new Cliente(idCliente, nombreCliente, apellidoCliente, direccionCliente);
                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de datos de clientes: " + e.getMessage());
        }

        return listaClientes;
    }

    @Override
    public Cliente getById(int id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setInt(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                cliente = new Cliente(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cliente;
    }
}
