# Pangea Test Publisher Subscriber

**This is a SpringBoot Application:**

Git repo:  https://github.com/mugdhago/PangeaTest/tree/develop

- mvn clean install

- cd into the jar location

- java -jar test-0.0.1-SNAPSHOT.jar

This will start the Server.

There are few assumptions here:
1. Topic will be created first time subscribed  at.
2. Service will return an error when topic used for publishing is not available. We need to first subscribe to a topic.

The service has been tested using postman:

The postman collection to test these scripts is also being checked in with the code.
Postman Collection:
Pangea request Collection.postman_collection.json

 - There is no external database. I am using an in memory DB.
 - /event endpoint has been implemented to handle any published events to this url.
