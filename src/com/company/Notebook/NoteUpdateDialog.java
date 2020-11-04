package com.company.Notebook;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import static com.company.Main.sqliteProcesses;
import static com.company.SqliteDatabaseRelated.SqliteProcesses.notes_arraylist;

public class NoteUpdateDialog extends JDialog implements ActionListener {
    Note note;
    int note_id;
    private JLabel labelTitle;
    private JLabel labelDate;
    private JLabel labelNote;
    private JTextArea titleTextArea;
    private JSpinner dateSpinner;
    private JTextArea noteTextArea;
    private JButton okButton;
    private JButton cancelButton;

    public void init() {
        setTitle("Update Note Dialog");
        setMinimumSize(new Dimension(600,400));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel title_panel = new JPanel();
        title_panel.setLayout(new GridLayout(0,2));
        labelTitle = new JLabel("Title: ");
        title_panel.add(labelTitle);
        titleTextArea = new JTextArea();
        titleTextArea.setText(note.getTitle());
        titleTextArea.setLineWrap(true);
        title_panel.add(titleTextArea);
        add(title_panel);

        JPanel date_panel = new JPanel();
        date_panel.setLayout(new GridLayout(0,2));
        labelDate = new JLabel("Date: ");
        date_panel.add(labelDate);
        Date today = new Date();
        dateSpinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy");
        DateFormatter formatter = (DateFormatter) editor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        dateSpinner.setEditor(editor);
        date_panel.add(dateSpinner);
        add(date_panel);

        JPanel note_panel = new JPanel();
        note_panel.setLayout(new GridLayout(0,2));
        labelNote = new JLabel("Note: ");
        note_panel.add(labelNote);
        noteTextArea = new JTextArea();
        noteTextArea.setText(note.getNote());
        noteTextArea.setLineWrap(true);
        note_panel.add(noteTextArea);
        add(note_panel);

        JPanel button_panel = new JPanel();
        button_panel.setLayout(new GridLayout(0,2));
        okButton = new JButton("Okay");
        okButton.addActionListener(this);
        button_panel.add(okButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        button_panel.add(cancelButton);
        add(button_panel);

        setLayout(new GridLayout(0,1));
        setVisible(true);
    }

    public NoteUpdateDialog(int note_id) {
        this.note = notes_arraylist.get(note_id);
        this.note_id = note.getId();
        init();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Object o = actionEvent.getSource();

        if(o == okButton) {
            JSpinner.DateEditor dateEditor =(JSpinner.DateEditor)dateSpinner.getEditor();
            String date = dateEditor.getFormat().format(dateSpinner.getValue());
            // Insert new note to database
            sqliteProcesses.update(note_id, titleTextArea.getText(), date, noteTextArea.getText());
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
            // Close note creation dialog
            this.dispose();
        } else if(o == cancelButton) {
            // Close note creation dialog without any process
            this.dispose();
        }
    }
}
