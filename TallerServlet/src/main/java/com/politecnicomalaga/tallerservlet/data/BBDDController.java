package com.politecnicomalaga.tallerservlet.data;

import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Map;

public class BBDDController {

    public static String lastSQL = "";
    //Función para abrir la conexión TCP/IP con la BBDD en el
    //puerto 3306 con las credenciales user+pass típicas en la URL
    //proporcionada por el entorno docker a la BBDD taller_db
    //Encima de TCP/IP se usa JDBC para Mysql

    private static Connection openConnectionDataBase() throws ClassNotFoundException, SQLException {

        String jdbcUrl = "jdbc:mysql://bbdd:3306/taller_db";
        String user = "taller";
        String password = "2secret2know";

        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection(jdbcUrl, user, password);

        return conn;

    }

    //M é t o d o  para cerrar la conexión una vez atendida la petición
    private static void closeConnectionDatabase(Connection conn) throws SQLException {
        if (conn!=null) conn.close();
    }

    //Función para ejecutar una Prepared Statement
    //En el mapa nos pasan las parejas clave-valor equivalentes a
    // los nombres de las columnas y sus valores
    private static void executeQueryINSERT(Connection con, String tabla, Map<String,String> data) throws SQLException{
        if (con==null) return;
        String SQL = "Insert into " + tabla + "(";// + " (ccc) values (vvv)";
        String SQLColumns = "", SQLValues = "";


        for(Map.Entry<String,String> entry:data.entrySet()) {
            SQLColumns += entry.getKey() + ",";
            SQLValues += "'" + entry.getValue() + "',";
        }

        SQLColumns = SQLColumns.substring(0,SQLColumns.length()-1);
        SQLValues = SQLValues.substring(0,SQLValues.length()-1);

        SQL += SQLColumns + ") values (";
        SQL += SQLValues + ")";

        PreparedStatement ps = con.prepareStatement(SQL);
        lastSQL = ps.toString();

        if (ps != null) {
           lastSQL += " Insertadas " + ps.executeUpdate() + " filas";
        }

    }

    public static String insertarMapeable(Mapeable object,String table) {
        try {
            Connection con = BBDDController.openConnectionDataBase();

            Map<String,String> data;
            data = object.toMap();

            BBDDController.executeQueryINSERT(con,table,data);

            BBDDController.closeConnectionDatabase(con);
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            return "Error de tipo BBDD Insert: " + sqlE.getMessage();
        } catch (ClassNotFoundException cnfe) {
            return "Error al cargar Driver Mysql: " + cnfe.getMessage();
        }
        return "OK";
    }

    public static String selectAll(String table, String columnsName, int columns) {
        String data = "";
        try {
            Connection con = BBDDController.openConnectionDataBase();

            data = BBDDController.executeQuerySELECT(con, table,columnsName, columns);

            BBDDController.closeConnectionDatabase(con);
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
            return "Error de tipo BBDD Select: " + sqlE.getMessage();
        } catch (ClassNotFoundException cnfe) {
            return "Error al cargar Driver Mysql: " + cnfe.getMessage();
        }


        return data;
    }

    private static String executeQuerySELECT(Connection con, String table, String columnsNames, int columns) throws SQLException {

        String data = "";

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT " + columnsNames +  " FROM " + table);
        lastSQL = "SELECT " + columnsNames +  " FROM " + table;
        while (rs.next()) {
            for (int i = 1;i<=columns;i++) {
                data += rs.getString(i) + ";";
            }
            data += "\n";
        }

        return data;
    }

}
