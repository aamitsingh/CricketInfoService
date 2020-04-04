   
# Overview

Description: This service fetches (from 'cricapi.com' webapi) the total score of winning team in a cricket match using match unique id and round rotates the score.
             The service should be accessible via web browser on internet and end user should be able to view results by changing match's unique ID.
             Output of this service should be presented in web browser in JSON format.


### Requirements:

* Oracle Java Runtime 1.8 or higher
* Maven 3.5.0
* Spring 4
* Eclipse / IntelliJ

### Start-up the application

Build : ``./mvnw clean install``
Run: ``mvn spring-boot:run``


## Execution URLs:

    Generic URL: http://localhost:8080/cric-info/result?unique_id={unique_id}

    Testing URLs :

      - http://localhost:8080/cric-info/result?unique_id=1136617

