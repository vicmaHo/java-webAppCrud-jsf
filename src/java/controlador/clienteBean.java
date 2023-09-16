package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.inject.Named;


import modelo.Conexion;


/**
 *
 * @author Juan Carlos Gonzalez
 */

@Named(value = "clienteBean")
@RequestScoped
public class clienteBean {
    /**
     * Creates a new instance of clienteBean
     */
    public clienteBean() {
    }
    
    private int idcliente;
    private String nombrecliente;
    private String apellidocliente;
    private String direccioncliente;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getApellidocliente() {
        return apellidocliente;
    }

    public void setApellidocliente(String apellidocliente) {
        this.apellidocliente = apellidocliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }
    
    public List<clienteBean> getTablaClientes() {
        List<clienteBean> data = new ArrayList<>();

        Conexion con = new Conexion();

        try {
            Connection conexion = con.getConnection();
            Statement sql = conexion.createStatement();
            ResultSet rs = sql.executeQuery("select * from cliente");

            while (rs.next()) {
                clienteBean obj = new clienteBean();
                obj.setIdcliente(rs.getInt("idcliente"));
                obj.setNombrecliente(rs.getString("nombrecliente"));
                obj.setApellidocliente(rs.getString("apellidocliente"));
                obj.setDireccioncliente(rs.getString("direccioncliente"));

                data.add(obj);
                
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println("error al leer los datos" + e);
        }

        return data;
    }
    
    public void agregarCliente() {
        Conexion conex = new Conexion();

        try {
            Connection con = conex.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("INSERT INTO cliente (nombrecliente,apellidocliente,direccioncliente) values(?,?,?)");
            ps.setString(1, nombrecliente);
            ps.setString(2, apellidocliente);
            ps.setString(3, direccioncliente);
            ps.executeUpdate();
            
            
            System.out.println("se agrego correctamente");
            
        } catch (Exception e) {
            System.out.println("error al agregar el cliente"+ e);
        }
    }
    
    public void modificarCliente() {
        Conexion conex = new Conexion();

        try {
            Connection con = conex.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("UPDATE cliente SET nombrecliente=?, apellidocliente=?,direccioncliente=? where idcliente=?");
            ps.setString(1, nombrecliente);
            ps.setString(2, apellidocliente);
            ps.setString(3, direccioncliente);
            ps.setInt(4,idcliente);//al ser el ultimo datos de la consulta se pasa de ultimo
            ps.executeUpdate();
            
            System.out.println("se modifico correctamente");
            
        } catch (Exception e) {
            System.out.println("error al agregar el cliente"+ e);
        }
    }
    
    public void eliminarCliente() {
        Conexion conex = new Conexion();

        try {
            Connection con = conex.getConnection();
            PreparedStatement ps;
            ps = con.prepareStatement("delete from cliente where idcliente=?");
            ps.setInt(1,idcliente);//al ser el ultimo datos de la consulta se pasa de ultimo
            ps.executeUpdate();
            
            System.out.println("se elimino correctamente");
            
        } catch (Exception e) {
            System.out.println("error al eliminar el cliente"+ e);
        }
    }

}
