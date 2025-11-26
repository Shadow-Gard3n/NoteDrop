// package com.example.NoteDrop.dto;


// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// public class NotesDTO {
//     private int notesid;
//     private String username;
//     private String subject;
//     private String topic;
//     private String about;
//     private String filePath;
// }


package com.example.NoteDrop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotesDTO {
    private String notesid; // Changed to String
    private String username;
    private String subject;
    private String topic;
    private String about;
    private String filePath;
}