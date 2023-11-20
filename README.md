# Spring Boot - Push Notification

A Spring Boot Base rest api for sending notification to IOS as well as Android devices using FCM(Firebase Cloud Messaging) service



## Table of contents
- [Spring Boot - Push Notification](#spring-boot---push-notification)
  - [Table of contents](#table-of-contents)
  - [Spring Boot Support](#Spring-Boot-support)
  - [Demo](#demo)
  - [Features](#features)
  - [Getting started](#getting-started)
  - [Usage](#usage)
    - [Methods](#methods)
  - [Directive options](#directive-options)
    - [Firebase project setup steps](#firebase-project-setup-steps)
  - [Want to Contribute?](#want-to-contribute)
  - [Collection of Components](#collection-of-components)
  - [Changelog](#changelog)
  - [License](#license)
  - [Keywords](#keywords)


## Spring Boot Support

Version - Spring Boot 3.1.5 (stable)

We have tested our program in above version, however you can use it in other versions as well.




## Features

* Add the mobile device-specific token to the API request and get a notification on your device.


## Getting started

* Create a Spring Boot project add required dependancies According to your project then add folowing dependancy inside your pom.xml 
* <dependency> 

    <groupId>com.google.firebase</groupId> 

    <artifactId>firebase-admin</artifactId> 

    <version>8.1.0</version> 

  </dependency> 
* Update your maven.  
* Do firebase Setup as suggested in firebase project setup section of this file. 
* Download firebase Admin SDK for java(your-firebase-credentials.json) from service accounts of firebase into the resources folder of your spring boot project.
* Add following  code into application.properties file in your Spring Boot Prtoject

app.firebase-configuration-file = your-firebase-credentials.json

* change the json file name according to your project
* You will using this variable using value annotation in FCMinitializer

## Usage

Setup your project structure as following: 

```plaintext
project-root/
├── src/
│   └── main/
│       └── java/
│           └── com/yourapp/
│               ├── YourApplication.java
│               ├── controller/
│               │   └── PushNotificationController.java
│               ├── service/
│               │   ├── FCMService.java
│               │   ├── PushNotificationService.java
│               │   └── FCMinitializer.java
│               └── dto/
│                   ├── RequestDTO.java
│                   └── ResponseDTO.java
├── resources/
│   ├── application.properties
│   └── your-firebase-credentials.json
└── target/
```

### Methods

Sends a push notification message to a specific device token.

	 public void sendMessageToToken(PushNotificationRequest request)
	            throws InterruptedException, ExecutionException {
	        Message message = getPreconfiguredMessageToToken(request);
	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
	        String jsonOutput = gson.toJson(message);
	        String response = sendAndGetResponse(message);
	        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
	    }

Sends a message and retrieves the response.

	    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
	        return FirebaseMessaging.getInstance().sendAsync(message).get();
	    }
Constructs the Android-specific configuration for the message.

	    private AndroidConfig getAndroidConfig(String topic) {
	        return AndroidConfig.builder()
	                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
	                .setPriority(AndroidConfig.Priority.HIGH)
	                .setNotification(AndroidNotification.builder()
	                        .setTag(topic).build()).build();
	    }

Constructs the IOS-specific configuration for the message.

	    private ApnsConfig getApnsConfig(String topic) {
	        return ApnsConfig.builder()
	                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
	    }

Constructs a message with the provided data for a specific device token.

	    private Message getPreconfiguredMessageToToken(PushNotificationRequest request) {
	        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
	                .build();
	    }

Constructs a message without data for a specific topic.

	    private Message getPreconfiguredMessageWithoutData(PushNotificationRequest request) {
	        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
	                .build();
	    }

Constructs a message for a specific device token.

	    private Message getPreconfiguredMessageWithData(Map<String, String> data, PushNotificationRequest request) {
	        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
	                .build();
	    }
Constructs a message builder with common configurations.

	    private Message.Builder getPreconfiguredMessageBuilder(PushNotificationRequest request) {
	        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
	        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());

	        Notification notification = Notification.builder()
	                .setTitle(request.getTitle())
	                .setBody(request.getMessage())
	                .build();

	        return Message.builder()
	                .setApnsConfig(apnsConfig)
	                .setAndroidConfig(androidConfig)
	                .setNotification(notification);
	    }
    
------

## Directive options

### Firebase project setup steps

Create Project setup in firebase console using below URL [https://console.firebase.google.com/](https://console.firebase.google.com/) .

Follow the following steps:->
* Open your application in the firebase 
* Click on the Gear icon and goto the project setting
* In project setting open the Service Accounts table
* Select Java in admin SDK configuration snippet
* Click On the generate new private key
* your-firebase-credentials.json file will be get downloaded 
* Add this file into your Spring Boot project's resources folder


## Want to Contribute?

- Created something awesome, made this code better, added some functionality, or whatever (this is the hardest part).
- [Fork it](http://help.github.com/forking/).
- Create new branch to contribute your changes.
- Commit all your changes to your branch.
- Submit a [pull request](http://help.github.com/pull-requests/).

 ------
 
## Collection of Components
 We have built many other components and free resources for software development in various programming languages. Kindly click here to view our [Free Resources for Software Development.](https://www.weblineindia.com/software-development-resources.html)
 
------

## Changelog
Detailed changes for each release are documented in [CHANGELOG](./CHANGELOG).

## License
[MIT](LICENSE)

[mit]: https://github.com/weblineindia/Java-Push-Notification/blob/main/LICENSE

## Keywords
PushNotification, Notification, Firebase, FCM, SpringBoot, JAVA.


[def]: #SpringBoot-support
