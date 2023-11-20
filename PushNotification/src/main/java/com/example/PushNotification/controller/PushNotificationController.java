package com.example.PushNotification.controller;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PushNotification.dto.PushNotificationRequest;
import com.example.PushNotification.dto.PushNotificationResponse;
import com.example.PushNotification.service.PushNotificationService;


/**
 * The PushNotificationController class handles HTTP requests related to push notifications.
 */
@RestController
public class PushNotificationController {

	 private PushNotificationService pushNotificationService;
	    
	 	/**
	     * Constructs a new PushNotificationController with the provided PushNotificationService.
	     *
	     * @param pushNotificationService The service responsible for handling push notifications.
	     */
	    public PushNotificationController(PushNotificationService pushNotificationService) {
	        this.pushNotificationService = pushNotificationService;
	    }
	    
	    /**
	     * Handles a POST request to send a push notification to a specific token.
	     *
	     * @param request The PushNotificationRequest containing the notification data and token.
	     * @return ResponseEntity<PushNotificationResponse> The response entity with the result of the notification.
	     */
	    @PostMapping("/notification/token")
	    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
	        pushNotificationService.sendPushNotificationToToken(request);
	        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
	    }
	
}