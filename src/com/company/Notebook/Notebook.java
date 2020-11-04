package com.company.Notebook;

import javax.swing.*;
import java.awt.*;


/**
 * Main frame for notebook application
 */
public class Notebook extends JFrame {
    public static JFrame NotebookFrame;
    public static NotebookToolbar notebookToolbar;
    public static NotePanel notePanel;
    public static NoteList noteList;

    public Notebook() {
        NotebookFrame = new JFrame();
        NotebookFrame.setTitle("Notebook");
        NotebookFrame.setMinimumSize(new Dimension(800, 600));
        NotebookFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        NotebookFrame.getContentPane().setLayout(new BorderLayout());

        // Adding main toolbar
        notebookToolbar = new NotebookToolbar();
        NotebookFrame.getContentPane().add(notebookToolbar, BorderLayout.NORTH);
        // Adding note panel
        notePanel = new NotePanel();
        NotebookFrame.getContentPane().add(notePanel, BorderLayout.CENTER);
        // Adding note list panel
        noteList = new NoteList();
        NotebookFrame.getContentPane().add(noteList, BorderLayout.WEST);

        NotebookFrame.setVisible(true);
    }
}
