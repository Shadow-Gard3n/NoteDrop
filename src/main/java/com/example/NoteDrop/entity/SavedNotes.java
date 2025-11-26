package com.example.NoteDrop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "saved_notes")
public class SavedNotes {
    @Id
    private String id;

    private String username;
    private String noteId; // Changed from int pdfId to String noteId

    public SavedNotes() {
    }

    public SavedNotes(String username, String noteId) {
        this.username = username;
        this.noteId = noteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }
}

// package com.example.NoteDrop.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "saved_notes")
// public class SavedNotes {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @Column(name = "username",length = 100)
//     private String username;

//     @Column(name = "pdf_id",length = 20)
//     private int pdfId;

// //    private LocalDateTime savedAt = LocalDateTime.now();


//     public SavedNotes() {
//     }

//     public SavedNotes(Long id, String username, int pdfId) {
//         this.id = id;
//         this.username = username;
//         this.pdfId = pdfId;
//     }

//     public SavedNotes(String username, int pdfId) {
//         this.username = username;
//         this.pdfId = pdfId;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public int getPdfId() {
//         return pdfId;
//     }

//     public void setPdfId(int pdfId) {
//         this.pdfId = pdfId;
//     }

//     @Override
//     public String toString() {
//         return "SavedNotes{" +
//                 "id=" + id +
//                 ", username='" + username + '\'' +
//                 ", pdfId='" + pdfId + '\'' +
//                 '}';
//     }
// }
