// package com.example.NoteDrop.service;

// import com.example.NoteDrop.dto.NotesSaveDTO;
// import com.example.NoteDrop.entity.Notes;

// import java.util.List;

// public interface NotesService {
//     void addNotes(NotesSaveDTO notesSaveDTO);
//     List<Notes> searchNotes(String query);
//     List<Notes> notesByUsername(String username);
//     public boolean saveNoteForUser(String username, int pdfId);
// }

package com.example.NoteDrop.service;

import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.entity.Notes;
import java.util.List;

public interface NotesService {
    void addNotes(NotesSaveDTO notesSaveDTO);
    List<Notes> searchNotes(String query);
    List<Notes> notesByUsername(String username);
    // Changed parameter pdfId from int to String
    boolean saveNoteForUser(String username, String noteId);
}