package com.library.service;

import com.library.model.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    // Method to add a new book
    public boolean addBook(String title, String author) {
        String sql = "INSERT INTO books (title, author, is_borrowed) VALUES (?, ?, false)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, author);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to borrow a book
    public boolean borrowBook(int id) {
        String sql = "UPDATE books SET is_borrowed = true WHERE id = ? AND is_borrowed = false";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to return a book
    public boolean returnBook(int id) {
        String sql = "UPDATE books SET is_borrowed = false WHERE id = ? AND is_borrowed = true";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to fetch all borrowed books
    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE is_borrowed = true";

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                borrowedBooks.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("is_borrowed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowedBooks;
    }

    // Method to fetch available books
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE is_borrowed = false";

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                availableBooks.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("is_borrowed")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableBooks;
    }
}
