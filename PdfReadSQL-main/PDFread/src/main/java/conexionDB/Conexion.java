package conexionDB;

import java.sql.*;

public class Conexion {

    public Conexion(String tipoLibro, int pagina, String texto){
        String URL = "jdbc:mysql://localhost:3306/Colection?serverTimezone=UTC";
        String USER = "prueba";
        String PASSWORD = "1234";

        DataConexion data = new DataConexion();
        data.URL = URL;
        data.USER = USER;
        data.PASSWORD = PASSWORD;
        data.tipoLibro = tipoLibro;
        data.pagina = pagina;
        data.texto = texto;

        //Si el SELECT != null
        insert(data);
        //NO --- UPDATE registro


    }

    public void insert(DataConexion data){
        try{
            Connection connection = DriverManager.getConnection(data.URL,data.USER,data.PASSWORD);
            String sql = "INSERT INTO Colection.Libro(tipo_libro,pagina,texto) VALUES('" + data.tipoLibro + "', " + data.pagina + ", '" + data.texto + "')";
            PreparedStatement insert = connection.prepareStatement(sql);

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

    public int select(DataConexion data){
        try{
            Connection connection = DriverManager.getConnection(data.URL,data.USER,data.PASSWORD);
            //PreparedStatement select = connection.prepareStatement("INSERT INTO Colection.Libro(tipo_libro,pagina,texto) VALUES('" + data.tipoLibro + "', " + data.pagina + ", '" + data.texto + "')");
            String sql = "SELECT COUNT(*) AS count FROM Libro";
            PreparedStatement select = connection.prepareStatement(sql);

            // Ejecutar la consulta
            ResultSet resultSet = select.executeQuery();

            // Obtener el resultado
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                System.out.println("Número total de libros: " + count);
                return  count;
            }


            resultSet.close();
            select.close();
            connection.close();

        }catch(SQLException e){
            e.printStackTrace();

        }

        //Regresar un valor o null
        //int valor = 0;//Realizar un Select count(*)

        //return valor;
        return 0;
    }
    public void update(DataConexion data){

    }
}
