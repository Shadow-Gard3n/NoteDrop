package com.example.NoteDrop.service;

import com.example.NoteDrop.dto.NotesSaveDTO;
import com.example.NoteDrop.entity.Notes;

import java.util.List;

public interface NotesService {
    void addNotes(NotesSaveDTO notesSaveDTO);
    List<Notes> searchNotes(String query);
}
