package com.example.NoteDrop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotesSaveDTO {
    private String username;
    private String subject;
    private String topic;
    private String about;
    private String filePath;
}
