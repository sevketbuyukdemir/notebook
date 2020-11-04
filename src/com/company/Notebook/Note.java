package com.company.Notebook;

/**
 * Data class for note
 */
public class Note {
    private int id;
    private String title;
    private String date;
    private String note;

    public Note(String title, String date, String note) {
        this.title = title;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
