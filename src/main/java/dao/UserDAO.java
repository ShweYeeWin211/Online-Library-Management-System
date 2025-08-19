package dao;

import java.sql.*;
import model.User;
import model.DBConnect;

public class UserDAO {
    public static boolean register(User u) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(name,email,password) VALUES (?,?,?)"
            );
            ps.setString(1, u.getName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    public static User login(String email, String password) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email=? AND password=?"
            );
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"),
                                rs.getString("email"), rs.getString("password"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }
}
