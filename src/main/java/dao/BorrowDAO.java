package dao;

import java.sql.*;
import java.util.*;
import model.Borrow;
import model.DBConnect;

public class BorrowDAO {
    public static boolean borrowBook(Borrow b) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO borrow(user_id, book_id, borrow_date) VALUES (?,?,?)"
            );
            ps.setInt(1, b.getUserId());
            ps.setInt(2, b.getBookId());
            ps.setDate(3, new java.sql.Date(b.getBorrowDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
}