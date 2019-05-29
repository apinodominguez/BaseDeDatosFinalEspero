package COnsultas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    
    static Connection con = null;

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        Conexion.con = con;
    }
    
    

   public static Connection connect(String ruta) {
        Connection conn = null;
        try {
            String url = ("jdbc:sqlite:" +ruta + ".db");
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            setCon(conn);
        } catch (SQLException e) {
            
        }
        return conn;
    }
   
        public static boolean crearConexion(String ruta) {
        Connection conn = null;
        boolean conectado = false;
        try {
            String url = ("jdbc:sqlite:" +ruta + ".db");
            conn = DriverManager.getConnection(url);
            conectado =true;
            setCon(conn);
        } catch (SQLException e) {
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conectado;
    }
    
    public static boolean cerrarConexion(){
        Connection con = getCon();
        boolean conectado = true;
       try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                conectado = false;
            }
       return conectado;
        }
    
        
        
    public static boolean crearNuevaTabla(String ruta) {
        Connection conectar = connect(ruta);
        boolean cierto = false;
        String url = ("jdbc:sqlite:" + ruta + ".db");

        String sql = "DROP TABLE IF EXISTS autores";

        String sql2 = "CREATE TABLE autores (\n" +
        "    ID     INTEGER     PRIMARY KEY AUTOINCREMENT,\n" +
        "    nombre STRING (25) \n" +
        ");";

        String sql3 = "DROP TABLE IF EXISTS alumnos";

        String sql4 = "CREATE TABLE libros (\n" +
        "    ISBN    INTEGER     PRIMARY KEY AUTOINCREMENT,\n" +
        "    titulo  STRING (25),\n" +
        "    autorid INTEGER     REFERENCES autores (ID) \n" +
        ");";

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                stmt.execute(sql2);
                stmt.execute(sql3);
                stmt.execute(sql4);
                cierto = true;
        } catch (SQLException e) {
        }finally {
            try {
                if (conectar != null) {
                    connect(ruta).close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        return true;
    }
    }




}
    


