// package com.example.NoteDrop.repo;

// import com.example.NoteDrop.entity.Notes;
// import com.example.NoteDrop.entity.SavedNotes;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.List;

// public interface SavedNotesRepo extends JpaRepository<SavedNotes, Long> {

//     boolean existsByUsernameAndPdfId(String username, int pdfId);
//     List<SavedNotes> findByUsername(String username);
// }


package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.SavedNotes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedNotesRepo extends MongoRepository<SavedNotes, String> {
    boolean existsByUsernameAndNoteId(String username, String noteId);
    List<SavedNotes> findByUsername(String username);
}