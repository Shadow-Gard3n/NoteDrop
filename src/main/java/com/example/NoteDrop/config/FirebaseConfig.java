//package com.example.NoteDrop.config;
//
//
///// no longer important
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
//@Configuration
//public class FirebaseConfig {
//
//    @Bean
//    public FirebaseApp firebaseApp() throws IOException {
//        // Path to your Firebase Admin SDK JSON credentials file
//        FileInputStream serviceAccount = new FileInputStream("D:\\Coding\\java\\NoteDrop\\serviceAccountKey.json");
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .build();
//
//        // Initialize Firebase with the service account
//        return FirebaseApp.initializeApp(options);
//    }
//}
