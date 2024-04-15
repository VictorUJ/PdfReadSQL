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
        data.texto = texto;
        data.pagina = String.format("%03d", pagina);


        if(select(data) == 0){
            insert(data);
        }else{
            update(data);
        }


    }

    public void insert(DataConexion data){
        try{
            Connection connection = DriverManager.getConnection(data.URL,data.USER,data.PASSWORD);
            //String sql = "INSERT INTO Colection.Libro(tipo_libro,pagina,texto) VALUES('" + data.tipoLibro + "', " + data.pagina + ", '" + data.texto + "')";
            String sql = "INSERT INTO libros_ocr (clave_libro, pagina, texto_pagina, id_revisor, texto_pdf)" + "VALUES ('"+ data.tipoLibro +"', '"+ data.pagina +"', '', 4, '"+ data.texto +"')";
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
            //String sql = "SELECT COUNT(*) AS count FROM Libro WHERE pagina = '"+ data.pagina +"' AND tipo_libro = '" + data.tipoLibro + "'";
            String sql = "SELECT COUNT(*) AS count FROM libros_ocr WHERE pagina = '"+ data.pagina +"' AND clave_libro = '" + data.tipoLibro + "'";
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


        return 0;
    }
    public void update(DataConexion data){
        try {

            Connection connection = DriverManager.getConnection(data.URL, data.USER, data.PASSWORD);
            //String sql = "UPDATE Libro SET texto = ? WHERE pagina = ? AND tipo_libro = ?";
            String sql = "UPDATE libros_ocr SET texto_pdf = ? WHERE pagina = ? AND clave_libro = ?";
            PreparedStatement update = connection.prepareStatement(sql);

            String nuevoTexto = data.texto;
            String pagina = data.pagina;
            String tipoLibro = data.tipoLibro;

            update.setString(1, nuevoTexto);
            update.setString(2, pagina);
            update.setString(3, tipoLibro);

            int filasActualizadas = update.executeUpdate();
            System.out.println("Filas actualizadas: " + filasActualizadas);

            update.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
