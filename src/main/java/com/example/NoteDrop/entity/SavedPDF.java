package com.example.NoteDrop.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "saved_pdf")
public class SavedPDF {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",length = 100)
    private String username;

    @Column(name = "pdf_id",length = 20)
    private String pdfId;

//    private LocalDateTime savedAt = LocalDateTime.now();
}
