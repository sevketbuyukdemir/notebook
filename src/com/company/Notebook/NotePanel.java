package com.company.Notebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.company.Main.sqliteProcesses;
import static com.company.SqliteDatabaseRelated.SqliteProcesses.notes_arraylist;

/**
 * JPanel for display note
 */
public class NotePanel extends JPanel implements ActionListener {
    // Toolbar
    public JToolBar toolBar = new JToolBar();

    // Buttons
    public JButton btnUpdateNote = new JButton("Update Note");
    public JButton btnDeleteNote = new JButton("Delete Note");

    // Text areas
    JTextArea textAreaTitle = new JTextArea();
    JTextArea textAreaDate = new JTextArea();
    JTextArea textAreaNote = new JTextArea();
    JScrollPane scrollPane;

    public void init() {
        JPanel toolbar_panel = new JPanel();
        Dimension dim = new Dimension(5, 0);

        btnUpdateNote.addActionListener(this);
        toolBar.add(btnUpdateNote);
        toolBar.addSeparator(dim);

        btnDeleteNote.addActionListener(this);
        toolBar.add(btnDeleteNote);
        toolBar.addSeparator(dim);

        toolbar_panel.setLayout(new BorderLayout());
        toolbar_panel.add(toolBar ,BorderLayout.NORTH);

        JPanel note_panel = new JPanel();

        JPanel top_panel = new JPanel();
        top_panel.setLayout(new BorderLayout());

        textAreaTitle.setLineWrap(true);
        textAreaTitle.setEditable(false);
        top_panel.add(textAreaTitle, BorderLayout.NORTH);

        textAreaDate.setLineWrap(true);
        textAreaDate.setEditable(false);
        top_panel.add(textAreaDate, BorderLayout.CENTER);

        textAreaNote.setLineWrap(true);
        textAreaNote.setEditable(false);

        scrollPane = new JScrollPane(textAreaNote,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        note_panel.setLayout(new BorderLayout());
        note_panel.add(top_panel, BorderLayout.NORTH);
        note_panel.add(scrollPane, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(toolbar_panel ,BorderLayout.NORTH);
        add(note_panel ,BorderLayout.CENTER);
    }

    public NotePanel() {
        init();
    }

    public void setTextAreaNote(Note note_data) {
        textAreaTitle.setText(note_data.getTitle());
        textAreaDate.setText(note_data.getDate());
        textAreaNote.setText(note_data.getNote());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();

        if (o == btnUpdateNote) {
            System.out.println("Update note is clicked.");
            NoteUpdateDialog noteUpdateDialog =
                    new NoteUpdateDialog(Notebook.noteList.listTitleList.getSelectedIndex());
        } else if (o == btnDeleteNote) {
            System.out.println("Delete note is clicked.");
            Note note = notes_arraylist.get(Notebook.noteList.listTitleList.getSelectedIndex());
            sqliteProcesses.delete(note.getId());
            // Fill notes_arraylist again
            sqliteProcesses.selectAll();
            // Remove old note panel and note list
            Notebook.NotebookFrame.getContentPane().remove(Notebook.notePanel);
            Notebook.NotebookFrame.getContentPane().remove(Notebook.noteList);
            // Adding note panel again
            Notebook.notePanel = new NotePanel();
            Notebook.NotebookFrame.getContentPane().add(Notebook.notePanel, BorderLayout.CENTER);
            // Adding note list panel again
            Notebook.noteList = new NoteList();
            Notebook.NotebookFrame.getContentPane().add(Notebook.noteList, BorderLayout.WEST);
        }
    }
}
