package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    Connection connection;
    public UserDao(){
        connection=DbOperations.createConnection();
    }

    public boolean isUserNamePresent(String userName) throws SQLException {
        String query="select * from user where user_name=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1, userName);
        ResultSet result=statement.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }

    public boolean isUserPresent(String userName,long phioneNumber) throws SQLException {
        String query="select * from user where user_name=? and phone_number=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1, userName);
        statement.setLong(2,phioneNumber);
        ResultSet result=statement.executeQuery();
        if (result.next()) {
            return true;
        }
        return false;
    }
    public User createUser(User user) throws SQLException {
        String query="insert into user(user_name,password,name,phone_number,email,signup_date) values(?,?,?,?,?,?)";
        PreparedStatement statement= connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setString(1,user.getUserName());
        statement.setString(2, user.getPassword());
        statement.setString(3,user.getName());
        statement.setLong(4,user.getPhoneNumber());
        statement.setString(5,user.getEmail());
        statement.setString(6,user.getDate());
        int result=statement.executeUpdate();
        if (result > 0) {
            ResultSet keys= statement.getGeneratedKeys();
            if (keys.next()) {
                user.setUserId(keys.getInt(1));
            }
        }
        return user;
    }

    public User login(String userName,String password) throws SQLException {
        User user=null;
        String query="select * from user where user_name=? and password=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,userName);
        statement.setString(2,password);
        ResultSet resultSet= statement.executeQuery();
        if (resultSet.next()) {
            user=new User(resultSet.getInt("user_id"),resultSet.getString("user_name"), resultSet.getString("password"), resultSet.getString("name"),resultSet.getLong("phone_number"), resultSet.getString("email"),resultSet.getString("signup_date") );
        }
        return user;
    }


}
