package com.example.NoteDrop.dto;


///  no longer needed
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaveNotesDTO {
    private String username;
    private String pdfId;
}
