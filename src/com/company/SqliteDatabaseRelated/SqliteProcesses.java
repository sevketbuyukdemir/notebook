package com.company.SqliteDatabaseRelated;

import com.company.Notebook.Note;
import java.sql.*;
import java.util.ArrayList;

/**
 * @Author: https://www.sqlitetutorial.net/sqlite-java/
 * !!! edited !!!
 */
public class SqliteProcesses {
    public static ArrayList<Note> notes_arraylist;

    public SqliteProcesses() {
    }

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:lib/notebook.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void insert(String note_title, String note_date, String note_description) {
        String sql = "INSERT INTO notes(note_title,note_date,note_description) VALUES(?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, note_title);
            pstmt.setString(2, note_date);
            pstmt.setString(3, note_description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAll(){
        String sql = "SELECT id, note_title, note_date, note_description FROM notes";
        notes_arraylist = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                // insert notes_arraylist in here
                Note note = new Note(rs.getString("note_title"),
                        rs.getString("note_date"),
                        rs.getString("note_description"));
                note.setId(rs.getInt("id"));
                notes_arraylist.add(note);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String note_title, String note_date, String note_description) {
        String sql = "UPDATE notes SET note_title = ? , "
                + "note_date = ? ,"
                + "note_description = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, note_title);
            pstmt.setString(2, note_date);
            pstmt.setString(3, note_description);
            pstmt.setInt(4, id);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM notes WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
