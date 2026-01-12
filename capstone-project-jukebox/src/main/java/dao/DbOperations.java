package dao;

import java.sql.*;

public class DbOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    public static Connection createConnection() {
        Connection con=null;
        try {
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }
        catch(SQLException e) {
            System.out.println(e);
        }
        return con;
    }

   /* public static int setOrDeleteData(String query) {
        int n=0;
        try{
            Connection con=createConnection();
            Statement st= con.createStatement();
            n= st.executeUpdate(query);
        }catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }*/

    public static ResultSet getData(String query) {
        ResultSet rs=null;
        try{
            Connection con=createConnection();
            Statement st= con.createStatement();
            rs= st.executeQuery(query);
        }catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }
}
