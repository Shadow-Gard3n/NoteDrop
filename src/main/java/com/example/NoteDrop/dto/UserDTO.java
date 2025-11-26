// package com.example.NoteDrop.dto;


// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// public class UserDTO {
//     private int userid;
//     private String username;
//     private String password;
// }


package com.example.NoteDrop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String userid; // Changed to String
    private String username;
    private String password;
}