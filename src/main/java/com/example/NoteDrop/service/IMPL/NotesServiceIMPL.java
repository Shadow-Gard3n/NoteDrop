
package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.entity.SavedNotes;
import com.example.NoteDrop.repo.NotesRepo;
import com.example.NoteDrop.repo.SavedNotesRepo;
import com.example.NoteDrop.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesServiceIMPL implements NotesService {

    @Autowired
    private NotesRepo notesRepo;

    @Autowired
    private SavedNotesRepo savedNotesRepo;

    @Override
    public void addNotes(NotesSaveDTO notesSaveDTO) {
        Notes notes = new Notes(
                notesSaveDTO.getUsername(),
                notesSaveDTO.getSubject(),
                notesSaveDTO.getTopic(),
                notesSaveDTO.getAbout(),
                notesSaveDTO.getFilePath()
        );
        notesRepo.save(notes);
    }

    @Override
    public boolean saveNoteForUser(String username, String noteId) {
        if (savedNotesRepo.existsByUsernameAndNoteId(username, noteId)) {
            return false; // already saved
        }

        SavedNotes savedNotes = new SavedNotes(username, noteId);
        savedNotesRepo.save(savedNotes);
        return true;
    }

    @Override
    public List<Notes> searchNotes(String query) {
        return notesRepo.searchNotes(query);
    }

    @Override
    public List<Notes> notesByUsername(String username) {
        return notesRepo.findByUsername(username);
    }
}


// package com.example.NoteDrop.service.IMPL;

// import com.example.NoteDrop.dto.NotesSaveDTO;
// import com.example.NoteDrop.entity.Notes;
// import com.example.NoteDrop.entity.SavedNotes;
// import com.example.NoteDrop.repo.NotesRepo;
// import com.example.NoteDrop.repo.SavedNotesRepo;
// import com.example.NoteDrop.service.NotesService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;


// @Service
// public class NotesServiceIMPL implements NotesService {

//     @Autowired
//     private NotesRepo notesRepo;

//     @Autowired
//     private SavedNotesRepo savedNotesRepo;

//     @Override
//     public void addNotes(NotesSaveDTO notesSaveDTO) {
//         Notes notes = new Notes(
//                 notesSaveDTO.getUsername(),
//                 notesSaveDTO.getSubject(),
//                 notesSaveDTO.getTopic(),
//                 notesSaveDTO.getAbout(),
//                 notesSaveDTO.getFilePath()
//         );
//         notesRepo.save(notes);
//     }

//     public boolean saveNoteForUser(String username, int pdfId) {
//         if (savedNotesRepo.existsByUsernameAndPdfId(username, pdfId)) {
//             return false; // already saved
//         }

//         SavedNotes savedNotes = new SavedNotes();
//         savedNotes.setUsername(username);
//         savedNotes.setPdfId(pdfId);
//         savedNotesRepo.save(savedNotes);
//         return true;
//     }

//     @Override
//     public List<Notes> searchNotes(String query) {
//         return notesRepo.searchNotes(query);
//     }

//     @Override
//     public List<Notes> notesByUsername(String username) {
//         return notesRepo.findByUsername(username);
//     }

// }
