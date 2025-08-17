package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import model.Book;
import model.DBConnect;

public class BookDAO {

    public static List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM books");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("category"),
                    rs.getBoolean("available")
                ));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return list;
    }

    public static boolean addBook(Book b) {
        try {
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO books(title, author, category, available) VALUES (?,?,?,?)"
            );
            ps.setString(1, b.getTitle());
            ps.setString(2, b.getAuthor());
            ps.setString(3, b.getCategory());
            ps.setBoolean(4, b.isAvailable());

            boolean success = ps.executeUpdate() > 0;

            ps.close();
            con.close();
            return success;
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
        return false;
    }
}
