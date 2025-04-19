package com.example.NoteDrop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_notes")
public class SavedNotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 100)
    private String username;

    @Column(name = "pdf_id",length = 20)
    private int pdfId;

//    private LocalDateTime savedAt = LocalDateTime.now();


    public SavedNotes() {
    }

    public SavedNotes(Long id, String username, int pdfId) {
        this.id = id;
        this.username = username;
        this.pdfId = pdfId;
    }

    public SavedNotes(String username, int pdfId) {
        this.username = username;
        this.pdfId = pdfId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPdfId() {
        return pdfId;
    }

    public void setPdfId(int pdfId) {
        this.pdfId = pdfId;
    }

    @Override
    public String toString() {
        return "SavedNotes{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pdfId='" + pdfId + '\'' +
                '}';
    }
}
