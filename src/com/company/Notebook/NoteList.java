package com.company.Notebook;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

import static com.company.SqliteDatabaseRelated.SqliteProcesses.notes_arraylist;

/**
 * Display panel for all notes which are saved
 */
public class NoteList extends JPanel implements ListSelectionListener {
    NotePanel notePanel = Notebook.notePanel;

    public static JList listTitleList;
    JScrollPane scrollPane;

    public void init() {
        String titles[] = new String[notes_arraylist.size()];
        for(int i = 0; i < notes_arraylist.size(); i++) {
            titles[i] = notes_arraylist.get(i).getTitle();
        }
        listTitleList = new JList(titles);
        listTitleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listTitleList.setLayoutOrientation(JList.VERTICAL);
        listTitleList.setVisibleRowCount(-1);
        scrollPane = new JScrollPane(listTitleList);
        scrollPane.setPreferredSize(new Dimension(250, 80));

        listTitleList.addListSelectionListener(this);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public NoteList() {
        init();
        listTitleList.setSelectedIndex(0);
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {
            notePanel.setTextAreaNote(notes_arraylist.get(listTitleList.getSelectedIndex()));
        }
    }
}
