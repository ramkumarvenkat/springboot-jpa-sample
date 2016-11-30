# springboot-jpa-sample
Small example of a simple REST API built with Springboot, JPA, Actuator and HSQLDB

This is a sample app to demonstrate how easy it is to use springboot to create REST APIs and persistence

The API has 2 endpoints:

# Upload Resume 
POST `http://localhost:8080/api/uploadResume`

Request Body:
```json
{ 
  "name":"Test",
  "currentJob": {
    "title": "Software Engineer",
    "description": "Engineer",
    "company": "Random"
  }
}
```

This returns the created resume resource, which has an incrementing unique id:
```json
{ 
  "id": 1,
  "name":"Test",
  "currentJob": {
    "title": "Software Engineer",
    "description": "Engineer",
    "company": "Random"
  }
}
```

# Get an uploaded resume
GET `http://localhost:8080/api/getResume/{id}`

`{id}` should be a valid id uploaded from the POST endpoint

Example, GET `http://localhost:8080/api/getResume/1` returns

```json
{ 
  "id": 1,
  "name":"Test",
  "currentJob": {
    "title": "Software Engineer",
    "description": "Engineer",
    "company": "Random"
  }
}
```

# Running the app
It is complicated. You need to `mvn spring-boot:run`. Please ensure you have Java 8 installed. This will start the server at the port 8080.

There is another way to do start the app, you can use `mvn package` and run the jar inside the `target` folder.
