package com.example.PushNotification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.PushNotification.dto.PushNotificationRequest;

/**
 * The PushNotificationService class is responsible for sending push notifications to specific tokens.
 */
@Service
public class PushNotificationService {

private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    
    private FCMService fcmService;
    
    /**
     * Constructs a new PushNotificationService with the specified FCMService.
     *
     * @param fcmService The service responsible for sending push notifications using Firebase Cloud Messaging (FCM).
     */
    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }
    
    /**
     * Sends a push notification to a specific token.
     *
     * @param request The PushNotificationRequest containing notification data and the token.
     */
    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
	
}
