
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    static private final String URL = "jdbc:mysql://localhost:3306/contactos?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    static private final String USER = "root";
    static private final String PASS = "admin";
    
    
    
    public Connection getConnection(){
        Connection conn = null;
       
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println("Error al realizar la conexion a la base de datos: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
        
    }
}
