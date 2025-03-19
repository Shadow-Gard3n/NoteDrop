package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepo extends JpaRepository<Notes, Integer> {
}
