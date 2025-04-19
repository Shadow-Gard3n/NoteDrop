package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.entity.SavedNotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SavedNotesRepo extends JpaRepository<SavedNotes, Long> {

    boolean existsByUsernameAndPdfId(String username, int pdfId);
    List<SavedNotes> findByUsername(String username);
}
