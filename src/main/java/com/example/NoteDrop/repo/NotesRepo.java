// package com.example.NoteDrop.repo;

// import com.example.NoteDrop.entity.Notes;
// import com.example.NoteDrop.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;
// import java.util.Optional;

// public interface NotesRepo extends JpaRepository<Notes, Integer> {

//     @Query("SELECT n FROM Notes n WHERE LOWER(n.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//             "OR LOWER(n.subject) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//             "OR LOWER(n.topic) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
//             "OR LOWER(n.about) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//     List<Notes> searchNotes(@Param("keyword") String keyword);
//     List<Notes> findByNotesidIn(List<Integer> notesIds);
//     List<Notes> findByUsername(String username);
// }

package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.Notes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepo extends MongoRepository<Notes, String> {

    // MongoDB Regex query for case-insensitive search across multiple fields
    @Query("{'$or': [" +
           "{'username': {$regex: ?0, $options: 'i'}}, " +
           "{'subject': {$regex: ?0, $options: 'i'}}, " +
           "{'topic': {$regex: ?0, $options: 'i'}}, " +
           "{'about': {$regex: ?0, $options: 'i'}}" +
           "]}")
    List<Notes> searchNotes(String keyword);

    List<Notes> findByNotesidIn(List<String> notesIds);
    
    List<Notes> findByUsername(String username);
}