package COnsultas;

import Interfaces.Principal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Metodos {

    Principal objP = new Principal();
    
     public static int añadirAutorYTitulo(String autor, String titulo, String ruta) {
        String sql = "INSERT INTO autores(nombre) VALUES(?)";
        String sql3 = "INSERT INTO libros(titulo,autorid) VALUES (?,(SELECT ID FROM autores where nombre = ?))";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql3)) {
            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 3;System.out.println("3");
            System.out.println(e.getMessage());
        }
        
        return fallos;
    }
     
    public static int añadirLibro(String titulo, String ruta) {
        String sql = "INSERT INTO libros(titulo) VALUES(?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos;
     } 

    public static int añadirAutor(String autor, String ruta) {
        String sql = "INSERT INTO autores(nombre) VALUES(?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos;
     }
    
    public static int borrarISBN(int isbn, String ruta) {
        String sql = "DELETE FROM libros WHERE ISBN = (?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos;
    }
    
    public static int borrarAutor(String autor, String ruta) {
        String sql = "DELETE FROM autores WHERE nombre = (?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos;
    }
    
    public static int actualizarLibros(String libro, int isbn, String ruta){
        String sql = "UPDATE libros SET titulo = (?) WHERE ISBN = (?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, libro);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos; 
    }
    
    public static int actualizarAutor(String autor, int isbn, String ruta){
        String sql = "UPDATE autores SET autor = (?) WHERE ID = (SELECT autorid FROM libros where ISBN = ?)";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, autor);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        return fallos; 
    }
    
    public static int actualizarAutorYTitulo(String titulo, String autor,int isbn, String ruta) {
        String sql = "UPDATE libros SET titulo = (?) WHERE ISBN = (?)";
        String sql3 = "INSERT INTO libros(titulo,autorid) VALUES (?,(SELECT ID FROM autores where nombre = ?))";
        int autorid = 0;
        int fallos = 0;
        
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titulo);
            pstmt.setInt(2,isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 1;System.out.println("1");
            System.out.println(e.getMessage());
        }
        try (Connection conn = Conexion.connect(ruta);
                PreparedStatement pstmt = conn.prepareStatement(sql3)) {
            pstmt.setString(1, autor);
            pstmt.setInt(2, isbn);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            fallos = 3;System.out.println("3");
            System.out.println(e.getMessage());
        }
        
        return fallos;
    }
    
    
    
    
    
    public static int contarISBN(int ISBN, String ruta){
        String sql = "Select count(ISBN) from libros where ISBN= (?)";    
        int cuenta= 0;
        try (Connection conn = Conexion.connect(ruta);
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setInt(1,ISBN);
            //
            ResultSet rs  = pstmt.executeQuery();
            
           cuenta = rs.getInt(1);
        } catch (SQLException e) {
            
        }
        return cuenta;
    }

    public static int contarAutor(String autor, String ruta){
        String sql = "Select count(nombre) from autores where nombre= (?)";    
        int cuenta= 0;
        try (Connection conn = Conexion.connect(ruta);
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,autor);
            //
            ResultSet rs  = pstmt.executeQuery();
            
           cuenta = rs.getInt(1);
        } catch (SQLException e) {
            
        }
        return cuenta;
    }
    
     public static int contarLibro(String libro, String ruta){
        String sql = "Select count(titulo) from libros where titulo= (?)";    
        int cuenta= 0;
        try (Connection conn = Conexion.connect(ruta);
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,libro);
            //
            ResultSet rs  = pstmt.executeQuery();
            
           cuenta = rs.getInt(1);
        } catch (SQLException e) {
            
        }
        return cuenta;
    }
    
    public void selectAll(String ruta){
        String sql = "SELECT isbn, titulo, nombre FROM libros INNER JOIN autor ON autor.id = libros.idautor";
        
        try (Connection conn = Conexion.connect(ruta);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
                Object[] row = { rs.getInt("isbn"), rs.getString("titulo"), rs.getString("nombre")};
                 objP.crearLinea(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } 
     
     
     
     
     
}
