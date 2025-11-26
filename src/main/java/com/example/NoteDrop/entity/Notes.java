package com.example.NoteDrop.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "notes")
public class Notes {
    @Id
    private String notesid; // Changed to String

    private String username;
    private String subject;
    private String topic;
    private String about;
    private String filePath;

    public Notes() {
    }

    public Notes(String username, String subject, String topic, String about, String filePath) {
        this.username = username;
        this.subject = subject;
        this.topic = topic;
        this.about = about;
        this.filePath = filePath;
    }

    public String getNotesid() {
        return notesid;
    }

    public void setNotesid(String notesid) {
        this.notesid = notesid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

// package com.example.NoteDrop.entity;

// import jakarta.persistence.*;


// @Entity
// @Table(name = "Notes")
// public class Notes {
//     @Id
//     @Column(name = "notes_id",length = 20)
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     private int notesid;

//     @Column(name = "username",length = 100)
//     private String username;

//     @Column(name = "subject",length = 60)
//     private String subject;

//     @Column(name = "topic",length = 60)
//     private String topic;

//     @Column(name = "about",length = 1000)
//     private String about;

//     @Column(name = "file_path", length = 500, nullable = false)
//     private String filePath;

//     public Notes() {
//     }

//     public Notes(int notesid, String username, String subject, String topic, String about, String filePath) {
//         this.notesid = notesid;
//         this.username = username;
//         this.subject = subject;
//         this.topic = topic;
//         this.about = about;
//         this.filePath = filePath;
//     }

//     public Notes(String username, String subject, String topic, String about, String filePath) {
//         this.username = username;
//         this.subject = subject;
//         this.topic = topic;
//         this.about = about;
//         this.filePath = filePath;
//     }

//     public int getNotesid() {
//         return notesid;
//     }

//     public void setNotesid(int notesid) {
//         this.notesid = notesid;
//     }

//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getSubject() {
//         return subject;
//     }

//     public void setSubject(String subject) {
//         this.subject = subject;
//     }

//     public String getTopic() {
//         return topic;
//     }

//     public void setTopic(String topic) {
//         this.topic = topic;
//     }

//     public String getAbout() {
//         return about;
//     }

//     public void setAbout(String about) {
//         this.about = about;
//     }

//     public String getFilePath() {
//         return filePath;
//     }

//     public void setFilePath(String filePath) {
//         this.filePath = filePath;
//     }

//     @Override
//     public String toString() {
//         return "Notes{" +
//                 "notesid=" + notesid +
//                 ", username='" + username + '\'' +
//                 ", subject='" + subject + '\'' +
//                 ", topic='" + topic + '\'' +
//                 ", about='" + about + '\'' +
//                 ", filePath='" + filePath + '\'' +
//                 '}';
//     }
// }
