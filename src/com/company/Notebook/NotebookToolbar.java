package com.company.Notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Toolbar for main frame (Notebook.java)
 * Operations:
 * 1- Add note
 */
public class NotebookToolbar extends JPanel implements ActionListener {
    // Toolbar
    public JToolBar toolBar = new JToolBar();
    // Buttons
    public JButton btnAddNote = new JButton("Add Note");

    public void init() {
        Dimension dim = new Dimension(5, 0);

        btnAddNote.addActionListener(this);
        toolBar.add(btnAddNote);
        toolBar.addSeparator(dim);

        setLayout(new BorderLayout());
        add(toolBar ,BorderLayout.NORTH);
    }

    public NotebookToolbar() {
        init();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();

        if (o == btnAddNote) {
            System.out.println("Add note is clicked.");
            NoteCreationDialog noteCreationDialog = new NoteCreationDialog();
        }
    }
}
