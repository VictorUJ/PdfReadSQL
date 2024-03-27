package conexionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {

    public Conexion(String tipoLibro, int pagina, String texto){
        String URL = "jdbc:mysql://localhost:3306/Colection?serverTimezone=UTC";
        String USER = "prueba";
        String PASSWORD = "1234";

        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
            PreparedStatement insert = connection.prepareStatement("INSERT INTO Colection.Libro(tipo_libro,pagina,texto) VALUES('" + tipoLibro + "', " + pagina + ", '" + texto + "')");

            try{
                insert.executeUpdate();
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                connection.close();
                insert.close();

            }
            connection.close();//Cerrar coneccion
            insert.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
        finally {

        }
    }
}
