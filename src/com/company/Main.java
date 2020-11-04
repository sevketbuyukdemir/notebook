package com.company;

import com.company.Notebook.Notebook;
import com.company.SqliteDatabaseRelated.CreateSqlite;
import com.company.SqliteDatabaseRelated.SqliteProcesses;

public class Main {
    public static SqliteProcesses sqliteProcesses;
    public static Notebook nb;

    public static void main(String[] args) {
        try {
            CreateSqlite createSqlite = new CreateSqlite();
            sqliteProcesses = new SqliteProcesses();
            sqliteProcesses.selectAll();
            nb = new Notebook();
        } catch (Exception e) {
            e.printStackTrace();
            nb = new Notebook();
        }
    }
}
