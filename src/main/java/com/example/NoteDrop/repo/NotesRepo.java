package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.Notes;
import com.example.NoteDrop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NotesRepo extends JpaRepository<Notes, Integer> {

    @Query("SELECT n FROM Notes n WHERE LOWER(n.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(n.subject) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(n.topic) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(n.about) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Notes> searchNotes(@Param("keyword") String keyword);

    List<Notes> findByUsername(String username);
}
