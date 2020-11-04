package com.company.SqliteDatabaseRelated;

import java.sql.*;

/**
 * @Author: https://www.sqlitetutorial.net/sqlite-java/
 * !!! edited !!!
 */
public class CreateSqlite {

    public CreateSqlite() {
        connect();
        createNewDatabase("notebook.db");
        createNewTable();
    }

    public static void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:lib/sqlite-jdbc-3.32.3.2.jar";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:lib/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:lib/notebook.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS notes (\n"
                + "	id INTEGER PRIMARY KEY,\n"
                + "	note_title TEXT NOT NULL,\n"
                + "	note_date TEXT NOT NULL,\n"
                + "	note_description TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
