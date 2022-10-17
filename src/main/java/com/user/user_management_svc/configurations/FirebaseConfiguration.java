package com.user.user_management_svc.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfiguration
{

    private final static String pathToServiceAccountKey
            = "src/main/resources/student-support-system-6b748-firebase-adminsdk-89632-7e1dce2f88.json";
    private static boolean isFirebaseInitialized = false;

    public static void initFirebase()
    {
        if(!isFirebaseInitialized)
        {
            setupFirebase();
            isFirebaseInitialized = true;
        }
    }

    private static void setupFirebase()
    {
        try{
            FileInputStream serviceAccount =
                    new FileInputStream(pathToServiceAccountKey);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }catch(IOException exception){
            System.out.println(">> [ FirebaseConfiguration.setupFirebase() ] " + exception.getMessage());
        }
    }
}
