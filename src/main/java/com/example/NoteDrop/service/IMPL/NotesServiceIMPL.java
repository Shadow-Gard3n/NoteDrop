package com.example.NoteDrop.service.IMPL;

import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.repo.NotesRepo;
import com.example.NoteDrop.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NotesServiceIMPL implements NotesService {

    @Autowired
    private NotesRepo notesRepo;

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
    public List<Notes> searchNotes(String query) {
        return notesRepo.searchNotes(query);
    }
}
