package com.example.PushNotification.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

/**
 * The FCMInitializer class initializes Firebase Cloud Messaging (FCM) for the application.
 */
@Service
public class FCMInitializer {

	 @Value("${app.firebase-configuration-file}")
	    private String firebaseConfigPath;
	    Logger logger = LoggerFactory.getLogger(FCMInitializer.class);
	    
	    /**
	     * Initializes the Firebase application during the application startup.
	     */
	    @PostConstruct
	    public void initialize() {
	        try {
	            FirebaseOptions options = new FirebaseOptions.Builder()
	                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())).build();
	            if (FirebaseApp.getApps().isEmpty()) {
	                FirebaseApp.initializeApp(options);
	                logger.info("Firebase application has been initialized");
	            }
	        } catch (IOException e) {
	            logger.error(e.getMessage());
	        }
	    }
	
}
